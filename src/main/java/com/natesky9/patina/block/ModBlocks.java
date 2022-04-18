package com.natesky9.patina.block;

import com.natesky9.patina.Patina;
import com.natesky9.patina.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Patina.MOD_ID);
    //--------------------------------------------------//
    public static final RegistryObject<Block> TEST_BLOCK = registerBlock("test_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(4f).requiresCorrectToolForDrops()));
    //machines
    public static final RegistryObject<Block> MACHINE_BLAST_CAULDRON = registerBlock("machine_blast_cauldron",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(4f)));
    public static final RegistryObject<Block> MACHINE_CAULDRON_BREWING = registerBlock("machine_cauldron_brewing",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(4f)));
    public static final RegistryObject<Block> MACHINE_SMOKER_GRINDSTONE = registerBlock("machine_smoker_grindstone",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(4f)));
    //end machines
    public static final RegistryObject<Block> CUSTOM_BLOCK = registerBlock("custom_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(8f).requiresCorrectToolForDrops()));
    //--------------------------------------------------//

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(Patina.CREATIVE_MODE_TAB)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
