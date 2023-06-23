package com.natesky9.patina.datagen;

import com.natesky9.patina.init.ModBlocks;
import com.natesky9.patina.init.ModItems;
import net.minecraft.advancements.AdvancementList;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.*;
import net.minecraft.client.Minecraft;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.datafix.fixes.AdvancementsFix;
import net.minecraft.util.datafix.fixes.AdvancementsRenameFix;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    private void smithingTable(Item result,Item template,Item item1, Item item2, Consumer<FinishedRecipe> pWriter)
    {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(template),
                        Ingredient.of(item1),
                        Ingredient.of(item2),
                        RecipeCategory.MISC, result)
                .unlocks(item1.getDescriptionId(),has(item1))
                .unlocks(item2.getDescriptionId(),has(item2))
                .save(pWriter,result.getDescriptionId());
    }
    private void charmCrafting(Item result, Item catalyst, CriterionTriggerInstance criteria, Consumer<FinishedRecipe> pWriter)
    {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.CHARM_FRAGMENT.get()),
                        Ingredient.of(catalyst),
                        Ingredient.of(ModItems.CHARM_FRAGMENT.get()),
                        RecipeCategory.MISC, result)
                .unlocks(result.getDescriptionId() + "criteria",criteria)
                .save(pWriter,result.getDescriptionId());
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        //region fragment weapons
        smithingTable(ModItems.PIG_FRAGMENT_A.get(),
                ModItems.CHARM_FRAGMENT.get(), ModItems.PIG_FRAGMENT_1.get(), ModItems.PIG_FRAGMENT_2.get()
                ,pWriter);
        smithingTable(ModItems.PIG_FRAGMENT_B.get(),
                ModItems.CHARM_FRAGMENT.get(), ModItems.PIG_FRAGMENT_3.get(), ModItems.PIG_FRAGMENT_4.get()
                ,pWriter);
        smithingTable(ModItems.PIG_FRAGMENT_C.get(),
                ModItems.CHARM_FRAGMENT.get(), ModItems.PIG_FRAGMENT_1.get(), ModItems.PIG_FRAGMENT_3.get()
                ,pWriter);
        smithingTable(ModItems.PIG_FRAGMENT_D.get(),
                ModItems.CHARM_FRAGMENT.get(), ModItems.PIG_FRAGMENT_2.get(), ModItems.PIG_FRAGMENT_4.get()
                ,pWriter);
        smithingTable(ModItems.PIG_SWORD.get(),
                ModItems.CHARM_FRAGMENT.get(), ModItems.PIG_FRAGMENT_A.get(), ModItems.PIG_FRAGMENT_B.get()
                ,pWriter);
        smithingTable(ModItems.PIG_CROSSBOW.get(),
                ModItems.CHARM_FRAGMENT.get(), ModItems.PIG_FRAGMENT_C.get(), ModItems.PIG_FRAGMENT_D.get()
                ,pWriter);
        //Bee
        smithingTable(ModItems.BEE_FRAGMENT_A.get(),
                ModItems.CHARM_FRAGMENT.get(), ModItems.BEE_FRAGMENT_1.get(), ModItems.BEE_FRAGMENT_2.get()
                ,pWriter);
        smithingTable(ModItems.BEE_FRAGMENT_B.get(),
                ModItems.CHARM_FRAGMENT.get(), ModItems.BEE_FRAGMENT_3.get(), ModItems.BEE_FRAGMENT_4.get()
                ,pWriter);
        smithingTable(ModItems.BEE_FRAGMENT_C.get(),
                ModItems.CHARM_FRAGMENT.get(), ModItems.BEE_FRAGMENT_1.get(), ModItems.BEE_FRAGMENT_3.get()
                ,pWriter);
        smithingTable(ModItems.BEE_FRAGMENT_D.get(),
                ModItems.CHARM_FRAGMENT.get(), ModItems.BEE_FRAGMENT_2.get(), ModItems.BEE_FRAGMENT_4.get()
                ,pWriter);
        smithingTable(ModItems.BEE_SWORD.get(),
                ModItems.CHARM_FRAGMENT.get(), ModItems.BEE_FRAGMENT_A.get(), ModItems.BEE_FRAGMENT_B.get()
                ,pWriter);
        smithingTable(ModItems.BEE_SHIELD.get(),
                ModItems.CHARM_FRAGMENT.get(), ModItems.BEE_FRAGMENT_C.get(), ModItems.BEE_FRAGMENT_D.get()
                ,pWriter);
        //
        //endregion fragment weapons
        //region charms
        CriterionTriggerInstance alchemy_criteria = EffectsChangedTrigger.TriggerInstance//has these effects
                .hasEffects(MobEffectsPredicate.effects().and(MobEffects.MOVEMENT_SPEED).and(MobEffects.DAMAGE_BOOST).and(MobEffects.REGENERATION).and(MobEffects.ABSORPTION));
        CriterionTriggerInstance fertility_criteria = BredAnimalsTrigger.TriggerInstance//bred these animals
                        .bredAnimals(EntityPredicate.Builder.entity().of(EntityType.SHEEP).of(EntityType.PIG).of(EntityType.COW).of(EntityType.BEE).of(EntityType.CHICKEN).of(EntityType.RABBIT));
        CriterionTriggerInstance ambush_criteria = PlayerTrigger.TriggerInstance.avoidVibration();
        CriterionTriggerInstance vitality_criteria = has(Items.ENCHANTED_GOLDEN_APPLE);
        CriterionTriggerInstance detonation_criteria = KilledTrigger.TriggerInstance
                        .playerKilledEntity(EntityPredicate.Builder.entity().of(EntityType.CREEPER), DamageSourcePredicate.Builder.damageType().tag(TagPredicate.is(DamageTypeTags.IS_EXPLOSION)));
        CriterionTriggerInstance contraband_criteria = EffectsChangedTrigger.TriggerInstance.hasEffects(MobEffectsPredicate.effects()
                .and(MobEffects.CONDUIT_POWER).and(MobEffects.WITHER).and(MobEffects.LEVITATION).and(MobEffects.WEAKNESS).and(MobEffects.BLINDNESS));
        CriterionTriggerInstance warding_criteria = has(Items.RABBIT_FOOT);
        CriterionTriggerInstance experience_criteria = KilledTrigger.TriggerInstance
                .playerKilledEntity(EntityPredicate.Builder.entity().of(EntityType.ENDER_DRAGON));
        CriterionTriggerInstance vanilla_totem = KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityType.EVOKER));
        //
        charmCrafting(ModItems.CHARM_ALCHEMY.get(),Items.BREWING_STAND,alchemy_criteria,pWriter);
        charmCrafting(ModItems.CHARM_AMBUSH.get(),Items.SCULK_SENSOR,ambush_criteria,pWriter);
        charmCrafting(ModItems.CHARM_FERTILITY.get(),Items.GOLDEN_CARROT,fertility_criteria,pWriter);
        charmCrafting(ModItems.CHARM_VITALITY.get(),Items.ENCHANTED_GOLDEN_APPLE,vitality_criteria,pWriter);
        charmCrafting(ModItems.CHARM_DETONATION.get(),Items.FIRE_CHARGE,detonation_criteria,pWriter);
        charmCrafting(ModItems.CHARM_CONTRABAND.get(),Items.EMERALD_BLOCK,contraband_criteria,pWriter);
        charmCrafting(ModItems.CHARM_WARDING.get(),Items.RABBIT_FOOT,warding_criteria,pWriter);
        charmCrafting(ModItems.CHARM_EXPERIENCE.get(),Items.EXPERIENCE_BOTTLE,experience_criteria,pWriter);
        charmCrafting(Items.TOTEM_OF_UNDYING,ModItems.CHARM_FRAGMENT.get(),vanilla_totem,pWriter);
        //endregion charms

        //ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.CHARM_FERTILITY.get())
        //        .define('C', ModItems.CHARM_FRAGMENT.get())
        //        .define('I',Items.GOLDEN_CARROT)
        //        .pattern("CCC")
        //        .pattern("CIC")
        //        .pattern("CCC")
        //        .unlockedBy("unlocked_charm", BredAnimalsTrigger.TriggerInstance
        //                .bredAnimals(EntityPredicate.Builder.entity()
        //                        .of(EntityType.SHEEP).of(EntityType.PIG)
        //                        .of(EntityType.COW).of(EntityType.BEE)
        //                        .of(EntityType.CHICKEN).of(EntityType.RABBIT)))
        //        .save(pWriter);

        //ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.CHARM_VITALITY.get())
        //        .define('A', ModItems.CHARM_FRAGMENT.get())
        //        .define('B', Items.GOLDEN_APPLE)
        //        .define('C', Items.GOLD_INGOT)
        //        .pattern("CBC").pattern("BAB").pattern("CBC")
        //        .unlockedBy("gapple",has(Items.ENCHANTED_GOLDEN_APPLE))
        //        .save(pWriter);
        //ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.CHARM_DETONATION.get())
        //        .define('A', ModItems.CHARM_FRAGMENT.get())
        //        .define('B', Items.TNT_MINECART)
        //        .define('C', Items.GOLD_INGOT)
        //        .pattern("CBC").pattern("BAB").pattern("CBC")
        //        .unlockedBy("boom_boom_pow",KilledTrigger.TriggerInstance
        //                .playerKilledEntity(EntityPredicate.Builder.entity()
        //                        .of(EntityType.CREEPER), DamageSourcePredicate.Builder.damageType()
        //                        .tag(TagPredicate.is(DamageTypeTags.IS_EXPLOSION))))
        //        .save(pWriter);
        //ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.CHARM_AMBUSH.get())
        //        .define('A', ModItems.CHARM_FRAGMENT.get())
        //        .define('B', Items.POINTED_DRIPSTONE)
        //        .define('C', Items.GOLD_INGOT)
        //        .pattern("CBC").pattern("BAB").pattern("CBC")
        //        .unlockedBy("sneaky_beaky",PlayerTrigger.TriggerInstance.avoidVibration())
        //        .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.COPPER_AXE.get())
                .define('I', Items.COPPER_INGOT)
                .define('S', Items.STICK)
                .pattern("II").pattern("IS").pattern(" S")
                .unlockedBy("has_copper",has(Items.COPPER_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.COPPER_PICK.get())
                .define('I', Items.COPPER_INGOT)
                .define('S', Items.STICK)
                .pattern("III").pattern(" S ").pattern(" S ")
                .unlockedBy("has_copper",has(Items.COPPER_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.COPPER_SHOVEL.get())
                .define('I', Items.COPPER_INGOT)
                .define('S', Items.STICK)
                .pattern("I").pattern("S").pattern("S")
                .unlockedBy("has_copper",has(Items.COPPER_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.COPPER_SWORD.get())
                .define('I', Items.COPPER_INGOT)
                .define('S', Items.STICK)
                .pattern("I").pattern("I").pattern("S")
                .unlockedBy("has_copper",has(Items.COPPER_INGOT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.COPPER_HOE.get())
                .define('I', Items.COPPER_INGOT)
                .define('S', Items.STICK)
                .pattern("II").pattern(" S").pattern(" S")
                .unlockedBy("has_copper",has(Items.COPPER_INGOT))
                .save(pWriter);
    }
}
