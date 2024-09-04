package com.natesky9.datagen;

import com.natesky9.patina.Patina;
import com.natesky9.patina.init.ModItems;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;

public class ModAdvancementGenerator implements ForgeAdvancementProvider.AdvancementGenerator {
    static Criterion<RecipeCraftedTrigger.TriggerInstance> smelt_copper = RecipeCraftedTrigger.TriggerInstance
            .craftedItem(ResourceLocation.withDefaultNamespace("item/copper_ingot"));
    static Criterion<BrewedPotionTrigger.TriggerInstance> brewed = BrewedPotionTrigger.TriggerInstance.brewedPotion();
    static Criterion<FilledBucketTrigger.TriggerInstance> foundryCriteria = FilledBucketTrigger.TriggerInstance.filledBucket(ItemPredicate.Builder.item().of(Items.LAVA_BUCKET));
    static Criterion<InventoryChangeTrigger.TriggerInstance> textilerCriteria = InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(Items.WHITE_WOOL).build());
    static Criterion<KilledTrigger.TriggerInstance> killed_dragon = KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityType.ENDER_DRAGON));
    static Criterion<KilledTrigger.TriggerInstance> killed_phantom = KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityType.PHANTOM));

    static Criterion<ImpossibleTrigger.TriggerInstance> impossible_criteria = CriteriaTriggers.IMPOSSIBLE.createCriterion(new ImpossibleTrigger.TriggerInstance());

    //AdvancementType, toast, announce, hidden
    public static final AdvancementHolder root = Advancement.Builder.advancement()
            .display(Items.COPPER_INGOT,Component.literal("Patina Start"),
                    Component.literal("The dawn of tech"),ResourceLocation.withDefaultNamespace("textures/block/copper_block.png"),
                    AdvancementType.TASK,true,true,false)
            .addCriterion("has_copper",smelt_copper)
            .build(name("root"));
    public static final AdvancementHolder machina = Advancement.Builder.advancement()
            .display(Items.CRAFTER, Component.literal("Automation"),
                    Component.literal("Using technology for gain"),null,
                    AdvancementType.TASK,true,true,false)
            .addCriterion("minecraft:adventure/crafters_crafting_crafters",impossible_criteria)
            .parent(root)
            .build(name("automation"));
    public static final AdvancementHolder torch = Advancement.Builder.advancement()
            .display(ModItems.LIGHTER.get(), Component.literal("Lighting the path"),
                    Component.literal("Pocket full of sunshine"),null,
                    AdvancementType.TASK,true,true,false)
            .addCriterion("minecraft:story/lava_bucket",impossible_criteria)
            .addCriterion("minecraft:adventure/lighten_up",impossible_criteria)
            .addCriterion("has_daylight_detector", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DAYLIGHT_DETECTOR))
            .parent(machina)
            .build(name("lighter"));
    public static final AdvancementHolder brewing_stand = Advancement.Builder.advancement()
            .display(Items.BREWING_STAND,Component.literal("Bubble and Boil"),
                    Component.literal("alchemy for the daring"), null,
                    AdvancementType.TASK,true,true,false)
            .addCriterion("minecraft:nether/brew_potion",impossible_criteria)
            .addCriterion("minecraft:nether/obtain_blaze_rod",impossible_criteria)
            .parent(root)
            .build(name("brewing"));
    public static final AdvancementHolder foundry = Advancement.Builder.advancement()
            .display(Items.BLAST_FURNACE,Component.literal("More melty than blasty"),
                    Component.literal("advanced metalurgy"), null,
                    AdvancementType.TASK,true,false,false)
            .addCriterion("foundry", foundryCriteria)
            .parent(root)
            .build(name("foundry"));
    public static final AdvancementHolder loom = Advancement.Builder.advancement()
        .display(Items.LOOM, Component.literal("Tight Fit"),
                    Component.literal("unlocked advanced loom techniques"), null,
                    AdvancementType.TASK,true,true,false)
            .addCriterion("tight_fit", textilerCriteria)
            .parent(root)
        .build(name("tight_fit"));
    public static final AdvancementHolder crystal = Advancement.Builder.advancement()
            .display(ModItems.PRIME_GLASS.get(),Component.literal("The finest crystal"),
                    Component.literal("Advanced alloying techniques"), null,
                            AdvancementType.TASK, true, true, false)
            .addCriterion("minecraft:story/smelt_iron",impossible_criteria)
            .addCriterion("minecraft:nether/distract_piglin",impossible_criteria)
            .addCriterion("minecraft:nether/obtain_ancient_debris",impossible_criteria)
            .parent(foundry)
            .build(name( "alloy"));
    public static final AdvancementHolder phantom = Advancement.Builder.advancement()
            .display(Items.PHANTOM_MEMBRANE,Component.literal("Insomniatic"),
                    Component.literal("wear the skin of your nightmare"), null,
                    AdvancementType.TASK,true,true,false)
            .addCriterion("minecraft:adventure/two_birds_one_arrow",impossible_criteria)
            .parent(loom)
            .build(name("phantom"));
    public static final AdvancementHolder dragon = Advancement.Builder.advancement()
            .display(ModItems.DRAGON_SCALE.get(),Component.literal("Dwagon"),
                    Component.literal("unlocked dragon scale"), null,
                    AdvancementType.CHALLENGE,true,true,false)
            .addCriterion("killed_dragon", killed_dragon)
            .addCriterion("minecraft:end/kill_dragon",impossible_criteria)
            .parent(loom)
            .build(name("dragon"));
    //newer advancements here
    public static final AdvancementHolder weaponBeeShield = Advancement.Builder.advancement()
            .display(ModItems.BEE_SHIELD.get(),Component.literal("Bee our guest!"),
                    Component.literal("research advanced weaponry"),null,
                    AdvancementType.TASK,true,true,false)
            .addCriterion("minecraft:story/deflect_arrow",impossible_criteria)
            .addCriterion("minecraft:husbandry/silk_touch_nest",impossible_criteria)
            .parent(foundry)
            .build(name("bee_shield"));
    public static final AdvancementHolder weaponBeeSword = Advancement.Builder.advancement()
            .display(ModItems.BEE_SWORD.get(),Component.literal("Put our service to the test!"),
                    Component.literal("research advanced weaponry"),null,
                    AdvancementType.TASK,true,true,false)
            .addCriterion("minecraft:adventure/honey_block_slide",impossible_criteria)
            .addCriterion("minecraft:adventure_sherd",impossible_criteria)
            .parent(weaponBeeShield)
            .build(name("bee_sword"));
    public static final AdvancementHolder weaponPigBow = Advancement.Builder.advancement()
            .display(ModItems.PIG_CROSSBOW.get(),Component.literal("Heavy Hitter"),
                    Component.literal("research advanced weaponry"),null,
                    AdvancementType.TASK,true,true,false)
            .addCriterion("minecraft:adventure/arbalistic",impossible_criteria)
            .addCriterion("minecraft:adventure/sniper_duel",impossible_criteria)
            .parent(foundry)
            .build(name("pig_bow"));

    public static final AdvancementHolder weaponPigAxe = Advancement.Builder.advancement()
            .display(ModItems.PIG_SWORD.get(),Component.literal("Like the mace, but reversed!"),
                    Component.literal("research advanced weaponry"),null,
                    AdvancementType.TASK,true,true,false)
            .addCriterion("minecraft:nether/find_bastion",impossible_criteria)
            .addCriterion("minecraft:adventure/under_lock_and_key",impossible_criteria)
            .parent(weaponPigBow)
            .build(name("pig_sword"));

    public static final AdvancementHolder weaponWitherStaff = Advancement.Builder.advancement()
            .display(ModItems.WITHER_STAFF.get(),Component.literal("That sinking feeling..."),
                    Component.literal("research advanced weaponry"),null,
                    AdvancementType.TASK,true,true,false)
            .addCriterion("minecraft:nether/return_to_sender",impossible_criteria)
            .addCriterion("minecraft:nether/charge_respawn_anchor",impossible_criteria)
            .addCriterion("minecraft:nether/explore_nether",impossible_criteria)
            .addCriterion("minecraft:adventure/trim_with_any_armor_pattern",impossible_criteria)
            .parent(foundry)
            .build(name("wither_staff"));
    //potion flasks
    public static final AdvancementHolder potionFlask = Advancement.Builder.advancement()
            .display(ModItems.POTION_FLASK.get(),Component.literal("Improved capacity!"),
                    Component.literal("test title"),null,
                    AdvancementType.TASK,true,true,false)
            .addCriterion("minecraft:nether/brew_potion",impossible_criteria)
            .addCriterion("minecraft:husbandry/wax_on",impossible_criteria)
            .addCriterion("minecraft:nether/distract_piglin",impossible_criteria)
            .parent(brewing_stand)
            .build(name("flask1"));
    public static final AdvancementHolder potionFlask1 = Advancement.Builder.advancement()
            .display(ModItems.IMPETUS_FLASK.get(),Component.literal("Juicing for combat"),
                    Component.literal("test title"),null,
                    AdvancementType.TASK,true,true,false)
            .addCriterion("minecraft:nether/brew_potion",impossible_criteria)
            .addCriterion("minecraft:nether/all_potions",impossible_criteria)
            .addCriterion("minecraft:husbandry/ride_a_boat_with_a_goat",impossible_criteria)
            .parent(potionFlask)
            .build(name("flask2"));
    public static final AdvancementHolder potionFlask2 = Advancement.Builder.advancement()
            .display(ModItems.MAGNA_FLASK.get(),Component.literal("Did you say more?"),
                    Component.literal("test title"),null,
                    AdvancementType.TASK,true,true,false)
            .addCriterion("minecraft:nether/brew_potion",impossible_criteria)
            .addCriterion("minecraft:nether/all_potions",impossible_criteria)
            .addCriterion("minecraft:adventure/fall_from_world_height",impossible_criteria)
            .parent(potionFlask)
            .build(name("flask3"));
    public static final AdvancementHolder potionFlask3 = Advancement.Builder.advancement()
            .display(ModItems.VITA_FLASK.get(),Component.literal("For when you need it most"),
                    Component.literal("test title"),null,
                    AdvancementType.TASK,true,true,false)
            .addCriterion("minecraft:nether/brew_potion",impossible_criteria)
            .addCriterion("minecraft:nether/all_potions",impossible_criteria)
            .addCriterion("minecraft:husbandry/kill_axolotl_target",impossible_criteria)
            .parent(potionFlask)
            .build(name("flask4"));
    public static final AdvancementHolder arcana = Advancement.Builder.advancement()
            .display(Items.EXPERIENCE_BOTTLE, Component.literal("Advanced Arcana"),
                    Component.literal("Unlocking the science of magic!"),null,
                    AdvancementType.CHALLENGE,true,true,false)
            .addCriterion("minecraft:story/cure_zombie_villager",impossible_criteria)
            .addCriterion("minecraft:story/enchant_item",impossible_criteria)
            .addCriterion("minecraft:nether/use_lodestone",impossible_criteria)
            .addCriterion("minecraft:nether/brew_potion",impossible_criteria)
            .addCriterion("minecraft:end/dragon_breath",impossible_criteria)
            .addCriterion("minecraft:adventure/totem_of_undying",impossible_criteria)
            .addCriterion("minecraft:husbandry/axolotl_in_a_bucket",impossible_criteria)
            .addCriterion("minecraft:husbandry/obtain_sniffer_egg",impossible_criteria)
            .parent(brewing_stand)
            .build(name("arcana"));
    //potions
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
        saver.accept(root);
        saver.accept(machina);
        saver.accept(loom);
        saver.accept(foundry);
        saver.accept(brewing_stand);
        saver.accept(torch);
        saver.accept(crystal);
        saver.accept(phantom);
        saver.accept(dragon);
        saver.accept(weaponBeeShield);
        saver.accept(weaponBeeSword);
        saver.accept(weaponPigBow);
        saver.accept(weaponPigAxe);
        saver.accept(weaponWitherStaff);
        saver.accept(potionFlask);
        saver.accept(potionFlask1);
        saver.accept(potionFlask2);
        saver.accept(potionFlask3);
        saver.accept(arcana);
    }
    static ResourceLocation name(String string)
    {
        return ResourceLocation.fromNamespaceAndPath(Patina.MODID,string);
    }
}
