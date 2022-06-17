package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import com.natesky9.patina.item.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Patina.MOD_ID);
    //add item predicates
    public static final ResourceLocation rustlevel = new ResourceLocation(Patina.MOD_ID,"rustlevel");

    public static final RegistryObject<Item> TEST = ITEMS.register("test",
            () -> new Item(new Item.Properties().tab(Patina.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> CUSTOM_INGOT = ITEMS.register("custom_ingot",
            () -> new Item(new Item.Properties().tab(Patina.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> CUSTOM_NUGGET = ITEMS.register("custom_nugget",
            () -> new Item(new Item.Properties().tab(Patina.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> COAL_COKE = ITEMS.register("coal_coke",
            () -> new CoalCokeItem(new Item.Properties().rarity(Rarity.UNCOMMON).tab(Patina.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> INERT_ROD = ITEMS.register("inert_rod",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).tab(Patina.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> LUXOMETER = ITEMS.register("luxometer",
            () -> new LuxometerItem(new Item.Properties().rarity(Rarity.UNCOMMON).tab(Patina.CREATIVE_MODE_TAB)));


    public static final RegistryObject<Item> CUSTOM_SWORD = ITEMS.register("custom_sword",
            () -> new SwordItem(ModTiers.Custom,2,3f,new Item.Properties().tab(Patina.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> CUSTOM_AXE = ITEMS.register("custom_axe",
            () -> new AxeItem(ModTiers.Custom,2,3f,new Item.Properties().tab(Patina.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> CUSTOM_PICK = ITEMS.register("custom_pick",
            () -> new PickaxeItem(ModTiers.Custom,2,3f,new Item.Properties().tab(Patina.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> CUSTOM_SHOVEL = ITEMS.register("custom_shovel",
            () -> new ShovelItem(ModTiers.Custom,2,3f,new Item.Properties().tab(Patina.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> CUSTOM_HOE = ITEMS.register("custom_hoe",
            () -> new HoeItem(ModTiers.Custom,2,3f,new Item.Properties().tab(Patina.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> VENOM_SWORD = ITEMS.register("venom_sword",
            () -> new VenomSwordItem(ModTiers.Custom,3,3f,new Item.Properties().tab(Patina.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> FIRE_PIPE = ITEMS.register("fire_pipe",
            () -> new FirePipeItem(new Item.Properties().rarity(Rarity.UNCOMMON).tab(Patina.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> PISTON_SHIELD = ITEMS.register("knockback_shield",
            () -> new KnockbackShieldItem(new Item.Properties().rarity(Rarity.UNCOMMON).durability(200).tab(Patina.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> CHARGED_SHEARS = ITEMS.register("charged_shears",
            () -> new ChargedShearsItems(new Item.Properties().rarity(Rarity.UNCOMMON).durability(200).tab(Patina.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> CHARGED_PICK = ITEMS.register("charged_pick",
            () -> new ChargedPickaxeItem(ModTiers.Custom,2,3f,new Item.Properties().tab(Patina.CREATIVE_MODE_TAB)));
    //boss shards

    public static final RegistryObject<Item> BEE_FRAGMENT_1= ITEMS.register("bee_fragment_1",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).tab(Patina.BOSS_LOOT_TAB)));
    public static final RegistryObject<Item> BEE_FRAGMENT_2 = ITEMS.register("bee_fragment_2",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).tab(Patina.BOSS_LOOT_TAB)));
    public static final RegistryObject<Item> BEE_FRAGMENT_3 = ITEMS.register("bee_fragment_3",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).tab(Patina.BOSS_LOOT_TAB)));
    public static final RegistryObject<Item> BEE_FRAGMENT_4 = ITEMS.register("bee_fragment_4",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).tab(Patina.BOSS_LOOT_TAB)));
    public static final RegistryObject<Item> BEE_FRAGMENT_A = ITEMS.register("bee_fragment_a",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).tab(Patina.BOSS_LOOT_TAB)));
    public static final RegistryObject<Item> BEE_FRAGMENT_B = ITEMS.register("bee_fragment_b",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).tab(Patina.BOSS_LOOT_TAB)));
    public static final RegistryObject<Item> BEE_WEAPON = ITEMS.register("bee_weapon",
            () -> (new SwordItem(ModTiers.Boss,3,4f,
                    new Item.Properties().rarity(Rarity.RARE).tab(Patina.BOSS_LOOT_TAB))));

    public static final RegistryObject<Item> PIG_FRAGMENT_1= ITEMS.register("pig_fragment_1",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).tab(Patina.BOSS_LOOT_TAB)));
    public static final RegistryObject<Item> PIG_FRAGMENT_2 = ITEMS.register("pig_fragment_2",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).tab(Patina.BOSS_LOOT_TAB)));
    public static final RegistryObject<Item> PIG_FRAGMENT_3 = ITEMS.register("pig_fragment_3",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).tab(Patina.BOSS_LOOT_TAB)));
    public static final RegistryObject<Item> PIG_FRAGMENT_4 = ITEMS.register("pig_fragment_4",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).tab(Patina.BOSS_LOOT_TAB)));
    public static final RegistryObject<Item> PIG_FRAGMENT_A = ITEMS.register("pig_fragment_a",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).tab(Patina.BOSS_LOOT_TAB)));
    public static final RegistryObject<Item> PIG_FRAGMENT_B = ITEMS.register("pig_fragment_b",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).tab(Patina.BOSS_LOOT_TAB)));
    public static final RegistryObject<Item> PIG_WEAPON = ITEMS.register("pig_weapon",
            () -> (new SwordItem(ModTiers.Boss,5,1f,
                    new Item.Properties().rarity(Rarity.RARE).tab(Patina.BOSS_LOOT_TAB))));
    public static final RegistryObject<Item> PIGLIN_BALLISTA = ITEMS.register("piglin_ballista",
            () -> new CrossbowItem(new Item.Properties().rarity(Rarity.RARE).tab(Patina.BOSS_LOOT_TAB)));
    public static final RegistryObject<Item> WITHER_WEAPON = ITEMS.register("wither_weapon",
            () -> new WitherScepterItem(new Item.Properties().rarity(Rarity.RARE).tab(Patina.BOSS_LOOT_TAB)));
    //end boss shards
    //offhands
    public static final RegistryObject<Item> MELEE_OFFHAND = ITEMS.register("melee_augment",
            () -> new OffhandItem(Attributes.ATTACK_DAMAGE,4,
                    new Item.Properties().rarity(Rarity.RARE).tab(Patina.BOSS_LOOT_TAB)));
    public static final RegistryObject<Item> RANGE_OFFHAND = ITEMS.register("range_augment",
            () -> new OffhandItem(ModAttributes.RANGED_DAMAGE, 2,
                    new Item.Properties().rarity(Rarity.RARE).tab(Patina.BOSS_LOOT_TAB)));
    public static final RegistryObject<Item> MAGIC_OFFHAND = ITEMS.register("magic_augment",
            () -> new OffhandItem(ModAttributes.MAGIC_DAMAGE, 2,
                    new Item.Properties().rarity(Rarity.RARE).tab(Patina.BOSS_LOOT_TAB)));

    //armor//
    public static final RegistryObject<Item> COPPER_HELMET = ITEMS.register("copper_helmet",
            () -> new CopperItem(ModArmorMaterials.COPPER, EquipmentSlot.HEAD,
                    new Item.Properties().tab(Patina.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate",
            () -> new CopperItem(ModArmorMaterials.COPPER, EquipmentSlot.CHEST,
                    new Item.Properties().tab(Patina.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings",
            () -> new CopperItem(ModArmorMaterials.COPPER, EquipmentSlot.LEGS,
                    new Item.Properties().tab(Patina.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> COPPER_BOOTS = ITEMS.register("copper_boots",
            () -> new CopperItem(ModArmorMaterials.COPPER, EquipmentSlot.FEET,
                    new Item.Properties().tab(Patina.CREATIVE_MODE_TAB)));

    public static final RegistryObject<Item> CRYSTAL_HELMET = ITEMS.register("crystal_helmet",
            () -> new CustomArmorItem(ModArmorMaterials.CRYSTAL, EquipmentSlot.HEAD,
                    new Item.Properties().tab(Patina.CREATIVE_MODE_TAB),
                    ModAttributes.MAGIC_DAMAGE,1));
    public static final RegistryObject<Item> CRYSTAL_CHESTPLATE = ITEMS.register("crystal_chestplate",
            () -> new CustomArmorItem(ModArmorMaterials.CRYSTAL, EquipmentSlot.CHEST,
                    new Item.Properties().tab(Patina.CREATIVE_MODE_TAB),
                    ModAttributes.MAGIC_DAMAGE,1));
    public static final RegistryObject<Item> CRYSTAL_LEGGINGS = ITEMS.register("crystal_leggings",
            () -> new CustomArmorItem(ModArmorMaterials.CRYSTAL, EquipmentSlot.LEGS,
                    new Item.Properties().tab(Patina.CREATIVE_MODE_TAB),
                    ModAttributes.MAGIC_DAMAGE,1));
    public static final RegistryObject<Item> CRYSTAL_BOOTS = ITEMS.register("crystal_boots",
            () -> new CustomArmorItem(ModArmorMaterials.CRYSTAL, EquipmentSlot.FEET,
                    new Item.Properties().tab(Patina.CREATIVE_MODE_TAB),
                    ModAttributes.MAGIC_DAMAGE,1));

    public static final RegistryObject<Item> ARCHER_HELMET = ITEMS.register("archer_helmet",
            () -> new CustomArmorItem(ModArmorMaterials.CRYSTAL, EquipmentSlot.HEAD,
                    new Item.Properties().tab(Patina.CREATIVE_MODE_TAB),
                    ModAttributes.RANGED_DAMAGE,1));
    public static final RegistryObject<Item> ARCHER_CHESTPLATE = ITEMS.register("archer_chestplate",
            () -> new CustomArmorItem(ModArmorMaterials.CRYSTAL, EquipmentSlot.CHEST,
                    new Item.Properties().tab(Patina.CREATIVE_MODE_TAB),
                    ModAttributes.RANGED_DAMAGE,1));
    public static final RegistryObject<Item> ARCHER_LEGGINGS = ITEMS.register("archer_leggings",
            () -> new CustomArmorItem(ModArmorMaterials.CRYSTAL, EquipmentSlot.LEGS,
                    new Item.Properties().tab(Patina.CREATIVE_MODE_TAB),
                    ModAttributes.RANGED_DAMAGE,1));
    public static final RegistryObject<Item> ARCHER_BOOTS = ITEMS.register("archer_boots",
            () -> new CustomArmorItem(ModArmorMaterials.CRYSTAL, EquipmentSlot.FEET,
                    new Item.Properties().tab(Patina.CREATIVE_MODE_TAB),
                    ModAttributes.RANGED_DAMAGE,1));
    //foods
    public static final RegistryObject<Item> TEST_FOOD = ITEMS.register("test_food",
            () -> new CustomFood(new Item.Properties().food(ModFoods.TEST_FOOD)
                    .tab(Patina.FOOD_TAB)));
    public static final RegistryObject<Item> BLINK_BROWNIE = ITEMS.register("blink_brownie",
            () -> new BlinkBrownie(new Item.Properties().food(ModFoods.BLINK_BROWNIE)
                    .tab(Patina.FOOD_TAB)));
    public static final RegistryObject<Item> CANDY_WARTS = ITEMS.register("candy_warts",
            () -> new Item(new Item.Properties().food(ModFoods.CANDY_WARTS)
                    .tab(Patina.FOOD_TAB)));
    public static final RegistryObject<Item> CHEESE = ITEMS.register("cheese",
            () -> new Item(new Item.Properties().food(Foods.APPLE)
                    .tab(Patina.FOOD_TAB)));
    public static final RegistryObject<Item> MONSTER_MEATBALLS = ITEMS.register("monster_meatballs",
            () -> new Item(new Item.Properties().food(ModFoods.TEST_FOOD)
                    .tab(Patina.FOOD_TAB)));
    public static final RegistryObject<Item> TRAIL_MIX = ITEMS.register("trail_mix",
            () -> new Item(new Item.Properties().food(ModFoods.TEST_FOOD)
                    .tab(Patina.FOOD_TAB)));
    public static final RegistryObject<Item> SWEETS = ITEMS.register("sweets",
            () -> new Item(new Item.Properties().food(ModFoods.TEST_FOOD)
                    .tab(Patina.FOOD_TAB)));

    //potion salt
    public static final RegistryObject<Item> MAGIC_SALT = ITEMS.register("magic_salt",
            () -> new MagicSalt(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(16)
                    .tab(Patina.FOOD_TAB)));
    public static final RegistryObject<Item> VOID_SALT = ITEMS.register("void_salt",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(32).fireResistant()
                    .tab(Patina.CREATIVE_MODE_TAB)));

    //loot
    public static final RegistryObject<Item> ROYAL_JELLY = ITEMS.register("royal_jelly",
            () -> new Item(new Item.Properties()
                    .tab(Patina.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> SPIDER_NEST = ITEMS.register("spider_nest",
            () -> new Item(new Item.Properties()
                    .tab(Patina.CREATIVE_MODE_TAB)));
    //seeds
    public static final RegistryObject<Item> HERB_SEEDS = ITEMS.register("herb_seeds",
            () -> new ItemNameBlockItem(ModBlocks.HERB_BLOCK.get(),
                    new Item.Properties()
                            .rarity(Rarity.UNCOMMON)
                            .tab(Patina.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> HERB = ITEMS.register("herb",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.UNCOMMON)
                    .food(ModFoods.HERB)));
    public static final RegistryObject<Item> FERTILIZER = ITEMS.register("fertilizer",
            () -> new FertilizerItem(new Item.Properties()
                    .rarity(Rarity.UNCOMMON)
                    .tab(Patina.CREATIVE_MODE_TAB)));
    //resources
    //public static final RegistryObject<Item> CALCITE_DUST = ITEMS.register("calcite_dust",
    //        () -> new Item(new Item.Properties()
    //                .tab(Patina.CREATIVE_MODE_TAB)));
    //public static final RegistryObject<Item> KELP_MEAL = ITEMS.register("kelp_meal",
    //        () -> new Item(new Item.Properties()
    //                .tab(Patina.CREATIVE_MODE_TAB)));
    //public static final RegistryObject<Item> CRUSHED_SHELLS = ITEMS.register("crushed_shells",
    //        () -> new Item(new Item.Properties()
    //                .tab(Patina.CREATIVE_MODE_TAB)));
    //--------------------------------------------------//

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
