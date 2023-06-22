package com.natesky9.patina.event;

import com.natesky9.patina.Patina;
import com.natesky9.patina.datagen.DataGenerators;
import com.natesky9.patina.init.ModCreativeModeTab;
import com.natesky9.patina.init.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

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

    }
    @SubscribeEvent
    public static void gatherDataEvent(GatherDataEvent event)
    {
        DataGenerators.gatherData(event);
    }
    @SubscribeEvent
    public static void doCommonStuff(final FMLCommonSetupEvent event)
    {

    }
    @SubscribeEvent
    public static void doClientStuff(FMLClientSetupEvent event)
    {

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
