package com.natesky9.patina.Block.Template;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class MachineTemplateEntity extends BlockEntity implements MenuProvider {
    protected ItemStackHandler itemStackHandler;
    protected ItemStackHandler automationHandler;


    protected abstract boolean mySlotValid(int slot, @NotNull ItemStack stack);

    protected LazyOptional<IItemHandler> itemCapability = LazyOptional.empty();
    protected LazyOptional<IItemHandler> automationCapability = LazyOptional.empty();

    protected final ContainerData data;

    protected int progress = 0;
    protected int progressMax = 20;
    protected int machineSlots;

    public MachineTemplateEntity(BlockPos pWorldPosition, BlockState pBlockState, int slots) {
        super(((MachineTemplateBlock)pBlockState.getBlock()).getBlockEntityType(),pWorldPosition, pBlockState);

        machineSlots = slots;
        //create the handler
        itemStackHandler = new ItemStackHandler(slots)
        {
            @Override
            protected void onContentsChanged(int slot)
            {
                setChanged();
                myContentsChanged();
            }

            @Override
            public int getSlotLimit(int slot) {
                return mySlotLimit(slot);
            }

            @Override
            public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
                return super.extractItem(slot, amount, simulate);
            }

            @Override
            public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
                return super.insertItem(slot, stack, simulate);
                //if (mySlotValid(slot, stack))
                //    return stack;
                //else return ItemStack.EMPTY;
            }

            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return true;
            }
        };
        automationHandler = new ItemStackHandler(slots)
        {
            @Override
            public int getSlotLimit(int slot) {
                return itemStackHandler.getSlotLimit(slot);
            }

            @Override
            public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
                if (!mySlotValid(slot, itemStackHandler.getStackInSlot(slot)))
                    return itemStackHandler.extractItem(slot, amount, simulate);
                else return ItemStack.EMPTY;
            }

            @Override
            public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
                return itemStackHandler.insertItem(slot, stack, simulate);
            }
        };
        this.data = createData();
    }
    //creates the data slots to read/write
    protected abstract ContainerData createData();

    protected abstract int mySlotLimit(int slot);
    protected abstract void myContentsChanged();

    @Override
    public abstract Component getDisplayName();

    @Nullable
    @Override
    public abstract AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER)
        {
            return side == null ? itemCapability.cast() : automationCapability.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        itemCapability = LazyOptional.of(() -> itemStackHandler);
        automationCapability = LazyOptional.of(() -> automationHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        itemCapability.invalidate();
        automationCapability.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        tag.put("inventory", itemStackHandler.serializeNBT(provider));
        super.saveAdditional(tag, provider);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        //load the inventory but also set the inventory size if we changed it since last update
        itemStackHandler.deserializeNBT(provider,tag.getCompound("inventory"));
        if (itemStackHandler.getSlots() != machineSlots)
        {
            System.out.println("Slots do not match! setting to correct now!");
            itemStackHandler.setSize(machineSlots);
        }
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
    public void resetProgress()
    {
        this.progress = 0;
    }

}
