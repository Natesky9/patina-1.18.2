package com.natesky9.patina.Loot;

import com.mojang.serialization.MapCodec;
import com.natesky9.patina.Patina;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Patina.MODID);
    //
    //TODO: create the supplier inline during registration
    public static final RegistryObject<MapCodec<? extends IGlobalLootModifier>> ADD_ITEM =
            LOOT_MODIFIER_SERIALIZERS.register("add_item", () -> AddItemModifier.CODEC);
    public static final RegistryObject<MapCodec<? extends IGlobalLootModifier>> ADD_SINGLE_ITEM =
            LOOT_MODIFIER_SERIALIZERS.register("add_single_item", () -> AddSingleItemModifier.CODEC);
    public static final RegistryObject<MapCodec<? extends IGlobalLootModifier>> ADD_MIN_MAX =
            LOOT_MODIFIER_SERIALIZERS.register("add_min_max", () -> AddMinMax.CODEC);
    public static final RegistryObject<MapCodec<? extends IGlobalLootModifier>> REPLACE_ITEM =
            LOOT_MODIFIER_SERIALIZERS.register("replace_item", () -> ReplaceItemModifier.CODEC);
    //
    public static void register(IEventBus eventBus)
    {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}
