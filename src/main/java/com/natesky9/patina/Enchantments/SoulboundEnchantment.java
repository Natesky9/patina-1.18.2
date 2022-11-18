package com.natesky9.patina.Enchantments;

import com.natesky9.patina.init.ModEnchantments;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.util.*;

public class SoulboundEnchantment extends Enchantment {
    public SoulboundEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }
    //
    protected static Map<UUID,List<ItemStack>> soulboundItems = new HashMap<>();
    public static void store(LivingDropsEvent event)
    {
        if (event.getEntity() instanceof Player player)
        {
            Collection<ItemEntity> drops =  event.getDrops();
            //why do we need 2 lists? because shut up, that's why
            List<ItemEntity> items = new ArrayList<>();
            List<ItemStack> itemstacks = new ArrayList<>();
            for (ItemEntity item:drops)
            {
                boolean soulbound = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.SOULBOUND.get(),item.getItem()) > 0;
                if (soulbound)
                {
                    items.add(item);
                    itemstacks.add(item.getItem());
                }
            }
            //prevent the items from dropping?
            drops.removeAll(items);
            soulboundItems.put(player.getUUID(),itemstacks);
        }
    }
    public static void restore(PlayerEvent.Clone event)
    {
        if (!event.isWasDeath()) return;
        if (event.getPlayer() instanceof ServerPlayer player)
        {
            List<ItemStack> items = soulboundItems.get(player.getUUID());
            for (ItemStack stack:items)
            {
                player.addItem(stack);
                player.displayClientMessage(new TextComponent("Restored Item!"),true);
            }
            soulboundItems.remove(player);
        }
    }
    @Override
    public Component getFullname(int pLevel) {
        return new TranslatableComponent(this.getDescriptionId()).withStyle(ChatFormatting.DARK_PURPLE);
    }
}
