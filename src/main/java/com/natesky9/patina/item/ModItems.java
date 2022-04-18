package com.natesky9.patina.item;

import com.natesky9.patina.Patina;
import net.minecraft.world.item.Item;
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

    //--------------------------------------------------//
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
