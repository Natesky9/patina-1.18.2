package com.natesky9.patina.Item;

import com.natesky9.patina.Patina;
import com.natesky9.patina.entity.FangGoal;
import com.natesky9.patina.init.ModItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.Optional;

public class OminousTrinketItem extends Item {
    public OminousTrinketItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack fresh = new ItemStack(this);
        fresh.set(DataComponents.OMINOUS_BOTTLE_AMPLIFIER,0);
        fresh.setDamageValue(0);
        return fresh;
    }


    @Override
    public boolean overrideOtherStackedOnMe(ItemStack pStack, ItemStack pOther, Slot pSlot, ClickAction pAction, Player pPlayer, SlotAccess pAccess) {
        if (!(pOther.is(Items.OMINOUS_BOTTLE))) return false;
        //only interact with ominous bottles
        if (pStack.getDamageValue() == pStack.getMaxDamage()) return false;
        //don't bother with full trinkets

        for (int i = 0;pOther.getCount() >=1;i++)
        {
            if (pStack.getDamageValue() == pStack.getMaxDamage()) return true;
            int value = pOther.getOrDefault(DataComponents.OMINOUS_BOTTLE_AMPLIFIER,0)+1;
            pOther.shrink(1);
            value = value*value;
            pStack.setDamageValue(Math.min(pStack.getMaxDamage(),pStack.getDamageValue()+value));
            pPlayer.playSound(SoundEvents.BOTTLE_FILL);
            if (pAction == ClickAction.SECONDARY) return true;
        }
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        return stack.getDamageValue() >= 50 ? ItemUtils.startUsingInstantly(pLevel,pPlayer,pUsedHand) : InteractionResultHolder.fail(stack);
    }

    @Override
    public int getUseDuration(ItemStack pStack, LivingEntity pEntity) {
        return 20;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        ResourceLocation scale = ResourceLocation.fromNamespaceAndPath(Patina.MODID,"boss_scale");
        AttributeModifier modifier = new AttributeModifier(scale,
        .1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);

        AABB aabb = new AABB(pLivingEntity.blockPosition()).inflate(64);
        List<Monster> monsters = pLevel.getEntitiesOfClass(Monster.class,aabb,(monster) -> monster.isAlive() && monster.getHealth()==monster.getMaxHealth());
        //find any monster that can path to us and doesn't have the attribute
        Optional<Monster> target = monsters.stream().filter(monster ->
        {
            Path path = monster.getNavigation().createPath(pLivingEntity,1);
            return path.canReach() && !monster.getAttributes().hasModifier(Attributes.SCALE,scale);
            //monster.getNavigation().moveTo(pLivingEntity,1);
        }).findAny();
        if (target.isPresent())
        {
            Monster monster = target.get();
            monster.setGlowingTag(true);
            monster.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,10000));
            monster.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,10000));
            //move to you
            monster.getNavigation().moveTo(pLivingEntity,.5);
            monster.goalSelector.addGoal(2,new FangGoal(monster));
            monster.setTarget(pLivingEntity);
            //scale the mob up
            monster.getAttribute(Attributes.SCALE).addPermanentModifier(modifier);
            List<Item> items = List.of(ModItems.BEAR_FRAGMENT_1.get(),ModItems.BEAR_FRAGMENT_2.get(),
                    ModItems.BEAR_FRAGMENT_3.get(),ModItems.BEAR_FRAGMENT_4.get());
            Item item = items.get((int) Math.floor(Math.random()*4));
            monster.setItemSlot(EquipmentSlot.OFFHAND,new ItemStack(item));
            monster.setGuaranteedDrop(EquipmentSlot.OFFHAND);

            //subtract 50 extract
            pStack.setDamageValue(Math.max(0,pStack.getDamageValue()-50));
        }
            return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        int count = pStack.getDamageValue();
        pTooltipComponents.add(Component.literal("Contains " + count + " extract"));
        if (count < 50)
            pTooltipComponents.add(Component.literal("The device thirsts for ominous extracts"));
        else
            pTooltipComponents.add(Component.literal("The device is eager for a hunt!"));
    }
}
