package com.natesky9.patina.datagen;

import com.natesky9.patina.Block.ApplianceIcebox.ApplianceIceboxBlock;
import com.natesky9.patina.Block.ApplianceWardrobe.ApplianceWardrobeBlock;
import com.natesky9.patina.init.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate()
    {
        this.dropSelf(ModBlocks.APPLIANCE_ICEBOX.get());
        //this.dropSelf(ModBlocks.APPLIANCE_WARDROBE.get());
        this.add(ModBlocks.APPLIANCE_WARDROBE.get(), createSinglePropConditionTable(ModBlocks.APPLIANCE_WARDROBE.get(),
                ApplianceWardrobeBlock.HALF,DoubleBlockHalf.LOWER));
        this.add(ModBlocks.APPLIANCE_ICEBOX.get(), createSinglePropConditionTable(ModBlocks.APPLIANCE_ICEBOX.get(),
                ApplianceIceboxBlock.HALF,DoubleBlockHalf.LOWER));
        this.dropSelf(ModBlocks.APPLIANCE_ARCANE_CONSOLIDATOR.get());

        this.dropSelf(ModBlocks.MACHINE_FOUNDRY.get());
        this.dropSelf(ModBlocks.MACHINE_ENCHANTER.get());
        this.dropSelf(ModBlocks.MACHINE_EVAPORATOR.get());
        this.dropSelf(ModBlocks.MACHINE_ALEMBIC.get());
        this.dropSelf(ModBlocks.MACHINE_MINCERATOR.get());
        this.dropSelf(ModBlocks.ADDON_ALEMBIC.get());
        this.dropSelf(ModBlocks.ADDON_FOUNDRY.get());
        this.add(ModBlocks.GRAVESTONE.get(),noDrop());

        this.dropSelf(ModBlocks.PRISMATIC_ORE.get());
        dropOther(ModBlocks.PRISMATIC_ORE.get(), Items.PRISMARINE_SHARD);
        this.dropSelf(ModBlocks.BISMUTH_ORE.get());
        this.dropSelf(ModBlocks.CHORUS_CABLE.get());
        this.dropSelf(ModBlocks.CHARGE_CABLE.get());
        this.dropSelf(ModBlocks.TEST_GENERATOR.get());
        this.dropSelf(ModBlocks.CHORUS_TELEPORTER.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
