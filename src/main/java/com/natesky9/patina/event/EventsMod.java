package com.natesky9.patina.event;

import com.natesky9.patina.Item.PotionFlaskItem;
import com.natesky9.patina.Item.RustableItem;
import com.natesky9.patina.Patina;
import com.natesky9.patina.datagen.DataGenerators;
import com.natesky9.patina.init.ModCreativeModeTab;
import com.natesky9.patina.init.ModItems;
import com.natesky9.patina.init.ModPotions;
import com.natesky9.patina.init.ModScreens;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

@Mod.EventBusSubscriber(modid = Patina.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventsMod {
    @SubscribeEvent
    public static void CreativeMenu(CreativeModeTabEvent.BuildContents event)
    {
        ModCreativeModeTab.addItem(event);

    }

    @SubscribeEvent
    public static void Tab(CreativeModeTabEvent.Register event)
    {
        ModCreativeModeTab.registerCreativeModeTabs(event);
    }
    @SubscribeEvent
    public static void ColorHandlerEvent(final RegisterColorHandlersEvent.Item event)
    {
        //potion flask
        event.register((pStack, pTintIndex) ->
                pTintIndex == 1 ? PotionUtils.getColor(pStack) : -1
            ,ModItems.POTION_FLASK.get());
        //salts
        event.register((pStack, pTintIndex) ->
                pTintIndex == 0 ? PotionUtils.getColor(pStack) : -1,ModItems.POTION_SALT.get());
        //tint on layer 1
        event.register(((pStack, pTintIndex) -> pTintIndex == 1 ? RustableItem.getRust(pStack):-1),
                ModItems.COPPER_AXE.get(),ModItems.COPPER_HOE.get(),ModItems.COPPER_SWORD.get(),
                ModItems.COPPER_PICK.get(),ModItems.COPPER_SHOVEL.get());
        event.register(((pStack, pTintIndex) ->
                {return pTintIndex == 1 ? RustableItem.getSheen(Minecraft.getInstance().level) : -1;}),
                ModItems.BRONZE_AXE.get(),ModItems.BRONZE_HOE.get(),ModItems.BRONZE_SWORD.get(),
                ModItems.BRONZE_PICK.get(),ModItems.BRONZE_SHOVEL.get());
        //tint on layer 0
        event.register(((pStack, pTintIndex) -> pTintIndex == 0 ? RustableItem.getRust(pStack) : -1),
                ModItems.COPPER_HELMET.get(),ModItems.COPPER_CHESTPLATE.get(),
                ModItems.COPPER_LEGGINGS.get(),ModItems.COPPER_BOOTS.get());
        event.register(((pStack, pTintIndex) ->
        {return RustableItem.getSheen(Minecraft.getInstance().level);}),
                ModItems.BRONZE_INGOT.get(),ModItems.BRONZE_HELMET.get(),ModItems.BRONZE_CHESTPLATE.get(),
                ModItems.BRONZE_LEGGINGS.get(),ModItems.BRONZE_BOOTS.get());
    }
    @SubscribeEvent
    public static void gatherDataEvent(GatherDataEvent event)
    {
        DataGenerators.gatherData(event);
    }
    @SubscribeEvent
    public static void doCommonStuff(final FMLCommonSetupEvent event)
    {
        ModPotions.removePotions();
        ModPotions.addNormalPotions();
        ModPotions.addSaltPotions();
    }
    @SubscribeEvent
    public static void doClientStuff(FMLClientSetupEvent event)
    {

        ModScreens.register();
        ItemProperties.register(ModItems.PIG_CROSSBOW.get(), new ResourceLocation("pull"),
                (p_174610_, p_174611_, p_174612_, p_174613_) -> {
                    if (p_174612_ == null) {return 0.0F;}
                    else {
                        return CrossbowItem.isCharged(p_174610_) ? 0.0F : (float)(p_174610_.getUseDuration() -
                                p_174612_.getUseItemRemainingTicks()) / (float)CrossbowItem.getChargeDuration(p_174610_);
                    }});

        ItemProperties.register(ModItems.PIG_CROSSBOW.get(), new ResourceLocation("pulling"),
                (itemStack, level, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == itemStack && !CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F);
        ItemProperties.register(ModItems.PIG_CROSSBOW.get(), new ResourceLocation("charged"),
                (itemStack, level, entity, seed) -> CrossbowItem.isCharged(itemStack) ? 1.0F : 0.0F);
        ItemProperties.register(ModItems.POTION_FLASK.get(), new ResourceLocation("capacity"),
                ((pStack, pLevel, pEntity, pSeed) -> PotionFlaskItem.percentFull(pStack)));
    }
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event)
    {

    }
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
    {

    }
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event)
    {

    }
}
