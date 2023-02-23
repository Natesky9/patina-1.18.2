package com.natesky9.patina.item.BoltPouch;

import com.natesky9.patina.BackpackSlot;
import com.natesky9.patina.init.ModMenuTypes;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class BoltPouchMenu extends AbstractContainerMenu
{
    ItemStack selected;
    Container pouch;
    public Slot slot;
    public Player player;


    public BoltPouchMenu(int containerID, Inventory inventory, FriendlyByteBuf extraData)
    {
        this(containerID, inventory, inventory.player, new SimpleContainer(1024)
        {
            @Override
            public int getMaxStackSize() {
                return 1027;
            }
        },inventory.getItem(inventory.selected));
    }

    public BoltPouchMenu(int containerID, Inventory inventory, Player player, Container container, ItemStack itemStack) {
        super(ModMenuTypes.BOLT_POUCH_MENU.get(), containerID);
        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);
        this.slot = addSlot(new BackpackSlot(container,0,80,35,itemStack));
        selected = itemStack;
        this.player = player;

        //this.pouch = container;

        if (selected.getItem() instanceof BoltPouchItem)
        {
            CompoundTag tag = selected.getOrCreateTag();
            ItemStack stack = ItemStack.of(tag);
            int count = selected.getOrCreateTag().getInt("True Count");
            stack.setCount(count);
            slot.set(stack);
        }
    }
    @Override
    public void initializeContents(int p_182411_, List<ItemStack> stacks, ItemStack carried) {
        super.initializeContents(p_182411_, stacks, carried);
        for(int i = 0; i < stacks.size(); ++i) {
            Slot myslot = this.getSlot(i);
            if (myslot instanceof BackpackSlot slot)
            {
                int count = selected.getOrCreateTag().getInt("True Count");
                slot.getItem().setCount(count);
            }
        }
    }

    @Override
    public void broadcastChanges() {

        super.broadcastChanges();

        //for (Slot value : this.slots) {
        //    ItemStack stack = value.getItem();
        //    int count = stack.getOrCreateTag().getInt("True Count");
        //    stack.setCount(count);
        //    value.set(stack);
        //}
    }

    @Override
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        int count = slot.getItem().getCount();//save the count
        slot.getItem().setCount(69);//set it to an arbitrary number
        CompoundTag tag = slot.getItem().save(selected.getOrCreateTag());
        selected.getOrCreateTag().putInt("True Count",count);
        selected.setTag(tag);
    }
    public CompoundTag saveItem(ItemStack stack)
    {
        CompoundTag tag = new CompoundTag();
        ResourceLocation resourcelocation = Registry.ITEM.getKey(stack.getItem());
        tag.putString("id",resourcelocation.toString());
        tag.putInt("Count", stack.getCount());
        if (stack.getTag() != null) {
            tag.put("tag", stack.getTag().copy());
        }
        return tag;
    }
    public ItemStack loadItem(CompoundTag tag)
    {
        ItemStack stack = ItemStack.of(tag);
        //stack.setCount(tag.getInt("count"));
        //we have to handhold this because minecraft can't count past 127
        return stack;
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
        return ItemStack.EMPTY;
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
                this.addSlot(ImmutableSlot(playerInventory, i,8+i*18,142));
            else
                this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
    private Slot ImmutableSlot(Inventory inventory, int i, int x, int y)
    {
        //containerslot is the backpack, make sure we can't pick this up
        return new Slot(inventory,i,x,y)
        {
            @Override
            public boolean mayPickup(Player pPlayer) {
                return false;
            }
        };
    }
}
