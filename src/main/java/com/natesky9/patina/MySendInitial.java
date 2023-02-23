package com.natesky9.patina;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

public class MySendInitial {
    private final int containerId;
    private final int stateId;
    private final List<ItemStack> items;
    private final ItemStack itemStack;
    //why is this one called on every change, and not the set slot one

    public MySendInitial(int pContainerId, int pStateId, NonNullList<ItemStack> pItems, ItemStack pItemStack)
    {
        this.containerId = pContainerId;
        this.stateId = pStateId;
        this.items = NonNullList.withSize(pItems.size(), ItemStack.EMPTY);
        for (int i = 0; i < pItems.size(); ++i)
        {this.items.set(i, pItems.get(i).copy());}
        this.itemStack = pItemStack.copy();
    }
    public static MySendInitial read(FriendlyByteBuf buffer)
    {
        int container = buffer.readByte();
        int state = buffer.readVarInt();

        NonNullList<ItemStack> items = buffer.readCollection
                (NonNullList::createWithCapacity, FriendlyByteBuf::readItem);//MySendInitial::readItemStack);
        ItemStack helditem = buffer.readItem();//readItemStack(buffer);
        return new MySendInitial(container,state,items,helditem);
    }


    public void write(FriendlyByteBuf pBuffer) {
        pBuffer.writeByte(this.containerId);
        pBuffer.writeVarInt(this.stateId);
        pBuffer.writeCollection(this.items,FriendlyByteBuf::writeItem);
        pBuffer.writeItem(this.itemStack);
        //for (ItemStack item : items)
        //{pBuffer = writeItemStack(pBuffer, item);}
        //pBuffer = writeItemStack(pBuffer,this.itemStack);
    }

    public static void handle(MySendInitial packet, Supplier<NetworkEvent.Context> context)
    {
        context.get().enqueueWork(() -> {
            //if (Minecraft.getInstance().player.containerMenu instanceof BoltPouchMenu menu)
            {
                //menu.slots.get(36).set(packet.items.get(36));
                System.out.println(packet.itemStack.getCount() + " : " + packet.itemStack);
            }
            System.out.println(packet.containerId + ":" + packet.items + ":" + packet.itemStack);
        });
        context.get().setPacketHandled(true);
    }

    //
    public static ItemStack readItemStack(FriendlyByteBuf buffer) {
        if (!buffer.readBoolean()) {
            return ItemStack.EMPTY;
        } else {
            int i = buffer.readVarInt();
            int j = buffer.readShort();
            ItemStack itemstack = new ItemStack(Item.byId(i), j);
            itemstack.readShareTag(buffer.readNbt());
            return itemstack;
        }
    }
    public FriendlyByteBuf writeItemStack(FriendlyByteBuf buffer,ItemStack pStack) {
        if (pStack.isEmpty()) {
            buffer.writeBoolean(false);
        } else {
            buffer.writeBoolean(true);
            Item item = pStack.getItem();
            buffer.writeVarInt(Item.getId(item));
            buffer.writeShort(pStack.getCount());
            CompoundTag compoundtag = null;
            if (item.isDamageable(pStack) || !(compoundtag == null)) {
                compoundtag = pStack.getTag();
            }
            buffer.writeNbt(compoundtag);
        }
        return buffer;
    }
}
