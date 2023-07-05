package com.natesky9.patina.Block.ApplianceWardrobe;

import com.natesky9.patina.init.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

public class ApplianceWardrobeMenu extends AbstractContainerMenu {
    public ApplianceWardrobeMenu(int pContainerId, Inventory inv, FriendlyByteBuf buf)
    {
        this(pContainerId, inv, inv.player.level.getBlockEntity(buf.readBlockPos()));
    }
    public ApplianceWardrobeMenu(int pContainerId, Inventory inv, BlockEntity entity)
    {
        super(ModMenuTypes.APPLIANCE_WARDROBE_MENU.get(), pContainerId);
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return false;
    }
}
