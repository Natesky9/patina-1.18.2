package com.natesky9.patina.block.GrindstoneBarrel;

import com.natesky9.patina.block.Template.MachineTemplateEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class MachineGrindstoneBarrelEntity extends MachineTemplateEntity implements MenuProvider {
    public static final int slots = 2;
    private final ItemStackHandler itemStackHandler = new ItemStackHandler(slots)
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0 -> true;
                case 1 -> true;
                default -> false;
            };
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    public MachineGrindstoneBarrelEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(pWorldPosition, pBlockState,slots);
    }

    @Override
    protected boolean mySlotValid(int slot, @NotNull ItemStack stack) {
        return false;
    }

    @Override
    public Component getDisplayName() {return new TranslatableComponent("block.patina.machine_grindstone_barrel");}

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new MachineGrindstoneBarrelMenu(pContainerId,pInventory,this);
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

    public void drops()
    {
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for (int i=0;i < itemStackHandler.getSlots();i++)
        {
            inventory.setItem(i,itemStackHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level,this.worldPosition,inventory);
    }

    @Override
    protected void MachineTick() {

    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, MachineGrindstoneBarrelEntity pBlockEntity)
    {
        System.out.println("machine tick");
        if (hasRecipe(pBlockEntity) && hasNotReachedStackLimit(pBlockEntity))
        {
            //System.out.println("craft");
            craftItem(pBlockEntity);
        }
    }

    private static boolean hasRecipe(MachineGrindstoneBarrelEntity blockEntity)
    {
        boolean hasFirstSlot = blockEntity.itemStackHandler.getStackInSlot(0).getCount() > 0;
        boolean hasSecondSlot = blockEntity.itemStackHandler.getStackInSlot(1).getCount() > 0;

        return hasFirstSlot && hasSecondSlot;
    }
    private static void craftItem(MachineGrindstoneBarrelEntity blockEntity)
    {
        ItemStack slot1 = blockEntity.itemStackHandler.extractItem(0,1,false);
        blockEntity.itemStackHandler.extractItem(1,1,false);
        blockEntity.itemStackHandler.insertItem(2,slot1,false);
    }
    private static boolean hasNotReachedStackLimit(MachineGrindstoneBarrelEntity blockEntity)
    {
        return blockEntity.itemStackHandler.getStackInSlot(2).getCount() < blockEntity.itemStackHandler.getStackInSlot(2).getMaxStackSize();
    }
}
