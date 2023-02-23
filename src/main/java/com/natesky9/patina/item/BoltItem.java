package com.natesky9.patina.item;

import com.natesky9.patina.init.ModEnchantments;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public class BoltItem extends ArrowItem {
    public BoltItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        AbstractArrow bolt = new Arrow(pLevel,pShooter)
        {
            @Override
            protected ItemStack getPickupItem() {
                ItemStack stack = pStack.copy();
                stack.setCount(1);
                return stack;
            }


            @Override
            protected void doPostHurtEffects(LivingEntity pLiving) {
                ItemStack stack = this.getPickupItem();
                Enchantment myenchant = ModEnchantments.QUARTZ.get();
                if (!stack.getOrCreateTag().contains("enchantment")) return;
                String enchantment = stack.getOrCreateTag().getString("enchantment");
                Enchantment enchant = ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(enchantment));
                if (enchant == null || this.getOwner() == null) return;
                enchant.doPostAttack((LivingEntity) this.getOwner(),pLiving,0);
            }

            @Override
            public void setEnchantmentEffectsFromEntity(LivingEntity p_36746_, float p_36747_) {
                //do stuff with enchants
                super.setEnchantmentEffectsFromEntity(p_36746_, p_36747_);
            }
        };
        bolt.setBaseDamage(1);
        bolt.setCritArrow(true);
        return bolt;
    }

    //should always have a base, duh
    public static int getBase(ItemStack stack)
    {
        int color = stack.getOrCreateTag().getInt("metal color");
        return color;
        //return PatinaArchery.materialColor(getItem);
    }
    public static int getTip(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt("gem color");
    }
    public static int getFeather(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt("feather color");
    }

    @Override
    public Component getName(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag == null) return super.getName(stack);
        //remove ingot from the name
        String base = tag.getString("metal");
        String gem = tag.getString("gem");

        Item baseItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(base));
        Item gemItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(gem));


        if (tag.contains("gem"))
            return new TranslatableComponent("component.patina.tipped_bolt_name",
                gemItem.getName(new ItemStack(gemItem)),
                baseItem.getName(new ItemStack(baseItem)).getString().replace(" Ingot",""));
        return new TranslatableComponent("component.patina.bolt_name",
                baseItem.getName(new ItemStack(baseItem)).getString().replace(" Ingot",""));
    }
}
