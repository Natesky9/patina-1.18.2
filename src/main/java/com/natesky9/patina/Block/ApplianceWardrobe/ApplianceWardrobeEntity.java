package com.natesky9.patina.Block.ApplianceWardrobe;

import com.natesky9.patina.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ApplianceWardrobeEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler handler;
    private LazyOptional<IItemHandler> itemHandler = LazyOptional.empty();
    public ApplianceWardrobeEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.APPLIANCE_WARDROBE_ENTITY.get(), pPos, pBlockState);
        this.handler = new ItemStackHandler(20)
        {
            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return (stack.getItem() instanceof ArmorItem);
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.patina.appliance_wardrobe");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new ApplianceWardrobeMenu(pContainerId,pPlayerInventory,this);
    }


    @Override
    public void onLoad() {
        super.onLoad();
        itemHandler = LazyOptional.of(() -> handler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        itemHandler.invalidate();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER)
        {
            return itemHandler.cast();
        }
        return super.getCapability(cap,side);
    }
}
