package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import com.natesky9.patina.block.Template.MachineTemplateEntity;
import com.natesky9.patina.block.AnvilSmithing.MachineAnvilSmithingEntity;
import com.natesky9.patina.block.BlastCauldron.MachineBlastCauldronEntity;
import com.natesky9.patina.block.GrindstoneBarrel.MachineGrindstoneBarrelEntity;
import com.natesky9.patina.block.SmokerGrindstone.MachineSmokerGrindstoneEntity;
import com.natesky9.patina.block.BeaconGrindstone.MachineBeaconGrindstoneEntity;
import com.natesky9.patina.block.CauldronSmoker.MachineCauldronSmokerEntity;
import com.natesky9.patina.block.CauldronBrewing.MachineCauldronBrewingEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Patina.MOD_ID);

    //--------------------------------------------------//
    //public static final RegistryObject<BlockEntityType<MachineTemplateEntity>> MACHINE_TEMPLATE_BLOCK_ENTITY =
    //        BLOCK_ENTITIES.register("machine_template_block_entity", () ->
    //                BlockEntityType.Builder.of(MachineTemplateEntity::new,
    //                        ModBlocks.MACHINE_TEMPLATE.get()).build(null));
    //attribute forge
    public static final RegistryObject<BlockEntityType<MachineAnvilSmithingEntity>> MACHINE_ANVIL_SMITHING_ENTITY =
            BLOCK_ENTITIES.register("machine_anvil_smithing_entity", () ->
            BlockEntityType.Builder.of(MachineAnvilSmithingEntity::new,
                    ModBlocks.MACHINE_ANVIL_SMITHING.get()).build(null));
    //Forge
    public static final RegistryObject<BlockEntityType<MachineBlastCauldronEntity>> MACHINE_BLAST_CAULDRON_ENTITY =
            BLOCK_ENTITIES.register("machine_blast_furnace_entity",() ->
                    BlockEntityType.Builder.of(MachineBlastCauldronEntity::new,
                            ModBlocks.MACHINE_BLAST_CAULDRON.get()).build(null));
    //brews potions
    public static final RegistryObject<BlockEntityType<MachineCauldronBrewingEntity>> MACHINE_CAULDRON_BREWING_ENTITY =
            BLOCK_ENTITIES.register("machine_cauldron_brewing_entity", () ->
                    BlockEntityType.Builder.of(MachineCauldronBrewingEntity::new,
                            ModBlocks.MACHINE_CAULDRON_BREWING.get()).build(null));
    //Mincerator
    public static final RegistryObject<BlockEntityType<MachineSmokerGrindstoneEntity>> MACHINE_SMOKER_GRINDSTONE_ENTITY =
            BLOCK_ENTITIES.register("machine_smoker_grindstone_entity", () ->
                    BlockEntityType.Builder.of(MachineSmokerGrindstoneEntity::new,
                            ModBlocks.MACHINE_SMOKER_GRINDSTONE.get()).build(null));
    //Strips enchants
    public static final RegistryObject<BlockEntityType<MachineGrindstoneBarrelEntity>> MACHINE_GRINDSTONE_BARREL_ENTITY =
            BLOCK_ENTITIES.register("machine_grindstone_barrel_entity", () ->
                    BlockEntityType.Builder.of(MachineGrindstoneBarrelEntity::new,
                            ModBlocks.MACHINE_GRINDSTONE_BARREL.get()).build(null));
    //Makes Void salt
    public static final RegistryObject<BlockEntityType<MachineBeaconGrindstoneEntity>> MACHINE_BEACON_GRINDSTONE_ENTITY =
            BLOCK_ENTITIES.register("machine_beacon_grindstone_entity", () ->
                    BlockEntityType.Builder.of(MachineBeaconGrindstoneEntity::new,
                            ModBlocks.MACHINE_BEACON_GRINDSTONE.get()).build(null));
    //Makes Potion Salt
    public static final RegistryObject<BlockEntityType<MachineCauldronSmokerEntity>> MACHINE_CAULDRON_SMOKER_ENTITY =
            BLOCK_ENTITIES.register("machine_cauldron_smoker_entity", () ->
                    BlockEntityType.Builder.of(MachineCauldronSmokerEntity::new,
                            ModBlocks.MACHINE_CAULDRON_SMOKER.get()).build(null));
    //--------------------------------------------------//
    public static void register(IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
    }
}
