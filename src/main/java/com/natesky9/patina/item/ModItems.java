package com.natesky9.patina.item;

import com.natesky9.patina.Patina;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Patina.MOD_ID);

    public static final RegistryObject<Item> TEST = ITEMS.register("test",
            () -> new Item(new Item.Properties().tab(Patina.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> CUSTOM_INGOT = ITEMS.register("custom_ingot",
            () -> new Item(new Item.Properties().tab(Patina.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> CUSTOM_NUGGET = ITEMS.register("custom_nugget",
            () -> new Item(new Item.Properties().tab(Patina.CREATIVE_MODE_TAB)));

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
            () -> new SwordItem(ModTiers.Custom,3,3f,new Item.Properties().tab(Patina.CREATIVE_MODE_TAB)));

    //--------------------------------------------------//
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
