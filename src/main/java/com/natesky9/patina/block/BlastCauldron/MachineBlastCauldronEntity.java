package com.natesky9.patina.block.BlastCauldron;

import com.natesky9.patina.init.ModBlockEntities;
import com.natesky9.patina.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class MachineBlastCauldronEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemStackHandler = new ItemStackHandler(3)
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0 -> stack.getItem() == Items.FIRE_CHARGE;
                case 1 -> stack.getItem() == Items.RAW_COPPER
                        || stack.getItem() == Items.RAW_IRON
                        || stack.getItem() == Items.RAW_GOLD;
                case 2 -> true;
                default -> false;
            };
        }
    };
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 200;

    public MachineBlastCauldronEntity(BlockPos pWorldPosition, BlockState pBlockState)
    {
        super(ModBlockEntities.MACHINE_BLAST_CAULDRON_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> MachineBlastCauldronEntity.this.progress;
                    case 1 -> MachineBlastCauldronEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value)
            {
                switch (index) {
                    case 0 -> MachineBlastCauldronEntity.this.progress = value;
                    case 1 -> MachineBlastCauldronEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Foundry");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new MachineBlastCauldronMenu(pContainerId,pInventory,this,this.data);
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
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inventory",itemStackHandler.serializeNBT());
        tag.putInt("progress",progress);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        itemStackHandler.deserializeNBT(tag.getCompound("inventory"));
        progress = tag.getInt("progress");
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

    public static void tick(Level level, BlockPos pos, BlockState blockState, MachineBlastCauldronEntity entity)
    {
        //System.out.println("machine tick");
        if (hasRecipe(entity) && hasNotReachedStackLimit(entity))
        {
            entity.progress++;
            //System.out.println("craft");
            if (entity.progress >= entity.maxProgress)
            {
                craftItem(entity);
                entity.resetProgress();
            }
        }
        else
        {
            entity.resetProgress();
        }
        //update it whether it crafted or not
        setChanged(level, pos, blockState);
    }
    private void resetProgress()
    {
        this.progress = 0;
    }

    private static boolean hasRecipe(MachineBlastCauldronEntity blockEntity)
    {
        boolean hasFirstSlot = blockEntity.itemStackHandler.getStackInSlot(0).getItem() == Items.FIRE_CHARGE;
        Item secondSlot =blockEntity.itemStackHandler.getStackInSlot(1).getItem();
        boolean hasSecondSlot = secondSlot == Items.RAW_COPPER
                || secondSlot == Items.RAW_IRON
                || secondSlot == Items.RAW_GOLD;

        return hasSecondSlot && hasFirstSlot;
    }
    private static void craftItem(MachineBlastCauldronEntity blockEntity)
    {
        Item raw = blockEntity.itemStackHandler.getStackInSlot(1).getItem();
        Item output = blockEntity.itemStackHandler.getStackInSlot(2).getItem();
        Item smelt = null;
        if (raw == Items.RAW_COPPER) smelt = Items.COPPER_INGOT;
        if (raw == Items.RAW_IRON) smelt = Items.IRON_INGOT;
        if (raw == Items.RAW_GOLD) smelt = Items.GOLD_INGOT;
        //check if space is there
        if (output != smelt && !blockEntity.itemStackHandler.getStackInSlot(2).isEmpty())
            return;

        blockEntity.itemStackHandler.extractItem(0,1,false);
        blockEntity.itemStackHandler.extractItem(1,1,false);
        blockEntity.itemStackHandler.insertItem(2,new ItemStack(smelt,2),false);
    }
    private static boolean hasNotReachedStackLimit(MachineBlastCauldronEntity blockEntity)
    {
        return blockEntity.itemStackHandler.getStackInSlot(2).getCount() < blockEntity.itemStackHandler.getStackInSlot(2).getMaxStackSize();
    }
}
