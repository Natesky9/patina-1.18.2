package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import com.natesky9.patina.recipe.*;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeTypes
{
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(Registry.RECIPE_TYPE_REGISTRY, Patina.MOD_ID);
    //
    public static final RegistryObject<RecipeType<HammerRecipe>> HAMMER_RECIPE_TYPE = create("hammer");
    public static final RegistryObject<RecipeType<ChiselRecipe>> CHISEL_RECIPE_TYPE = create("chisel");
    public static final RegistryObject<RecipeType<KnifeRecipe>> KNIFE_RECIPE_TYPE = create("knife");
    //
    public static final RegistryObject<RecipeType<FletchingRecipe>> FLETCHING_RECIPE_TYPE = create("fletching");
    public static final RegistryObject<RecipeType<SmokerGrindstoneRecipe>> SMOKER_GRINDSTONE_RECIPE_TYPE = create("smoker_grindstone");
    //do I need a recipe type for an existing crafting recipe?
    public static final RegistryObject<RecipeType<CrossbowRecipe>> CROSSBOW_UPGRADE_RECIPE_TYPE = create("crossbow_upgrade");
    //----------//
    static <T extends Recipe<?>> RegistryObject<RecipeType<T>> create(final String id)
    {
        return RECIPE_TYPES.register(id, () -> new RecipeType<T>()
        {
            @Override//goofy ahh spacing
            public String toString() {return id;}});
    }
    //
    public static void register(IEventBus eventBus)
    {
        RECIPE_TYPES.register(eventBus);
    }
}
