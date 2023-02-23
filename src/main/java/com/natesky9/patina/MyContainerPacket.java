package com.natesky9.patina;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MyContainerPacket {
    private final int containerId;
    private final int stateId;
    private final int slot;
    private final ItemStack itemStack;

    public MyContainerPacket(int pContainerId, int pStateId, int pSlot, ItemStack pItemStack)
    {
        this.containerId = pContainerId;
        this.stateId = pStateId;
        this.slot = pSlot;
        this.itemStack = pItemStack.copy();
    }
    public MyContainerPacket(FriendlyByteBuf buffer)
    {
        this.containerId = buffer.readByte();
        this.stateId = buffer.readVarInt();
        this.slot = buffer.readShort();//this used to be a byte
        this.itemStack = readItem(buffer);
    }

    public void write(FriendlyByteBuf pBuffer) {
        pBuffer.writeByte(this.containerId);
        pBuffer.writeVarInt(this.stateId);
        pBuffer.writeShort(this.slot);
        writeItemStack(pBuffer,this.itemStack,true);
    }

    public static void handle(MyContainerPacket packet, Supplier<NetworkEvent.Context> context)
    {
        context.get().enqueueWork( () -> {
            System.out.println("changed slot");
        });
        context.get().setPacketHandled(true);

    }

    //
    public ItemStack readItem(FriendlyByteBuf buffer) {
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
    public FriendlyByteBuf writeItemStack(FriendlyByteBuf buffer,ItemStack pStack, boolean limitedTag) {
        if (pStack.isEmpty()) {
            buffer.writeBoolean(false);
        } else {
            buffer.writeBoolean(true);
            Item item = pStack.getItem();
            buffer.writeVarInt(Item.getId(item));
            buffer.writeShort(pStack.getCount());
            CompoundTag compoundtag = null;
            if (item.isDamageable(pStack) || item.shouldOverrideMultiplayerNbt()) {
                compoundtag = limitedTag ? pStack.getShareTag() : pStack.getTag();
            }
            buffer.writeNbt(compoundtag);
        }
        return buffer;
    }
}
