package com.natesky9.patina;

import com.natesky9.patina.item.BoltPouch.BoltPouchMenu;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerSynchronizer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkDirection;

public class MyContainerSynchronizer implements ContainerSynchronizer {
    @Override
    public void sendInitialData(AbstractContainerMenu menu, NonNullList<ItemStack> stacks, ItemStack stack, int[] ints)
    {
        //MySendInitial packet = new MySendInitial(menu.containerId,menu.incrementStateId(),stacks,stack);
        //Patina.PacketChannel.sendTo(packet,((ServerPlayer)((BoltPouchMenu)menu).player).connection.getConnection(),NetworkDirection.PLAY_TO_CLIENT);
        if (menu instanceof BoltPouchMenu boltmenu)
        {
            if (!(boltmenu.player instanceof ServerPlayer player)) return;
            MySendInitial packet = new MySendInitial(menu.containerId, menu.incrementStateId(), stacks, stack);
            Patina.PacketChannel.sendTo(packet, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        }
            //    ((ServerPlayer)boltmenu.player).connection.send(
        //            new MyContainerSetContentsPacket(menu.containerId,menu.incrementStateId(),stacks,stack));
        //}
    }
    @Override
    public void sendSlotChange(AbstractContainerMenu menu, int slot, ItemStack stack)
    {
        if (menu instanceof BoltPouchMenu boltmenu)
        {
            if (!(boltmenu.player instanceof ServerPlayer player)) return;
            //Patina.PacketChannel.sendTo(new MyContainerPacket(menu.containerId, menu.incrementStateId(), slot, stack),player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        }
        //    if (!(boltmenu.player instanceof ServerPlayer player))
        //    ((ServerPlayer)boltmenu.player).connection.send(
        //            new MyContainerPacket(menu.containerId,menu.incrementStateId(), slot, stack)
        //    );
        //}

    }
    @Override
    public void sendCarriedChange(AbstractContainerMenu menu, ItemStack stack)
    {
        System.out.println("you need this");
        //if (menu instanceof BoltPouchMenu boltmenu)
        //{
        //    if (!(boltmenu.player instanceof ServerPlayer player)) return;
        //    Patina.PacketChannel.sendTo(new MyContainerSetContentsPacket(menu.containerId, menu.incrementStateId(),slot, stack),player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
        //}
        //}
    }
    @Override
    public void sendDataChange(AbstractContainerMenu menu, int p_150528_, int p_150529_)
    {
        //I don't use data, so this isn't needed
        throw new RuntimeException("Oh yes you do!");
    }
}
