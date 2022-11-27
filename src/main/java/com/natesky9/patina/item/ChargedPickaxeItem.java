package com.natesky9.patina.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChargedPickaxeItem extends PickaxeItem
{
    private final Multimap<Attribute,AttributeModifier> chargedModifiers;
    private final int chargeMax = 100;

    public ChargedPickaxeItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties)
    {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);

        float damage = pAttackDamageModifier + pTier.getAttackDamageBonus();
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", (double)damage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", (double)pAttackSpeedModifier, AttributeModifier.Operation.ADDITION));
        this.chargedModifiers = builder.build();
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        int getCharge = pStack.getOrCreateTag().getInt("charge");

        pTooltipComponents.add(new TextComponent("Charge: " + getCharge).withStyle(ChatFormatting.RED));
        if (getCharge < 10) pTooltipComponents.add(new TextComponent("Mine redstone ore to recharge!"));


    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        //fully charge when dropped, debug feature
        CompoundTag nbt = item.getOrCreateTag();
        nbt.putBoolean("charged",true);
        nbt.putInt("charge",chargeMax);

        return super.onDroppedByPlayer(item, player);
    }

    @Override
    public boolean isFoil(ItemStack itemStack) {
        if (!itemStack.hasTag()) return false;
        return itemStack.getTag().getBoolean("charged");
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        CompoundTag nbt = pStack.getOrCreateTag();
        int getcharge = nbt.getInt("charge");
        nbt.putInt("charge",Math.max(0,getcharge-1));

        //recharge with redstone ore
        if (pState.is(BlockTags.REDSTONE_ORES))
        {
            nbt.putBoolean("charged",true);
            nbt.putInt("charge", chargeMax);
        }
        //if the charge is 0, update it
        if (nbt.getInt("charge") == 0)
            nbt.putBoolean("charged",false);


        return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
    }

    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        if (pState.is(BlockTags.MINEABLE_WITH_PICKAXE))
        {
            boolean charged = pStack.getOrCreateTag().getBoolean("charged");

            if (charged) return this.speed*4;
            else return this.speed;
        }
        return 1.0F;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if (stack.getTag().getBoolean("charged"))
        {
            if (slot == EquipmentSlot.MAINHAND) return chargedModifiers;
            return ImmutableMultimap.of();
        }
        return super.getAttributeModifiers(slot, stack);
    }
}
