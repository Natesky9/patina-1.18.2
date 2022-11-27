package com.natesky9.patina.item.PowderPouch;

import com.natesky9.patina.init.ModMenuTypes;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PowderPouchMenu extends AbstractContainerMenu
{
    ItemStack selected;
    Container pouch;
    Slot slot;


    public PowderPouchMenu(int containerID, Inventory inventory, FriendlyByteBuf extraData)
    {
        this(containerID, inventory, inventory.player, new SimpleContainer(1),inventory.getItem(inventory.selected));
    }

    public PowderPouchMenu(int containerID, Inventory inventory, Player player, Container container, ItemStack itemStack) {
        super(ModMenuTypes.POWDER_POUCH_MENU.get(), containerID);
        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);
        this.slot = addSlot(BackpackSlot(container,0,80,35,itemStack));
        selected = itemStack;

        this.pouch = container;

        if (selected.getItem() instanceof PowderPouchItem)
        {
            //load from item into menu
            CompoundTag tag = selected.getOrCreateTag();
            Item getItem = Registry.ITEM.get(new ResourceLocation(tag.getString("id")));
            int count = tag.getInt("count");
            int min = Math.min(count,getItem.getMaxStackSize());
            //ItemStack stack = new ItemStack(getItem,min);
            //tag.putInt("count",count-min);
            //slot.set(stack);

            //NonNullList<ItemStack> itemstacks = NonNullList.withSize(1,ItemStack.EMPTY);
            //if (tag.contains("Items",9))
            //{
            //    ContainerHelper.loadAllItems(tag, itemstacks);
            //    //initializeContents(containerID,items,ItemStack.EMPTY);
            //    slot.set(itemstacks.get(0));
            //}
        }
    }

    @Override
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        ItemStack getPouch = pPlayer.getInventory().getSelected();
        if (getPouch.getItem() instanceof PowderPouchItem pouch)
        {
            //save menu to item
            //not needed?
            //pouch.setContents(getPouch,slot.getItem());
        }
    }


    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private static final int TE_INVENTORY_SLOT_COUNT = 1;  // must be the number of slots you have!

    @Override
    public ItemStack quickMoveStack(Player player, int index)
    {
        player.displayClientMessage(new TextComponent("moved stack"),true);
        //        //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
        //        //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
        //        //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
        CompoundTag tag = selected.getOrCreateTag();
        Item item = Registry.ITEM.get(new ResourceLocation(tag.getString("id")));
        int count = tag.contains("count") ? tag.getInt("count") : 0;
        int count_stack = Math.min(count,64);
        if (index == 36)
        {
            if (item == Items.AIR) return ItemStack.EMPTY;
            boolean complete = moveItemStackTo(new ItemStack(item,count), 0, 35, false);

            tag.putInt("count",count-count_stack);
            if (tag.getInt("count") == 0)
            {
                tag.remove("count");
                tag.remove("id");
            }
            if (!complete)
                return ItemStack.EMPTY;
        }
        else
        {
            //the stack from inventory
            ItemStack stack = slots.get(index).getItem();

            Item existingItem = Registry.ITEM.get(new ResourceLocation(tag.getString("id")));
            if ((existingItem != stack.getItem()) && count != 0)
                return ItemStack.EMPTY;

            ((PowderPouchItem)selected.getItem()).setContents(selected,stack);
            boolean complete = moveItemStackTo(stack, 36, 36, false);
            slots.get(index).set(ItemStack.EMPTY);
            if (!complete)
                return ItemStack.EMPTY;
        }
        return ItemStack.EMPTY;

        //Slot sourceSlot = slots.get(index);
        //if (!sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        //ItemStack sourceStack = sourceSlot.getItem();
        //ItemStack copyOfSourceStack = sourceStack.copy();
        //// Check if the slot clicked is one of the vanilla container slots
        //if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT)
        //{
        //    // This is a vanilla container slot so merge the stack into the tile inventory
        //    if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
        //            + TE_INVENTORY_SLOT_COUNT, false)) {
        //        return ItemStack.EMPTY;  // EMPTY_ITEM
        //    }
        //} else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT)
        //{
        //    // This is a TE slot so merge the stack into the players inventory
        //    if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
        //        return ItemStack.EMPTY;
        //    }
        //} else {
        //    System.out.println("Invalid slotIndex:" + index);
        //    return ItemStack.EMPTY;
        //}
        //// If stack size == 0 (the entire stack was moved) set slot contents to null
        //if (sourceStack.getCount() == 0) {
        //    sourceSlot.set(ItemStack.EMPTY);
        //} else {
        //    sourceSlot.setChanged();
        //}
        //sourceSlot.onTake(player, sourceStack);
        //return copyOfSourceStack;
    }

    private Slot BackpackSlot(Container container, int index, int x, int y,ItemStack bag)
    {
        return new Slot(container,index,x,y)
        {
            @Override
            public int getMaxStackSize() {
                return 64;
            }

            @Override
            public boolean mayPlace(ItemStack pStack) {
                return pStack.is(Tags.Items.DUSTS)
                        || pStack.is(Tags.Items.GUNPOWDER)
                        || pStack.is(Items.BLAZE_POWDER)
                        || pStack.is(Items.BONE_MEAL)
                        || pStack.is(Tags.Items.DYES)
                        || pStack.is(Items.SUGAR);
            }

            @Override
            public void onTake(Player pPlayer, ItemStack pStack) {
                int count = bag.getOrCreateTag().contains("count") ? bag.getOrCreateTag().getInt("count"):0;
                bag.getOrCreateTag().putInt("count",count-pStack.getCount());
                container.setItem(0,new ItemStack(pStack.getItem(),count-pStack.getCount()));
            }

            @Override
            public ItemStack safeInsert(ItemStack itemstack) {
                int count = bag.getOrCreateTag().contains("count") ? bag.getOrCreateTag().getInt("count"):0;
                bag.getOrCreateTag().putInt("count",count+itemstack.getCount());
                return ItemStack.EMPTY;
            }
        };
    }
    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
    //
    private void addPlayerInventory(Inventory playerInventory) {
        //TODO: change all my other uis to have this correct height
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory)
    {
        for (int i = 0; i < 9; ++i)
        {
            if (i == playerInventory.selected)
                this.addSlot(ContainerSlot(playerInventory, i,8+i*18,142));
            else
                this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
    private Slot ContainerSlot(Inventory inventory, int i, int x, int y)
    {
        return new Slot(inventory,i,x,y)
        {
            @Override
            public boolean mayPickup(Player pPlayer) {
                return false;
            }
        };
    }
}
