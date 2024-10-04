package com.natesky9.patina.Block.MachineMincerator;

import com.natesky9.patina.Block.Template.MachineTemplateEntity;
import com.natesky9.patina.Item.PotionSaltItem;
import com.natesky9.patina.Recipe.MinceratorRecipe;
import com.natesky9.patina.Recipe.MinceratorRecipeInput;
import com.natesky9.patina.init.ModItems;
import com.natesky9.patina.init.ModRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class MachineMinceratorEntity extends MachineTemplateEntity implements MenuProvider {
    public static final int slots = 6;
    public int progressMax = 100;
    final int inputOne = 0;
    final int inputTwo = 1;
    final int inputThree = 2;
    final int inputFour = 3;
    final int output = 4;
    final int salt = 5;


    private Optional<RecipeHolder<MinceratorRecipe>> recipe = Optional.empty();

    public MachineMinceratorEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(pWorldPosition, pBlockState,slots);
    }

    @Override
    protected boolean mySlotValid(int slot, @NotNull ItemStack stack) {
        return switch (slot) {
            case inputOne, inputTwo, inputThree, inputFour -> !stack.is(ModItems.POTION_SALT.get());
            case salt -> stack.is(ModItems.POTION_SALT.get());
            default -> false;
        };
    }

    @Override
    protected ContainerData createData() {
        return new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> MachineMinceratorEntity.this.progress;
                    case 1 -> MachineMinceratorEntity.this.progressMax;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value)
            {
                switch (index) {
                    case 0 -> MachineMinceratorEntity.this.progress = value;
                    case 1 -> MachineMinceratorEntity.this.progressMax = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    private Optional<RecipeHolder<MinceratorRecipe>> getRecipe()
    {
        RecipeInput input = new MinceratorRecipeInput(
                this.itemStackHandler.getStackInSlot(0),
                this.itemStackHandler.getStackInSlot(1),
                this.itemStackHandler.getStackInSlot(2),
                this.itemStackHandler.getStackInSlot(3));
        //
            RecipeManager manager = level.getRecipeManager();
        Optional<RecipeHolder<MinceratorRecipe>> winner = Optional.empty();
            List<RecipeHolder<MinceratorRecipe>> recipes = manager.getAllRecipesFor(ModRecipeTypes.MINCERATOR_RECIPE_TYPE.get());
            for (RecipeHolder<MinceratorRecipe> recipe: recipes)
            {
                boolean fits = recipe.value().matches(input, level);
                if (fits) winner = Optional.of(recipe);
            }
            return winner;
    }
    @Override
    protected void myContentsChanged()
    {
        recipe = getRecipe();
        //Optional<RecipeHolder<MinceratorRecipe>> tempRecipe = recipe;
        //recipe = getRecipe();
        //if (!hasRecipe())
        //{
        //    recipe = Optional.empty();
        //    resetProgress();
        //    return;
        //}
        //if (tempRecipe.isPresent() && recipe.isPresent())
        //{
        //    if (tempRecipe.get() != recipe.get())
        //        resetProgress();
        //}
    }

    @Override
    protected int mySlotLimit(int slot) {
        return 64;
    }

    private boolean hasRecipe()
    {
        return recipe.isPresent() && hasNotReachedStackLimit();
    }

    @Override
    public Component getDisplayName() {return Component.translatable("block.patina.machine_mincerator");}

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new MachineMinceratorMenu(pContainerId,pInventory,this,this.data);
    }

    @Override
    protected void MachineTick()
    {
        if (!itemStackHandler.getStackInSlot(5).isEmpty())
        {
            ItemStack food = itemStackHandler.getStackInSlot(4);
            ItemStack augment = itemStackHandler.getStackInSlot(5);
            if (augment.getItem() instanceof PotionSaltItem)
            {
                if (food.get(DataComponents.POTION_CONTENTS) == augment.get(DataComponents.POTION_CONTENTS)) return;
                food.set(DataComponents.POTION_CONTENTS,augment.get(DataComponents.POTION_CONTENTS));
                itemStackHandler.extractItem(5,1,false);
            }
        }
        if (hasRecipe() && outputEmpty()) {
            //System.out.println("craft");
            craftItem();
        }


    }
    boolean outputEmpty()
    {
        return itemStackHandler.getStackInSlot(5).isEmpty();
    }

    //private boolean noDuplicates()
    //{
    //    ItemStackHandler handler = itemStackHandler;
    //    Item slot1 = handler.getStackInSlot(0).getItem();
    //    Item slot2 = handler.getStackInSlot(1).getItem();
    //    Item slot3 = handler.getStackInSlot(2).getItem();
    //    Item slot4 = handler.getStackInSlot(3).getItem();
    //    //all the slots are different
    //    return slot1 != slot2
    //            && slot1 != slot3
    //            && slot1 != slot4
    //            && slot2 != slot3
    //            && slot2 != slot4
    //            && slot3 != slot4;
    //}
    //private boolean twoFoods()
    //{
    //    int foodCount = 0;
    //    if (itemStackHandler.getStackInSlot(0).isEdible()) foodCount++;
    //    if (itemStackHandler.getStackInSlot(1).isEdible()) foodCount++;
    //    if (itemStackHandler.getStackInSlot(2).isEdible()) foodCount++;
    //    if (itemStackHandler.getStackInSlot(3).isEdible()) foodCount++;
    //    return foodCount >= 2;
    //}

    //private boolean inputsPopulated()
    //{
    //    boolean hasFirstSlot = !itemStackHandler.getStackInSlot(0).isEmpty();
    //    boolean hasSecondSlot = !itemStackHandler.getStackInSlot(1).isEmpty();
    //    boolean hasThirdSlot = !itemStackHandler.getStackInSlot(2).isEmpty();
    //    boolean hasFourthSlot = !itemStackHandler.getStackInSlot(3).isEmpty();
    //    boolean outputEmpty = itemStackHandler.getStackInSlot(4).isEmpty();
    //    return hasFirstSlot && hasSecondSlot && hasThirdSlot && hasFourthSlot && outputEmpty;
    //}
    private void craftItem()
    {
        Optional<RecipeHolder<MinceratorRecipe>> recipe = getRecipe();
    if (recipe.isPresent())
        {
            ItemStack stack = recipe.get().value().getResultItem(level.registryAccess());
        //int hunger = 0;
        //float saturation = 0;

        //String food1 = itemStackHandler.getStackInSlot(0).getDisplayName().getString();
        //String food2 = itemStackHandler.getStackInSlot(1).getDisplayName().getString();
        //String food3 = itemStackHandler.getStackInSlot(2).getDisplayName().getString();
        //String food4 = itemStackHandler.getStackInSlot(3).getDisplayName().getString();


        //for (int i=0;i<4;i++)
        //{
        //    ItemStack get = itemStackHandler.extractItem(i,1,false);

        //    if (get.getFoodProperties(null) == null)
        //    {
        //        //handle the non-foods here
        //        if (get.getItem() == Items.SUGAR) saturation += .5f;
        //        if (get.getItem() == Items.COCOA_BEANS) saturation += 1f;
        //        if (get.getItem() == Items.WHEAT_SEEDS) hunger += 1;
        //        if (get.getItem() == Items.PUMPKIN_SEEDS) hunger += 2;
        //        if (get.getItem() == Items.SNOWBALL) saturation += 1f;
        //        if (get.getItem() == Items.BEETROOT_SEEDS) hunger += 1;
        //        if (get.getItem() == Items.EGG) hunger +=2;
        //        if (get.getItem() == Items.DRAGON_BREATH) saturation += 2f;
        //        if (get.getItem() == Items.GLOW_LICHEN) saturation += 1f;
        //        continue;
        //    }
        //    hunger += get.getFoodProperties(null).getNutrition();
        //    saturation += get.getFoodProperties(null).getSaturationModifier();
        //}
        //ItemStack stack = new ItemStack(Items.SUSPICIOUS_STEW);
        //SuspiciousStewItem.saveMobEffect(stack, MobEffects.ABSORPTION,200);
        //CustomFood.setFoodProperties(food,hunger,saturation);
        //CustomFood.setIngredients(food,food1,food2,food3,food4);
        //
            //ItemStack stack = new ItemStack(recipe.get().getResultItem().getItem());

            itemStackHandler.insertItem(4,stack,false);
            for (int i=0;i < 4;i++)
            {
                itemStackHandler.extractItem(i,1,false);
            }
        }


    }
    private boolean hasNotReachedStackLimit()
    {
        ItemStack output = itemStackHandler.getStackInSlot(4);
        return output.getCount() < 1;//output.getMaxStackSize();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER)
        {
            if (side == Direction.DOWN)
                return automationCapability.cast();
            return itemCapability.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
    }

}
