package com.natesky9.patina;

import com.natesky9.patina.block.Template.MachineTemplateScreen;
import com.natesky9.patina.block.AnvilSmithing.MachineAnvilSmithingScreen;
import com.natesky9.patina.block.BeaconGrindstone.MachineBeaconGrindstoneScreen;
import com.natesky9.patina.block.BlastCauldron.MachineBlastCauldronScreen;
import com.natesky9.patina.block.CauldronBrewing.MachineCauldronBrewingScreen;
import com.natesky9.patina.block.CauldronSmoker.MachineCauldronSmokerScreen;
import com.natesky9.patina.block.GrindstoneBarrel.MachineGrindstoneBarrelScreen;
import com.natesky9.patina.block.SmokerGrindstone.MachineSmokerGrindstoneScreen;
import com.natesky9.patina.init.ModBlocks;
import com.natesky9.patina.init.ModItems;
import com.natesky9.patina.init.ModMenuTypes;
import com.natesky9.patina.init.ModPotions;
import com.natesky9.patina.item.CopperItem;
import com.natesky9.recipe.SaltBrewingRecipe;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import static java.lang.Math.min;

@Mod.EventBusSubscriber(modid = Patina.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventsMod {
    @SubscribeEvent
    public static void ColorHandlerEvent(final ColorHandlerEvent.Item event)
    {
        event.getItemColors().register((item,hue) ->
                {return hue > 0 ? -1 : PotionUtils.getColor(item);}
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
    }
    @SubscribeEvent
    public static void doClientStuff(FMLClientSetupEvent event)
    {
        //Render types
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HERB_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HONEY_PUDDLE.get(), RenderType.translucentNoCrumbling());
        //screen stuff

        //IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        //ModMenuTypes.register(eventBus);
        MenuScreens.register(ModMenuTypes.MACHINE_TEMPLATE_MENU.get(), MachineTemplateScreen::new);

        MenuScreens.register(ModMenuTypes.MACHINE_ANVIL_SMITHING_MENU.get(), MachineAnvilSmithingScreen::new);
        MenuScreens.register(ModMenuTypes.MACHINE_BEACON_GRINDSTONE_MENU.get(), MachineBeaconGrindstoneScreen::new);
        MenuScreens.register(ModMenuTypes.MACHINE_BLAST_CAULDRON_MENU.get(), MachineBlastCauldronScreen::new);
        MenuScreens.register(ModMenuTypes.MACHINE_CAULDRON_BREWING_MENU.get(), MachineCauldronBrewingScreen::new);
        MenuScreens.register(ModMenuTypes.MACHINE_CAULDRON_SMOKER_MENU.get(), MachineCauldronSmokerScreen::new);
        MenuScreens.register(ModMenuTypes.MACHINE_GRINDSTONE_BARREL_MENU.get(), MachineGrindstoneBarrelScreen::new);
        MenuScreens.register(ModMenuTypes.MACHINE_SMOKER_GRINDSTONE_MENU.get(), MachineSmokerGrindstoneScreen::new);


        ItemProperties.register(ModItems.LUXOMETER.get(),
                new ResourceLocation(Patina.MOD_ID,"toggle"),(stack, world, entity, number) ->
                {
                    CompoundTag nbt = stack.getOrCreateTag();
                    return nbt.getBoolean("toggle") ? 1F:0F;
                });
    }
}
