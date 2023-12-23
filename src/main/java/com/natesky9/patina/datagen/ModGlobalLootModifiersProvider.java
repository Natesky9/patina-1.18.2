package com.natesky9.patina.datagen;

import com.natesky9.patina.Loot.AddItemModifier;
import com.natesky9.patina.Loot.AddMinMax;
import com.natesky9.patina.Loot.AddSingleItemModifier;
import com.natesky9.patina.Loot.ReplaceItemModifier;
import com.natesky9.patina.Patina;
import com.natesky9.patina.init.ModEntityTypes;
import com.natesky9.patina.init.ModItems;
import net.minecraft.advancements.critereon.*;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
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
        //region wither fragments
        add("add_wither_fragment_1", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition(BuiltInLootTables.NETHER_BRIDGE)
        }, ModItems.WITHER_FRAGMENT_1.get(),.1f));
        add("add_wither_fragment_2", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(BuiltInLootTables.RUINED_PORTAL).build()
        }, ModItems.WITHER_FRAGMENT_1.get(),.5f));
        add("add_wither_fragment_3",new AddSingleItemModifier(new LootItemCondition[]{
                LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                        EntityPredicate.Builder.entity().of(EntityType.WITHER_SKELETON))
                        .and(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.KILLER,
                        EntityPredicate.Builder.entity().equipment(EntityEquipmentPredicate.Builder.equipment()
                                .mainhand(ItemPredicate.Builder.item().hasEnchantment(
                                        new EnchantmentPredicate(Enchantments.SMITE, MinMaxBounds.Ints.atLeast(1)))))))
                        .build()
        },ModItems.WITHER_FRAGMENT_3.get()));
        add("add_wither_fragment_4",new AddSingleItemModifier(new LootItemCondition[]{
                LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                        EntityPredicate.Builder.entity().of(EntityType.WITHER_SKELETON))
                        .and(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.KILLER,
                        EntityPredicate.Builder.entity().of(EntityType.PIGLIN))).build()
        },ModItems.WITHER_FRAGMENT_4.get()));
        //endregion wither fragments
        //region bee fragments
        add("add_bee_fragment_1", new AddItemModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.BEE_NEST).build()
        },ModItems.BEE_FRAGMENT_1.get(),.1f));
        add("add_bee_fragment_2", new AddItemModifier(new LootItemCondition[]{
                LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                        EntityPredicate.Builder.entity().of(EntityType.BEE))
                        .and(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.KILLER,
                                EntityPredicate.Builder.entity().equipment(EntityEquipmentPredicate.Builder.equipment()
                                        .mainhand(ItemPredicate.Builder.item().hasEnchantment(
                                                new EnchantmentPredicate(Enchantments.BANE_OF_ARTHROPODS, MinMaxBounds.Ints.atLeast(1)))))))
                        .build()
        },ModItems.BEE_FRAGMENT_2.get(), .1f));
        add("add_bee_fragment_3", new AddSingleItemModifier(new LootItemCondition[]{
                LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                        EntityPredicate.Builder.entity().of(ModEntityTypes.BEE_BOSS.get())).build()
        },ModItems.BEE_FRAGMENT_3.get()));
        add("add_bee_fragment_4",new AddSingleItemModifier(new LootItemCondition[]{
                LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.KILLER,
                                EntityPredicate.Builder.entity().of(EntityType.BEE)).build()
        },ModItems.BEE_FRAGMENT_4.get()));
        //endregion bee fragments
        //region pig fragments
        add("add_pig_fragment_1", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition(BuiltInLootTables.BASTION_OTHER)
        },ModItems.PIG_FRAGMENT_1.get(), .25f));
        add("add_pig_fragment_2", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition(new ResourceLocation("gameplay/piglin_bartering"))
        },ModItems.PIG_FRAGMENT_2.get(), .1f));
        add("add_pig_fragment_3",new AddSingleItemModifier(new LootItemCondition[]{
                LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity().of(EntityType.PIGLIN))
                        .and(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.KILLER,
                                EntityPredicate.Builder.entity().of(EntityType.WITHER_SKELETON))).build()
        },ModItems.PIG_FRAGMENT_3.get()));
        add("add_pig_fragment_4", new AddItemModifier(new LootItemCondition[]{
                LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity().of(EntityType.PIGLIN_BRUTE))
                        .build()
        },ModItems.PIG_FRAGMENT_4.get(), .1f));
        //endregion pig fragments


        add("turtle_shell_from_turtle",new AddSingleItemModifier(new LootItemCondition[]{
                LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS,
                        EntityPredicate.Builder.entity().of(EntityType.TURTLE)
                                .flags(EntityFlagsPredicate.Builder.flags().setIsBaby(false))).build()
        }, Items.TURTLE_HELMET));
        add("dragon_scale_from_dragon",new AddMinMax(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("entities/ender_dragon")).build()
        },4,8,ModItems.DRAGON_SCALE.get()));
        //
        add("crab_claw_from_fishing",new ReplaceItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/fishing/treasure")).build()
        },ModItems.CLAW.get()));
    }
}
