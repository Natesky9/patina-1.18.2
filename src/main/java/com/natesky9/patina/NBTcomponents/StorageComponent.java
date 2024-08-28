package com.natesky9.patina.NBTcomponents;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class StorageComponent {
    private static final Codec<Holder<Item>> ITEM_CODEC = BuiltInRegistries.ITEM.holderByNameCodec();
    private static final Codec<Integer> INTEGER_CODEC = Codec.intRange(0,1000);
    public static final Codec<StorageComponent> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    ITEM_CODEC.fieldOf("item").forGetter(component -> component.item),
                    INTEGER_CODEC.fieldOf("count").forGetter(component -> component.count)
            ).apply(instance, StorageComponent::new)
    );
    public static StreamCodec<RegistryFriendlyByteBuf, StorageComponent> STREAM_CODEC = StreamCodec.composite(
            Item.STREAM_CODEC,
            inst -> inst.item,
            ByteBufCodecs.INT,
            other -> other.count,
            StorageComponent::new
    );

    final Holder<Item> item;
    final int count;
    public static final StorageComponent EMPTY = new StorageComponent(Items.AIR.builtInRegistryHolder(),0);

    public StorageComponent(Holder<Item> item, int count)
    {
        this.item = item;
        this.count = count;
    }
    public boolean isEmpty()
    {
        return item == Items.AIR || count == 0;
    }
    public Item getItem()
    {
        return item.get();
    }
    public int getCount() {
        return count;
    }
}
