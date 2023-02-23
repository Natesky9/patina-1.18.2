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
    public static final RegistryObject<RecipeType<HammerRecipe>> HAMMER_RECIPE_TYPE = create(HammerRecipe.name);
    public static final RegistryObject<RecipeType<ChiselRecipe>> CHISEL_RECIPE_TYPE = create(ChiselRecipe.name);
    public static final RegistryObject<RecipeType<KnifeRecipe>> KNIFE_RECIPE_TYPE = create(KnifeRecipe.name);
    //
    public static final RegistryObject<RecipeType<CombiningRecipe>> COMBINING_RECIPE_TYPE = create(CombiningRecipe.name);
    public static final RegistryObject<RecipeType<FinishingRecipe>> FINISHING_RECIPE_TYPE = create(FinishingRecipe.name);
    public static final RegistryObject<RecipeType<EnchantingRecipe>> ENCHANTING_RECIPE_TYPE = create(EnchantingRecipe.name);


    public static final RegistryObject<RecipeType<SmokerGrindstoneRecipe>> SMOKER_GRINDSTONE_RECIPE_TYPE = create(SmokerGrindstoneRecipe.name);
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
