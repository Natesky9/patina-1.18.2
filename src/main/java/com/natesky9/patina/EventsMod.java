package com.natesky9.patina;

import com.natesky9.patina.entity.MiscModels.BEWLR;
import com.natesky9.patina.init.*;
import com.natesky9.patina.item.BoltComponent;
import com.natesky9.patina.item.BoltItem;
import com.natesky9.patina.item.CopperItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import static java.lang.Math.min;

@Mod.EventBusSubscriber(modid = Patina.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventsMod {

    //@SubscribeEvent
    //public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event)
    //{
    //    Registry.register(Registry.RECIPE_TYPE, SmokerGrindstoneRecipe.Type.ID, SmokerGrindstoneRecipe.Type.INSTANCE);
    //    Registry.register(Registry.RECIPE_TYPE, ToolRecipe.Type.ID, ToolRecipe.Type.INSTANCE);
    //    Registry.register(Registry.RECIPE_TYPE, FletchingRecipe.Type.ID,FletchingRecipe.Type.INSTANCE);
    //    Registry.register(Registry.RECIPE_TYPE, CrossbowRecipe.NAME, RecipeType.CRAFTING);
    //}
    @SubscribeEvent
    public static void ColorHandlerEvent(final ColorHandlerEvent.Item event)
    {
        //color bolt tips
        event.getItemColors().register((item,hue) -> {return BoltComponent.getColor(item);},
                ModItems.BOLT_TIPS.get());
        //color unfinished bolts
        event.getItemColors().register((item,hue) -> {return BoltComponent.getColor(item);},
                ModItems.UNFINISHED_BOLTS.get());
        //color finished bolts
        event.getItemColors().register((item,index) ->
                switch (index) {
                    case 0 -> BoltItem.getBase(item);
                    case 1 -> BoltItem.getFeather(item);
                    case 2 -> BoltItem.getTip(item);
                    default -> -1;
                },ModItems.BOLTS.get());
        event.getItemColors().register((item,hue) ->
                        hue > 0 ? -1 : PotionUtils.getColor(item)
                ,ModItems.MAGIC_SALT.get());
        event.getItemColors().register((item,hue) ->
        {return hue > 0 ? -1 : CopperItem.getRustColor(item);},
            ModItems.COPPER_HELMET.get(),ModItems.COPPER_CHESTPLATE.get(),
                ModItems.COPPER_LEGGINGS.get(),ModItems.COPPER_BOOTS.get());
    }
    @SubscribeEvent
    public static void doCommonStuff(final FMLCommonSetupEvent event)
    {
        ModPotions.addSaltPotions();
        ModPotions.addVoidPotions();
    }
    @SubscribeEvent
    public static void doClientStuff(FMLClientSetupEvent event)
    {
        Patina.bewlr = new BEWLR(Minecraft.getInstance().getBlockEntityRenderDispatcher(),Minecraft.getInstance().getEntityModels());
        //Render types
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HERB_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HONEY_PUDDLE.get(), RenderType.translucentNoCrumbling());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MACHINE_BEACON_GRINDSTONE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WATER_VALVE.get(),RenderType.cutout());
        //screen stuff

        //IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        //ModMenuTypes.register(eventBus);
        ModScreens.register();


        ItemProperties.register(ModItems.LUXOMETER.get(),
                new ResourceLocation(Patina.MOD_ID,"toggle"),(stack, world, entity, number) ->
                {
                    CompoundTag nbt = stack.getOrCreateTag();
                    return nbt.getBoolean("toggle") ? 1F:0F;
                });
        ItemProperties.register(ModItems.BOLTS.get(),
                new ResourceLocation(Patina.MOD_ID,"item/stage"),(stack, pLevel, pEntity, pSeed) ->
                {
                    boolean hasFeather = PatinaArchery.HAS_FEATHER.test(stack);
                    boolean hasTip = PatinaArchery.HAS_TIP.test(stack);
                    if (hasTip) return 1.0F;
                    if (hasFeather) return 0.5F;
                    return 0.0F;
                });
    }
}
