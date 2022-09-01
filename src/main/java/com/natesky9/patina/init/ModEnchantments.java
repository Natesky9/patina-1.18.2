package com.natesky9.patina.init;

import com.natesky9.patina.Enchantments.*;
import com.natesky9.patina.Patina;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.ArrayUtils;

public class ModEnchantments
{
    public static final DeferredRegister<Enchantment> ENCHANTMENTS
            = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Patina.MOD_ID);
    //why this isn't public in the enchantment class, I do not know

    private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
    private static final EquipmentSlot[] HAND_SLOTS = new EquipmentSlot[]{EquipmentSlot.OFFHAND,EquipmentSlot.MAINHAND};
    private static final EquipmentSlot[] ALL_SLOTS = ArrayUtils.addAll(ARMOR_SLOTS,HAND_SLOTS);
    //----------//
    public static RegistryObject<Enchantment> CURSE =
            ENCHANTMENTS.register("curse",
                    () -> new Curse(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.ARMOR,ARMOR_SLOTS));
    public static RegistryObject<Enchantment> CURSE_OF_OVERLOAD =
            ENCHANTMENTS.register("overload",
                    () -> new CurseOverload(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.ARMOR,HAND_SLOTS));
    public static RegistryObject<Enchantment> CURSE_OF_HUNGER =
            ENCHANTMENTS.register("hunger",
                    () -> new CurseHunger(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.ARMOR_CHEST,EquipmentSlot.CHEST));
    public static RegistryObject<Enchantment> CURSE_SUNSCORNED =
            ENCHANTMENTS.register("sunscorned",
                    () -> new CurseSunscorned(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.ARMOR_HEAD,EquipmentSlot.HEAD));
    public static RegistryObject<Enchantment> CURSE_OF_BRITTLENESS =
            ENCHANTMENTS.register("brittleness",
                    () -> new CurseBrittle(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,ALL_SLOTS));

    //deadly sins
    public static RegistryObject<Enchantment> ENVY =
            ENCHANTMENTS.register("envy",
                    () -> new EnvyEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,EquipmentSlot.CHEST));
    public static RegistryObject<Enchantment> GREED =
            ENCHANTMENTS.register("greed",
                    () -> new GreedEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,EquipmentSlot.CHEST));
    public static RegistryObject<Enchantment> GLUTTONY =
            ENCHANTMENTS.register("gluttony",
                    () -> new GluttonyEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,EquipmentSlot.CHEST));
    public static RegistryObject<Enchantment> LUST =
            ENCHANTMENTS.register("lust",
                    () -> new LustEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,EquipmentSlot.CHEST));
    public static RegistryObject<Enchantment> PRIDE =
            ENCHANTMENTS.register("pride",
                    () -> new PrideEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,EquipmentSlot.CHEST));
    public static RegistryObject<Enchantment> SLOTH =
            ENCHANTMENTS.register("sloth",
                    () -> new SlothEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,EquipmentSlot.CHEST));
    public static RegistryObject<Enchantment> Wrath =
            ENCHANTMENTS.register("wrath",
                    () -> new WrathEnchantment(Enchantment.Rarity.VERY_RARE,EnchantmentCategory.BREAKABLE,EquipmentSlot.CHEST));
    //----------//
    public static void register(IEventBus eventBus)
    {
        ENCHANTMENTS.register(eventBus);
    }
}
