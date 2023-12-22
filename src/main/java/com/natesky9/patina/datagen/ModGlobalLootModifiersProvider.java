package com.natesky9.patina.datagen;

import com.natesky9.patina.Loot.AddMinMax;
import com.natesky9.patina.Loot.AddSingleItemModifier;
import com.natesky9.patina.Patina;
import com.natesky9.patina.init.ModItems;
import net.minecraft.advancements.critereon.EntityFlagsPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, Patina.MODID);
    }

    @Override
    protected void start() {
        add("add_wither_fragment_1", new AddSingleItemModifier(new LootItemCondition[]{
                new LootTableIdCondition(BuiltInLootTables.NETHER_BRIDGE)
        }, ModItems.WITHER_FRAGMENT_1.get()));
        //add("add_wither_fragment_2", new AddItemModifier(new LootItemCondition[]{
        //        new LootTableIdCondition.Builder(new ResourceLocation("chests/ruined_portal")).build()
        //}, ModItems.WITHER_FRAGMENT_1.get()));
        //add("add_wither_fragment_3",new AddSingleItemModifier(new LootItemCondition[]{
        //        new LootTableIdCondition.Builder(new ResourceLocation("chests/nether_bridge")).build()
        //},ModItems.WITHER_FRAGMENT_3.get()));
        //add("add_wither_fragment_4",new AddSingleItemModifier(new LootItemCondition[]{
        //        new LootTableIdCondition.Builder(new ResourceLocation("entities/wither")).build()
        //},ModItems.WITHER_FRAGMENT_4.get()));
        //
        add("turtle_shell_from_turtle",new AddSingleItemModifier(new LootItemCondition[]{
                LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                        EntityPredicate.Builder.entity().of(EntityType.TURTLE)
                                .flags(EntityFlagsPredicate.Builder.flags().setIsBaby(false))).build()
        }, Items.TURTLE_HELMET));
        add("dragon_scale_from_dragon",new AddMinMax(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("entities/ender_dragon")).build()
        },4,8,ModItems.DRAGON_SCALE.get()));
        //
        add("crab_claw_from_fishing",new AddSingleItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/fishing/treasure")).build()
        },ModItems.CLAW.get()));
    }
}
