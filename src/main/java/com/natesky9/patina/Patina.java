package com.natesky9.patina;

import com.mojang.logging.LogUtils;
import com.natesky9.patina.entity.BeeQueen.BeeQueen;
import com.natesky9.patina.entity.BeeQueen.BeeQueenModel;
import com.natesky9.patina.entity.BeeQueen.BeeQueenRender;
import com.natesky9.patina.entity.SpiderNest.SpiderNestEntity;
import com.natesky9.patina.entity.SpiderNest.SpiderNestModel;
import com.natesky9.patina.entity.SpiderNest.SpiderNestRender;
import com.natesky9.patina.entity.SpiderQueen.SpiderQueen;
import com.natesky9.patina.entity.SpiderQueen.SpiderQueenModel;
import com.natesky9.patina.entity.SpiderQueen.SpiderQueenRender;
import com.natesky9.patina.entity.PigKing.PigKing;
import com.natesky9.patina.entity.PigKing.PigKingModel;
import com.natesky9.patina.entity.PigKing.PigKingRender;
import com.natesky9.patina.init.ModBlocks;
import com.natesky9.patina.init.ModEffects;
import com.natesky9.patina.init.ModEntityTypes;
import com.natesky9.patina.init.ModItems;
import com.natesky9.patina.overlay.VenomOverlay;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
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

    public Patina() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        //eventBus.addListener(this::setup);
        //eventBus.addListener(this::enqueueIMC);
        //eventBus.addListener(this::processIMC);
        //register everything
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModEffects.register(eventBus);
        ModEntityTypes.register(eventBus);




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
        @SubscribeEvent
        public static void doClientStuff(FMLClientSetupEvent event)
        {
            //Render types
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.HERB_BLOCK.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.HONEY_PUDDLE.get(), RenderType.translucentNoCrumbling());
            //armor stuff
            ItemProperties.register(ModItems.COPPER_HELMET.get(),
                    new ResourceLocation(Patina.MOD_ID, "rustlevel"), (stack, world, living, number) ->
                    {
                        CompoundTag nbt = stack.getOrCreateTag();
                        int rust =  nbt.getInt("oxidation");
                        float rust_level = min(rust / 100,3);
                        float rust_float = (rust_level/4);
                        System.out.println("copper helmet is at stage: " + rust_float);
                        return rust_float;
                    });
            ItemProperties.register(ModItems.COPPER_CHESTPLATE.get(),
                    new ResourceLocation(Patina.MOD_ID, "rustlevel"), (stack, world, living, number) ->
                    {

                        CompoundTag nbt = stack.getOrCreateTag();
                        int rust = nbt.getInt("oxidation");
                        float rust_level = min(rust / 100,3);
                        float rust_float = (rust_level/4);
                        System.out.println("copper chest is at stage: " + rust_float);
                        return rust_float;
                    });
            ItemProperties.register(ModItems.COPPER_LEGGINGS.get(),
                    new ResourceLocation(Patina.MOD_ID, "rustlevel"), (stack, world, living, number) ->
                    {
                        CompoundTag nbt = stack.getOrCreateTag();
                        int rust = nbt.getInt("oxidation");
                        float rust_level = min(rust / 100,3);
                        float rust_float = (rust_level/4);
                        System.out.println("copper pants is at stage: " + rust_float);
                        return rust_float;
                    });
            ItemProperties.register(ModItems.COPPER_BOOTS.get(),
                    new ResourceLocation(Patina.MOD_ID, "rustlevel"), (stack, world, living, number) ->
                    {
                        CompoundTag nbt = stack.getOrCreateTag();
                        int rust = nbt.getInt("oxidation");
                        float rust_level = min(rust / 100,3);
                        float rust_float = (rust_level/4);
                        System.out.println("copper boots is at stage: " + rust_float);
                        return rust_float;
                    });
            //entity
            ItemProperties.register(ModItems.LUXOMETER.get(),
                    new ResourceLocation(Patina.MOD_ID,"toggle"),(stack, world, entity, number) ->
                    {
                        CompoundTag nbt = stack.getOrCreateTag();
                        return nbt.getBoolean("toggle") ? 1F:0F;
                    });
        }
    }

    @Mod.EventBusSubscriber(modid = Patina.MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeEvents{
        @SubscribeEvent
        public void PotionRemoveEvent(LivingEntity living, MobEffectInstance effect){
            int duration = effect.getDuration();
            int intensity = effect.getAmplifier();
            MobEffect potion = effect.getEffect();
            LOGGER.info("effect duration is: " + duration);
            LOGGER.info("effect intensity is: " + intensity);

            if (intensity >= 1) {
                MobEffectInstance newpotion = new MobEffectInstance(potion,100,intensity-1);
                living.addEffect(newpotion);
            }
        }

    }
}
