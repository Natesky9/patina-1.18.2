package com.natesky9.patina.Block.MachineFoundry;

import com.natesky9.patina.Block.Template.MachineTemplateEntity;
import com.natesky9.patina.Recipe.FoundryRecipe;
import com.natesky9.patina.init.ModRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class MachineFoundryEntity extends MachineTemplateEntity implements MenuProvider {
    protected final ContainerData data;
    public static final int dataSlots = 2;
    public static final int slots = 4;

    public static final int input = 0;
    public static final int catalyst = 1;
    public static final int fuel = 2;
    public static final int output = 3;
    private Optional<FoundryRecipe> recipe = Optional.empty();
    public MachineFoundryEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(pWorldPosition, pBlockState,slots);
        this.data = createData();
        this.progressMax = 20;
        //this.itemStackHandler = new ItemStackHandler(slots);
    }

    @Override
    protected boolean mySlotValid(int slot, @NotNull ItemStack stack) {
        return true;
    }

    @Override
    protected ContainerData createData() {
        return new ContainerData() {
            @Override
            public int get(int index)
            {
                return switch (index)
                {
                    case 0 -> MachineFoundryEntity.this.progress;
                    case 1 -> MachineFoundryEntity.this.progressMax;
                    default -> 0;
                };
            }
            @Override
            public void set(int index, int value)
            {
                switch (index)
                {
                    case 0 -> MachineFoundryEntity.this.progress = value;
                    case 1 -> MachineFoundryEntity.this.progressMax = value;
                }
            }
            @Override
            public int getCount() {
                return dataSlots;
            }
        };
    }

    @Override
    protected void myContentsChanged()
    {
        SimpleContainer container = new SimpleContainer(itemStackHandler.getStackInSlot(input),
                itemStackHandler.getStackInSlot(catalyst));
        recipe = level.getRecipeManager().getRecipeFor(ModRecipeTypes.FOUNDRY_RECIPE_TYPE.get(),container,level);
        if (recipe.isEmpty())
            resetProgress();
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.patina.machine_foundry");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new MachineFoundryMenu(pContainerId,pInventory,this,this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {

        if (cap == ForgeCapabilities.ITEM_HANDLER)
        {
            return itemCapability.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    protected void MachineTick() {
        if (hasRecipe() && hasRoom())
        {
            progress++;
            if (progress >= progressMax)
            {
                craftItem();
                resetProgress();
            }
        }
    }
    private boolean hasRoom()
    {
        if (recipe.isEmpty()) return false;
        ItemStack slot = itemStackHandler.getStackInSlot(output);
        if (slot.isEmpty()) return true;
        ItemStack result = recipe.get().getResultItem(level.registryAccess());
        boolean stack = ItemHandlerHelper.canItemStacksStack(slot,result);
        boolean limit = slot.getCount() + result.getCount() <= slot.getMaxStackSize();
        return stack && limit;
    }
    private boolean hasRecipe()
    {
        return recipe.isPresent();
    }
    private void craftItem()
    {
        ItemStack stack = recipe.get().getResultItem(level.registryAccess());
        itemStackHandler.extractItem(input,1,false);
        itemStackHandler.extractItem(catalyst,1,false);
        itemStackHandler.insertItem(output,stack,false);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("progress",progress);
        pTag.putInt("progressMax",progressMax);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        progress = tag.getInt("progress");
        progressMax = tag.getInt("progressMax");
    }
}
