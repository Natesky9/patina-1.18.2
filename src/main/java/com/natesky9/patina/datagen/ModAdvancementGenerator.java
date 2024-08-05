package com.natesky9.patina.datagen;

import com.natesky9.patina.Patina;
import com.natesky9.patina.init.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.Criterion;
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
    public static Criterion<RecipeCraftedTrigger.TriggerInstance> smelt_copper = RecipeCraftedTrigger.TriggerInstance
            .craftedItem(new ResourceLocation("item/copper_ingot"));
    public static Criterion<BrewedPotionTrigger.TriggerInstance> brewed = BrewedPotionTrigger.TriggerInstance.brewedPotion();
    public static Criterion<FilledBucketTrigger.TriggerInstance> foundryCriteria = FilledBucketTrigger.TriggerInstance.filledBucket(ItemPredicate.Builder.item().of(Items.LAVA_BUCKET));
    public static Criterion<InventoryChangeTrigger.TriggerInstance> textilerCriteria = InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(Items.WHITE_WOOL).build());
    public static Criterion<KilledTrigger.TriggerInstance> killed_dragon = KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityType.ENDER_DRAGON));
    public static Criterion<KilledTrigger.TriggerInstance> killed_phantom = KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityType.PHANTOM));
    //
    public static final AdvancementHolder start = Advancement.Builder.advancement()
            .display(Items.COPPER_INGOT,Component.literal("Patina Start"),
                    Component.literal("The dawn of tech"),new ResourceLocation("textures/block/copper_block"),
                    AdvancementType.TASK,true,true,false)
            .addCriterion("has_copper",smelt_copper)
            .build(new ResourceLocation(Patina.MODID,"base"));
    public static final AdvancementHolder brewing_stand = Advancement.Builder.advancement()
            .display(Items.BREWING_STAND,Component.literal("Bubble and Boil"),
                    Component.literal("alchemy for the daring"), null,
                    AdvancementType.TASK,true,true,true)
            .addCriterion("brewing",brewed)
            .parent(start)
            .build(new ResourceLocation(Patina.MODID,"brewing"));
    public static final AdvancementHolder foundry = Advancement.Builder.advancement()
            .display(Items.BLAST_FURNACE,Component.literal("More melty than blasty"),
                    Component.literal("advanced metalurgy"), null,
                    AdvancementType.TASK,true,false,true)
            .addCriterion("foundry", foundryCriteria)
            .parent(start)
            .build(new ResourceLocation(Patina.MODID,"foundry"));
    public static final AdvancementHolder loom = Advancement.Builder.advancement()
        .display(Items.LOOM, Component.literal("Tight Fit"),
                    Component.literal("unlocked advanced loom techniques"), null,
                    AdvancementType.TASK,true,true,true)
            .addCriterion("tight_fit", textilerCriteria)
            .parent(start)
        .build(new ResourceLocation(Patina.MODID,"tight_fit"));
    public static final AdvancementHolder phantom = Advancement.Builder.advancement()
            .display(Items.PHANTOM_MEMBRANE,Component.literal("Insomniatic"),
                    Component.literal("wear the skin of your nightmare"), null,
                    AdvancementType.TASK,true,true,false)
            .addCriterion("killed_phantom",killed_phantom)
            .parent(loom)
            .build(new ResourceLocation(Patina.MODID,"phantom"));
    public static final AdvancementHolder dragon = Advancement.Builder.advancement()
            .display(ModItems.DRAGON_SCALE.get(),Component.literal("Dwagon"),
                    Component.literal("unlocked dragon scale"), null,
                    AdvancementType.CHALLENGE,true,true,false)
            .addCriterion("killed_dragon", killed_dragon)
            .parent(loom)
            .build(new ResourceLocation(Patina.MODID,"dragon"));
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
        saver.accept(start);
        saver.accept(loom);
        saver.accept(foundry);
        saver.accept(brewing_stand);
        saver.accept(phantom);
        saver.accept(dragon);
    }
}
