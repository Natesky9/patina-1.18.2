package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import com.natesky9.patina.recipe.ToolRecipe;
import com.natesky9.patina.recipe.SmokerGrindstoneRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Patina.MOD_ID);

    public static final RegistryObject<RecipeSerializer<SmokerGrindstoneRecipe>> SMOKER_GRINDSTONE_SERIALIZER =
            SERIALIZERS.register("smoker_grindstone", () -> SmokerGrindstoneRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<ToolRecipe>> CHISEL_SERIALIZER =
            SERIALIZERS.register("chisel", () -> ToolRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus)
    {
        SERIALIZERS.register(eventBus);
    }
}
