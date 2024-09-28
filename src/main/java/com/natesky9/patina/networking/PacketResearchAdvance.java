package com.natesky9.patina.networking;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class PacketResearchAdvance {
    private final ResourceLocation research;
    private final ResourceLocation name;
    public PacketResearchAdvance(ResourceLocation string, ResourceLocation name)
    {
        this.research = string;
        this.name = name;
    }
    public PacketResearchAdvance(FriendlyByteBuf buf)
    {
        this(buf.readResourceLocation(),buf.readResourceLocation());
    }
    public void encode(FriendlyByteBuf buf)
    {
        buf.writeResourceLocation(this.research);
        buf.writeResourceLocation(this.name);
    }
    public void handle(CustomPayloadEvent.Context context)
    {
        System.out.println("Granting a criteria");
        ServerPlayer player = context.getSender();
        PlayerAdvancements advancements = player.getAdvancements();
        //
        ServerAdvancementManager manager = player.getServer().getAdvancements();
        AdvancementHolder holder = manager.get(research);
        //
        Advancement advancement = manager.tree().get(holder).advancement();
        advancements.award(holder, name.toString());
        advancements.flushDirty(player);
        //for (List<String> requirements: advancement.requirements().requirements()) {
        //    System.out.println(requirements);
        //    for (String string : requirements) {
        //        System.out.println(string);
        //    }
        //}
    }
}
