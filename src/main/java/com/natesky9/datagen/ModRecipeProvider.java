package com.natesky9.datagen;

import com.natesky9.patina.Patina;
import com.natesky9.patina.Recipe.FoundryRecipe;
import com.natesky9.patina.init.ModBlocks;
import com.natesky9.patina.init.ModItems;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.data.recipes.packs.BundleRecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output,provider);
    }

    @Override
    public void buildRecipes(@NotNull RecipeOutput pWriter) {
        //TODO: add the recipes, you dummy
        pWriter.accept(name("foundry/prime"),
                new FoundryRecipe(new ItemStack(ModItems.PRIME_GLASS.get()),
                        Ingredient.of(Items.PRISMARINE_CRYSTALS),Ingredient.of(Items.SOUL_SAND)),
                ModAdvancementGenerator.material_crystal);
        pWriter.accept(name("foundry/anima"),
                new FoundryRecipe(new ItemStack(ModItems.ANIMA_GLASS.get()),
                        Ingredient.of(ModItems.PRIME_GLASS.get()),Ingredient.of(Items.CHORUS_FLOWER)),
                ModAdvancementGenerator.material_anima);
        pWriter.accept(name("foundry/fortis"),
                new FoundryRecipe(new ItemStack(ModItems.FERUS_GLASS.get()),
                        Ingredient.of(ModItems.PRIME_GLASS.get()),Ingredient.of(Items.GOAT_HORN)),
                ModAdvancementGenerator.material_fortis);
        pWriter.accept(name("foundry/magna"),
                new FoundryRecipe(new ItemStack(ModItems.FORTIS_GLASS.get()),
                        Ingredient.of(ModItems.PRIME_GLASS.get()),Ingredient.of(Items.NETHERITE_INGOT)),
                ModAdvancementGenerator.material_fortis);
        pWriter.accept(name("foundry/malachite"),
                new FoundryRecipe(new ItemStack(ModItems.MALACHITE.get()),
                        Ingredient.of(Items.EMERALD_BLOCK),Ingredient.of(Items.COPPER_INGOT)),
                        ModAdvancementGenerator.material_malachite);
        //
        //pWriter.accept(ResourceLocation.fromNamespaceAndPath(Patina.MODID,"textiler/silk"),
        //        new TextilerRecipe(new ItemStack(ModItems.SILK.get()), NonNullList.withSize(9,Ingredient.of(Items.STRING))),
        //        ModAdvancementGenerator.loom);
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
        Criterion<EffectsChangedTrigger.TriggerInstance> alchemy_criteria = EffectsChangedTrigger.TriggerInstance//has these effects
                .hasEffects(MobEffectsPredicate.Builder.effects().and(MobEffects.MOVEMENT_SPEED).and(MobEffects.DAMAGE_BOOST).and(MobEffects.REGENERATION).and(MobEffects.ABSORPTION));
        Criterion<BredAnimalsTrigger.TriggerInstance> fertility_criteria = BredAnimalsTrigger.TriggerInstance//bred these animals
                        .bredAnimals(EntityPredicate.Builder.entity().of(EntityType.SHEEP).of(EntityType.PIG).of(EntityType.COW).of(EntityType.BEE).of(EntityType.CHICKEN).of(EntityType.RABBIT));
        Criterion<PlayerTrigger.TriggerInstance> ambush_criteria = PlayerTrigger.TriggerInstance.avoidVibration();
        Criterion<InventoryChangeTrigger.TriggerInstance> vitality_criteria = has(Items.ENCHANTED_GOLDEN_APPLE);
        Criterion<KilledTrigger.TriggerInstance> detonation_criteria = KilledTrigger.TriggerInstance
                        .playerKilledEntity(EntityPredicate.Builder.entity().of(EntityType.CREEPER), DamageSourcePredicate.Builder.damageType().tag(TagPredicate.is(DamageTypeTags.IS_EXPLOSION)));
        Criterion<EffectsChangedTrigger.TriggerInstance> contraband_criteria = EffectsChangedTrigger.TriggerInstance.hasEffects(MobEffectsPredicate.Builder.effects()
                .and(MobEffects.CONDUIT_POWER).and(MobEffects.WITHER).and(MobEffects.LEVITATION).and(MobEffects.WEAKNESS).and(MobEffects.BLINDNESS));
        Criterion<InventoryChangeTrigger.TriggerInstance> warding_criteria = has(Items.RABBIT_FOOT);
        Criterion<KilledTrigger.TriggerInstance> experience_criteria = KilledTrigger.TriggerInstance
                .playerKilledEntity(EntityPredicate.Builder.entity().of(EntityType.ENDER_DRAGON));
        Criterion<KilledTrigger.TriggerInstance> vanilla_totem = KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityType.EVOKER));
        //this needs to be reworked for the new system
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
        //region armor
        //ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.CLOTH_BOOTS.get())
        //    .define('A',ModItems.SILK.get())
        //    .pattern("A A").pattern("A A")
        //    .unlockedBy("unlocked_silk",ModAdvancementGenerator.textilerCriteria)
        //    .save(pWriter);
        //umbral
        //ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,ModItems.UMBRA_HAT.get())
        //        .define('A',ModItems.UMBRA.get())
        //        .pattern("AAA").pattern("A A")
        //        .unlockedBy("unlocked_umbra", ModAdvancementGenerator.killed_phantom)
        //        .save(pWriter);
        //ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,ModItems.UMBRA_TOP.get())
        //        .define('A',ModItems.UMBRA.get())
        //        .pattern("A A").pattern("AAA").pattern("AAA")
        //        .unlockedBy("unlocked_umbra", ModAdvancementGenerator.killed_phantom)
        //        .save(pWriter);
        //ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,ModItems.UMBRA_BOTTOM.get())
        //        .define('A',ModItems.UMBRA.get())
        //        .pattern("AAA").pattern("A A").pattern("A A")
        //        .unlockedBy("unlocked_umbra", ModAdvancementGenerator.killed_phantom)
        //        .save(pWriter);
        //ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,ModItems.DRAGON_HELMET.get())
        //        .define('I', ModItems.DRAGON_SCALE.get())
        //        .define('C', Items.CHAINMAIL_HELMET)
        //        .pattern("ICI").pattern("I I")
        //        .unlockedBy("unlocked_dragon",ModAdvancementGenerator.killed_dragon)
        //        .save(pWriter);
        //ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,ModItems.DRAGON_CHESTPLATE.get())
        //        .define('I', ModItems.DRAGON_SCALE.get())
        //        .define('C', Items.CHAINMAIL_CHESTPLATE)
        //        .pattern("I I").pattern("ICI").pattern("III")
        //        .unlockedBy("unlocked_dragon",ModAdvancementGenerator.killed_dragon)
        //        .save(pWriter);
        //ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,ModItems.DRAGON_LEGGINGS.get())
        //        .define('I', ModItems.DRAGON_SCALE.get())
        //        .define('C', Items.CHAINMAIL_LEGGINGS)
        //        .pattern("ICI").pattern("I I").pattern("I I")
        //        .unlockedBy("unlocked_dragon",ModAdvancementGenerator.killed_dragon)
        //        .save(pWriter);
        //endregion armor
        //temporary recipes
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.VOID_SALT.get())
                .requires(ModItems.POTION_SALT.get(),9)
                .unlockedBy("has_salt",has(ModItems.POTION_SALT.get())).save(pWriter);
        nineBlockStorageRecipes(pWriter,RecipeCategory.MISC,ModItems.BISMUTH_NUGGET.get(),RecipeCategory.MISC,ModItems.BISMUTH_INGOT.get());
        //region flasks
        String[] flask = new String[]{" a ","b b"," b "};
        pWriter.accept(name("brewing/prime_flask"),
                new ShapedRecipe("prime_flask", CraftingBookCategory.MISC,
                        ShapedRecipePattern.of(Map.of('a',Ingredient.of(ModItems.COPPER_NUGGET.get()),
                                        'b',Ingredient.of(ModItems.PRIME_GLASS.get())),
                                flask),
                        ModItems.POTION_FLASK.get().getDefaultInstance()),
                ModAdvancementGenerator.prime_flask);
        pWriter.accept(name("brewing/vita_flask"),
                new ShapedRecipe("vita_flask", CraftingBookCategory.MISC,
                        ShapedRecipePattern.of(Map.of('a',Ingredient.of(ModItems.COPPER_NUGGET.get()),
                                        'b',Ingredient.of(ModItems.ANIMA_GLASS.get())),
                                flask),
                        ModItems.VITA_FLASK.get().getDefaultInstance()),
                ModAdvancementGenerator.vita_flask);
        pWriter.accept(name("brewing/impetus_flask"),
                new ShapedRecipe("impetus_flask", CraftingBookCategory.MISC,
                        ShapedRecipePattern.of(Map.of('a',Ingredient.of(ModItems.COPPER_NUGGET.get()),
                                        'b',Ingredient.of(ModItems.FERUS_GLASS.get())),
                                flask),
                        ModItems.IMPETUS_FLASK.get().getDefaultInstance()),
                ModAdvancementGenerator.impetus_flask);
        pWriter.accept(name("brewing/magna_flask"),
                new ShapedRecipe("magna_flask", CraftingBookCategory.MISC,
                        ShapedRecipePattern.of(Map.of('a',Ingredient.of(ModItems.COPPER_NUGGET.get()),
                                        'b',Ingredient.of(ModItems.FORTIS_GLASS.get())),
                                new String[]{" a ","b b"," b "}),
                        ModItems.MAGNA_FLASK.get().getDefaultInstance()),
                ModAdvancementGenerator.magna_flask);
        //flask recycling recipes
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.POTION_FLASK.get()),RecipeCategory.MISC,ModItems.PRIME_GLASS.get(),0f,400)
                .unlockedBy("has_flask",has(ModItems.POTION_FLASK.get()))
                .save(pWriter);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.VITA_FLASK.get()),RecipeCategory.MISC,ModItems.ANIMA_GLASS.get(),0f,400)
                .unlockedBy("has_flask",has(ModItems.VITA_FLASK.get()))
                .save(pWriter);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.IMPETUS_FLASK.get()),RecipeCategory.MISC,ModItems.FERUS_GLASS.get(),0f,400)
                .unlockedBy("has_flask",has(ModItems.POTION_FLASK.get()))
                .save(pWriter);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItems.MAGNA_FLASK.get()),RecipeCategory.MISC,ModItems.FORTIS_GLASS.get(),0f,400)
                .unlockedBy("has_flask",has(ModItems.MAGNA_FLASK.get()))
                .save(pWriter);
        //endregion flasks
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.SEED_POUCH.get())
                .define('A', Items.LEATHER)
                .define('B',Items.STRING)
                .define('C',ModItems.COPPER_NUGGET.get())
                .pattern("BAC").pattern("A A").pattern("AAA")
                .unlockedBy("bundle",RecipeCraftedTrigger.TriggerInstance
                        .craftedItem(ResourceLocation.withDefaultNamespace(BundleRecipeProvider.getItemName(Items.BUNDLE))))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.DUST_POUCH.get())
                .define('A', Items.LEATHER)
                .define('B',Items.STRING)
                .define('C',Items.IRON_NUGGET)
                .pattern("BAC").pattern("A A").pattern("AAA")
                .unlockedBy("bundle",RecipeCraftedTrigger.TriggerInstance
                        .craftedItem(ResourceLocation.withDefaultNamespace(BundleRecipeProvider.getItemName(Items.BUNDLE))))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.GEM_POUCH.get())
                .define('A', Items.LEATHER)
                .define('B',Items.STRING)
                .define('C',Items.GOLD_NUGGET)
                .pattern("BAC").pattern("A A").pattern("AAA")
                .unlockedBy("bundle",RecipeCraftedTrigger.TriggerInstance
                        .craftedItem(ResourceLocation.withDefaultNamespace(BundleRecipeProvider.getItemName(Items.BUNDLE))))
                .save(pWriter);
        //todo:make a list of items to unlock, until I create the research aspect
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.LIGHTER.get())
                .define('A', Items.DAYLIGHT_DETECTOR)
                .define('B', Items.COMPARATOR)
                .define('C', Items.DISPENSER)
                .define('D', Items.LEVER)
                .define('E', ModItems.COPPER_NUGGET.get())
                .pattern("EAE").pattern("BCD").pattern("E E")
                .unlockedBy("unlock_lighter",has(Items.DAYLIGHT_DETECTOR))
                .save(pWriter);


        //endregion flasks
        //region machines
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModBlocks.MACHINE_EVAPORATOR.get())
                .define('C',Items.COPPER_INGOT)
                .define('K',Items.CAULDRON)
                .define('F',Items.SOUL_CAMPFIRE)
                .pattern("CKC")
                .pattern("CFC")
                .pattern("CCC")
                .unlockedBy("unlocked_evaporator",has(Items.SOUL_CAMPFIRE))
                .save(pWriter);
        pWriter.accept(name("foundry"),
                new ShapedRecipe("foundry",CraftingBookCategory.MISC,
                        ShapedRecipePattern.of(Map.of('a',Ingredient.of(Items.BLAST_FURNACE),
                                        'b',Ingredient.of(Items.CAULDRON),
                                        'c',Ingredient.of(Items.CUT_COPPER_STAIRS),
                                        'd',Ingredient.of(Items.BLAZE_ROD)),
                                new String[]{"cbc","ada","cbc"}),
                        ModBlocks.MACHINE_FOUNDRY.get().asItem().getDefaultInstance())
                ,ModAdvancementGenerator.foundry);
        pWriter.accept(name("foundry_addon"),
                new ShapedRecipe("foundry_addon",CraftingBookCategory.MISC,
                        ShapedRecipePattern.of(Map.of('a',Ingredient.of(Items.LEATHER),
                                        'b',Ingredient.of(Items.PISTON),
                                        'c',Ingredient.of(Items.CUT_COPPER_STAIRS),
                                        'd',Ingredient.of(Items.BREEZE_ROD)),
                                new String[]{"cbc","ada","cbc"}),
                        ModBlocks.ADDON_FOUNDRY.get().asItem().getDefaultInstance())
                ,ModAdvancementGenerator.foundry_augment);

        pWriter.accept(name("alembic"),
                new ShapedRecipe("alembic",CraftingBookCategory.REDSTONE,
                        ShapedRecipePattern.of(Map.of('a',Ingredient.of(Items.IRON_INGOT),
                                        'b',Ingredient.of(ModItems.POTION_FLASK.get()),
                                        'c',Ingredient.of(Items.WATER_BUCKET),
                                        'd',Ingredient.of(Items.CUT_COPPER_SLAB)),
                                new String[]{"bab","aca","ddd"}),
                        ModBlocks.MACHINE_ALEMBIC.get().asItem().getDefaultInstance()),
                ModAdvancementGenerator.brewing);
        pWriter.accept(name("alembic_addon_1"),
                new ShapedRecipe("alembic_addon_1",CraftingBookCategory.REDSTONE,
                        ShapedRecipePattern.of(Map.of('a',Ingredient.of(Items.QUARTZ_SLAB),
                                        'b',Ingredient.of(ModItems.MAGNA_FLASK.get()),
                                        'c',Ingredient.of(Items.SEA_PICKLE),
                                        'd',Ingredient.of(Items.CUT_COPPER_SLAB),
                                        'e',Ingredient.of(Items.WATER_BUCKET)),
                                new String[]{"aba","aca","ded"}),
                        ModBlocks.ADDON_ALEMBIC.get().asItem().getDefaultInstance()),
                ModAdvancementGenerator.brewing_augment);
        pWriter.accept(name("alembic_addon_2"),
                new ShapedRecipe("alembic_addon_2",CraftingBookCategory.REDSTONE,
                        ShapedRecipePattern.of(Map.of('a',Ingredient.of(Items.QUARTZ_SLAB),
                                        'b',Ingredient.of(ModItems.VITA_FLASK.get()),
                                        'c',Ingredient.of(Items.SEA_PICKLE),
                                        'd',Ingredient.of(Items.CUT_COPPER_SLAB),
                                        'e',Ingredient.of(Items.WATER_BUCKET)),
                                new String[]{"aba","aca","ded"}),
                        ModBlocks.ADDON_ALEMBIC.get().asItem().getDefaultInstance()),
                ModAdvancementGenerator.brewing_augment);
        pWriter.accept(name("alembic_addon_3"),
                new ShapedRecipe("alembic_addon_3",CraftingBookCategory.REDSTONE,
                        ShapedRecipePattern.of(Map.of('a',Ingredient.of(Items.QUARTZ_SLAB),
                                        'b',Ingredient.of(ModItems.IMPETUS_FLASK.get()),
                                        'c',Ingredient.of(Items.SEA_PICKLE),
                                        'd',Ingredient.of(Items.CUT_COPPER_SLAB),
                                        'e',Ingredient.of(Items.WATER_BUCKET)),
                                new String[]{"aba","aca","ded"}),
                        ModBlocks.ADDON_ALEMBIC.get().asItem().getDefaultInstance()),
                ModAdvancementGenerator.brewing_augment);

        //ShapedRecipeBuilder.shaped(RecipeCategory.BREWING, ModBlocks.MACHINE_ALEMBIC.get())
        //        .pattern("AAA").pattern("BBB").pattern("CCC")
        //        .define('A', ModItems.PRIME_GLASS.get())
        //        .define('B', Items.BREWING_STAND)
        //        .define('C', Items.CUT_COPPER_SLAB)
        //        .unlockedBy("unlock_alembic", ModAdvancementGenerator.brewing_stand)
        //        .save(pWriter);
        //ShapedRecipeBuilder.shaped(RecipeCategory.BREWING, ModBlocks.ADDON_ALEMBIC.get())
        //        .pattern("GIP").pattern("IBI").pattern("I I")
        //        .define('G', Items.GOLD_INGOT)
        //        .define('I', Items.IRON_INGOT)
        //        .define('P', ModItems.PRIME_GLASS.get())
        //        .define('B', Items.BUCKET)
        //        .unlockedBy("unlock_alembic_addon", RecipeCraftedTrigger.TriggerInstance
        //                .craftedItem(ModBlocks.MACHINE_ALEMBIC.getId()))
        //        .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModBlocks.MACHINE_MINCERATOR.get())
                .pattern("AAA").pattern("AGA").pattern("ASA")
                .define('A', Items.CUT_COPPER_SLAB)
                .define('G', Items.GRINDSTONE)
                .define('S', Items.SMOKER)
                //unlocked by curing a zombie?
                .unlockedBy("unlock_mincerator", PlayerInteractTrigger.TriggerInstance
                        .itemUsedOnEntity(ItemPredicate.Builder.item().of(Items.GOLDEN_APPLE),
                                Optional.of(EntityPredicate.wrap(EntityPredicate.Builder.entity().of(EntityType.ZOMBIE_VILLAGER)))))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.APPLIANCE_ICEBOX.get())
                .define('A',Items.CUT_COPPER_SLAB)
                .define('B',Items.IRON_DOOR)
                .define('C',Items.SNOW_BLOCK)
                .pattern("AAA").pattern("ACB").pattern("AAA")
                .unlockedBy("unlocked_fridge",has(Items.SNOWBALL))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModBlocks.APPLIANCE_WARDROBE.get())
                .define('A',ItemTags.WOODEN_SLABS)
                .define('B',Items.ARMOR_STAND)
                .define('C',ItemTags.WOODEN_DOORS)
                .pattern("AAA").pattern("ABC").pattern("AAA")
                .unlockedBy("unlocked_wardrobe",has(Items.ARMOR_STAND))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.APPLIANCE_ARCANE_CONSOLIDATOR.get())
                .pattern("ABA").pattern("BCB").pattern("ABA")
                .define('A', Items.CUT_COPPER_STAIRS)
                .define('B', Items.LAPIS_BLOCK)
                .define('C', Items.DISPENSER)
                .unlockedBy("unlock_consolidator", EnchantedItemTrigger.TriggerInstance.enchantedItem())
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION,ModBlocks.CHORUS_TELEPORTER.get())
                .pattern("PEP").pattern("ENE").pattern("PEP")
                .define('P',Items.PURPUR_BLOCK)
                .define('E',Items.END_STONE_BRICK_STAIRS)
                .define('N',Items.ENDER_EYE)
                .unlockedBy("has_purpur",has(Items.POPPED_CHORUS_FRUIT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION,ModBlocks.CHORUS_CABLE.get())
                .pattern("CPC").pattern("CPC").pattern("CPC")
                .define('C', ItemTags.WOOL_CARPETS).define('P',Items.POPPED_CHORUS_FRUIT)
                .unlockedBy("has_purpur",has(Items.POPPED_CHORUS_FRUIT))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ModBlocks.CHARGE_CABLE.get())
                .pattern("WCW").pattern("WCW").pattern("WCW")
                .define('W',ItemTags.WOOL_CARPETS).define('C',Items.CUT_COPPER)
                .unlockedBy("has_copper",has(Items.CUT_COPPER))
                .save(pWriter);
        //endregion machines
        //region copper
        nineBlockStorageRecipes(pWriter,RecipeCategory.MISC,ModItems.COPPER_NUGGET.get(),RecipeCategory.MISC,Items.COPPER_INGOT);
        Criterion<InventoryChangeTrigger.TriggerInstance> hasCopper = has(Items.COPPER_INGOT);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.COPPER_AXE.get())
                .define('I', Items.COPPER_INGOT)
                .define('S', Items.STICK)
                .pattern("II").pattern("IS").pattern(" S")
                .unlockedBy("has_copper",hasCopper)
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.COPPER_PICK.get())
                .define('I', Items.COPPER_INGOT)
                .define('S', Items.STICK)
                .pattern("III").pattern(" S ").pattern(" S ")
                .unlockedBy("has_copper",hasCopper)
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.COPPER_SHOVEL.get())
                .define('I', Items.COPPER_INGOT)
                .define('S', Items.STICK)
                .pattern("I").pattern("S").pattern("S")
                .unlockedBy("has_copper",hasCopper)
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.COPPER_SWORD.get())
                .define('I', Items.COPPER_INGOT)
                .define('S', Items.STICK)
                .pattern("I").pattern("I").pattern("S")
                .unlockedBy("has_copper",hasCopper)
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.COPPER_HOE.get())
                .define('I', Items.COPPER_INGOT)
                .define('S', Items.STICK)
                .pattern("II").pattern(" S").pattern(" S")
                .unlockedBy("has_copper",hasCopper)
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,ModItems.COPPER_HELMET.get())
                .define('I', Items.COPPER_INGOT)
                .pattern("III").pattern("I I")
                .unlockedBy("has_copper",hasCopper)
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,ModItems.COPPER_CHESTPLATE.get())
                .define('I', Items.COPPER_INGOT)
                .pattern("I I").pattern("III").pattern("III")
                .unlockedBy("has_copper",hasCopper)
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,ModItems.COPPER_LEGGINGS.get())
                .define('I', Items.COPPER_INGOT)
                .pattern("III").pattern("I I").pattern("I I")
                .unlockedBy("has_copper",hasCopper)
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,ModItems.COPPER_BOOTS.get())
                .define('I', Items.COPPER_INGOT)
                .pattern("I I").pattern("I I")
                .unlockedBy("has_copper",hasCopper)
                .save(pWriter);
        //endregion copper
        //region bronze
        Criterion<InventoryChangeTrigger.TriggerInstance> hasBronze = has(ModItems.BRONZE_INGOT.get());
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.BRONZE_AXE.get())
                .define('I', ModItems.BRONZE_INGOT.get())
                .define('S', Items.STICK)
                .pattern("II").pattern("IS").pattern(" S")
                .unlockedBy("has_bronze",hasBronze)
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.BRONZE_PICK.get())
                .define('I', ModItems.BRONZE_INGOT.get())
                .define('S', Items.STICK)
                .pattern("III").pattern(" S ").pattern(" S ")
                .unlockedBy("has_bronze",hasBronze)
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.BRONZE_SHOVEL.get())
                .define('I', ModItems.BRONZE_INGOT.get())
                .define('S', Items.STICK)
                .pattern("I").pattern("S").pattern("S")
                .unlockedBy("has_bronze",hasBronze)
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.BRONZE_SWORD.get())
                .define('I', ModItems.BRONZE_INGOT.get())
                .define('S', Items.STICK)
                .pattern("I").pattern("I").pattern("S")
                .unlockedBy("has_bronze",hasBronze)
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.BRONZE_HOE.get())
                .define('I', ModItems.BRONZE_INGOT.get())
                .define('S', Items.STICK)
                .pattern("II").pattern(" S").pattern(" S")
                .unlockedBy("has_bronze",hasBronze)
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,ModItems.BRONZE_HELMET.get())
                .define('I', ModItems.BRONZE_INGOT.get())
                .pattern("III").pattern("I I")
                .unlockedBy("has_bronze",hasBronze)
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,ModItems.BRONZE_CHESTPLATE.get())
                .define('I', ModItems.BRONZE_INGOT.get())
                .pattern("I I").pattern("III").pattern("III")
                .unlockedBy("has_bronze",hasBronze)
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,ModItems.BRONZE_LEGGINGS.get())
                .define('I', ModItems.BRONZE_INGOT.get())
                .pattern("III").pattern("I I").pattern("I I")
                .unlockedBy("has_bronze",hasBronze)
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,ModItems.BRONZE_BOOTS.get())
                .define('I', ModItems.BRONZE_INGOT.get())
                .pattern("I I").pattern("I I")
                .unlockedBy("has_bronze",hasBronze)
                .save(pWriter);
        //endregion bronze
        //ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,ModItems.DRAGON_BOOTS.get())
        //        .define('I', ModItems.DRAGON_SCALE.get())
        //        .define('C', Items.CHAINMAIL_BOOTS)
        //        .pattern("ICI").pattern("I I")
        //        .unlockedBy("has_bronze",hasDragon)
        //        .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ModItems.COPPER_CLAW.get())
                .define('A',ModItems.CLAW.get())
                .define('B',Items.COPPER_INGOT)
                .define('C',ModItems.COPPER_NUGGET.get())
                .pattern("CBC").pattern("BAB").pattern(" BC")
                .unlockedBy("has_claw",has(ModItems.CLAW.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS,ModItems.DRAGON_CLAW.get())
                .requires(ModItems.COPPER_CLAW.get())
                .requires(ModItems.DRAGON_SCALE.get())
                .unlockedBy("has_copper_claw",has(ModItems.COPPER_CLAW.get()))
                .save(pWriter);
    }

    private void smithingTable(Item result,Item template,Item item1, Item item2, RecipeOutput pWriter)
    {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(template),
                        Ingredient.of(item1),
                        Ingredient.of(item2),
                        RecipeCategory.MISC, result)
                .unlocks(item1.getDescriptionId(),has(item1))
                .unlocks(item2.getDescriptionId(),has(item2))
                .save(pWriter,result.getDescriptionId());
    }
    private void charmCrafting(Item result, Item catalyst, Criterion<?> criteria, RecipeOutput pWriter)
    {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.CHARM_FRAGMENT.get()),
                        Ingredient.of(catalyst),
                        Ingredient.of(ModItems.CHARM_FRAGMENT.get()),
                        RecipeCategory.MISC, result)
                .unlocks(result.getDescriptionId() + "criteria",criteria)
                .save(pWriter,result.getDescriptionId());
    }
    static ResourceLocation name(String string)
    {
        return ResourceLocation.fromNamespaceAndPath(Patina.MODID,string);
    }
}
