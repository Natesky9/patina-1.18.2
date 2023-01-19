package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import com.natesky9.patina.recipe.ItemBrewingRecipe;
import com.natesky9.patina.recipe.SaltBrewingRecipe;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
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
            () -> new Potion("night_vision",new MobEffectInstance(MobEffects.NIGHT_VISION,9600,1)));
    public static final RegistryObject<Potion> IMPROVED_INVISIBILITY = MOD_POTIONS.register("improved_invisibility",
            () -> new Potion("invisibility",new MobEffectInstance(MobEffects.INVISIBILITY,9600,1)));
    public static final RegistryObject<Potion> IMPROVED_LEAPING = MOD_POTIONS.register("improved_leaping",
            () -> new Potion("leaping",new MobEffectInstance(MobEffects.JUMP,3600,2)));
    public static final RegistryObject<Potion> IMPROVED_FIRE_RESISTANCE = MOD_POTIONS.register("improved_fire_resistance",
            () -> new Potion("fire_resistance",new MobEffectInstance(MobEffects.FIRE_RESISTANCE,9600,1)));//fire resistance does not have a potency,extended is 9600
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
            () -> new Potion("water_breathing",new MobEffectInstance(MobEffects.WATER_BREATHING,9600,1)));//water breathing does not have a potency
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
            () -> new Potion("slow_falling",new MobEffectInstance(MobEffects.SLOW_FALLING,4800,2)));
    //my potions
    public static final RegistryObject<Potion> HASTE = MOD_POTIONS.register("haste",
            () -> new Potion("haste",new MobEffectInstance(MobEffects.DIG_SPEED,3600)));
    public static final RegistryObject<Potion> LONG_HASTE = MOD_POTIONS.register("long_haste",
            () -> new Potion("haste",new MobEffectInstance(MobEffects.DIG_SPEED,9600)));
    public static final RegistryObject<Potion> STRONG_HASTE = MOD_POTIONS.register("strong_haste",
            () -> new Potion("haste",new MobEffectInstance(MobEffects.DIG_SPEED,2400,1)));
    public static final RegistryObject<Potion> IMPROVED_HASTE = MOD_POTIONS.register("improved_haste",
            () -> new Potion("haste",new MobEffectInstance(MobEffects.DIG_SPEED,3600,2)));
    public static final RegistryObject<Potion> RESISTANCE = MOD_POTIONS.register("resistance",
            () -> new Potion("resistance",new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,3600)));
    public static final RegistryObject<Potion> LONG_RESISTANCE = MOD_POTIONS.register("long_resistance",
            () -> new Potion("resistance",new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,3600)));
    public static final RegistryObject<Potion> STRONG_RESISTANCE = MOD_POTIONS.register("strong_resistance",
            () -> new Potion("resistance",new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,3600,1)));
    public static final RegistryObject<Potion> IMPROVED_RESISTANCE = MOD_POTIONS.register("improved_resistance",
            () -> new Potion("resistance",new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,3600,2)));


    public static final RegistryObject<Potion> ENHANCED_HASTE = MOD_POTIONS.register("enhanced_haste",
            () -> new Potion("haste",new MobEffectInstance(MobEffects.DIG_SPEED,3600,3),
                    new MobEffectInstance(MobEffects.WITHER,4)));
    public static final RegistryObject<Potion> ENHANCED_RESISTANCE = MOD_POTIONS.register("enhanced_resistance",
            () -> new Potion("resistance",new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,3600,3)
                    ,new MobEffectInstance(MobEffects.WITHER,4)));


    //void salt potions
    public static final RegistryObject<Potion> ENHANCED_NIGHT_VISION = MOD_POTIONS.register("enhanced_night_vision",
            () -> new Potion("night_vision",new MobEffectInstance(MobEffects.NIGHT_VISION,9600,2)
                    ,new MobEffectInstance(MobEffects.WITHER,4)));
    public static final RegistryObject<Potion> ENHANCED_INVISIBILITY = MOD_POTIONS.register("enhanced_invisibility",
            () -> new Potion("invisibility",new MobEffectInstance(MobEffects.INVISIBILITY,9600,2)
                    ,new MobEffectInstance(MobEffects.WITHER,4)));
    public static final RegistryObject<Potion> ENHANCED_LEAPING = MOD_POTIONS.register("enhanced_leaping",
            () -> new Potion("leaping",new MobEffectInstance(MobEffects.JUMP,3600,3)
                    ,new MobEffectInstance(MobEffects.WITHER,4)));
    public static final RegistryObject<Potion> ENHANCED_FIRE_RESISTANCE = MOD_POTIONS.register("enhanced_fire_resistance",
            () -> new Potion("fire_resistance",new MobEffectInstance(MobEffects.FIRE_RESISTANCE,9600,2)
                    ,new MobEffectInstance(MobEffects.WITHER,4)));
    public static final RegistryObject<Potion> ENHANCED_SWIFTNESS = MOD_POTIONS.register("enhanced_swiftness",
            () -> new Potion("swiftness",new MobEffectInstance(MobEffects.MOVEMENT_SPEED,3600,3)
                    ,new MobEffectInstance(MobEffects.WITHER,4)));
    public static final RegistryObject<Potion> ENHANCED_SLOWNESS = MOD_POTIONS.register("enhanced_slowness",
            () -> new Potion("slowness",new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,3600,3)
                    ,new MobEffectInstance(MobEffects.WITHER,4)));
    public static final RegistryObject<Potion> ENHANCED_WATER_BREATHING = MOD_POTIONS.register("enhanced_water_breathing",
            () -> new Potion("water_breathing",new MobEffectInstance(MobEffects.WATER_BREATHING,9600,2)
                    ,new MobEffectInstance(MobEffects.WITHER,4)));
    public static final RegistryObject<Potion> ENHANCED_HEALING = MOD_POTIONS.register("enhanced_healing",
            () -> new Potion("healing",new MobEffectInstance(MobEffects.HEAL,1,3)
                    ,new MobEffectInstance(MobEffects.WITHER,4)));
    public static final RegistryObject<Potion> ENHANCED_HARMING = MOD_POTIONS.register("enhanced_harming",
            () -> new Potion("harming",new MobEffectInstance(MobEffects.HARM,1,3)
                    ,new MobEffectInstance(MobEffects.WITHER,4)));
    public static final RegistryObject<Potion> ENHANCED_POISON = MOD_POTIONS.register("enhanced_poison",
            () -> new Potion("poison",new MobEffectInstance(MobEffects.POISON,900,3)
                    ,new MobEffectInstance(MobEffects.WITHER,4)));
    public static final RegistryObject<Potion> ENHANCED_REGENERATION = MOD_POTIONS.register("enhanced_regeneration",
            () -> new Potion("regeneration",new MobEffectInstance(MobEffects.REGENERATION,1800,3)
                    ,new MobEffectInstance(MobEffects.WITHER,4)));
    public static final RegistryObject<Potion> ENHANCED_STRENGTH = MOD_POTIONS.register("enhanced_strength",
            () -> new Potion("strength",new MobEffectInstance(MobEffects.DAMAGE_BOOST,3600,3)
                    ,new MobEffectInstance(MobEffects.WITHER,4)));
    public static final RegistryObject<Potion> ENHANCED_WEAKNESS = MOD_POTIONS.register("enhanced_weakness",
            () -> new Potion("weakness",new MobEffectInstance(MobEffects.WEAKNESS,1800,3)
                    ,new MobEffectInstance(MobEffects.WITHER,4)));
    public static final RegistryObject<Potion> ENHANCED_SLOW_FALLING = MOD_POTIONS.register("enhanced_slow_falling",
            () -> new Potion("slow_falling",new MobEffectInstance(MobEffects.SLOW_FALLING,4800,3)
                    ,new MobEffectInstance(MobEffects.WITHER,4)));


    //
    public static void register(IEventBus eventBus){MOD_POTIONS.register(eventBus);}
    //
    public static void addNormalPotions()
    {
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(
                Potions.AWKWARD,
                Items.CRIMSON_FUNGUS,
                Potions.STRENGTH));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(
                Potions.AWKWARD,
                Items.WARPED_FUNGUS,
                ModPotions.RESISTANCE.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(
                Potions.AWKWARD,
                Items.AMETHYST_CLUSTER,
                ModPotions.HASTE.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(
                ModPotions.HASTE.get(),
                Items.REDSTONE,
                ModPotions.LONG_HASTE.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(
                ModPotions.HASTE.get(),
                Items.GLOWSTONE_DUST,
                ModPotions.STRONG_HASTE.get()));


    }
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
                Potions.STRONG_SWIFTNESS,
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
                Potions.LONG_SLOW_FALLING,
                MobEffects.SLOW_FALLING,
                ModPotions.IMPROVED_SLOW_FALLING.get()));


        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(
                ModPotions.LONG_HASTE.get(),
                MobEffects.DIG_SPEED,
                ModPotions.IMPROVED_HASTE.get()));
        BrewingRecipeRegistry.addRecipe(new SaltBrewingRecipe(
                ModPotions.STRONG_HASTE.get(),
                MobEffects.DIG_SPEED,
                ModPotions.IMPROVED_HASTE.get()));
    }
    public static void addVoidPotions()
    {
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(
                ModPotions.IMPROVED_NIGHT_VISION.get(),
                ModItems.VOID_SALT.get(),
                ModPotions.ENHANCED_NIGHT_VISION.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(
                ModPotions.IMPROVED_INVISIBILITY.get(),
                ModItems.VOID_SALT.get(),
                ModPotions.ENHANCED_INVISIBILITY.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(
                ModPotions.IMPROVED_LEAPING.get(),
                ModItems.VOID_SALT.get(),
                ModPotions.ENHANCED_LEAPING.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(
                ModPotions.IMPROVED_FIRE_RESISTANCE.get(),
                ModItems.VOID_SALT.get(),
                ModPotions.ENHANCED_FIRE_RESISTANCE.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(
                ModPotions.IMPROVED_SWIFTNESS.get(),
                ModItems.VOID_SALT.get(),
                ModPotions.ENHANCED_SWIFTNESS.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(
                ModPotions.IMPROVED_SLOWNESS.get(),
                ModItems.VOID_SALT.get(),
                ModPotions.ENHANCED_SLOWNESS.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(
                ModPotions.IMPROVED_WATER_BREATHING.get(),
                ModItems.VOID_SALT.get(),
                ModPotions.ENHANCED_WATER_BREATHING.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(
                ModPotions.IMPROVED_HEALING.get(),
                ModItems.VOID_SALT.get(),
                ModPotions.ENHANCED_HEALING.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(
                ModPotions.IMPROVED_HARMING.get(),
                ModItems.VOID_SALT.get(),
                ModPotions.ENHANCED_HARMING.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(
                ModPotions.IMPROVED_POISON.get(),
                ModItems.VOID_SALT.get(),
                ModPotions.ENHANCED_POISON.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(
                ModPotions.IMPROVED_REGENERATION.get(),
                ModItems.VOID_SALT.get(),
                ModPotions.ENHANCED_REGENERATION.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(
                ModPotions.IMPROVED_STRENGTH.get(),
                ModItems.VOID_SALT.get(),
                ModPotions.ENHANCED_STRENGTH.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(
                ModPotions.IMPROVED_WEAKNESS.get(),
                ModItems.VOID_SALT.get(),
                ModPotions.ENHANCED_WEAKNESS.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(
                ModPotions.IMPROVED_SLOW_FALLING.get(),
                ModItems.VOID_SALT.get(),
                ModPotions.ENHANCED_SLOW_FALLING.get()));
        BrewingRecipeRegistry.addRecipe(new ItemBrewingRecipe(
                ModPotions.IMPROVED_HASTE.get(),
                ModItems.VOID_SALT.get(),
                ModPotions.ENHANCED_HASTE.get()));
    }
}
