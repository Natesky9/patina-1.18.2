package com.natesky9.patina.item.PowderPouch;

import com.natesky9.patina.Patina;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PowderPouchItem extends Item {
    Component name = new TranslatableComponent("container.dust_bag");

    public PowderPouchItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        Component title = itemstack.hasCustomHoverName() ? itemstack.getHoverName() : name;
        if (pPlayer instanceof ServerPlayer player) {
            NetworkHooks.openGui(player, new SimpleMenuProvider((pContainerId, pInventory, pPlayer1) ->
            new PowderPouchMenu(pContainerId, player.getInventory(), player, new SimpleContainer(1),itemstack),
                    title));
            pLevel.playSound(null,player, SoundEvents.ARMOR_EQUIP_LEATHER, SoundSource.PLAYERS,1,1);
        }

        return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        CompoundTag tag = pStack.getOrCreateTag();
        if (tag.contains("id"))
        {
            Item item = Registry.ITEM.get(new ResourceLocation(tag.getString("id")));
            int count = tag.getInt("count");
            pTooltipComponents.add(new TextComponent("stored: " + count + " " + item));
        }
    }
}
