package com.natesky9.patina.datagen;

import com.natesky9.patina.init.ModBlocks;
import com.natesky9.patina.init.ModItems;
import net.minecraft.advancements.AdvancementList;
import net.minecraft.advancements.AdvancementRewards;
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

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        //region fragment weapons
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.FRAGMENT_TEMPLATE.get()),
                        Ingredient.of(ModItems.PIG_FRAGMENT_1.get()),
                        Ingredient.of(ModItems.PIG_FRAGMENT_2.get()),
                        RecipeCategory.MISC, ModItems.PIG_FRAGMENT_A.get())
                .unlocks("pig_a",has(ModItems.PIG_FRAGMENT_1.get())).save(pWriter,"pig_a");
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.FRAGMENT_TEMPLATE.get()),
                        Ingredient.of(ModItems.PIG_FRAGMENT_3.get()),
                        Ingredient.of(ModItems.PIG_FRAGMENT_4.get()),
                        RecipeCategory.MISC, ModItems.PIG_FRAGMENT_B.get())
                .unlocks("pig_a",has(ModItems.PIG_FRAGMENT_3.get())).save(pWriter,"pig_b");
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.FRAGMENT_TEMPLATE.get()),
                        Ingredient.of(ModItems.PIG_FRAGMENT_1.get()),
                        Ingredient.of(ModItems.PIG_FRAGMENT_3.get()),
                        RecipeCategory.MISC, ModItems.PIG_FRAGMENT_C.get())
                .unlocks("pig_a",has(ModItems.PIG_FRAGMENT_1.get())).save(pWriter,"pig_c");
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.FRAGMENT_TEMPLATE.get()),
                        Ingredient.of(ModItems.PIG_FRAGMENT_2.get()),
                        Ingredient.of(ModItems.PIG_FRAGMENT_4.get()),
                        RecipeCategory.MISC, ModItems.PIG_FRAGMENT_D.get())
                .unlocks("pig_a",has(ModItems.BEE_FRAGMENT_2.get())).save(pWriter,"pig_d");
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.FRAGMENT_TEMPLATE.get()),
                        Ingredient.of(ModItems.PIG_FRAGMENT_A.get()),
                        Ingredient.of(ModItems.PIG_FRAGMENT_B.get()),
                        RecipeCategory.MISC, ModItems.PIG_SWORD.get())
                .unlocks("pig_a",has(ModItems.PIG_FRAGMENT_A.get())).save(pWriter,"pig_sword");
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.FRAGMENT_TEMPLATE.get()),
                        Ingredient.of(ModItems.PIG_FRAGMENT_C.get()),
                        Ingredient.of(ModItems.PIG_FRAGMENT_D.get()),
                        RecipeCategory.MISC, ModItems.PIG_CROSSBOW.get())
                .unlocks("pig_a",has(ModItems.PIG_FRAGMENT_C.get())).save(pWriter,"pig_crossbow");
        //
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.FRAGMENT_TEMPLATE.get()),
                        Ingredient.of(ModItems.BEE_FRAGMENT_1.get()),
                        Ingredient.of(ModItems.BEE_FRAGMENT_2.get()),
                        RecipeCategory.MISC, ModItems.BEE_FRAGMENT_A.get())
                .unlocks("bee_a",has(ModItems.BEE_FRAGMENT_1.get())).save(pWriter,"bee_a");
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.FRAGMENT_TEMPLATE.get()),
                        Ingredient.of(ModItems.BEE_FRAGMENT_3.get()),
                        Ingredient.of(ModItems.BEE_FRAGMENT_4.get()),
                        RecipeCategory.MISC, ModItems.BEE_FRAGMENT_B.get())
                .unlocks("bee_a",has(ModItems.BEE_FRAGMENT_3.get())).save(pWriter,"bee_b");
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.FRAGMENT_TEMPLATE.get()),
                        Ingredient.of(ModItems.BEE_FRAGMENT_1.get()),
                        Ingredient.of(ModItems.BEE_FRAGMENT_3.get()),
                        RecipeCategory.MISC, ModItems.BEE_FRAGMENT_C.get())
                .unlocks("bee_a",has(ModItems.BEE_FRAGMENT_1.get())).save(pWriter,"bee_c");
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.FRAGMENT_TEMPLATE.get()),
                        Ingredient.of(ModItems.BEE_FRAGMENT_2.get()),
                        Ingredient.of(ModItems.BEE_FRAGMENT_4.get()),
                        RecipeCategory.MISC, ModItems.BEE_FRAGMENT_D.get())
                .unlocks("bee_a",has(ModItems.BEE_FRAGMENT_2.get())).save(pWriter,"bee_d");
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.FRAGMENT_TEMPLATE.get()),
                        Ingredient.of(ModItems.BEE_FRAGMENT_A.get()),
                        Ingredient.of(ModItems.BEE_FRAGMENT_B.get()),
                        RecipeCategory.MISC, ModItems.BEE_SWORD.get())
                .unlocks("bee_a",has(ModItems.BEE_FRAGMENT_A.get())).save(pWriter,"bee_sword");
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.FRAGMENT_TEMPLATE.get()),
                        Ingredient.of(ModItems.BEE_FRAGMENT_C.get()),
                        Ingredient.of(ModItems.BEE_FRAGMENT_D.get()),
                        RecipeCategory.MISC, ModItems.BEE_SHIELD.get())
                .unlocks("bee_a",has(ModItems.BEE_FRAGMENT_C.get())).save(pWriter,"bee_shield");
        //endregion fragment weapons
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.FRAGMENT_TEMPLATE.get()),
                Ingredient.of(ModItems.CHARM_FRAGMENT.get()),
                Ingredient.of(Items.EMERALD_BLOCK),
                RecipeCategory.BREWING,ModItems.CHARM_CONTRABAND.get())
                        .unlocks("thieves_guild",has(ModItems.CHARM_FRAGMENT.get()))
                                .save(pWriter,"contraband_charm");

        //ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,ModItems.PIG_SWORD.get())
        //        .requires(ModItems.PIG_FRAGMENT_A.get())
        //        .requires(ModItems.PIG_FRAGMENT_B.get())
        //        .unlockedBy("part_a", has(ModItems.PIG_FRAGMENT_A.get()))
        //        .unlockedBy("part_b", has(ModItems.PIG_FRAGMENT_B.get()))
        //        .save(pWriter);
        //ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,ModItems.BEE_SHIELD.get())
        //        .requires(ModItems.BEE_FRAGMENT_A.get())
        //        .requires(ModItems.BEE_FRAGMENT_B.get())
        //        .unlockedBy("beelocation",BeeNestDestroyedTrigger.TriggerInstance.destroyedBeeNest(Blocks.BEEHIVE, ItemPredicate.Builder.item(), MinMaxBounds.Ints.ANY))
        //        .unlockedBy("part_a", has(ModItems.BEE_FRAGMENT_A.get()))
        //        .unlockedBy("part_b", has(ModItems.BEE_FRAGMENT_B.get()))
        //        .save(pWriter);
