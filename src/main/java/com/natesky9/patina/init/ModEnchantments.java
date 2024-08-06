package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;

public class ModEnchantments {
    //enchantments changed in 1.21 to be resourcekeys
    public static final DeferredRegister<Enchantment> ENCHANTMENTS
            = DeferredRegister.create(Registries.ENCHANTMENT, Patina.MODID);

    //region enchants
    public static final ResourceKey<Enchantment> CURSE_ENVY = key("envy");
    public static final ResourceKey<Enchantment> CURSE_GLUTTONY = key("gluttony");
    public static final ResourceKey<Enchantment> CURSE_PRIDE = key("pride");
    public static final ResourceKey<Enchantment> CURSE_GREED = key("greed");
    public static final ResourceKey<Enchantment> CURSE_SLOTH = key("sloth");
    public static final ResourceKey<Enchantment> CURSE_LUST = key("lust");
    public static final ResourceKey<Enchantment> CURSE_WRATH = key("wrath");

    public static final ResourceKey<Enchantment> BLESSING_ENVY = key("strife");
    public static final ResourceKey<Enchantment> BLESSING_GLUTTONY = key("plethora");
    public static final ResourceKey<Enchantment> BLESSING_PRIDE = key("humility");
    public static final ResourceKey<Enchantment> BLESSING_GREED = key("avarice");
    public static final ResourceKey<Enchantment> BLESSING_SLOTH = key("ambition");
    public static final ResourceKey<Enchantment> BLESSING_LUST = key("coercion");
    public static final ResourceKey<Enchantment> BLESSING_WRATH = key("retribution");

    public static final ResourceKey<Enchantment> BLESSING_VANISHING = key("soulbound");
    //endregion enchants

    //region bootstrap
    public static void bootstrap(BootstrapContext<Enchantment> context)
    {
        HolderGetter<DamageType> holderDamageType = context.lookup(Registries.DAMAGE_TYPE);
        HolderGetter<Enchantment> holderEnchantment = context.lookup(Registries.ENCHANTMENT);
        HolderGetter<Item> holderItem = context.lookup(Registries.ITEM);
        HolderGetter<Block> holderBlock = context.lookup(Registries.BLOCK);
        //
        //EnchantmentDefinition
        //  HolderSet<Item> supportedItems,
        //  Optional<HolderSet<Item>> primaryItems,
        //  int weight,
        //  int maxLevel,
        //  Enchantment.Cost minCost,
        //  Enchantment.Cost maxCost,
        //  int anvilCost,
        //  List<EquipmentSlotGroup> slots
        register(context, CURSE_ENVY,
                Enchantment.enchantment(Enchantment.definition(holderItem.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                        10,1, Enchantment.constantCost(1),
                        Enchantment.constantCost(1),
                        1, EquipmentSlotGroup.HEAD))
                        .exclusiveWith(holderEnchantment.getOrThrow(ModTags.CURSE_EXCLUSIVE)));
        register(context, CURSE_GLUTTONY,
                Enchantment.enchantment(Enchantment.definition(holderItem.getOrThrow(ItemTags.CHEST_ARMOR_ENCHANTABLE),
                        10,1,Enchantment.dynamicCost(1,1),
                        Enchantment.constantCost(1),
                        1, EquipmentSlotGroup.CHEST))
                        .exclusiveWith(holderEnchantment.getOrThrow(ModTags.CURSE_EXCLUSIVE)));
        register(context, CURSE_GREED,
                Enchantment.enchantment(Enchantment.definition(holderItem.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                10,1,Enchantment.constantCost(1),
                                Enchantment.constantCost(1),
                                1,EquipmentSlotGroup.LEGS))
                        .exclusiveWith(holderEnchantment.getOrThrow(ModTags.CURSE_EXCLUSIVE)));
        register(context, CURSE_LUST,
                Enchantment.enchantment(Enchantment.definition(holderItem.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                        10,1,Enchantment.constantCost(1),
                        Enchantment.constantCost(1),
                        1,EquipmentSlotGroup.FEET))
                        .exclusiveWith(holderEnchantment.getOrThrow(ModTags.CURSE_EXCLUSIVE)));
        register(context, CURSE_SLOTH,
                Enchantment.enchantment(Enchantment.definition(holderItem.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                                10,1,Enchantment.constantCost(1),
                                Enchantment.constantCost(1),
                                1,EquipmentSlotGroup.LEGS))
                        .exclusiveWith(holderEnchantment.getOrThrow(ModTags.CURSE_EXCLUSIVE)));
        register(context, CURSE_WRATH,
                Enchantment.enchantment(Enchantment.definition(holderItem.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                                10,1,Enchantment.constantCost(1),
                                Enchantment.constantCost(1),
                                1,EquipmentSlotGroup.ANY))
                        .exclusiveWith(holderEnchantment.getOrThrow(ModTags.CURSE_EXCLUSIVE)));
        register(context, CURSE_PRIDE,
                Enchantment.enchantment(Enchantment.definition(holderItem.getOrThrow(ItemTags.LEG_ARMOR_ENCHANTABLE),
                                10,1,Enchantment.constantCost(1),
                                Enchantment.constantCost(1),
                                1,EquipmentSlotGroup.LEGS))
                        .exclusiveWith(holderEnchantment.getOrThrow(ModTags.CURSE_EXCLUSIVE)));
    }
    //endregion bootstrap
    //
    private static ResourceKey<Enchantment> key(String string) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(Patina.MODID,string));
    }
    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> resourceKey, Enchantment.Builder builder) {
        context.register(resourceKey, builder.build(resourceKey.location()));
    }
    //everything below this is old code---------------------------------------//
