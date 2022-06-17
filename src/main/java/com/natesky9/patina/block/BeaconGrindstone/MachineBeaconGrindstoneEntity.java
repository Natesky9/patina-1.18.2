package com.natesky9.patina.block.BeaconGrindstone;

import com.natesky9.patina.block.Template.MachineTemplateEntity;
import com.natesky9.patina.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class MachineBeaconGrindstoneEntity extends MachineTemplateEntity implements MenuProvider {
    public static int slots = 2;

    public MachineBeaconGrindstoneEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(pWorldPosition, pBlockState,slots);
    }

    @Override
    protected boolean mySlotValid(int slot, @NotNull ItemStack stack)
    {            return switch (slot) {
        case 0 -> stack.getItem() == ModItems.MAGIC_SALT.get();
        case 1 -> stack.getItem() == ModItems.VOID_SALT.get();
        default -> false;
    };
    }

    @Override
    public Component getDisplayName() {return new TranslatableComponent("block.patina.machine_beacon_grindstone");}

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new MachineBeaconGrindstoneMenu(pContainerId,pInventory,this);
    }
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side)
    {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap,side);
    }

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
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
    }

    @Override
    protected void MachineTick() {
        //System.out.println("machine tick");
        if (hasRecipe() && hasNotReachedStackLimit())
        {
            //System.out.println("craft");
            craftItem();
        }
    }

    private boolean hasRecipe()
    {
        boolean hasFirstSlot = itemStackHandler.getStackInSlot(0).getCount() > 0;

        return hasFirstSlot;
    }
    private void craftItem()
    {
        itemStackHandler.extractItem(0,1,false);
        itemStackHandler.insertItem(1, new ItemStack(ModItems.VOID_SALT.get()),false);
    }
    private boolean hasNotReachedStackLimit()
    {
        return itemStackHandler.getStackInSlot(1).getCount() < itemStackHandler.getStackInSlot(1).getMaxStackSize();
    }
}