//
        //ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT,ModItems.BEE_SWORD.get())
        //        .requires(ModItems.BEE_FRAGMENT_A.get())
        //        .requires(ModItems.BEE_FRAGMENT_B.get())
        //        .unlockedBy("sting_like_a_bee",EntityHurtPlayerTrigger.TriggerInstance.entityHurtPlayer(DamagePredicate.Builder.damageInstance().sourceEntity(EntityPredicate.Builder.entity().of(EntityType.BEE).build())))
        //        .unlockedBy("part_a", has(ModItems.BEE_FRAGMENT_A.get()))
        //        .unlockedBy("part_b", has(ModItems.BEE_FRAGMENT_B.get()))
        //        .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.CHARM_FERTILITY.get())
                .define('C', ModItems.CHARM_FRAGMENT.get())
                .define('I',Items.GOLDEN_CARROT)
                .pattern("CCC")
                .pattern("CIC")
                .pattern("CCC")
                .unlockedBy("unlocked_charm", BredAnimalsTrigger.TriggerInstance
                        .bredAnimals(EntityPredicate.Builder.entity()
                                .of(EntityType.SHEEP).of(EntityType.PIG)
                                .of(EntityType.COW).of(EntityType.BEE)
                                .of(EntityType.CHICKEN).of(EntityType.RABBIT)))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.CHARM_ALCHEMY.get())
                .define('A', ModItems.CHARM_FRAGMENT.get())
                .define('B', Items.BREWING_STAND)
                .define('C', Items.GOLD_INGOT)
                .pattern("CBC")
                .pattern("BAB")
                .pattern("CBC")
                .unlockedBy("alchemy_charm",EffectsChangedTrigger.TriggerInstance
                        .hasEffects(MobEffectsPredicate.effects()
                                .and(MobEffects.MOVEMENT_SPEED)
                                .and(MobEffects.DAMAGE_BOOST)
                                .and(MobEffects.REGENERATION)
                                .and(MobEffects.ABSORPTION)))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.CHARM_VITALITY.get())
                .define('A', ModItems.CHARM_FRAGMENT.get())
                .define('B', Items.GOLDEN_APPLE)
                .define('C', Items.GOLD_INGOT)
                .pattern("CBC").pattern("BAB").pattern("CBC")
                .unlockedBy("gapple",has(Items.ENCHANTED_GOLDEN_APPLE))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.CHARM_DETONATION.get())
                .define('A', ModItems.CHARM_FRAGMENT.get())
                .define('B', Items.TNT_MINECART)
                .define('C', Items.GOLD_INGOT)
                .pattern("CBC").pattern("BAB").pattern("CBC")
                .unlockedBy("boom_boom_pow",KilledTrigger.TriggerInstance
                        .playerKilledEntity(EntityPredicate.Builder.entity()
                                .of(EntityType.CREEPER), DamageSourcePredicate.Builder.damageType()
                                .tag(TagPredicate.is(DamageTypeTags.IS_EXPLOSION))))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.CHARM_AMBUSH.get())
                .define('A', ModItems.CHARM_FRAGMENT.get())
                .define('B', Items.POINTED_DRIPSTONE)
                .define('C', Items.GOLD_INGOT)
                .pattern("CBC").pattern("BAB").pattern("CBC")
                .unlockedBy("sneaky_beaky",PlayerTrigger.TriggerInstance.avoidVibration())
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModItems.FRAGMENT_TEMPLATE.get())
                .define('A', ModItems.FRAGMENT_TEMPLATE.get())
                .define('B', Items.SLIME_BLOCK)
                .define('C', Items.DIAMOND)
                .pattern("CAC").pattern("CBC").pattern("CCC")
                .unlockedBy("template_copy",has(ModItems.FRAGMENT_TEMPLATE.get()))
                .save(pWriter);
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
