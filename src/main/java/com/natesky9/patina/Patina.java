package com.natesky9.patina;

import com.mojang.logging.LogUtils;
import com.natesky9.patina.Loot.ModLootModifiers;
import com.natesky9.patina.init.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Patina.MODID)
public class Patina
{
    public static final String MODID = "patina";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Patina()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModBlockEntities.register(eventBus);
        ModMenuTypes.register(eventBus);
        //enchantments are data driven since 1.21
        //ModEnchantments.register(eventBus);
        ModLootModifiers.register(eventBus);
        ModPotions.register(eventBus);
        //paintings are data driven since 1.21
        //ModPaintings.register(eventBus);
        ModRecipeTypes.register(eventBus);
        ModRecipeSerializers.register(eventBus);
        ModCreativeTabs.register(eventBus);

        ModEntityTypes.register(eventBus);
        ModDataComponents.register(eventBus);



        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
