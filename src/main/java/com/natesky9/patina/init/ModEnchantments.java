package com.natesky9.patina.init;

import com.natesky9.patina.Enchantment.*;
import com.natesky9.patina.Patina;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.ArrayUtils;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS
            = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Patina.MODID);
    //why isn't this public?
    private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
    private static final EquipmentSlot[] HAND_SLOTS = new EquipmentSlot[]{EquipmentSlot.OFFHAND,EquipmentSlot.MAINHAND};
    private static final EquipmentSlot[] ALL_SLOTS = ArrayUtils.addAll(ARMOR_SLOTS,HAND_SLOTS);
    //
    //Curses
    public static RegistryObject<Enchantment> CURSEENVY =
            ENCHANTMENTS.register("envy",
                    () -> new EnvyEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.BREAKABLE,ALL_SLOTS));
    public static RegistryObject<Enchantment> CURSEGLUTTONY =
            ENCHANTMENTS.register("gluttony",
                    () -> new GluttonyEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> CURSEPRIDE =
            ENCHANTMENTS.register("pride",
                    () -> new PrideEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> CURSEGREED =
            ENCHANTMENTS.register("greed",
                    () -> new GreedEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> CURSESLOTH =
            ENCHANTMENTS.register("sloth",
                    () -> new SlothEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> CURSELUST =
            ENCHANTMENTS.register("lust",
                    () -> new LustEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> CURSEWRATH =
            ENCHANTMENTS.register("wrath",
                    () -> new WrathEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    //Blessings
    public static RegistryObject<Enchantment> BLESSINGENVY =
            ENCHANTMENTS.register("strife",
                    () -> new StrifeEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> BLESSINGGLUTTONY =
            ENCHANTMENTS.register("brimful",
                    () -> new BrimfulEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> BLESSINGGREED =
            ENCHANTMENTS.register("avarice",
                    () -> new AvariceEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> BLESSINGLUST =
            ENCHANTMENTS.register("coercion",
                    () -> new CoercionEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> BLESSINGPRIDE =
            ENCHANTMENTS.register("humility",
                    () -> new HumilityEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> BLESSINGSLOTH =
            ENCHANTMENTS.register("apathy",
                    () -> new ApathyEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> BLESSINGWRATH =
            ENCHANTMENTS.register("retribution",
                    () -> new RetributionEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ARMOR_SLOTS));
    //
    public static RegistryObject<Enchantment> BLESSINGSOULBOUND =
            ENCHANTMENTS.register("soulbound",
                    () -> new SoulboundEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ALL_SLOTS));
    //
    public static void register(IEventBus eventBus)
    {
        ENCHANTMENTS.register(eventBus);
    }
}
