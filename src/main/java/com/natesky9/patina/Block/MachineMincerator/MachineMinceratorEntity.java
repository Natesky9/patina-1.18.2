package com.natesky9.patina.Block.MachineMincerator;

import com.natesky9.patina.Block.Template.MachineTemplateEntity;
import com.natesky9.patina.Item.PotionSaltItem;
import com.natesky9.patina.Recipe.MinceratorRecipe;
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
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.RecipeHolder;
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


    private Optional<RecipeHolder<MinceratorRecipe>> recipe = Optional.empty();

    public MachineMinceratorEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(pWorldPosition, pBlockState,slots);
    }

    @Override
    protected boolean mySlotValid(int slot, @NotNull ItemStack stack) {
        return switch (slot) {
            case 0, 1, 2, 3, 4, 5, 6 -> true;
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

    private Optional<MinceratorRecipe> getRecipe()
    {
        SimpleContainer inventory = new SimpleContainer(this.itemStackHandler.getSlots());
        for (int i = 0; i < this.itemStackHandler.getSlots(); i++)
        {
            inventory.setItem(i,this.itemStackHandler.getStackInSlot(i));
        }
        //
            RecipeManager manager = level.getRecipeManager();
            Optional<MinceratorRecipe> winner = Optional.empty();
            List<RecipeHolder<MinceratorRecipe>> recipes = manager.getAllRecipesFor(ModRecipeTypes.MINCERATOR_RECIPE_TYPE.get());
            for (RecipeHolder<MinceratorRecipe> recipe: recipes)
            {
                boolean fits = recipe.value().matches(inventory, level);
                if (fits) winner = Optional.of(recipe.value());
            }
            return winner;

        //Optional<MinceratorRecipe> match = level.getRecipeManager()
        //        .getRecipeFor(ModRecipeTypes.MINCERATOR_RECIPE_TYPE.get(), inventory, level);
        //return match;
    }
    @Override
    protected void myContentsChanged()
    {
        SimpleContainer container = new SimpleContainer(
                itemStackHandler.getStackInSlot(0),itemStackHandler.getStackInSlot(1),
                itemStackHandler.getStackInSlot(2),itemStackHandler.getStackInSlot(3));
        Optional<RecipeHolder<MinceratorRecipe>> tempRecipe = recipe;
        recipe = level.getRecipeManager().getRecipeFor(ModRecipeTypes.MINCERATOR_RECIPE_TYPE.get(), container, level);
        if (!hasRecipe())
        {
            recipe = Optional.empty();
            resetProgress();
            return;
        }
        if (tempRecipe.isPresent() && recipe.isPresent())
        {
            if (tempRecipe.get() != recipe.get())
                resetProgress();
        }
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
                if (PotionUtils.getPotion(food) == PotionUtils.getPotion(augment)) return;
                PotionUtils.setPotion(food,PotionUtils.getPotion(augment));
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

    Optional<MinceratorRecipe> recipe = getRecipe();
    if (recipe.isPresent())
        {
            ItemStack stack = recipe.get().getResultItem(level.registryAccess());
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
            return itemCapability.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
    }
}
