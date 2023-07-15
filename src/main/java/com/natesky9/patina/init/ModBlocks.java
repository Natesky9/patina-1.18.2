package com.natesky9.patina.init;

import com.natesky9.patina.Block.ApplianceIcebox.ApplianceIceboxBlock;
import com.natesky9.patina.Block.ApplianceWardrobe.ApplianceWardrobeBlock;
import com.natesky9.patina.Block.ChorusCableBlock;
import com.natesky9.patina.Block.ChorusTeleporter;
import com.natesky9.patina.Block.CopperCableBlock;
import com.natesky9.patina.Block.GeneratorBlock;
import com.natesky9.patina.Block.MachineAlembic.MachineAlembicBlock;
import com.natesky9.patina.Block.MachineEnchanter.MachineEnchanterBlock;
import com.natesky9.patina.Block.MachineEvaporator.MachineEvaporatorBlock;
import com.natesky9.patina.Block.MachineFoundry.MachineFoundryBlock;
import com.natesky9.patina.Patina;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
            DeferredRegister.create(ForgeRegistries.BLOCKS, Patina.MODID);
    //--------------------//
    public static final RegistryObject<Block> CHORUS_CABLE = registerBlock("chorus_cable",
            () -> new ChorusCableBlock(BlockBehaviour.Properties.of(Material.PLANT)
                    .randomTicks()
                    .color(MaterialColor.COLOR_PURPLE)
                    .strength(1F)
                    .noOcclusion()));
    public static final RegistryObject<Block> CHARGE_CABLE = registerBlock("charge_cable",
            () -> new CopperCableBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .randomTicks()
                    .color(MaterialColor.COLOR_ORANGE)
                    .strength(2F)
                    .noOcclusion()));
    public static final RegistryObject<Block> CHORUS_TELEPORTER = registerBlock("chorus_teleporter",
            () -> new ChorusTeleporter(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(8F)
                    .color(MaterialColor.COLOR_YELLOW)));
    public static final RegistryObject<Block> TEST_GENERATOR = registerBlock("test_generator",
            () -> new GeneratorBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(1000)));
    //region machines
    //liquid to solid
    public static final RegistryObject<Block> MACHINE_EVAPORATOR = registerBlock("machine_evaporator",
            () -> new MachineEvaporatorBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(4f)));
    //solid to solid
    //public static final RegistryObject<Block> MACHINE_SAWMILL = registerBlock("machine_sawmill",
    //        () -> new MachineSawmillBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
    //                .strength(4f)));
    //solid to liquid
    public static final RegistryObject<Block> MACHINE_FOUNDRY = registerBlock("machine_foundry",
            () -> new MachineFoundryBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(4f)));
    //liquid to liquid
    public static final RegistryObject<Block> MACHINE_ALEMBIC = registerBlock("machine_alembic",
            () -> new MachineAlembicBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(4f)));
    //enchanting
    public static final RegistryObject<Block> MACHINE_ENCHANTER = registerBlock("machine_enchanter",
            () -> new MachineEnchanterBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(4f)));
    //appliances
    public static final RegistryObject<Block> APPLIANCE_WARDROBE = registerBlock("appliance_wardrobe",
            () -> new ApplianceWardrobeBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(3f)));
    public static final RegistryObject<Block> APPLIANCE_ICEBOX = registerBlock("appliance_icebox",
            () -> new ApplianceIceboxBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(3f)));
    //public static final RegistryObject<Block> APPLIANCE_TOOLRACK = registerBlock("appliance_toolrack",
    //        () -> new ApplianceToolrackBlock(BlockBehaviour.Properties.of(Material.WOOD)
    //                .strength(3f)));
    //endregion
    public static final RegistryObject<Block> PRISMATIC_ORE = registerBlock("prismatic_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).strength(3F)));

    //register block only
    public static final RegistryObject<Block> BISMUTH_ORE = BLOCKS.register("bismuth_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3F).requiresCorrectToolForDrops()));
    //-----------------------//
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block)
    {
        RegistryObject<T> registeredBlock = BLOCKS.register(name,block);
        registerBlockItem(name,registeredBlock);
        return registeredBlock;
    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block)
    {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()));
    }
    public static void register(IEventBus eventBus)
    {BLOCKS.register(eventBus);}
}
