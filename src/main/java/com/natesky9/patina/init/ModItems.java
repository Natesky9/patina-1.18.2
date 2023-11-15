package com.natesky9.patina.init;

import com.natesky9.patina.Item.*;
import com.natesky9.patina.Item.flasks.ImpetusFlask;
import com.natesky9.patina.Item.flasks.PotionFlaskItem;
import com.natesky9.patina.Item.flasks.SemiVitaFlask;
import com.natesky9.patina.Patina;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Patina.MODID);
    //add items here
    //region shards
    //buzz
    public static final RegistryObject<Item> BEE_FRAGMENT_1= ITEMS.register("bee_fragment_1",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> BEE_FRAGMENT_2 = ITEMS.register("bee_fragment_2",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> BEE_FRAGMENT_3 = ITEMS.register("bee_fragment_3",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> BEE_FRAGMENT_4 = ITEMS.register("bee_fragment_4",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> BEE_FRAGMENT_A = ITEMS.register("bee_fragment_a",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> BEE_FRAGMENT_B = ITEMS.register("bee_fragment_b",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> BEE_FRAGMENT_C = ITEMS.register("bee_fragment_c",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> BEE_FRAGMENT_D = ITEMS.register("bee_fragment_d",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> BEE_SWORD = ITEMS.register("bee_sword",
            () -> (new BeeWeaponItem(ModTiers.BOSS,2,-1.8F,
                    new Item.Properties().rarity(Rarity.RARE))));
    public static final RegistryObject<Item> BEE_SHIELD = ITEMS.register("bee_shield",
            () -> new BeeShieldItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    //oink
    public static final RegistryObject<Item> PIG_FRAGMENT_1= ITEMS.register("pig_fragment_1",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> PIG_FRAGMENT_2 = ITEMS.register("pig_fragment_2",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> PIG_FRAGMENT_3 = ITEMS.register("pig_fragment_3",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> PIG_FRAGMENT_4 = ITEMS.register("pig_fragment_4",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> PIG_FRAGMENT_A = ITEMS.register("pig_fragment_a",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> PIG_FRAGMENT_B = ITEMS.register("pig_fragment_b",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> PIG_FRAGMENT_C = ITEMS.register("pig_fragment_c",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> PIG_FRAGMENT_D = ITEMS.register("pig_fragment_d",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> PIG_SWORD = ITEMS.register("pig_sword",
            () -> (new PigWeaponItem(ModTiers.BOSS,4,-3.0F,
                    new Item.Properties().rarity(Rarity.RARE))));
    public static final RegistryObject<Item> PIG_CROSSBOW = ITEMS.register("pig_crossbow",
            () -> new PigCrossbowItem(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    //spooky
    public static final RegistryObject<Item>  WITHER_FRAGMENT_1 = ITEMS.register("wither_fragment_1",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item>  WITHER_FRAGMENT_2 = ITEMS.register("wither_fragment_2",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item>  WITHER_FRAGMENT_3 = ITEMS.register("wither_fragment_3",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item>  WITHER_FRAGMENT_4 = ITEMS.register("wither_fragment_4",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item>  WITHER_FRAGMENT_A = ITEMS.register("wither_fragment_a",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item>  WITHER_FRAGMENT_B = ITEMS.register("wither_fragment_b",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item>  WITHER_FRAGMENT_C = ITEMS.register("wither_fragment_c",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item>  WITHER_FRAGMENT_D = ITEMS.register("wither_fragment_d",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item>  WITHER_STAFF = ITEMS.register("wither_staff",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item>  WITHER_WINGS = ITEMS.register("wither_wings",
            () -> new ElytraItem(new Item.Properties().rarity(Rarity.RARE)));
    //endregion shards
    //region copper
    public static final RegistryObject<Item> COPPER_SWORD = ITEMS.register("copper_sword",
            () -> new SwordItem(ModTiers.COPPER,3,-2.4F, new Item.Properties())
            {@Override public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
                    RustableItem.rust(pStack);}
            @Override public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
                    return false;}});
    public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe",
            () -> new AxeItem(ModTiers.COPPER,7,-3.0F,new Item.Properties())
            {@Override public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
                RustableItem.rust(pStack);}
                @Override public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
                    return false;}});
    public static final RegistryObject<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel",
            () -> new ShovelItem(ModTiers.COPPER,1.5F,-3.0F,new Item.Properties())
            {@Override public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
                RustableItem.rust(pStack);}
                @Override public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
                    return false;}});
    public static final RegistryObject<Item> COPPER_PICK = ITEMS.register("copper_pick",
            () -> new PickaxeItem(ModTiers.COPPER,1,-2.8F,new Item.Properties())
            {@Override public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
                RustableItem.rust(pStack);}
                @Override public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
                    return false;}});
    public static final RegistryObject<Item> COPPER_HOE = ITEMS.register("copper_hoe",
            () -> new HoeItem(ModTiers.COPPER,0,-2.0F,new Item.Properties())
            {@Override public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
                RustableItem.rust(pStack);}
                @Override public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
                    return false;}});
    public static final RegistryObject<Item> COPPER_HELMET = ITEMS.register("copper_helmet",
            () -> new ArmorItem(ModArmorMaterials.COPPER,ArmorItem.Type.HELMET,new Item.Properties())
            {@Override public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
                RustableItem.rust(pStack);}
                @Override public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
                    return false;}});
    public static final RegistryObject<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate",
            () -> new ArmorItem(ModArmorMaterials.COPPER,ArmorItem.Type.CHESTPLATE,new Item.Properties())
            {@Override public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
                RustableItem.rust(pStack);}
                @Override public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
                    return false;}});
    public static final RegistryObject<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings",
            () -> new ArmorItem(ModArmorMaterials.COPPER,ArmorItem.Type.LEGGINGS,new Item.Properties())
            {@Override public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
                RustableItem.rust(pStack);}
                @Override public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
                    return false;}});
    public static final RegistryObject<Item> COPPER_BOOTS = ITEMS.register("copper_boots",
            () -> new ArmorItem(ModArmorMaterials.COPPER,ArmorItem.Type.BOOTS,new Item.Properties())
            {@Override public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
                RustableItem.rust(pStack);}
                @Override public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
                    return false;}});
    //endregion copper
    //region bronze
    public static final RegistryObject<Item> BRONZE_SWORD = ITEMS.register("bronze_sword",
            () -> new SwordItem(ModTiers.BRONZE,3,-2.4F, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_AXE = ITEMS.register("bronze_axe",
            () -> new AxeItem(ModTiers.BRONZE,7,-3.0F,new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_SHOVEL = ITEMS.register("bronze_shovel",
            () -> new ShovelItem(ModTiers.BRONZE,1.5F,-3.0F,new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_PICK = ITEMS.register("bronze_pick",
            () -> new PickaxeItem(ModTiers.BRONZE,1,-2.8F,new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_HOE = ITEMS.register("bronze_hoe",
            () -> new HoeItem(ModTiers.BRONZE,0,-2.0F,new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_HELMET = ITEMS.register("bronze_helmet",
            () -> new ArmorItem(ModArmorMaterials.BRONZE,ArmorItem.Type.HELMET,new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_CHESTPLATE = ITEMS.register("bronze_chestplate",
            () -> new ArmorItem(ModArmorMaterials.BRONZE,ArmorItem.Type.CHESTPLATE,new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_LEGGINGS = ITEMS.register("bronze_leggings",
            () -> new ArmorItem(ModArmorMaterials.BRONZE,ArmorItem.Type.LEGGINGS,new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_BOOTS = ITEMS.register("bronze_boots",
            () -> new ArmorItem(ModArmorMaterials.BRONZE,ArmorItem.Type.BOOTS,new Item.Properties()));
    //endregion bronze
    //region dragon
    public static final RegistryObject<Item> DRAGON_HELMET = ITEMS.register("dragon_helmet",
            () -> new ArmorItem(ModArmorMaterials.DRAGON,ArmorItem.Type.HELMET,new Item.Properties()));
    public static final RegistryObject<Item> DRAGON_CHESTPLATE = ITEMS.register("dragon_chestplate",
            () -> new ArmorItem(ModArmorMaterials.DRAGON,ArmorItem.Type.CHESTPLATE,new Item.Properties()));
    public static final RegistryObject<Item> DRAGON_LEGGINGS = ITEMS.register("dragon_leggings",
            () -> new ArmorItem(ModArmorMaterials.DRAGON,ArmorItem.Type.LEGGINGS,new Item.Properties()));
    public static final RegistryObject<Item> DRAGON_BOOTS = ITEMS.register("dragon_boots",
            () -> new ArmorItem(ModArmorMaterials.DRAGON,ArmorItem.Type.BOOTS,new Item.Properties()));

    //endregion dragon
    //region charms
    public static final RegistryObject<Item> CHARM_AMBUSH = ITEMS.register("charm_ambush",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CHARM_EXPERIENCE = ITEMS.register("charm_experience",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CHARM_VITALITY = ITEMS.register("charm_vitality",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CHARM_FERTILITY = ITEMS.register("charm_fertility",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CHARM_ALCHEMY = ITEMS.register("charm_alchemy",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CHARM_CONTRABAND = ITEMS.register("charm_contraband",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CHARM_DETONATION = ITEMS.register("charm_detonation",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CHARM_WARDING = ITEMS.register("charm_warding",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final RegistryObject<Item> CHARM_FRAGMENT = ITEMS.register("charm_fragment",
            CharmFragmentItem::makeFragment);
    //region magic
    public static final RegistryObject<Item> ESSENCE = ITEMS.register("essence",
            () -> new EssenceItem(new Item.Properties()
                    .rarity(Rarity.UNCOMMON).fireResistant().stacksTo(1)));
    //endregion magic
    //region potions/ores
    public static final RegistryObject<Item> POTION_SALT = ITEMS.register("potion_salt",
            () -> new PotionSaltItem(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> VOID_SALT = ITEMS.register("void_salt",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> BISMUTH_ORE = ITEMS.register("bismuth_ore",
            () -> new BlockItem(ModBlocks.BISMUTH_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BISMUTH_NUGGET = ITEMS.register("bismuth_nugget",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BISMUTH_INGOT = ITEMS.register("bismuth_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DRAGON_SCALE = ITEMS.register("dragon_scale",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> POTION_FLASK = ITEMS.register("potion_flask",
            () -> new PotionFlaskItem(new Item.Properties().rarity(Rarity.UNCOMMON)
                    .durability(3)));
    public static final RegistryObject<Item> IMPETUS_FLASK = ITEMS.register("potion_flask_impetus",
            () -> new ImpetusFlask(new Item.Properties().rarity(Rarity.UNCOMMON)
                    .durability(4)));
    public static final RegistryObject<Item> VITA_FLASK = ITEMS.register("potion_flask_vita",
            () -> new SemiVitaFlask(new Item.Properties().rarity(Rarity.UNCOMMON)
                    .durability(4)));
    public static final RegistryObject<Item> MAGNA_FLASK = ITEMS.register("potion_flask_magna",
            () -> new PotionFlaskItem(new Item.Properties().rarity(Rarity.UNCOMMON)
                    .durability(6)));

    public static final RegistryObject<Item> PRIME_GLASS = ITEMS.register("prime_glass",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> ANIMA_GLASS = ITEMS.register("anima_glass",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> FERUS_GLASS = ITEMS.register("ferus_glass",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> FORTIS_GLASS = ITEMS.register("fortis_glass",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> SMITHING_FLUX = ITEMS.register("smithing_flux",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> MALACHITE = ITEMS.register("malachite",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    //endregion potion/ores
    //region food
    public static final RegistryObject<Item> FOOD_MEATBALLS = ITEMS.register("meatballs",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FOOD_HONEY_NUGGETS = ITEMS.register("honey_nuggets",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FOOD_CHILI = ITEMS.register("chili",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FOOD_ICECREAM = ITEMS.register("icecream",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FOOD_BURGER = ITEMS.register("borger",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FOOD_SKEWERS = ITEMS.register("skewers",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FOOD_SWEETS = ITEMS.register("sweets",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FOOD_APPLE_PIE = ITEMS.register("apple_pie",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FOOD_BLINK_BROWNIE = ITEMS.register("blink_brownie",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FOOD_TRIPLE_MEAT_TREAT = ITEMS.register("triple_meat_treat",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FOOD_PIEROGI = ITEMS.register("pierogi",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FOOD_OMELETTE = ITEMS.register("omelette",
            () -> new Item(new Item.Properties()));
    //test for tier 2 foods?
    public static final RegistryObject<Item> FOOD_LOAF = ITEMS.register("loaf",
            () -> new Item(new Item.Properties()));


    //endregion food
    //
    public static void register(IEventBus eventBus)
    {ITEMS.register(eventBus);}
}
