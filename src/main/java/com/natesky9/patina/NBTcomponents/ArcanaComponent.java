package com.natesky9.patina.NBTcomponents;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public class ArcanaComponent {
    private static final Codec<Integer> INTEGER_CODEC = Codec.intRange(0,Integer.MAX_VALUE);
    public static final Codec<ArcanaComponent> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    INTEGER_CODEC.fieldOf("energy").forGetter(getter -> getter.potency)
            ).apply(instance, ArcanaComponent::new)
    );
    public static StreamCodec<RegistryFriendlyByteBuf, ArcanaComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            value -> value.potency,
            ArcanaComponent::new
    );
    final int potency;

    public ArcanaComponent(int energy)
    {
        this.potency = energy;
    }

    public int getPotency() {
        return potency;
    }
}
