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
            SERIALIZERS.register("hammer", () -> HammerRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<ChiselRecipe>> CHISEL_SERIALIZER =
            SERIALIZERS.register("chisel", () -> ChiselRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<SmokerGrindstoneRecipe>> SMOKER_GRINDSTONE_SERIALIZER =
            SERIALIZERS.register("smoker_grindstone", () -> SmokerGrindstoneRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<FletchingRecipe>> FLETCHING_SERIALIZER =
            SERIALIZERS.register("fletching", () -> FletchingRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<CrossbowRecipe>> CROSSBOW_SERIALIZER =
            SERIALIZERS.register("crossbow_upgrade", () -> CrossbowRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus)
    {
        SERIALIZERS.register(eventBus);
    }
}