//
//
    ////why isn't this public?
    //private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
    //private static final EquipmentSlot[] HAND_SLOTS = new EquipmentSlot[]{EquipmentSlot.OFFHAND,EquipmentSlot.MAINHAND};
    //private static final EquipmentSlot[] ALL_SLOTS = ArrayUtils.addAll(ARMOR_SLOTS,HAND_SLOTS);
    ////
    ////Curses
    //public static RegistryObject<Enchantment> CURSEENVY =
    //        ENCHANTMENTS.register("envy",
    //                () -> new EnvyEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.BREAKABLE,ALL_SLOTS));
    //public static RegistryObject<Enchantment> CURSEGLUTTONY =
    //        ENCHANTMENTS.register("gluttony",
    //                () -> new GluttonyEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    //public static RegistryObject<Enchantment> CURSEPRIDE =
    //        ENCHANTMENTS.register("pride",
    //                () -> new PrideEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    //public static RegistryObject<Enchantment> CURSEGREED =
    //        ENCHANTMENTS.register("greed",
    //                () -> new GreedEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    //public static RegistryObject<Enchantment> CURSESLOTH =
    //        ENCHANTMENTS.register("sloth",
    //                () -> new SlothEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    //public static RegistryObject<Enchantment> CURSELUST =
    //        ENCHANTMENTS.register("lust",
    //                () -> new LustEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    //public static RegistryObject<Enchantment> CURSEWRATH =
    //        ENCHANTMENTS.register("wrath",
    //                () -> new WrathEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    ////Blessings
    //public static RegistryObject<Enchantment> BLESSINGENVY =
    //        ENCHANTMENTS.register("strife",
    //                () -> new StrifeEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    //public static RegistryObject<Enchantment> BLESSINGGLUTTONY =
    //        ENCHANTMENTS.register("brimful",
    //                () -> new BrimfulEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    //public static RegistryObject<Enchantment> BLESSINGGREED =
    //        ENCHANTMENTS.register("avarice",
    //                () -> new AvariceEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    //public static RegistryObject<Enchantment> BLESSINGLUST =
    //        ENCHANTMENTS.register("coercion",
    //                () -> new CoercionEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    //public static RegistryObject<Enchantment> BLESSINGPRIDE =
    //        ENCHANTMENTS.register("humility",
    //                () -> new HumilityEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    //public static RegistryObject<Enchantment> BLESSINGSLOTH =
    //        ENCHANTMENTS.register("apathy",
    //                () -> new ApathyEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    //public static RegistryObject<Enchantment> BLESSINGWRATH =
    //        ENCHANTMENTS.register("retribution",
    //                () -> new RetributionEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    ////
    //public static RegistryObject<Enchantment> BLESSINGSOULBOUND =
    //        ENCHANTMENTS.register("soulbound",
    //                () -> new SoulboundEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ALL_SLOTS));
    ////depreciated deferred register (pre 1.21)
    ////public static void register(IEventBus eventBus)
    //{
    //    ENCHANTMENTS.register(eventBus);
    //}
}
