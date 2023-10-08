package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Patina.MODID);

    //region global tab
    public static final RegistryObject<CreativeModeTab> TAB_1 = TABS.register("main_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.main_tab"))
                    .icon(Items.COPPER_INGOT::getDefaultInstance)
                    .displayItems((params, output) ->
                    {
                        for (RegistryObject<Item> item: ModItems.ITEMS.getEntries())
                        {
                            output.accept(item.get().getDefaultInstance());
                        }
                    })
                    .build());
    //endregion
    //region Tool Tab
    public static final RegistryObject<CreativeModeTab> TOOL_TAB = TABS.register("tool_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.tool_tab"))
                    .icon(ModItems.POTION_FLASK.get()::getDefaultInstance)
                    .displayItems((params, output) ->
                    {
                        output.accept(ModItems.BEE_SWORD.get());
                        output.accept(ModItems.BEE_SHIELD.get());
                        output.accept(ModItems.PIG_CROSSBOW.get());
                        output.accept(ModItems.PIG_SWORD.get());
                        output.accept(ModItems.WITHER_STAFF.get());
                        output.accept(ModItems.WITHER_WINGS.get());

                        output.accept(ModItems.BRONZE_HELMET.get());
                        output.accept(ModItems.BRONZE_CHESTPLATE.get());
                        output.accept(ModItems.BRONZE_LEGGINGS.get());
                        output.accept(ModItems.BRONZE_BOOTS.get());
                        output.accept(ModItems.BRONZE_AXE.get());
                        output.accept(ModItems.BRONZE_PICK.get());
                        output.accept(ModItems.BRONZE_SHOVEL.get());
                        output.accept(ModItems.BRONZE_SWORD.get());
                        output.accept(ModItems.BRONZE_HOE.get());
                    })
                    .build());
    //endregion
    //region salt
    public static final RegistryObject<CreativeModeTab> POTION_TAB = TABS.register("potion_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.potion_tab"))
                    .icon(ModItems.POTION_FLASK.get()::getDefaultInstance)
                    .withSearchBar()
                    .displayItems( (params, output) ->
                    {
                        List<Item> flasks = List.of(ModItems.POTION_FLASK.get(),ModItems.IMPETUS_FLASK.get(),
                            ModItems.MAGNA_FLASK.get(),ModItems.VITA_FLASK.get());
                        for (Item item:flasks)
                        {
                            for (Potion potion: BuiltInRegistries.POTION)
                            {
                                if (potion.getEffects().isEmpty()) continue;
                                ItemStack stack = PotionUtils.setPotion(item.getDefaultInstance(),potion);
                                stack.setDamageValue(stack.getMaxDamage());
                                output.accept(stack);
                            }
                        }
                    })
                    .build());
    //endregion


    public static void register(IEventBus eventBus)
    {
        TABS.register(eventBus);
    }
}
