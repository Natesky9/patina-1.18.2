package com.natesky9.patina.block.SmokerGrindstone;

import com.natesky9.patina.block.Template.MachineTemplateEntity;
import com.natesky9.patina.init.ModItems;
import com.natesky9.patina.item.CustomFood;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class MachineSmokerGrindstoneEntity extends MachineTemplateEntity implements MenuProvider {
    public static final int slots = 5;


    public MachineSmokerGrindstoneEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(pWorldPosition, pBlockState,slots);
    }

    @Override
    protected boolean mySlotValid(int slot, @NotNull ItemStack stack) {
        return switch (slot) {
            case 0, 1, 2, 3, 4 -> true;
            default -> false;
        };
    }

    @Override
    public Component getDisplayName() {return new TranslatableComponent("block.patina.machine_smoker_grindstone");}

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new MachineSmokerGrindstoneMenu(pContainerId,pInventory,this);
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
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
    }

    @Override
    protected void MachineTick()
    {
        //System.out.println("machine tick");
        if (inputsPopulated()
                && twoFoods()
                && noDuplicates()
                && hasNotReachedStackLimit())
        {
            //System.out.println("craft");
            craftItem();
        }

    }

    private boolean noDuplicates()
    {
        ItemStackHandler handler = itemStackHandler;

        Item slot1 = handler.getStackInSlot(0).getItem();
        Item slot2 = handler.getStackInSlot(1).getItem();
        Item slot3 = handler.getStackInSlot(2).getItem();
        Item slot4 = handler.getStackInSlot(3).getItem();
        //all the slots are different
        return slot1 != slot2
                && slot1 != slot3
                && slot1 != slot4
                && slot2 != slot3
                && slot2 != slot4
                && slot3 != slot4;

    }
    private boolean twoFoods()
    {
        int foodCount = 0;
        if (itemStackHandler.getStackInSlot(0).isEdible()) foodCount++;
        if (itemStackHandler.getStackInSlot(1).isEdible()) foodCount++;
        if (itemStackHandler.getStackInSlot(2).isEdible()) foodCount++;
        if (itemStackHandler.getStackInSlot(3).isEdible()) foodCount++;
        return foodCount >= 2;
    }

    private boolean inputsPopulated()
    {
        boolean hasFirstSlot = !itemStackHandler.getStackInSlot(0).isEmpty();
        boolean hasSecondSlot = !itemStackHandler.getStackInSlot(1).isEmpty();
        boolean hasThirdSlot = !itemStackHandler.getStackInSlot(2).isEmpty();
        boolean hasFourthSlot = !itemStackHandler.getStackInSlot(3).isEmpty();

        boolean outputEmpty = itemStackHandler.getStackInSlot(4).isEmpty();

        return hasFirstSlot && hasSecondSlot && hasThirdSlot && hasFourthSlot && outputEmpty;
    }
    private void craftItem()
    {
        int hunger = 0;
        float saturation = 0;

        String food1 = itemStackHandler.getStackInSlot(0).getDisplayName().getString();
        String food2 = itemStackHandler.getStackInSlot(1).getDisplayName().getString();
        String food3 = itemStackHandler.getStackInSlot(2).getDisplayName().getString();
        String food4 = itemStackHandler.getStackInSlot(3).getDisplayName().getString();


        for (int i=0;i<4;i++)
        {
            ItemStack get = itemStackHandler.extractItem(i,1,false);

            if (get.getFoodProperties(null) == null)
            {
                //handle the non-foods here
                if (get.getItem() == Items.SUGAR) saturation += .5f;
                if (get.getItem() == Items.COCOA_BEANS) saturation += 1f;
                if (get.getItem() == Items.WHEAT_SEEDS) hunger += 1;
                if (get.getItem() == Items.PUMPKIN_SEEDS) hunger += 2;
                if (get.getItem() == Items.SNOWBALL) saturation += 1f;
                if (get.getItem() == Items.BEETROOT_SEEDS) hunger += 1;
                if (get.getItem() == Items.EGG) hunger +=2;
                if (get.getItem() == Items.DRAGON_BREATH) saturation += 2f;
                if (get.getItem() == Items.GLOW_LICHEN) saturation += 1f;
                continue;
            }
            hunger += get.getFoodProperties(null).getNutrition();
            saturation += get.getFoodProperties(null).getSaturationModifier();
        }
        ItemStack food = new ItemStack(ModItems.TEST_FOOD.get());
        CustomFood.setFoodProperties(food,hunger,saturation);
        CustomFood.setIngredients(food,food1,food2,food3,food4);


        itemStackHandler.insertItem(4,food,false);
    }
    private boolean hasNotReachedStackLimit()
    {
        ItemStack output = itemStackHandler.getStackInSlot(4);
        return output.getCount() < 1;//output.getMaxStackSize();
    }
}
