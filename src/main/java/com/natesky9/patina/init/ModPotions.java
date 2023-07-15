package com.natesky9.patina.init;

import com.natesky9.patina.Recipe.ItemBrewingRecipe;
import com.natesky9.patina.Patina;
import com.natesky9.patina.Recipe.SaltBrewingRecipe;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions
{
    static final int brief = 900;
    static final int shorter = 1800;
    static final int normal = 3600;
    static final int extended = 9600;
    public static final DeferredRegister<Potion> MOD_POTIONS
            = DeferredRegister.create(ForgeRegistries.POTIONS, Patina.MODID);
    //
    public static final RegistryObject<Potion> STRONG_WEAKNESS = MOD_POTIONS.register("strong_weakness",
            () -> new Potion(new MobEffectInstance(MobEffects.WEAKNESS, shorter,1)));
    //my potions
    public static final RegistryObject<Potion> HASTE = MOD_POTIONS.register("haste",
            () -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED,normal)));
    public static final RegistryObject<Potion> STRONG_HASTE = MOD_POTIONS.register("strong_haste",
            () -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED, shorter,1)));
    public static final RegistryObject<Potion> LONG_HASTE = MOD_POTIONS.register("long_haste",
            () -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED,extended)));
    //
    public static final RegistryObject<Potion> SWIFTNESS_INTERMEDIATE = MOD_POTIONS.register("intermediate_swiftness", Potion::new);
    public static final RegistryObject<Potion> ENHANCED_SWIFTNESS = MOD_POTIONS.register("enhanced_swiftness",
            () -> new Potion(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,extended,2)));
    public static final RegistryObject<Potion> SLOWNESS_INTERMEDIATE = MOD_POTIONS.register("intermediate_slowness", Potion::new);
    public static final RegistryObject<Potion> ENHANCED_SLOWNESS = MOD_POTIONS.register("enhanced_slowness",
            () -> new Potion(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, brief,4)));
    public static final RegistryObject<Potion> LEAPING_INTERMEDIATE = MOD_POTIONS.register("intermediate_jump_boost", Potion::new);
    public static final RegistryObject<Potion> ENHANCED_LEAPING = MOD_POTIONS.register("enhanced_jump_boost",
            () -> new Potion(new MobEffectInstance(MobEffects.JUMP,extended,2)));
    public static final RegistryObject<Potion> STRENGTH_INTERMEDIATE = MOD_POTIONS.register("intermediate_strength", Potion::new);
    public static final RegistryObject<Potion> ENHANCED_STRENGTH = MOD_POTIONS.register("enhanced_strength",
            () -> new Potion(new MobEffectInstance(MobEffects.DAMAGE_BOOST,extended,2)));
    public static final RegistryObject<Potion> WEAKNESS_INTERMEDIATE = MOD_POTIONS.register("intermediate_weakness", Potion::new);
    public static final RegistryObject<Potion> ENHANCED_WEAKNESS = MOD_POTIONS.register("enhanced_weakness",
            () -> new Potion(new MobEffectInstance(MobEffects.WEAKNESS,shorter,2)));
    public static final RegistryObject<Potion> HEALING_INTERMEDIATE = MOD_POTIONS.register("intermediate_healing", Potion::new);
    public static final RegistryObject<Potion> ENHANCED_HEALING = MOD_POTIONS.register("enhanced_healing",
            () -> new Potion(new MobEffectInstance(MobEffects.HEAL,1,2)));
    public static final RegistryObject<Potion> HARMING_INTERMEDIATE = MOD_POTIONS.register("intermediate_harming", Potion::new);
    public static final RegistryObject<Potion> ENHANCED_HARMING = MOD_POTIONS.register("enhanced_harming",
            () -> new Potion(new MobEffectInstance(MobEffects.HEAL,1,2)));
    public static final RegistryObject<Potion> POISON_INTERMEDIATE = MOD_POTIONS.register("intermediate_poison", Potion::new);
    public static final RegistryObject<Potion> ENHANCED_POISON = MOD_POTIONS.register("enhanced_poison",
            () -> new Potion(new MobEffectInstance(MobEffects.POISON,shorter,2)));
    public static final RegistryObject<Potion> REGENERATION_INTERMEDIATE = MOD_POTIONS.register("intermediate_regeneration", Potion::new);
    public static final RegistryObject<Potion> ENHANCED_REGENERATION = MOD_POTIONS.register("enhanced_regeneration",
            () -> new Potion(new MobEffectInstance(MobEffects.REGENERATION,shorter,2)));
    public static final RegistryObject<Potion> FIRE_RESIST_INTERMEDIATE = MOD_POTIONS.register("intermediate_fire_resist", Potion::new);
    public static final RegistryObject<Potion> ENHANCED_FIRE_RESIST = MOD_POTIONS.register("enhanced_fire_resist",
            () -> new Potion(new MobEffectInstance(MobEffects.REGENERATION,19200,1)));
    public static final RegistryObject<Potion> WATER_BREATHING_INTERMEDIATE = MOD_POTIONS.register("intermediate_water_breathing", Potion::new);
    public static final RegistryObject<Potion> ENHANCED_WATER_BREATHING = MOD_POTIONS.register("enhanced_water_breathing",
            () -> new Potion(new MobEffectInstance(MobEffects.WATER_BREATHING,19200,1)));
    //didn't feel like finishing these
    //public static final RegistryObject<Potion> NIGHT_VISION_INTERMEDIATE = MOD_POTIONS.register("intermediate_night_vision", Potion::new);
    //public static final RegistryObject<Potion> INVISIBILITY_INTERMEDIATE = MOD_POTIONS.register("intermediate_invisibility", Potion::new);
    //public static final RegistryObject<Potion> SLOW_FALLING_INTERMEDIATE = MOD_POTIONS.register("intermediate_slow_falling", Potion::new);
    //mundane potions
    public static final RegistryObject<Potion> ACRID_POTION = MOD_POTIONS.register("acrid_potion",
            Potion::new);
    public static final RegistryObject<Potion> VOLATILE_POTION = MOD_POTIONS.register("volatile_potion",
            Potion::new);
    //bismuth blend
    public static final RegistryObject<Potion> IRIDESCENT_POTION = MOD_POTIONS.register("iridescent_potion",
            Potion::new);
    //
    public static void register(IEventBus eventBus)
    {
        MOD_POTIONS.register(eventBus);
    }
    //
    public static void removePotions()
    {
        //replace strength with crimson
        PotionBrewing.POTION_MIXES.removeIf(potionMix -> potionMix.ingredient.test(new ItemStack(Items.BLAZE_POWDER)));
        //remove mundane and thick
        PotionBrewing.POTION_MIXES.removeIf(potionMix -> potionMix.to.get() == Potions.MUNDANE);
        PotionBrewing.POTION_MIXES.removeIf(potionMix -> potionMix.to.get() == Potions.THICK);
        //shift weakness behind strength
        PotionBrewing.POTION_MIXES.removeIf(potionMix -> potionMix.from.get() == Potions.EMPTY
                && potionMix.ingredient.test(new ItemStack(Items.FERMENTED_SPIDER_EYE)));
        //remove strength 2 recipe
        PotionBrewing.POTION_MIXES.removeIf(potionMix -> potionMix.from.get() == Potions.STRENGTH
                && potionMix.ingredient.test(new ItemStack(Items.GLOWSTONE_DUST)));
        //shift regeneration behind healing
        PotionBrewing.POTION_MIXES.removeIf(potionMix -> potionMix.from.get() == Potions.AWKWARD
            && potionMix.ingredient.test(new ItemStack(Items.GHAST_TEAR)));
        //shift leaping behind swiftness
        PotionBrewing.POTION_MIXES.removeIf(potionMix -> potionMix.ingredient.test(new ItemStack(Items.RABBIT_FOOT)));
    }
    public static void addNormalPotions()
    {
        //
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(Potions.SWIFTNESS,Items.RABBIT_FOOT,Potions.LEAPING));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(Potions.STRONG_SWIFTNESS,Items.RABBIT_FOOT,Potions.STRONG_LEAPING));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(Potions.LONG_SWIFTNESS,Items.RABBIT_FOOT,Potions.LONG_LEAPING));

        //strength and weakness crisscross, follow in salt potions
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(Potions.AWKWARD,Items.CRIMSON_FUNGUS,Potions.STRENGTH));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(Potions.AWKWARD,Items.WARPED_FUNGUS,Potions.WEAKNESS));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(Potions.STRENGTH,Items.GLOWSTONE_DUST,ModPotions.STRONG_WEAKNESS.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(Potions.WEAKNESS,Items.GLOWSTONE_DUST,Potions.STRONG_STRENGTH));

        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(Potions.HEALING,Items.GHAST_TEAR,Potions.REGENERATION));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(Potions.STRONG_HEALING,Items.GHAST_TEAR,Potions.STRONG_REGENERATION));

        //add thick
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(Potions.WATER, Items.CALCITE,Potions.THICK));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(Potions.WATER, Items.BONE_BLOCK,Potions.THICK));
        //add mundane
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(Potions.WATER,Items.MAGMA_CREAM,Potions.MUNDANE));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(Potions.MUNDANE,Items.DRIED_KELP_BLOCK,ModPotions.ACRID_POTION.get()));
        //coal and charcoal finishes off gunpowder synthesis
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(ModPotions.ACRID_POTION.get(),Items.CHARCOAL,ModPotions.VOLATILE_POTION.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(ModPotions.VOLATILE_POTION.get(),Items.COAL,ModPotions.VOLATILE_POTION.get()));
        //bismuth
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(Potions.MUNDANE,ModItems.BISMUTH_ORE.get(),ModPotions.IRIDESCENT_POTION.get()));
    }
    public static void addSaltPotions()
    {
        //haste
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(Potions.STRONG_SWIFTNESS,MobEffects.DAMAGE_BOOST,ModPotions.HASTE.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(Potions.STRONG_STRENGTH,MobEffects.MOVEMENT_SPEED,ModPotions.HASTE.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(ModPotions.HASTE.get(),Items.GLOWSTONE_DUST,ModPotions.STRONG_HASTE.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(ModPotions.HASTE.get(),Items.REDSTONE,ModPotions.LONG_HASTE.get()));

        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(Potions.LONG_SWIFTNESS,MobEffects.MOVEMENT_SPEED,ModPotions.SWIFTNESS_INTERMEDIATE.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(Potions.STRONG_SWIFTNESS,MobEffects.MOVEMENT_SPEED,ModPotions.SWIFTNESS_INTERMEDIATE.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(ModPotions.SWIFTNESS_INTERMEDIATE.get(),Items.COCOA_BEANS,ModPotions.ENHANCED_SWIFTNESS.get()));

        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(Potions.LONG_SLOWNESS,MobEffects.MOVEMENT_SLOWDOWN,ModPotions.SLOWNESS_INTERMEDIATE.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(Potions.STRONG_SLOWNESS,MobEffects.MOVEMENT_SLOWDOWN,ModPotions.SLOWNESS_INTERMEDIATE.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(ModPotions.SLOWNESS_INTERMEDIATE.get(),Items.COBWEB,ModPotions.ENHANCED_SLOWNESS.get()));

        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(Potions.LONG_LEAPING,MobEffects.JUMP,ModPotions.LEAPING_INTERMEDIATE.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(Potions.STRONG_LEAPING,MobEffects.JUMP,ModPotions.LEAPING_INTERMEDIATE.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(ModPotions.LEAPING_INTERMEDIATE.get(),Items.WARPED_ROOTS,ModPotions.ENHANCED_LEAPING.get()));

        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(Potions.STRONG_STRENGTH,MobEffects.DAMAGE_BOOST,ModPotions.WEAKNESS_INTERMEDIATE.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(Potions.STRONG_STRENGTH,MobEffects.WEAKNESS,ModPotions.WEAKNESS_INTERMEDIATE.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(ModPotions.STRONG_WEAKNESS.get(),MobEffects.DAMAGE_BOOST,ModPotions.STRENGTH_INTERMEDIATE.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(ModPotions.STRONG_WEAKNESS.get(),MobEffects.WEAKNESS,ModPotions.STRENGTH_INTERMEDIATE.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(ModPotions.STRENGTH_INTERMEDIATE.get(), Items.FERN,ModPotions.ENHANCED_STRENGTH.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(ModPotions.WEAKNESS_INTERMEDIATE.get(), Items.DEAD_BUSH,ModPotions.ENHANCED_WEAKNESS.get()));

        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(Potions.STRONG_HEALING,MobEffects.HEAL,ModPotions.HEALING_INTERMEDIATE.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(ModPotions.HEALING_INTERMEDIATE.get(),Items.GLOW_LICHEN,ModPotions.ENHANCED_HEALING.get()));

        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(Potions.STRONG_HARMING,MobEffects.HARM,ModPotions.HARMING_INTERMEDIATE.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(ModPotions.HARMING_INTERMEDIATE.get(),Items.GLOW_LICHEN,ModPotions.ENHANCED_HARMING.get()));

        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(Potions.STRONG_POISON,MobEffects.POISON,ModPotions.POISON_INTERMEDIATE.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(Potions.LONG_POISON,MobEffects.POISON,ModPotions.POISON_INTERMEDIATE.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(ModPotions.POISON_INTERMEDIATE.get(),Items.PUFFERFISH,ModPotions.ENHANCED_POISON.get()));

        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(Potions.STRONG_REGENERATION,MobEffects.REGENERATION,ModPotions.REGENERATION_INTERMEDIATE.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(Potions.LONG_REGENERATION,MobEffects.REGENERATION,ModPotions.REGENERATION_INTERMEDIATE.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(ModPotions.REGENERATION_INTERMEDIATE.get(), Items.BRAIN_CORAL,ModPotions.ENHANCED_REGENERATION.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(ModPotions.REGENERATION_INTERMEDIATE.get(), Items.TUBE_CORAL,ModPotions.ENHANCED_REGENERATION.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(ModPotions.REGENERATION_INTERMEDIATE.get(), Items.FIRE_CORAL,ModPotions.ENHANCED_REGENERATION.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(ModPotions.REGENERATION_INTERMEDIATE.get(), Items.BUBBLE_CORAL,ModPotions.ENHANCED_REGENERATION.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(ModPotions.REGENERATION_INTERMEDIATE.get(), Items.HORN_CORAL,ModPotions.ENHANCED_REGENERATION.get()));

        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(Potions.LONG_FIRE_RESISTANCE,MobEffects.FIRE_RESISTANCE,ModPotions.FIRE_RESIST_INTERMEDIATE.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(ModPotions.FIRE_RESIST_INTERMEDIATE.get(),Items.BLUE_ICE,ModPotions.ENHANCED_FIRE_RESIST.get()));

        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(Potions.LONG_WATER_BREATHING,MobEffects.WATER_BREATHING,ModPotions.WATER_BREATHING_INTERMEDIATE.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(ModPotions.WATER_BREATHING_INTERMEDIATE.get(),Items.DRIED_KELP_BLOCK,ModPotions.ENHANCED_WATER_BREATHING.get()));

    }
}
