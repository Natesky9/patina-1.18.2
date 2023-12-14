package com.natesky9.patina.Block.MachineTextiler;

import com.natesky9.patina.Block.Template.MachineTemplateEntity;
import com.natesky9.patina.Recipe.TextilerRecipe;
import com.natesky9.patina.init.ModRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class MachineTextilerEntity extends MachineTemplateEntity {
    public static final int slots = 2;
    public static final int dataSlots = 2;

    public static final int input = 0;
    public static final int output = 1;
    private Optional<RecipeHolder<TextilerRecipe>> recipe = Optional.empty();
    public MachineTextilerEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(pWorldPosition, pBlockState, slots);
    }

    @Override
    protected boolean mySlotValid(int slot, @NotNull ItemStack stack) {
        List<RecipeHolder<TextilerRecipe>> recipes = level.getRecipeManager()
                .getAllRecipesFor(ModRecipeTypes.TEXTILER_RECIPE_TYPE.get());
        return switch (slot)
                {
                    case input -> recipes.stream().anyMatch(holder -> holder.value().getIngredients().get(slot).test(stack));
                    case output -> true;
                    default -> false;
                };
    }

    @Override
    protected ContainerData createData() {
        return new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index)
                        {
                            case 0 -> MachineTextilerEntity.this.progress;
                            case 1 -> MachineTextilerEntity.this.progressMax;
                            default -> 0;
                        };
            }

            @Override
            public void set(int index, int value) {
                switch (index)
                {
                    case 0 -> MachineTextilerEntity.this.progress = value;
                    case 1 -> MachineTextilerEntity.this.progressMax = value;
                }
            }

            @Override
            public int getCount() {
                return dataSlots;
            }
        };
    }

    @Override
    protected int mySlotLimit(int slot) {
        return 64;
    }

    @Override
    protected void myContentsChanged() {
        SimpleContainer container = new SimpleContainer(itemStackHandler.getStackInSlot(input));
        recipe = level.getRecipeManager().getRecipeFor(ModRecipeTypes.TEXTILER_RECIPE_TYPE.get(),container,level);
        if (recipe.isEmpty())
            resetProgress();
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.patina.machine_textiler");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new MachineTextilerMenu(pContainerId,pInventory,this,this.data);
    }

    @Override
    protected void MachineTick() {
        if (hasRecipe() && hasRoom())
        {
            craftItem();
        }
    }
    private boolean hasRoom()
    {
        if (recipe.isEmpty()) return false;
        ItemStack slot = itemStackHandler.getStackInSlot(output);
        if (slot.isEmpty()) return true;
        ItemStack result = recipe.get().value().getResultItem(level.registryAccess());
        boolean stack = ItemHandlerHelper.canItemStacksStack(slot,result);
        boolean limit = slot.getCount() + result.getCount() <= slot.getMaxStackSize();
        return stack && limit;
    }
    private void craftItem()
    {
        ItemStack stack = recipe.get().value().getResultItem(level.registryAccess());
        itemStackHandler.extractItem(input,1,false);
        itemStackHandler.insertItem(output,stack,false);
    }

    private boolean hasRecipe()
    {
        return recipe.isPresent();
    }

    @Override
    public void saveAdditional(CompoundTag pTag) {
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
