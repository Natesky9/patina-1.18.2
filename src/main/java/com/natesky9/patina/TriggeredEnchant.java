package com.natesky9.patina;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

public interface TriggeredEnchant {

    default void onCooldown(Player player)
    {
        message(player,"The blessing activates");
        playSound(player,SoundEvents.BEACON_DEACTIVATE);
    }

    default void offCooldown(Player player)
    {
        playSound(player,SoundEvents.BEACON_ACTIVATE);
    }
    static void playSound(Player player, SoundEvent sound)
    {
        player.level.playSound(null,player.blockPosition(),sound,SoundSource.PLAYERS,.5F,.5F);
    }
    static void message(Player player, String string)
    {
        player.displayClientMessage(new TextComponent(string),true);
    }

}
