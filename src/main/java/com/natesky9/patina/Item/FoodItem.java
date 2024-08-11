package com.natesky9.patina.Item;

import net.minecraft.world.item.Item;

public class FoodItem extends Item {
    public FoodItem(Properties pProperties) {
        super(pProperties);
    }

    //TODO: figure out where this goes
    //@Override
    //public @Nullable FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity) {
    //    FoodProperties food = stack.get(DataComponents.FOOD);
    //    if (food == null) return Foods.DRIED_KELP;
    //    PotionContents potion = stack.get(DataComponents.POTION_CONTENTS);
    //    FoodProperties.Builder custom = new FoodProperties.Builder().nutrition(food.nutrition())
    //            .saturationModifier(food.saturation());
    //   for (MobEffectInstance instance: potion.getAllEffects())
    //   {
    //       custom.effect(() -> instance,1f/potion.getAllEffects().size());
    //   }
    //   return custom.build();
    //}
}
