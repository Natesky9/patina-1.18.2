package com.natesky9.patina.Item;

import com.mojang.realmsclient.util.LevelType;
import com.natesky9.patina.Enchantment.Util.ModEnchantmentUtil;
import com.natesky9.patina.Patina;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.data.worldgen.DimensionTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CharmFragmentItem extends SmithingTemplateItem {
    private static final String DESCRIPTION_ID = Util.makeDescriptionId("item", new ResourceLocation("charm_fragment"));
    //
    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
    private static final ResourceLocation fragment1 = new ResourceLocation(Patina.MODID,"bee_fragment_1");
    private static final ResourceLocation fragment2 = new ResourceLocation(Patina.MODID,"bee_fragment_2");
    private static final ResourceLocation fragment3 = new ResourceLocation(Patina.MODID,"bee_fragment_3");
    private static final ResourceLocation fragment4 = new ResourceLocation(Patina.MODID,"bee_fragment_4");

    public CharmFragmentItem(Component p_266834_, Component p_267043_, Component p_267048_, Component p_267278_, Component p_267090_, List<ResourceLocation> p_266755_, List<ResourceLocation> p_267060_) {
        super(p_266834_, p_267043_, p_267048_, p_267278_, p_267090_, p_266755_, p_267060_);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag p_266857_) {
        components.add(Component.translatable("component.charm_fragment"));
        components.add(Component.translatable("component.charm_fragment_hint"));
        if (level == null) return; //super.appendHoverText(stack,level,components,p_266857_);
        int moonPhase = level.getMoonPhase();
        ResourceKey<Level> type = level.dimension();
        if (type == Level.OVERWORLD) {
            switch (moonPhase) {
                case 0 -> components.add(Component.translatable("component.fragment_moon_phase_1"));
                case 1 -> components.add(Component.translatable("component.fragment_moon_phase_2"));
                case 2 -> components.add(Component.translatable("component.fragment_moon_phase_3"));
                case 3 -> components.add(Component.translatable("component.fragment_moon_phase_4"));
                case 4 -> components.add(Component.translatable("component.fragment_moon_phase_5"));
                case 5 -> components.add(Component.translatable("component.fragment_moon_phase_6"));
                case 6 -> components.add(Component.translatable("component.fragment_moon_phase_7"));
                case 7 -> components.add(Component.translatable("component.fragment_moon_phase_8"));
            }
        }
        if (type == Level.NETHER)
        {
            switch (moonPhase)
            {
                case 0 -> components.add(Component.translatable("component.fragment_sun_phase_1"));
                case 1 -> components.add(Component.translatable("component.fragment_sun_phase_2"));
                case 2 -> components.add(Component.translatable("component.fragment_sun_phase_3"));
                case 3 -> components.add(Component.translatable("component.fragment_sun_phase_4"));
                case 4 -> components.add(Component.translatable("component.fragment_sun_phase_5"));
                case 5 -> components.add(Component.translatable("component.fragment_sun_phase_6"));
                case 6 -> components.add(Component.translatable("component.fragment_sun_phase_7"));
                case 7 -> components.add(Component.translatable("component.fragment_sun_phase_8"));
            }
        }
    }

    @Override
    public String getDescriptionId() {
        return DESCRIPTION_ID;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 100;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BRUSH;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack other = pPlayer.getItemInHand(pUsedHand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND:InteractionHand.MAIN_HAND);
        Map<Enchantment,Integer> enchantments = EnchantmentHelper.getEnchantments(other);
        Optional<Enchantment> curse = enchantments.keySet().stream().filter(Enchantment::isCurse).findFirst();
        if (curse.isPresent())
        {
            pPlayer.startUsingItem(pUsedHand);
            return  InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (!(pLivingEntity instanceof Player player)) return pStack;
        ItemStack other = player.getItemInHand(pLivingEntity.getUsedItemHand() == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND:InteractionHand.MAIN_HAND);
        Map<Enchantment,Integer> enchantments = EnchantmentHelper.getEnchantments(other);
        Optional<Enchantment> curse = enchantments.keySet().stream().filter(Enchantment::isCurse).findFirst();
        if (curse.isPresent())
        {
            enchantments.remove(curse.get());
            other.enchant(ModEnchantmentUtil.getOpposite(curse.get()),0);
            pLevel.playSound(null,pLivingEntity.blockPosition(), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS);
            //pStack.shrink(1);
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }

    public static CharmFragmentItem makeFragment()
    {
        Component component1 = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(Patina.MODID,"fragment_half_a"))).withStyle(DESCRIPTION_FORMAT);
        Component component2 = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(Patina.MODID,"fragment_half_b"))).withStyle(DESCRIPTION_FORMAT);
        Component component3 = Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation(Patina.MODID,"fragment_combine"))).withStyle(TITLE_FORMAT);
        Component component4 = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(Patina.MODID,"fragment_combine"))).withStyle(TITLE_FORMAT);
        Component component5 = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(Patina.MODID,"fragment_combine"))).withStyle(TITLE_FORMAT);

        return new CharmFragmentItem(component1,component2,component3,component4,component5,createList(),createList());
    }
    private static List<ResourceLocation> createList() {
        return List.of(fragment1,fragment2,fragment3,fragment4);
    }
}
