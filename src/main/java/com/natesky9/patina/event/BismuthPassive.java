package com.natesky9.patina.event;

import com.natesky9.patina.init.ModArmorMaterials;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class BismuthPassive {
    public static void doBismuthEffect(LivingHurtEvent event)
    {
        if (!event.getSource().is(DamageTypeTags.IS_PROJECTILE))
            return;
        System.out.println("start bismuth reduction");
        int count = 0;
        LivingEntity entity = event.getEntity();
        entity.getArmorSlots();
        for (ItemStack stack:entity.getArmorSlots())
        {
            if (!(stack.getItem() instanceof ArmorItem armor)) continue;
            ArmorMaterial material = armor.getMaterial().get();
            if (!(material == ModArmorMaterials.BRONZE.get())) continue;
            count++;
        }
        float reduction = count * .1F;
        System.out.println("wearing " + count + " pieces of bismuth");
        event.setAmount(event.getAmount() - reduction);

    }
}
