package com.natesky9.patina.block.BlastCauldron;

import com.natesky9.patina.block.Template.MachineTemplateEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class MachineBlastCauldronEntity extends MachineTemplateEntity implements MenuProvider {
    public static final int slots = 3;

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 200;

    public MachineBlastCauldronEntity(BlockPos pWorldPosition, BlockState pBlockState)
    {
        super(pWorldPosition, pBlockState,slots);
        this.data = createData();
    }

    @Override
    protected ContainerData createData() {
        return new ContainerData() {
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
    protected void myContentsChanged() {

    }

    @Override
    protected boolean mySlotValid(int slot, @NotNull ItemStack stack)
    {
        return switch (slot) {
            case 0 -> stack.getItem() == Items.FIRE_CHARGE;
            case 1 -> stack.getItem() == Items.RAW_COPPER
                    || stack.getItem() == Items.RAW_IRON
                    || stack.getItem() == Items.RAW_GOLD;
            case 2 -> true;
            default -> false;
        };
    }

    @Override
    public Component getDisplayName() {return new TranslatableComponent("block.patina.machine_blast_cauldron");}

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
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("progress",progress);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        progress = tag.getInt("progress");
    }



    @Override
    protected void MachineTick() {
        //System.out.println("machine tick");
        if (hasRecipe() && hasNotReachedStackLimit())
        {
            progress++;
            //System.out.println("craft");
            if (progress >= maxProgress)
            {
                craftItem();
                resetProgress();
            }
        }
        else
        {
            resetProgress();
        }
        //update it whether it crafted or not
        setChanged(getLevel(), getBlockPos(), getBlockState());

    }

    private void resetProgress()
    {
        this.progress = 0;
    }

    private boolean hasRecipe()
    {
        boolean hasFirstSlot = itemStackHandler.getStackInSlot(0).getItem() == Items.FIRE_CHARGE;
        Item secondSlot = itemStackHandler.getStackInSlot(1).getItem();
        boolean hasSecondSlot = secondSlot == Items.RAW_COPPER
                || secondSlot == Items.RAW_IRON
                || secondSlot == Items.RAW_GOLD;

        return hasSecondSlot && hasFirstSlot;
    }
    private void craftItem()
    {
        Item raw = itemStackHandler.getStackInSlot(1).getItem();
        Item output = itemStackHandler.getStackInSlot(2).getItem();
        Item smelt = null;
        if (raw == Items.RAW_COPPER) smelt = Items.COPPER_INGOT;
        if (raw == Items.RAW_IRON) smelt = Items.IRON_INGOT;
        if (raw == Items.RAW_GOLD) smelt = Items.GOLD_INGOT;
        //check if space is there
        if (output != smelt && !itemStackHandler.getStackInSlot(2).isEmpty())
            return;

        itemStackHandler.extractItem(0,1,false);
        itemStackHandler.extractItem(1,1,false);
        itemStackHandler.insertItem(2,new ItemStack(smelt,2),false);
    }
    private boolean hasNotReachedStackLimit()
    {
        return itemStackHandler.getStackInSlot(2).getCount() < itemStackHandler.getStackInSlot(2).getMaxStackSize();
    }
}
