package com.natesky9.patina.Loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;


public class AddMinMax extends LootModifier {
    private final Item item;
    private final int min;
    private final int max;
    public static final MapCodec<AddMinMax> CODEC = RecordCodecBuilder.mapCodec(instance -> codecStart(instance)
                            .and(instance.group(
                                    Codec.INT.fieldOf("min").forGetter(m -> m.min),
                                    Codec.INT.fieldOf("max").forGetter(m -> m.max),
                                    ForgeRegistries.ITEMS.getCodec().fieldOf("item").forGetter(m -> m.item)
                            )).apply(instance,AddMinMax::new)
            );

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    public AddMinMax(LootItemCondition[] conditionsIn, int min, int max, Item item) {
        super(conditionsIn);
        this.min = min;
        this.max = max;
        this.item = item;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        //add inclusive count of an item
        int count = (int)(context.getRandom().nextFloat()*(max-min)+min);
        generatedLoot.add(new ItemStack(item,count));
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
