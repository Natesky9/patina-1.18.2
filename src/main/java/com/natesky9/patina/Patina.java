package com.natesky9.patina;

import com.mojang.logging.LogUtils;
import com.natesky9.patina.entity.BeeQueen.BeeQueen;
import com.natesky9.patina.entity.BeeQueen.BeeQueenModel;
import com.natesky9.patina.entity.BeeQueen.BeeQueenRender;
import com.natesky9.patina.entity.MiscModels.KnockbackShield;
import com.natesky9.patina.entity.SpiderNest.SpiderNestEntity;
import com.natesky9.patina.entity.SpiderNest.SpiderNestModel;
import com.natesky9.patina.entity.SpiderNest.SpiderNestRender;
import com.natesky9.patina.entity.SpiderQueen.SpiderQueen;
import com.natesky9.patina.entity.SpiderQueen.SpiderQueenModel;
import com.natesky9.patina.entity.SpiderQueen.SpiderQueenRender;
import com.natesky9.patina.entity.PigKing.PigKing;
import com.natesky9.patina.entity.PigKing.PigKingModel;
import com.natesky9.patina.entity.PigKing.PigKingRender;
import com.natesky9.patina.init.*;
import com.natesky9.patina.overlay.VenomOverlay;
import com.natesky9.patina.init.ModMenuTypes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import static java.lang.Math.min;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Patina.MOD_ID)
public class Patina
{
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "patina";
    //create a tab
    public static final CreativeModeTab CREATIVE_MODE_TAB = new CreativeModeTab("patina_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.TEST.get());
        }
    };
    public static final CreativeModeTab BOSS_LOOT_TAB = new CreativeModeTab("patina_loot") {
        @Override
        public ItemStack makeIcon() {return new ItemStack(ModItems.VENOM_SWORD.get());}
    };
    public static final CreativeModeTab FOOD_TAB = new CreativeModeTab("patina_food") {
        @Override
        public ItemStack makeIcon() {return new ItemStack(ModItems.CANDY_WARTS.get());}
    };
    public static final CreativeModeTab RESOURCE_TAB = new CreativeModeTab("patina_resources") {
        @Override
        public ItemStack makeIcon() {return new ItemStack(ModItems.CLOTH.get());}
    };

    public Patina() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //register everything
        ModAttributes.register(eventBus);
        ModItems.register(eventBus);
        ModBlockEntities.register(eventBus);
        ModBlocks.register(eventBus);
        ModEffects.register(eventBus);
        ModEntityTypes.register(eventBus);
        ModMenuTypes.register(eventBus);




        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        //
    }
    //----------//
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }



    @Mod.EventBusSubscriber(modid = Patina.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEvents
    {
        @SubscribeEvent
        public static void addEntityAttributes(EntityAttributeCreationEvent event)
        {
            event.put(ModEntityTypes.BEE_QUEEN.get(), BeeQueen.createAttributes().build());
            event.put(ModEntityTypes.SPIDER_QUEEN.get(), SpiderQueen.createAttributes().build());
            event.put(ModEntityTypes.SPIDER_NEST_ENTITY.get(), SpiderNestEntity.createAttributes().build());
            event.put(ModEntityTypes.PIGLIN_KING.get(), PigKing.createAttributes().build());

        }
        @SubscribeEvent
        public static void entityLayer(EntityRenderersEvent.RegisterLayerDefinitions event)
        {
            event.registerLayerDefinition(BeeQueenModel.LAYER_LOCATION,BeeQueenModel::createBodyLayer);
            event.registerLayerDefinition(SpiderQueenModel.LAYER_LOCATION,SpiderQueenModel::createBodyLayer);
            event.registerLayerDefinition(SpiderNestModel.LAYER_LOCATION,SpiderNestModel::createBodyLayer);
            event.registerLayerDefinition(PigKingModel.LAYER_LOCATION, PigKingModel::createBodyLayer);

            event.registerLayerDefinition(KnockbackShield.LAYER_LOCATION,KnockbackShield::createMainLayer);

        }
        @SubscribeEvent
        public static void entityRenderer(EntityRenderersEvent.RegisterRenderers event)
        {
            event.registerEntityRenderer(ModEntityTypes.BEE_QUEEN.get(), BeeQueenRender::new);
            event.registerEntityRenderer(ModEntityTypes.SPIDER_QUEEN.get(), SpiderQueenRender::new);
            event.registerEntityRenderer(ModEntityTypes.SPIDER_NEST_ENTITY.get(), SpiderNestRender::new);
            event.registerEntityRenderer(ModEntityTypes.PIGLIN_KING.get(), PigKingRender::new);
        }
        //overlays
        @SubscribeEvent
        public void RenderGameOverlayEvent(RenderGameOverlayEvent.Post event)
        {
            VenomOverlay.renderVenomOutline();
        }

    }
}
