package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import com.natesky9.recipe.SaltBrewingRecipe;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions
{
    public static final DeferredRegister<Potion> MOD_POTIONS
            = DeferredRegister.create(ForgeRegistries.POTIONS, Patina.MOD_ID);
    //
    public static final RegistryObject<Potion> IMPROVED_NIGHT_VISION = MOD_POTIONS.register("improved_night_vision",
            () -> new Potion("night_vision",new MobEffectInstance(MobEffects.NIGHT_VISION,3600,2)));
    public static final RegistryObject<Potion> IMPROVED_INVISIBILITY = MOD_POTIONS.register("improved_invisibility",
            () -> new Potion("invisibility",new MobEffectInstance(MobEffects.INVISIBILITY,3600,2)));
    public static final RegistryObject<Potion> IMPROVED_LEAPING = MOD_POTIONS.register("improved_leaping",
            () -> new Potion("leaping",new MobEffectInstance(MobEffects.JUMP,3600,2)));
    public static final RegistryObject<Potion> IMPROVED_FIRE_RESISTANCE = MOD_POTIONS.register("improved_fire_resistance",
            () -> new Potion("fire_resistance",new MobEffectInstance(MobEffects.FIRE_RESISTANCE,3600,2)));//fire resistance does not have a potency,extended is 9600
    public static final RegistryObject<Potion> IMPROVED_SWIFTNESS = MOD_POTIONS.register("improved_swiftness",
            () -> new Potion("swiftness",new MobEffectInstance(MobEffects.MOVEMENT_SPEED,3600,2)));
    public static final RegistryObject<Potion> IMPROVED_SLOWNESS = MOD_POTIONS.register("improved_slowness",
            () -> new Potion("slowness",new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,1800,3)));//slowness is a bit wierd, vanilla jumps from 1 to 4
    //can't add these two yet because they have compound effects
    //public static final RegistryObject<Potion> IMPROVED_TURTLE_MASTER = MOD_POTIONS.register("improved_turtle_master",
    //        () -> new Potion("turtle_master",new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,800,3),
    //                new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,800,4)));//turtle master is by far the strangest one here...
    //public static final RegistryObject<Potion> TORTOISE_MASTER = MOD_POTIONS.register("improved_turtle_master",
    //        () -> new Potion("turtle_master",new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,400,2),
    //                new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,800,3)));//tortoise, less damage resistance but faster
    public static final RegistryObject<Potion> IMPROVED_WATER_BREATHING = MOD_POTIONS.register("improved_water_breathing",
            () -> new Potion("water_breathing",new MobEffectInstance(MobEffects.WATER_BREATHING,3600,3)));//water breathing does not have a potency
    public static final RegistryObject<Potion> IMPROVED_HEALING = MOD_POTIONS.register("improved_healing",
            () -> new Potion("healing",new MobEffectInstance(MobEffects.HEAL,1,2)));
    public static final RegistryObject<Potion> IMPROVED_HARMING = MOD_POTIONS.register("improved_harming",
            () -> new Potion("harming",new MobEffectInstance(MobEffects.HARM,1,2)));//healing and harming are instant?
    public static final RegistryObject<Potion> IMPROVED_POISON = MOD_POTIONS.register("improved_poison",
            () -> new Potion("poison",new MobEffectInstance(MobEffects.POISON,900,2)));
    public static final RegistryObject<Potion> IMPROVED_REGENERATION = MOD_POTIONS.register("improved_regeneration",
            () -> new Potion("regeneration",new MobEffectInstance(MobEffects.REGENERATION,900,2)));
    public static final RegistryObject<Potion> IMPROVED_STRENGTH = MOD_POTIONS.register("improved_strength",
            () -> new Potion("strength",new MobEffectInstance(MobEffects.DAMAGE_BOOST,3600,2)));
    public static final RegistryObject<Potion> IMPROVED_WEAKNESS = MOD_POTIONS.register("improved_weakness",
            () -> new Potion("weakness",new MobEffectInstance(MobEffects.WEAKNESS,1800,2)));
    public static final RegistryObject<Potion> IMPROVED_SLOW_FALLING = MOD_POTIONS.register("improved_slow_falling",
            () -> new Potion("slow_falling",new MobEffectInstance(MobEffects.SLOW_FALLING)));
    //my potions
    public static final RegistryObject<Potion> HASTE = MOD_POTIONS.register("haste",
            () -> new Potion("haste",new MobEffectInstance(MobEffects.DIG_SPEED,3600)));
    public static final RegistryObject<Potion> LONG_HASTE = MOD_POTIONS.register("long_haste",
            () -> new Potion("haste",new MobEffectInstance(MobEffects.DIG_SPEED,9600)));
    public static final RegistryObject<Potion> STRONG_HASTE = MOD_POTIONS.register("strong_haste",
            () -> new Potion("haste",new MobEffectInstance(MobEffects.DIG_SPEED,2400,1)));
    public static final RegistryObject<Potion> IMPROVED_HASTE = MOD_POTIONS.register("improved_haste",
            () -> new Potion("haste",new MobEffectInstance(MobEffects.DIG_SPEED,3600,2)));
    //
    public static void register(IEventBus eventBus){MOD_POTIONS.register(eventBus);}
    //
    public static void addSaltPotions()
    {
        //potion salt
        //extending existing potions
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(
                Potions.LONG_NIGHT_VISION,
                MobEffects.NIGHT_VISION,
                ModPotions.IMPROVED_NIGHT_VISION.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(
                Potions.LONG_INVISIBILITY,
                MobEffects.INVISIBILITY,
                ModPotions.IMPROVED_INVISIBILITY.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(
                Potions.LONG_LEAPING,
                MobEffects.JUMP,
                ModPotions.IMPROVED_LEAPING.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(
                Potions.STRONG_LEAPING,
                MobEffects.JUMP,
                ModPotions.IMPROVED_LEAPING.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(
                Potions.LONG_FIRE_RESISTANCE,
                MobEffects.FIRE_RESISTANCE,
                ModPotions.IMPROVED_FIRE_RESISTANCE.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(
                Potions.LONG_SWIFTNESS,
                MobEffects.MOVEMENT_SPEED,
                ModPotions.IMPROVED_SWIFTNESS.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(
                Potions.STRONG_STRENGTH,
                MobEffects.MOVEMENT_SPEED,
                ModPotions.IMPROVED_SWIFTNESS.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(
                Potions.LONG_SLOWNESS,
                MobEffects.MOVEMENT_SLOWDOWN,
                ModPotions.IMPROVED_SLOWNESS.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(
                Potions.STRONG_SLOWNESS,
                MobEffects.MOVEMENT_SLOWDOWN,
                ModPotions.IMPROVED_SLOWNESS.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(
                Potions.LONG_WATER_BREATHING,
                MobEffects.WATER_BREATHING,
                ModPotions.IMPROVED_WATER_BREATHING.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(
                Potions.STRONG_HEALING,
                MobEffects.HEAL,
                ModPotions.IMPROVED_HEALING.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(
                Potions.STRONG_HARMING,
                MobEffects.HARM,
                ModPotions.IMPROVED_HARMING.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(
                Potions.LONG_POISON,
                MobEffects.POISON,
                ModPotions.IMPROVED_POISON.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(
                Potions.STRONG_POISON,
                MobEffects.POISON,
                ModPotions.IMPROVED_POISON.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(
                Potions.LONG_REGENERATION,
                MobEffects.REGENERATION,
                ModPotions.IMPROVED_REGENERATION.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(
                Potions.STRONG_REGENERATION,
                MobEffects.REGENERATION,
                ModPotions.IMPROVED_REGENERATION.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(
                Potions.LONG_STRENGTH,
                MobEffects.DAMAGE_BOOST,
                ModPotions.IMPROVED_STRENGTH.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(
                Potions.STRONG_STRENGTH,
                MobEffects.DAMAGE_BOOST,
                ModPotions.IMPROVED_STRENGTH.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(
                Potions.LONG_WEAKNESS,
                MobEffects.WEAKNESS,
                ModPotions.IMPROVED_WEAKNESS.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(
                Potions.WEAKNESS,
                MobEffects.WEAKNESS,
                ModPotions.IMPROVED_WEAKNESS.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(
                Potions.LONG_SLOW_FALLING,
                MobEffects.SLOW_FALLING,
                ModPotions.IMPROVED_SLOW_FALLING.get()));
    }
}
