package com.natesky9.patina;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public interface TriggeredEnchant {
    public String getMessage();
    public SoundEvent getSound();
    public SoundEvent getSound2();
    public ItemStack getItem();


    default void onCooldown(Player player)
    {
        message(player,getMessage());
        playSound(player,getSound());
        particle(player,getItem());
    }

    default void offCooldown(Player player)
    {
        playSound(player,getSound2());
    }
    default void playSound(Player player, SoundEvent sound)
    {
        player.level.playSound(null,player.blockPosition(),getSound(),SoundSource.PLAYERS,.5F,.5F);
    }
    static void message(Player player, String string)
    {
        player.displayClientMessage(new TextComponent(string),true);
    }
    default void particle(Player player, ItemStack stack)
    {
        //TODO: change into custom particle
        Random random = player.getRandom();
        for(int i = 0; i < 10; i++) {
            Vec3 vec3 = new Vec3(((double)random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, ((double)random.nextFloat() - 0.5D) * 0.1D);
            vec3 = vec3.xRot(-player.getXRot() * ((float)Math.PI / 180F));
            vec3 = vec3.yRot(-player.getYRot() * ((float)Math.PI / 180F));
            double d0 = (double)(-random.nextFloat()) * 0.6D - 0.3D;
            Vec3 vec31 = new Vec3(((double)random.nextFloat() - 0.5D) * 0.8D, d0, 1.0D + ((double)random.nextFloat() - 0.5D) * 0.4D);
            vec31 = vec31.yRot(-player.yBodyRot * ((float)Math.PI / 180F));
            vec31 = vec31.add(player.getX(), player.getEyeY() + 1.0D, player.getZ());
            player.level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, stack), vec31.x, vec31.y, vec31.z, vec3.x, vec3.y + 0.05D, vec3.z);
        }
    }
}
