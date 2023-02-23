package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import com.natesky9.patina.recipe.*;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Patina.MOD_ID);

    public static final RegistryObject<RecipeSerializer<HammerRecipe>> HAMMER_RECIPE_SERIALIZER =
            SERIALIZERS.register(HammerRecipe.name, () -> HammerRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<ChiselRecipe>> CHISEL_SERIALIZER =
            SERIALIZERS.register(ChiselRecipe.name, () -> ChiselRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<KnifeRecipe>> KNIFE_SERIALIZER =
            SERIALIZERS.register(KnifeRecipe.name, () -> KnifeRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<CombiningRecipe>> FLETCHING_SERIALIZER =
            SERIALIZERS.register(CombiningRecipe.name, () -> CombiningRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<FinishingRecipe>> FINISHING_SERIALIZER =
            SERIALIZERS.register(FinishingRecipe.name, () -> FinishingRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<EnchantingRecipe>> ENCHANTING_SERIALIZER =
            SERIALIZERS.register(EnchantingRecipe.name, () -> EnchantingRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<SmokerGrindstoneRecipe>> SMOKER_GRINDSTONE_SERIALIZER =
            SERIALIZERS.register(SmokerGrindstoneRecipe.name, () -> SmokerGrindstoneRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus)
    {
        SERIALIZERS.register(eventBus);
    }
}
