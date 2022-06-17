package com.natesky9.patina.datagen;

import com.natesky9.patina.block.HerbPlantBlock;
import com.natesky9.patina.init.ModBlocks;
import com.natesky9.patina.init.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockLootTables extends BlockLoot {
    @Override
    protected void addTables()
    {
        this.dropSelf(ModBlocks.CUSTOM_BLOCK.get());
        this.dropSelf(ModBlocks.TEST_BLOCK.get());
        this.dropSelf(ModBlocks.TEST_STAIRS.get());
        this.dropSelf(ModBlocks.TEST_WALL.get());

        this.dropSelf(ModBlocks.CHORUS_CABLE.get());

        this.add(ModBlocks.HONEY_PUDDLE.get(), noDrop());

        this.dropSelf(ModBlocks.TELECHORUS.get());

        this.dropSelf(ModBlocks.MACHINE_ANVIL_SMITHING.get());
        this.dropSelf(ModBlocks.MACHINE_BEACON_GRINDSTONE.get());
        this.dropSelf(ModBlocks.MACHINE_BLAST_CAULDRON.get());
        this.dropSelf(ModBlocks.MACHINE_CAULDRON_BREWING.get());
        this.dropSelf(ModBlocks.MACHINE_CAULDRON_SMOKER.get());
        this.dropSelf(ModBlocks.MACHINE_GRINDSTONE_BARREL.get());
        this.dropSelf(ModBlocks.MACHINE_SMOKER_GRINDSTONE.get());

        //special blocks
        this.add(ModBlocks.TEST_SLAB.get(), BlockLoot::createSlabItemTable);
        //crops
        this.add(ModBlocks.HERB_BLOCK.get(),createCropDrops(ModBlocks.HERB_BLOCK.get(), ModItems.HERB.get(), ModItems.HERB_SEEDS.get(),propertyEquals(ModBlocks.HERB_BLOCK.get(), HerbPlantBlock.AGE,5)));
    }

    LootItemBlockStatePropertyCondition.Builder propertyEquals(Block block, IntegerProperty properties, Integer value)
    {
        return LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
            .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(properties, value));
    }
    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
