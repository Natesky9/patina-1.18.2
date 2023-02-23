package com.natesky9.patina.item.BoltPouch;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BoltPouchItem extends Item {
    public BoltPouchItem(Properties pProperties) {
        super(pProperties);
    }

    Component name = new TranslatableComponent("container.dust_bag");

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        Component title = itemstack.hasCustomHoverName() ? itemstack.getHoverName() : name;
        if (pPlayer instanceof ServerPlayer player) {
            NetworkHooks.openGui(player, new SimpleMenuProvider((pContainerId, pInventory, pPlayer1) ->
                    new BoltPouchMenu(pContainerId, player.getInventory(), player, new SimpleContainer(1){
                        @Override
                        public int getMaxStackSize() {return 1024;}
                    },itemstack),
                    title));
            pLevel.playSound(null,player, SoundEvents.ARMOR_EQUIP_LEATHER, SoundSource.PLAYERS,1,1);
        }
        return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
