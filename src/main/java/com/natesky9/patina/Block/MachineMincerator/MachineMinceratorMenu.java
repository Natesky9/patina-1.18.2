package com.natesky9.patina.Block.MachineMincerator;

import com.natesky9.patina.Recipe.ModRecipeBookType;
import com.natesky9.patina.Recipe.MinceratorRecipe;
import com.natesky9.patina.init.ModBlocks;
import com.natesky9.patina.init.ModItems;
import com.natesky9.patina.init.ModMenuTypes;
import com.natesky9.patina.init.ModRecipeTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class MachineMinceratorMenu extends RecipeBookMenu<RecipeInput, MinceratorRecipe> {
    private final MachineMinceratorEntity blockEntity;
    private final Level level;
    private final ContainerData data;
    public MachineMinceratorMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData)
    {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }
    public MachineMinceratorMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data)
    {
        super(ModMenuTypes.MACHINE_MINCERATOR_MENU.get(), pContainerId);
        checkContainerSize(inv, 6);
        blockEntity = ((MachineMinceratorEntity) entity);
        this.level = inv.player.level();
        this.data = data;


        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler ->
        {
            this.addSlot(new SlotItemHandler(handler,0,28,15));
            this.addSlot(new SlotItemHandler(handler,1,60,15));
            this.addSlot(new SlotItemHandler(handler,2,28,47));
            this.addSlot(new SlotItemHandler(handler,3,60,47));
            this.addSlot(new SlotItemHandler(handler,4,126,32));
            this.addSlot(new SlotItemHandler(handler,5,126,8));
        });
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        addDataSlots(data);
    }

    public boolean isCrafting()
    {
        return data.get(0) > 0;
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
        // must assign a slot number to each of the slots used by the GUI.
        // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
        // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
        //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
        //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
        //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
        private static final int HOTBAR_SLOT_COUNT = 9;
        private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
        private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
        private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
        private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
        private static final int VANILLA_FIRST_SLOT_INDEX = 0;
        private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

        // THIS YOU HAVE TO DEFINE!
        private static final int TE_INVENTORY_SLOT_COUNT = MachineMinceratorEntity.slots;  // must be the number of slots you have!

        @Override
        public ItemStack quickMoveStack(Player playerIn, int index) {
            ItemStack temp;
            if (index >= 0 && index < 6)//click inside machine
            {
                temp = slots.get(index).getItem();
                if (temp.isEmpty()) return ItemStack.EMPTY;
                playerIn.addItem(temp);
                slots.get(index).getItem().shrink(1);
            }
            if (index >= 6)
            {//clicking in inventory
                temp = slots.get(index).getItem();
                if (temp.is(ModItems.POTION_SALT.get()))
                {//unique case for salts
                    if (slots.get(5).hasItem()) return ItemStack.EMPTY;
                    slots.get(5).set(temp.copyWithCount(1));
                    slots.get(index).getItem().shrink(1);
                    return ItemStack.EMPTY;
                }
                Item finalTemp = temp.getItem();
                if (level.getRecipeManager().getAllRecipesFor(ModRecipeTypes.MINCERATOR_RECIPE_TYPE.get()).stream().anyMatch(recipe ->
                        recipe.value().getResultItem(level.registryAccess()).is(finalTemp)))
                {
                    if (slots.get(4).hasItem()) return ItemStack.EMPTY;
                    slots.get(4).set(temp.copyWithCount(1));
                    slots.get(index).getItem().shrink(1);
                    return ItemStack.EMPTY;
                }
                for (int i=0;i < 4;i++)
                {//fill the first empty input slot
                    if (slots.get(i).hasItem()) continue;
                    slots.get(i).set(temp.copyWithCount(1));
                    slots.get(index).getItem().shrink(1);
                    return ItemStack.EMPTY;
                }
            }
            return ItemStack.EMPTY;
        }
    //

    @Override
    public boolean stillValid(Player pPlayer)
    {
        return stillValid(ContainerLevelAccess.create(level,blockEntity.getBlockPos()),
                pPlayer, ModBlocks.MACHINE_MINCERATOR.get());
    }
    private void addPlayerInventory(Inventory playerInventory)
    {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory)
    {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void fillCraftSlotsStackedContents(StackedContents p_40117_) {

    }

    @Override
    public void clearCraftingContent() {

    }

    @Override
    public int getResultSlotIndex() {
        return 4;
    }

    @Override
    public int getGridWidth() {
        return 2;
    }

    @Override
    public int getGridHeight() {
        return 2;
    }

    @Override
    public int getSize() {
        return 4;
    }

    @Override
    public RecipeBookType getRecipeBookType() {
        return ModRecipeBookType.MINCERATOR;
    }

    @Override
    public boolean shouldMoveToInventory(int p_150635_) {
        return false;
    }

    @Override
    public boolean recipeMatches(RecipeHolder p_297792_) {
        return false;
    }
}
