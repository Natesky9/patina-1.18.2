package com.natesky9.patina;

import net.minecraft.network.PacketListener;

public interface MyPacketListener extends PacketListener {
    void MyContainerContents(MySendInitial packet);
    void MyContainerSetSlot(MyContainerPacket packet);
}
