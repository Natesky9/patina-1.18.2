package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import com.natesky9.patina.Recipe.ConvertBrewingRecipe;
import com.natesky9.patina.Recipe.ItemBrewingRecipe;
import com.natesky9.patina.Recipe.OminousBrewingRecipe;
import com.natesky9.patina.Recipe.SaltBrewingRecipe;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;
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
            () -> new Potion("haste",new MobEffectInstance(MobEffects.DIG_SPEED,normal)));
    public static final RegistryObject<Potion> STRONG_HASTE = MOD_POTIONS.register("strong_haste",
            () -> new Potion("strong_haste",new MobEffectInstance(MobEffects.DIG_SPEED, shorter,1)));
    public static final RegistryObject<Potion> LONG_HASTE = MOD_POTIONS.register("long_haste",
            () -> new Potion("long_haste",new MobEffectInstance(MobEffects.DIG_SPEED,extended)));
    //region intermediates and enhanced
    public static final RegistryObject<Potion> SWIFTNESS_INTERMEDIATE = MOD_POTIONS.register("intermediate_swiftness",
            () -> new Potion("intermediate_swiftness"));
    public static final RegistryObject<Potion> ENHANCED_SWIFTNESS = MOD_POTIONS.register("enhanced_swiftness",
            () -> new Potion("enhanced_swiftness",new MobEffectInstance(MobEffects.MOVEMENT_SPEED,extended,2)));
    public static final RegistryObject<Potion> SLOWNESS_INTERMEDIATE = MOD_POTIONS.register("intermediate_slowness",
            () -> new Potion("intermediate_slowness"));
    public static final RegistryObject<Potion> ENHANCED_SLOWNESS = MOD_POTIONS.register("enhanced_slowness",
            () -> new Potion("enhanced_slowness",new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, brief,5)));
    public static final RegistryObject<Potion> LEAPING_INTERMEDIATE = MOD_POTIONS.register("intermediate_jump_boost",
            () -> new Potion("intermediate_jump_boost"));
    public static final RegistryObject<Potion> ENHANCED_LEAPING = MOD_POTIONS.register("enhanced_jump_boost",
            () -> new Potion("enhanced_jump_boost",new MobEffectInstance(MobEffects.JUMP,extended,2)));
    public static final RegistryObject<Potion> STRENGTH_INTERMEDIATE = MOD_POTIONS.register("intermediate_strength",
            () -> new Potion("intermediate_strength"));
    public static final RegistryObject<Potion> ENHANCED_STRENGTH = MOD_POTIONS.register("enhanced_strength",
            () -> new Potion("enhanced_strength",new MobEffectInstance(MobEffects.DAMAGE_BOOST,extended,2)));
    public static final RegistryObject<Potion> WEAKNESS_INTERMEDIATE = MOD_POTIONS.register("intermediate_weakness",
            () -> new Potion("intermediate_weakness"));
    public static final RegistryObject<Potion> ENHANCED_WEAKNESS = MOD_POTIONS.register("enhanced_weakness",
            () -> new Potion("enhanced_weakness",new MobEffectInstance(MobEffects.WEAKNESS,shorter,2)));
    public static final RegistryObject<Potion> HEALING_INTERMEDIATE = MOD_POTIONS.register("intermediate_healing",
            () -> new Potion("intermediate_healing"));
    public static final RegistryObject<Potion> ENHANCED_HEALING = MOD_POTIONS.register("enhanced_healing",
            () -> new Potion("enhanced_healing",new MobEffectInstance(MobEffects.HEAL,1,2)));
    public static final RegistryObject<Potion> HARMING_INTERMEDIATE = MOD_POTIONS.register("intermediate_harming",
            () -> new Potion("intermediate_harming"));
    public static final RegistryObject<Potion> ENHANCED_HARMING = MOD_POTIONS.register("enhanced_harming",
            () -> new Potion("enhanced_harming",new MobEffectInstance(MobEffects.HARM,1,2)));
    public static final RegistryObject<Potion> POISON_INTERMEDIATE = MOD_POTIONS.register("intermediate_poison",
            () -> new Potion("intermediate_poison"));
    public static final RegistryObject<Potion> ENHANCED_POISON = MOD_POTIONS.register("enhanced_poison",
            () -> new Potion("enhanced_poison",new MobEffectInstance(MobEffects.POISON,shorter,2)));
    public static final RegistryObject<Potion> REGENERATION_INTERMEDIATE = MOD_POTIONS.register("intermediate_regeneration",
            () -> new Potion("intermediate_regeneration"));
    public static final RegistryObject<Potion> ENHANCED_REGENERATION = MOD_POTIONS.register("enhanced_regeneration",
            () -> new Potion("enhanced_regeneration",new MobEffectInstance(MobEffects.REGENERATION,shorter,2)));
    //endregion
    public static final RegistryObject<Potion> FIRE_RESIST_INTERMEDIATE = MOD_POTIONS.register("intermediate_fire_resist",
            () -> new Potion("intermediate_fire_resist"));
    public static final RegistryObject<Potion> ENHANCED_FIRE_RESIST = MOD_POTIONS.register("enhanced_fire_resist",
            () -> new Potion("enhanced_fire_resist",new MobEffectInstance(MobEffects.REGENERATION,19200,1)));
    public static final RegistryObject<Potion> WATER_BREATHING_INTERMEDIATE = MOD_POTIONS.register("intermediate_water_breathing",
            () -> new Potion("intermediate_water_breathing"));
    public static final RegistryObject<Potion> ENHANCED_WATER_BREATHING = MOD_POTIONS.register("enhanced_water_breathing",
            () -> new Potion("enhanced_water_breathing",new MobEffectInstance(MobEffects.WATER_BREATHING,19200,1)));
    //didn't feel like finishing these
    //public static final RegistryObject<Potion> NIGHT_VISION_INTERMEDIATE = MOD_POTIONS.register("intermediate_night_vision", Potion::new);
    //public static final RegistryObject<Potion> INVISIBILITY_INTERMEDIATE = MOD_POTIONS.register("intermediate_invisibility", Potion::new);
    //public static final RegistryObject<Potion> SLOW_FALLING_INTERMEDIATE = MOD_POTIONS.register("intermediate_slow_falling", Potion::new);
    //mundane potions
    public static final RegistryObject<Potion> ACRID_POTION = MOD_POTIONS.register("acrid_potion",
            () -> new Potion("acrid_potion"));
    public static final RegistryObject<Potion> VOLATILE_POTION = MOD_POTIONS.register("volatile_potion",
            () -> new Potion("volatile_potion"));
    public static final RegistryObject<Potion> LUMINOUS_POTION = MOD_POTIONS.register("luminous_potion",
            () -> new Potion("luminous_potion",new MobEffectInstance(MobEffects.GLOWING,shorter)));
    public static final RegistryObject<Potion> LUMINOUS_LONG = MOD_POTIONS.register("extended_luminous_potion",
            () -> new Potion("luminous_potion",new MobEffectInstance(MobEffects.GLOWING,extended)));
    //bismuth blend
    public static final RegistryObject<Potion> IRIDESCENT_POTION = MOD_POTIONS.register("iridescent_potion",
            () -> new Potion("iridescent_potion"));
    public static final RegistryObject<Potion> KNOWLEDGE_POTION = MOD_POTIONS.register("knowledge_potion",
            () -> new Potion("knowledge_potion"));

    //region void potions
    public static final RegistryObject<Potion> VOID_HEALING = MOD_POTIONS.register("void_healing",
            () -> new Potion("void_healing", new MobEffectInstance(MobEffects.HEAL,1,3),
                    new MobEffectInstance(MobEffects.HUNGER,300)));
    public static final RegistryObject<Potion> VOID_STRENGTH = MOD_POTIONS.register("void_strength",
            () -> new Potion("void_strength", new MobEffectInstance(MobEffects.DAMAGE_BOOST,shorter,3),
                    new MobEffectInstance(MobEffects.HUNGER,300)));
    public static final RegistryObject<Potion> VOID_SWIFTNESS = MOD_POTIONS.register("void_swiftness",
            () -> new Potion("void_swiftness", new MobEffectInstance(MobEffects.MOVEMENT_SPEED,shorter,3),
                    new MobEffectInstance(MobEffects.HUNGER,300)));
    public static final RegistryObject<Potion> VOID_HARMING = MOD_POTIONS.register("void_harming",
            () -> new Potion("void_harming", new MobEffectInstance(MobEffects.HARM,shorter,3),
                    new MobEffectInstance(MobEffects.HUNGER,300)));
    //endregion
    //
    public static void register(IEventBus eventBus)
    {
        MOD_POTIONS.register(eventBus);
    }
    //
    public static void removePotions(BrewingRecipeRegisterEvent event)
    {
        event.getBuilder().potionMixes.removeIf(potionMix -> potionMix.ingredient().test(new ItemStack(Items.BLAZE_POWDER)));
    //    //replace strength with crimson
    //    PotionBrewing.POTION_MIXES.removeIf();
    //    //remove mundane and thick
    //    PotionBrewing.POTION_MIXES.removeIf(potionMix -> potionMix.to.get() == Potions.MUNDANE);
    //    PotionBrewing.POTION_MIXES.removeIf(potionMix -> potionMix.to.get() == Potions.THICK);
        //shift weakness behind strength
        event.getBuilder().potionMixes.removeIf(potionMix -> potionMix.from() == Potions.WATER
                && potionMix.ingredient().test(new ItemStack(Items.FERMENTED_SPIDER_EYE)));
    //    //remove strength 2 recipe
        event.getBuilder().potionMixes.removeIf(potionMix -> potionMix.from() == Potions.STRENGTH
                && potionMix.ingredient().test(new ItemStack(Items.GLOWSTONE_DUST)));
        //shift regeneration behind healing
        event.getBuilder().potionMixes.removeIf(potionMix -> potionMix.from() == Potions.AWKWARD
            && potionMix.ingredient().test(new ItemStack(Items.GHAST_TEAR)));
        //shift leaping behind swiftness
        event.getBuilder().potionMixes.removeIf(potionMix -> potionMix.ingredient().test(new ItemStack(Items.RABBIT_FOOT)));
        event.getBuilder().potionMixes.removeIf(potionMix -> potionMix.from().is(getHolder(KNOWLEDGE_POTION))
                && potionMix.ingredient().equals(Ingredient.of(Items.GUNPOWDER)));
    }
    public static void addNormalPotions(BrewingRecipeRegisterEvent event)
    {
        //swiftness + rabbit foot makes leaping
        event.addRecipe(new ItemBrewingRecipe(Potions.SWIFTNESS, Items.RABBIT_FOOT, Potions.LEAPING));
        event.addRecipe(new ItemBrewingRecipe(Potions.STRONG_SWIFTNESS,Items.RABBIT_FOOT,Potions.STRONG_LEAPING));
        event.addRecipe(new ItemBrewingRecipe(Potions.LONG_SWIFTNESS,Items.RABBIT_FOOT,Potions.LONG_LEAPING));
//
        //strength and weakness crisscross, follow in salt potions
        event.addRecipe(new ItemBrewingRecipe(Potions.AWKWARD,Items.CRIMSON_FUNGUS,Potions.STRENGTH));
        event.addRecipe(new ItemBrewingRecipe(Potions.AWKWARD,Items.WARPED_FUNGUS,Potions.WEAKNESS));
        event.addRecipe(new ItemBrewingRecipe(Potions.STRENGTH,Items.GLOWSTONE_DUST,getHolder(STRONG_WEAKNESS)));
        event.addRecipe(new ItemBrewingRecipe(Potions.WEAKNESS,Items.GLOWSTONE_DUST,Potions.STRONG_STRENGTH));

        event.addRecipe(new ItemBrewingRecipe(Potions.HEALING,Items.GHAST_TEAR,Potions.REGENERATION));
        event.addRecipe(new ItemBrewingRecipe(Potions.STRONG_HEALING,Items.GHAST_TEAR,Potions.STRONG_REGENERATION));

        //
        event.addRecipe(new ItemBrewingRecipe(Potions.THICK, Items.CALCITE,Potions.THICK));
        event.addRecipe(new ItemBrewingRecipe(Potions.THICK, Items.BONE_BLOCK,Potions.THICK));
        //gunpowder synthesis
        event.addRecipe(new ItemBrewingRecipe(Potions.THICK,Items.DRIED_KELP_BLOCK,getHolder(ACRID_POTION)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(ACRID_POTION),Items.CHARCOAL,getHolder(VOLATILE_POTION)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(ACRID_POTION),Items.COAL,getHolder(VOLATILE_POTION)));
        //bismuth alchemy
        event.addRecipe(new ItemBrewingRecipe(getHolder(ACRID_POTION),ModItems.BISMUTH_ORE.get(),getHolder(IRIDESCENT_POTION)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(ACRID_POTION), Items.EMERALD,getHolder(KNOWLEDGE_POTION)));
        //maybe this can just be replaced by crystalizer?
        event.addRecipe(new ConvertBrewingRecipe(KNOWLEDGE_POTION.get(),Items.GUNPOWDER,Items.EXPERIENCE_BOTTLE));

        ItemStack ominous_1 = new ItemStack(Items.OMINOUS_BOTTLE);
        ominous_1.set(DataComponents.OMINOUS_BOTTLE_AMPLIFIER,0);
        ItemStack ominous_2 = new ItemStack(Items.OMINOUS_BOTTLE);
        ominous_2.set(DataComponents.OMINOUS_BOTTLE_AMPLIFIER,1);
        ItemStack ominous_3 = new ItemStack(Items.OMINOUS_BOTTLE);
        ominous_3.set(DataComponents.OMINOUS_BOTTLE_AMPLIFIER,2);
        ItemStack ominous_4 = new ItemStack(Items.OMINOUS_BOTTLE);
        ominous_4.set(DataComponents.OMINOUS_BOTTLE_AMPLIFIER,3);
        ItemStack ominous_5 = new ItemStack(Items.OMINOUS_BOTTLE);
        ominous_5.set(DataComponents.OMINOUS_BOTTLE_AMPLIFIER,4);
        //ominous
        event.addRecipe(new OminousBrewingRecipe(Items.EXPERIENCE_BOTTLE.getDefaultInstance(),Items.FERMENTED_SPIDER_EYE,ominous_1));
        event.addRecipe(new OminousBrewingRecipe(ominous_1,Items.SUSPICIOUS_STEW,ominous_2));
        event.addRecipe(new OminousBrewingRecipe(ominous_2,Items.POISONOUS_POTATO,ominous_3));
        event.addRecipe(new OminousBrewingRecipe(ominous_3,Items.AMETHYST_SHARD,ominous_4));
        event.addRecipe(new OminousBrewingRecipe(ominous_4,Items.WITHER_ROSE,ominous_5));
        //glowstone
        event.addRecipe(new ItemBrewingRecipe(Potions.THICK,Items.GLOW_BERRIES,getHolder(LUMINOUS_POTION)));
        event.addRecipe(new ItemBrewingRecipe(Potions.THICK,Items.GLOW_INK_SAC,getHolder(LUMINOUS_POTION)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(LUMINOUS_POTION),Items.REDSTONE,getHolder(LUMINOUS_LONG)));
    }
    public static void addSaltPotions(BrewingRecipeRegisterEvent event)
    {
        event.addRecipe(new SaltBrewingRecipe(Potions.STRONG_SWIFTNESS,MobEffects.DAMAGE_BOOST.get(),getHolder(HASTE)));
        event.addRecipe(new SaltBrewingRecipe(Potions.STRONG_STRENGTH,MobEffects.MOVEMENT_SPEED.get(),getHolder(HASTE)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(HASTE),Items.GLOWSTONE_DUST,getHolder(STRONG_HASTE)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(HASTE),Items.REDSTONE,getHolder(LONG_HASTE)));

        event.addRecipe(new SaltBrewingRecipe(Potions.LONG_SWIFTNESS,MobEffects.MOVEMENT_SPEED.get(),getHolder(SWIFTNESS_INTERMEDIATE)));
        event.addRecipe(new SaltBrewingRecipe(Potions.STRONG_SWIFTNESS,MobEffects.MOVEMENT_SPEED.get(),getHolder(SWIFTNESS_INTERMEDIATE)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(SWIFTNESS_INTERMEDIATE),Items.COCOA_BEANS,getHolder(ENHANCED_SWIFTNESS)));

        event.addRecipe(new SaltBrewingRecipe(Potions.LONG_SLOWNESS,MobEffects.MOVEMENT_SLOWDOWN.get(),getHolder(SLOWNESS_INTERMEDIATE)));
        event.addRecipe(new SaltBrewingRecipe(Potions.STRONG_SLOWNESS,MobEffects.MOVEMENT_SLOWDOWN.get(),getHolder(SLOWNESS_INTERMEDIATE)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(SLOWNESS_INTERMEDIATE),Items.COBWEB,getHolder(ENHANCED_SLOWNESS)));

        event.addRecipe(new SaltBrewingRecipe(Potions.LONG_LEAPING,MobEffects.JUMP.get(),getHolder(LEAPING_INTERMEDIATE)));
        event.addRecipe(new SaltBrewingRecipe(Potions.STRONG_LEAPING,MobEffects.JUMP.get(),getHolder(LEAPING_INTERMEDIATE)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(LEAPING_INTERMEDIATE),Items.WARPED_ROOTS,getHolder(ENHANCED_LEAPING)));

        event.addRecipe(new SaltBrewingRecipe(Potions.STRONG_STRENGTH,MobEffects.DAMAGE_BOOST.get(),getHolder(WEAKNESS_INTERMEDIATE)));
        event.addRecipe(new SaltBrewingRecipe(Potions.STRONG_STRENGTH,MobEffects.WEAKNESS.get(),getHolder(WEAKNESS_INTERMEDIATE)));
        event.addRecipe(new SaltBrewingRecipe(getHolder(STRONG_WEAKNESS),MobEffects.DAMAGE_BOOST.get(),getHolder(STRENGTH_INTERMEDIATE)));
        event.addRecipe(new SaltBrewingRecipe(getHolder(STRONG_WEAKNESS),MobEffects.WEAKNESS.get(),getHolder(STRENGTH_INTERMEDIATE)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(STRENGTH_INTERMEDIATE), Items.FERN,getHolder(ENHANCED_STRENGTH)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(WEAKNESS_INTERMEDIATE), Items.DEAD_BUSH,getHolder(ENHANCED_WEAKNESS)));

        event.addRecipe(new SaltBrewingRecipe(Potions.STRONG_HEALING,MobEffects.HEAL.get(),getHolder(HEALING_INTERMEDIATE)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(HEALING_INTERMEDIATE),Items.GLOW_LICHEN,getHolder(ENHANCED_HEALING)));
//
        event.addRecipe(new SaltBrewingRecipe(Potions.STRONG_HARMING,MobEffects.HARM.get(),getHolder(HARMING_INTERMEDIATE)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(HARMING_INTERMEDIATE),Items.GLOW_LICHEN,getHolder(ENHANCED_HARMING)));
//
        event.addRecipe(new SaltBrewingRecipe(Potions.STRONG_POISON,MobEffects.POISON.get(),getHolder(POISON_INTERMEDIATE)));
        event.addRecipe(new SaltBrewingRecipe(Potions.LONG_POISON,MobEffects.POISON.get(),getHolder(POISON_INTERMEDIATE)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(POISON_INTERMEDIATE),Items.PUFFERFISH,getHolder(ENHANCED_POISON)));
//
        event.addRecipe(new SaltBrewingRecipe(Potions.STRONG_REGENERATION,MobEffects.REGENERATION.get(),getHolder(REGENERATION_INTERMEDIATE)));
        event.addRecipe(new SaltBrewingRecipe(Potions.LONG_REGENERATION,MobEffects.REGENERATION.get(),getHolder(REGENERATION_INTERMEDIATE)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(REGENERATION_INTERMEDIATE), Items.BRAIN_CORAL,getHolder(ENHANCED_REGENERATION)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(REGENERATION_INTERMEDIATE), Items.TUBE_CORAL,getHolder(ENHANCED_REGENERATION)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(REGENERATION_INTERMEDIATE), Items.FIRE_CORAL,getHolder(ENHANCED_REGENERATION)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(REGENERATION_INTERMEDIATE), Items.BUBBLE_CORAL,getHolder(ENHANCED_REGENERATION)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(REGENERATION_INTERMEDIATE), Items.HORN_CORAL,getHolder(ENHANCED_REGENERATION)));

        event.addRecipe(new SaltBrewingRecipe(Potions.LONG_FIRE_RESISTANCE,MobEffects.FIRE_RESISTANCE.get(),getHolder(FIRE_RESIST_INTERMEDIATE)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(FIRE_RESIST_INTERMEDIATE),Items.BLUE_ICE,getHolder(ENHANCED_FIRE_RESIST)));

        event.addRecipe(new SaltBrewingRecipe(Potions.LONG_WATER_BREATHING,MobEffects.WATER_BREATHING.get(),getHolder(WATER_BREATHING_INTERMEDIATE)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(WATER_BREATHING_INTERMEDIATE),Items.DRIED_KELP_BLOCK,getHolder(ENHANCED_WATER_BREATHING)));
    }
    public static void addVoidPotions(BrewingRecipeRegisterEvent event)
    {
        event.addRecipe(new ItemBrewingRecipe(getHolder(ENHANCED_HEALING), ModItems.VOID_SALT.get(), getHolder(VOID_HEALING)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(ENHANCED_STRENGTH), ModItems.VOID_SALT.get(), getHolder(VOID_STRENGTH)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(ENHANCED_SWIFTNESS), ModItems.VOID_SALT.get(), getHolder(VOID_SWIFTNESS)));
        event.addRecipe(new ItemBrewingRecipe(getHolder(ENHANCED_HARMING), ModItems.VOID_SALT.get(), getHolder(VOID_HARMING)));
    }
    public static <T> Holder<T> getHolder(RegistryObject<T> pot)
    {
        return pot.getHolder().get();
    }
}
