package com.natesky9.patina.block.Template;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class MachineTemplateEntity extends BlockEntity implements MenuProvider {
    protected final ItemStackHandler itemStackHandler;

    protected abstract boolean mySlotValid(int slot, @NotNull ItemStack stack);

    protected LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    public MachineTemplateEntity(BlockPos pWorldPosition, BlockState pBlockState, int slots) {
        super(((MachineTemplateBlock)pBlockState.getBlock()).getBlockEntityType(),pWorldPosition, pBlockState);
        //create the handler
        itemStackHandler = new ItemStackHandler(slots)
        {
            @Override
            protected void onContentsChanged(int slot)
            {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return mySlotValid(slot,stack);
            }
        };
    }

    @Override
    public abstract Component getDisplayName();

    @Nullable
    @Override
    public abstract AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer);


    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemStackHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory",itemStackHandler.serializeNBT());
        super.saveAdditional(pTag);
    }
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        itemStackHandler.deserializeNBT(tag.getCompound("inventory"));
    }

    public void drops()
    {
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for (int i=0;i < itemStackHandler.getSlots();i++)
        {
            inventory.setItem(i,itemStackHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level,this.worldPosition,inventory);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, MachineTemplateEntity pBlockEntity)
    {
        pBlockEntity.MachineTick();
    }
    protected abstract void MachineTick();

}
