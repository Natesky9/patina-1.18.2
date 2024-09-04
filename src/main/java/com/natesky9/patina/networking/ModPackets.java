package com.natesky9.patina.networking;

import com.natesky9.patina.Patina;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.*;

public class ModPackets {
    private static SimpleChannel INSTANCE;
    private static int packetId = 0;
    private static int id()
    {
        return packetId++;
    }

    public static void register()
    {
        SimpleChannel net = ChannelBuilder.named(
                        ResourceLocation.fromNamespaceAndPath(Patina.MODID,"main"))
                .serverAcceptedVersions(((status, version) -> true))
                .clientAcceptedVersions(((status, version) -> true))
                .networkProtocolVersion(1)
                .simpleChannel();
        INSTANCE = net;
        net.messageBuilder(PacketResearchTableClick.class, NetworkDirection.PLAY_TO_SERVER)
                .encoder(PacketResearchTableClick::encode)
                .decoder(PacketResearchTableClick::new)
                .consumerMainThread(PacketResearchTableClick::handle)
                .add();
    }


    public static <MSG> void sendToServer(MSG msg)
    {
        INSTANCE.send(msg, PacketDistributor.SERVER.noArg());
    }
    public static <MSG> void sendToPlayer(MSG msg, ServerPlayer player)
    {
        INSTANCE.send(msg, PacketDistributor.PLAYER.with(player));
    }
    public static <MSG> void sendToAllClients(MSG msg)
    {
        INSTANCE.send(msg, PacketDistributor.ALL.noArg());
    }
}
