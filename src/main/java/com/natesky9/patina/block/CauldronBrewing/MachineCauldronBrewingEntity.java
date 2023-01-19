package com.natesky9.patina.block.CauldronBrewing;

import com.natesky9.patina.block.GrindstoneBarrel.MachineGrindstoneBarrelEntity;
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
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
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

public class MachineCauldronBrewingEntity extends MachineTemplateEntity implements MenuProvider {
    public static final int slots = 3;
    private int progress;
    private int progressMax;


    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    public MachineCauldronBrewingEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(pWorldPosition, pBlockState, slots);
    }
    //
    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, MachineCauldronBrewingEntity pBlockEntity)
    {
        //System.out.println("machine tick");
        if (hasRecipe(pBlockEntity) && hasNotReachedStackLimit(pBlockEntity))
        {
            //System.out.println("craft");
            craftItem(pBlockEntity);
        }
    }

    private static boolean hasRecipe(MachineCauldronBrewingEntity blockEntity)
    {
        if (blockEntity.itemStackHandler.getStackInSlot(0).getItem() == Items.POTION)
        return true;
        return false;
    }
    private static void craftItem(MachineCauldronBrewingEntity blockEntity)
    {
        ItemStackHandler handler = blockEntity.itemStackHandler;
        handler.extractItem(0,1,false);
        handler.extractItem(1,1,false);
        handler.insertItem(2,new ItemStack(Items.SUGAR),false);
    }
    private static boolean hasNotReachedStackLimit(MachineCauldronBrewingEntity blockEntity)
    {
        return blockEntity.itemStackHandler.getStackInSlot(2).getCount() > 0;
    }

    @Override
    protected boolean mySlotValid(int slot, @NotNull ItemStack stack) {
        return false;
    }

    @Override
    protected ContainerData createData() {
        return new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> MachineCauldronBrewingEntity.this.progress;
                    case 1 -> MachineCauldronBrewingEntity.this.progressMax;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value)
            {
                switch (index) {
                    case 0 -> MachineCauldronBrewingEntity.this.progress = value;
                    case 1 -> MachineCauldronBrewingEntity.this.progressMax = value;
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

    //other methods
    @Override
    public Component getDisplayName() {return new TranslatableComponent("block.patina.machine_cauldron_brewing");}

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new MachineCauldronBrewingMenu(pContainerId,pInventory,this);
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
        pTag.put("inventory",itemStackHandler.serializeNBT());
        super.saveAdditional(pTag);
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
}
