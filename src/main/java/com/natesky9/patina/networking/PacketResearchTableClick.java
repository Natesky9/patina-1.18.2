package com.natesky9.patina.networking;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.CustomPayloadEvent;

import java.util.List;

public class PacketResearchTableClick {
    private final ResourceLocation research;

    public PacketResearchTableClick(ResourceLocation string)
    {
        this.research = string;
    }
    public PacketResearchTableClick(FriendlyByteBuf buf)
    {
        this(buf.readResourceLocation());
    }
    public void encode(FriendlyByteBuf buf)
    {
        buf.writeResourceLocation(this.research);
    }
    public void handle(CustomPayloadEvent.Context context)
    {
        ServerPlayer player = context.getSender();
        PlayerAdvancements advancements = player.getAdvancements();
        //
        ServerAdvancementManager manager = player.getServer().getAdvancements();
        AdvancementHolder holder = manager.get(research);
        //not criteria
        String done = manager.tree().get(holder).advancement().requirements().toString();
        System.out.println(done);
        //
        Advancement advancement = manager.tree().get(holder).advancement();
        for (List<String> requirements: advancement.requirements().requirements())
        {
            for (String string: requirements)
            {
                //System.out.println("granting: " + string);
                advancements.award(holder,string);
            }
        }
        //advancements.award(holder,advancement.requirements().requirements().stream().findFirst().get().stream().findFirst().get());
        //ItemStack stack = new ItemStack(Items.PAPER);
        //stack.set(DataComponents.LORE, new ItemLore(List.of(Component.literal(research.toString())
        //        .withStyle(ChatFormatting.DARK_PURPLE))));
        //player.addItem(stack);
    }

}
