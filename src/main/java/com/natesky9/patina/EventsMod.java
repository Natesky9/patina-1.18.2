package com.natesky9.patina;

import com.natesky9.patina.block.BlastCauldron.MachineBlastFurnaceScreen;
import com.natesky9.patina.init.ModBlocks;
import com.natesky9.patina.init.ModItems;
import com.natesky9.patina.block.Custom.CustomMachineScreen;
import com.natesky9.patina.init.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static java.lang.Math.min;

@Mod.EventBusSubscriber(modid = Patina.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventsMod {
    @SubscribeEvent
    public static void doClientStuff(FMLClientSetupEvent event)
    {
        //Render types
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HERB_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HONEY_PUDDLE.get(), RenderType.translucentNoCrumbling());
        //screen stuff
        MenuScreens.register(ModMenuTypes.CUSTOM_MACHINE_MENU.get(), CustomMachineScreen::new);
        MenuScreens.register(ModMenuTypes.MACHINE_BLAST_CAULDRON_MENU.get(), MachineBlastFurnaceScreen::new);
        //armor stuff
        ItemProperties.register(ModItems.COPPER_HELMET.get(),
                new ResourceLocation(Patina.MOD_ID, "rustlevel"), (stack, world, living, number) ->
                {
                    CompoundTag nbt = stack.getOrCreateTag();
                    int rust =  nbt.getInt("oxidation");
                    float rust_level = min(rust / 100,3);
                    float rust_float = (rust_level/4);
                    //System.out.println("copper helmet is at stage: " + rust_float);
                    return rust_float;
                });
        ItemProperties.register(ModItems.COPPER_CHESTPLATE.get(),
                new ResourceLocation(Patina.MOD_ID, "rustlevel"), (stack, world, living, number) ->
                {

                    CompoundTag nbt = stack.getOrCreateTag();
                    int rust = nbt.getInt("oxidation");
                    float rust_level = min(rust / 100,3);
                    float rust_float = (rust_level/4);
                    //System.out.println("copper chest is at stage: " + rust_float);
                    return rust_float;
                });
        ItemProperties.register(ModItems.COPPER_LEGGINGS.get(),
                new ResourceLocation(Patina.MOD_ID, "rustlevel"), (stack, world, living, number) ->
                {
                    CompoundTag nbt = stack.getOrCreateTag();
                    int rust = nbt.getInt("oxidation");
                    float rust_level = min(rust / 100,3);
                    float rust_float = (rust_level/4);
                    //System.out.println("copper pants is at stage: " + rust_float);
                    return rust_float;
                });
        ItemProperties.register(ModItems.COPPER_BOOTS.get(),
                new ResourceLocation(Patina.MOD_ID, "rustlevel"), (stack, world, living, number) ->
                {
                    CompoundTag nbt = stack.getOrCreateTag();
                    int rust = nbt.getInt("oxidation");
                    float rust_level = min(rust / 100,3);
                    float rust_float = (rust_level/4);
                    //System.out.println("copper boots is at stage: " + rust_float);
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
