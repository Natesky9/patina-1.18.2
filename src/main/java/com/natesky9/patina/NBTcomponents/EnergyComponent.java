package com.natesky9.patina.NBTcomponents;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public class EnergyComponent {
    private static final Codec<Integer> INTEGER_CODEC = Codec.intRange(0,Integer.MAX_VALUE);
    public static final Codec<EnergyComponent> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    INTEGER_CODEC.fieldOf("energy").forGetter(getter -> getter.energy)
            ).apply(instance, EnergyComponent::new)
    );
    public static StreamCodec<RegistryFriendlyByteBuf, EnergyComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            value -> value.energy,
            EnergyComponent::new
    );
    final int energy;

    public EnergyComponent(int energy)
    {
        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }
}
