package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import com.natesky9.patina.block.*;
import com.natesky9.patina.block.BeaconGrindstone.MachineBeaconGrindstoneBlock;
import com.natesky9.patina.block.BlastCauldron.MachineBlastCauldronBlock;
import com.natesky9.patina.block.AnvilSmithing.MachineAnvilSmithingBlock;
import com.natesky9.patina.block.CauldronBrewing.MachineCauldronBrewingBlock;
import com.natesky9.patina.block.GrindstoneBarrel.MachineGrindstoneBarrelBlock;
import com.natesky9.patina.block.SmokerGrindstone.MachineSmokerGrindstoneBlock;
import com.natesky9.patina.block.CauldronSmoker.MachineCauldronSmokerBlock;
import com.natesky9.patina.block.FeralLanternBlock;
import com.natesky9.patina.item.FeralLanternItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
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
    public static final RegistryObject<Block> TEST_STAIRS = registerBlock("test_stairs",
            () -> new StairBlock(() -> ModBlocks.TEST_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.of(Material.STONE).strength(4f)));
    public static final RegistryObject<Block> TEST_SLAB = registerBlock("test_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(4f)));
    public static final RegistryObject<Block> TEST_WALL = registerBlock("test_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(4f)));
    public static final RegistryObject<Block> HONEY_PUDDLE = registerBlock("honey_puddle",
            () -> new HoneyPuddleBlock(BlockBehaviour.Properties.of(Material.CLAY).color(MaterialColor.COLOR_ORANGE)
                    .sound(SoundType.HONEY_BLOCK).randomTicks()));
    public static final RegistryObject<Block> TELECHORUS = registerBlock("telechorus",
            () -> new TelechorusBlock(BlockBehaviour.Properties.of(Material.METAL).color(MaterialColor.METAL)
                    .strength(4).sound(SoundType.FUNGUS)));
    public static final RegistryObject<Block> CHORUS_CABLE = registerBlock("chorus_cable",
            () -> new ChorusCableBlock(BlockBehaviour.Properties.of(Material.PLANT).color(MaterialColor.COLOR_PURPLE)
                    .noOcclusion()));

    //machines
    //public static final RegistryObject<Block> MACHINE_TEMPLATE = registerBlock("machine_custom",
    //        () -> new MachineTemplateBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
    //                .strength(10f)));
    public static final RegistryObject<Block> MACHINE_ANVIL_SMITHING = registerBlock("machine_anvil_smithing",
            () -> new MachineAnvilSmithingBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(10f)));
    public static final RegistryObject<Block> MACHINE_BLAST_CAULDRON = registerBlock("machine_blast_cauldron",
            () -> new MachineBlastCauldronBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(4f)));
    public static final RegistryObject<Block> MACHINE_CAULDRON_BREWING = registerBlock("machine_cauldron_brewing",
            () -> new MachineCauldronBrewingBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(4f)));
    public static final RegistryObject<Block> MACHINE_SMOKER_GRINDSTONE = registerBlock("machine_smoker_grindstone",
            () -> new MachineSmokerGrindstoneBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(4f)));
    public static final RegistryObject<Block> MACHINE_GRINDSTONE_BARREL = registerBlock("machine_grindstone_barrel",
            () -> new MachineGrindstoneBarrelBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(4f)));
    public static final RegistryObject<Block> MACHINE_BEACON_GRINDSTONE = registerBlock("machine_beacon_grindstone",
            () -> new MachineBeaconGrindstoneBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(4f)));
    public static final RegistryObject<Block> MACHINE_CAULDRON_SMOKER = registerBlock("machine_cauldron_smoker",
            () -> new MachineCauldronSmokerBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(4f)));

    public static final RegistryObject<Block> FERAL_LANTERN = registerBlockOnly("feral_lantern",
            () -> new FeralLanternBlock(BlockBehaviour.Properties.of(Material.METAL).strength(1F)
                    .sound(SoundType.LANTERN).lightLevel((p_187431_) -> {
                return 10;
            }).noOcclusion()));
    public static final RegistryObject<Item> FERAL_LANTERN_ITEM = ModItems.ITEMS.register("feral_lantern",
            () -> new FeralLanternItem(FERAL_LANTERN.get(),new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1)
                    .tab(Patina.RESOURCE_TAB)));

    //end machines
    public static final RegistryObject<Block> CUSTOM_BLOCK = registerBlock("custom_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(8f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> HERB_BLOCK = registerBlockOnly("herb_block",
            () -> new HerbPlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion()));
    //--------------------------------------------------//

    private static <T extends Block> RegistryObject<T> registerBlockOnly(String name, Supplier<T> block)
    {
        return BLOCKS.register(name,block);
    }
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block)
    {
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block)
    {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(Patina.CREATIVE_MODE_TAB)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
