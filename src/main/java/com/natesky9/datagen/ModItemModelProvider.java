package com.natesky9.datagen;

import com.natesky9.patina.Patina;
import com.natesky9.patina.init.ModBlocks;
import com.natesky9.patina.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Patina.MODID, existingFileHelper);
    }
    @Override
    public void registerModels() {
        //region fragments
        simpleItem(ModItems.BEE_FRAGMENT_1);
        simpleItem(ModItems.BEE_FRAGMENT_2);
        simpleItem(ModItems.BEE_FRAGMENT_3);
        simpleItem(ModItems.BEE_FRAGMENT_4);
        simpleItem(ModItems.BEE_FRAGMENT_A);
        simpleItem(ModItems.BEE_FRAGMENT_B);
        simpleItem(ModItems.BEE_FRAGMENT_C);
        simpleItem(ModItems.BEE_FRAGMENT_D);
        handheldItem(ModItems.BEE_SWORD);


        simpleItem(ModItems.PIG_FRAGMENT_1);
        simpleItem(ModItems.PIG_FRAGMENT_2);
        simpleItem(ModItems.PIG_FRAGMENT_3);
        simpleItem(ModItems.PIG_FRAGMENT_4);
        simpleItem(ModItems.PIG_FRAGMENT_A);
        simpleItem(ModItems.PIG_FRAGMENT_B);
        simpleItem(ModItems.PIG_FRAGMENT_C);
        simpleItem(ModItems.PIG_FRAGMENT_D);
        handheldItem(ModItems.PIG_SWORD);

        simpleItem(ModItems.WITHER_FRAGMENT_1);
        simpleItem(ModItems.WITHER_FRAGMENT_2);
        simpleItem(ModItems.WITHER_FRAGMENT_3);
        simpleItem(ModItems.WITHER_FRAGMENT_4);
        handheldItem(ModItems.WITHER_STAFF);
        //endregion fragments

        //handheldItem(ModItems.PIG_CROSSBOW);

        simpleItem(ModItems.CHARM_CONTRABAND);
        simpleItem(ModItems.CHARM_DETONATION);
        simpleItem(ModItems.CHARM_EXPERIENCE);
        simpleItem(ModItems.CHARM_WARDING);
        simpleItem(ModItems.CHARM_ALCHEMY);
        simpleItem(ModItems.CHARM_FERTILITY);
        simpleItem(ModItems.CHARM_VITALITY);
        simpleItem(ModItems.CHARM_AMBUSH);

        simpleItem(ModItems.CHARM_FRAGMENT);

        //TODO: make this better
        orientable(ModBlocks.MACHINE_EVAPORATOR.getId().getPath(),
                ResourceLocation.fromNamespaceAndPath(Patina.MODID,"block/evaporator_side"),
                ResourceLocation.fromNamespaceAndPath(Patina.MODID,"block/evaporator_front"),
                ResourceLocation.fromNamespaceAndPath(Patina.MODID,"block/evaporator_top"));
        cubeColumn(ModBlocks.MACHINE_ALEMBIC.getId().getPath(),
                mcLoc("block/iron_block"),modLoc("block/machine_alembic"));
        cubeColumn(ModBlocks.ADDON_ALEMBIC.getId().getPath(),
                mcLoc("block/cauldron_top"),
                modLoc("block/addon_alembic"));
        orientable(ModBlocks.MACHINE_FOUNDRY.getId().getPath(),
                modLoc("block/evaporator_side"),
                mcLoc("block/blast_furnace_front"),
                mcLoc("block/cauldron_top"));
        cubeColumn(ModBlocks.APPLIANCE_ICEBOX.getId().getPath(),
                mcLoc("block/iron_block"),
                mcLoc("block/iron_trapdoor"));
        cubeColumn(ModBlocks.APPLIANCE_WARDROBE.getId().getPath(),
                mcLoc("block/barrel_side"),
                mcLoc("block/spruce_trapdoor"));
        cubeTop(ModBlocks.APPLIANCE_ARCANE_CONSOLIDATOR.getId().getPath(),
                mcLoc("block/furnace_side"),
                mcLoc("block/gold_block"));
        orientable(ModBlocks.MACHINE_MINCERATOR.getId().getPath(),
                mcLoc("block/piston_top"),
                mcLoc("block/note_block"),
                mcLoc("block/magenta_terracotta"));

        cubeAll(ModBlocks.BISMUTH_ORE.getId().getPath(),
                ResourceLocation.fromNamespaceAndPath(Patina.MODID,"block/bismuth_ore"));
        //
        //withExistingParent(ModItems.POTION_FLASK.getId().getPath(),new ResourceLocation("item/generated"))
        //        .texture("layer0", new ResourceLocation(Patina.MODID,"item/potion_flask.json"))
        //        .texture("layer1", new ResourceLocation(Patina.MODID,"item/potion_flask_fluid"));
        //simpleItem(ModItems.IMPETUS_FLASK);
        //simpleItem(ModItems.VITA_FLASK);
        //simpleItem(ModItems.MAGNA_FLASK);
        simpleItem(ModItems.PRIME_GLASS);
        simpleItem(ModItems.ANIMA_GLASS);
        simpleItem(ModItems.FERUS_GLASS);
        simpleItem(ModItems.FORTIS_GLASS);
        simpleItem(ModItems.MALACHITE);
        simpleItem(ModItems.SMITHING_FLUX);
        simpleItem(ModItems.SILK);
        simpleItem(ModItems.UMBRA);
        withExistingParent(ModItems.POTION_SALT.getId().getPath(), ResourceLocation.withDefaultNamespace("item/generated"))
                .texture("layer0",ResourceLocation.withDefaultNamespace("item/sugar"))
                .texture("layer1",ResourceLocation.withDefaultNamespace("item/light"));
        simpleItem(ModItems.VOID_SALT);
        simpleItem(ModItems.BISMUTH_NUGGET);
        simpleItem(ModItems.BISMUTH_INGOT);
        simpleItem(ModItems.BRONZE_INGOT);
        simpleItem(ModItems.DRAGON_SCALE);
        simpleItem(ModItems.CLAW);
        simpleItem(ModItems.COPPER_CLAW);
        simpleItem(ModItems.DRAGON_CLAW);
        simpleItem(ModItems.ESSENCE);
        //lighter is done through json
        simpleItem(ModItems.DUST_POUCH);
        simpleItem(ModItems.GEM_POUCH);
        simpleItem(ModItems.SEED_POUCH);

        withExistingParent(ModItems.ESSENCE.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(Patina.MODID, ModItems.ESSENCE.getId().getPath()).withPrefix("item/"))
                .override().predicate(ResourceLocation.fromNamespaceAndPath(Patina.MODID,"crude"),1.0F).model(withExistingParent("essence_refined",
                        ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                        ResourceLocation.fromNamespaceAndPath(Patina.MODID,"item/essence_refined"))).end();
        //copper tools--------------
        //region copper
        simpleItem(ModItems.COPPER_NUGGET);
        simpleItem(ModItems.NETHERITE_NUGGET);
        withExistingParent(ModItems.COPPER_AXE.getId().getPath(),ResourceLocation.withDefaultNamespace("item/handheld"))
                .texture("layer0",ResourceLocation.fromNamespaceAndPath(Patina.MODID,"item/handle"))
                .texture("layer1",ResourceLocation.fromNamespaceAndPath(Patina.MODID,"item/axe"));
        withExistingParent(ModItems.COPPER_PICK.getId().getPath(),ResourceLocation.withDefaultNamespace("item/handheld"))
                .texture("layer0",ResourceLocation.fromNamespaceAndPath(Patina.MODID,"item/handle"))
                .texture("layer1",ResourceLocation.fromNamespaceAndPath(Patina.MODID,"item/pick"));
        withExistingParent(ModItems.COPPER_SHOVEL.getId().getPath(),ResourceLocation.withDefaultNamespace("item/handheld"))
                .texture("layer0",ResourceLocation.fromNamespaceAndPath(Patina.MODID,"item/handle"))
                .texture("layer1",ResourceLocation.fromNamespaceAndPath(Patina.MODID,"item/shovel"));
        withExistingParent(ModItems.COPPER_SWORD.getId().getPath(),ResourceLocation.withDefaultNamespace("item/handheld"))
                .texture("layer0",ResourceLocation.fromNamespaceAndPath(Patina.MODID,"item/handle"))
                .texture("layer1",ResourceLocation.fromNamespaceAndPath(Patina.MODID,"item/sword"));
        withExistingParent(ModItems.COPPER_HOE.getId().getPath(),ResourceLocation.withDefaultNamespace("item/handheld"))
                .texture("layer0",ResourceLocation.fromNamespaceAndPath(Patina.MODID,"item/handle"))
                .texture("layer1",ResourceLocation.fromNamespaceAndPath(Patina.MODID,"item/hoe"));
        withExistingParent(ModItems.COPPER_HELMET.getId().getPath(),ResourceLocation.withDefaultNamespace("item/generated"))
                .texture("layer0",ResourceLocation.withDefaultNamespace("item/iron_helmet"));
        withExistingParent(ModItems.COPPER_CHESTPLATE.getId().getPath(),ResourceLocation.withDefaultNamespace("item/generated"))
                .texture("layer0",ResourceLocation.withDefaultNamespace("item/iron_chestplate"));
        withExistingParent(ModItems.COPPER_LEGGINGS.getId().getPath(),ResourceLocation.withDefaultNamespace("item/generated"))
                .texture("layer0",ResourceLocation.withDefaultNamespace("item/iron_leggings"));
        withExistingParent(ModItems.COPPER_BOOTS.getId().getPath(),ResourceLocation.withDefaultNamespace("item/generated"))
                .texture("layer0",ResourceLocation.withDefaultNamespace("item/iron_boots"));
        //endregion copper
        //region bronze
        withExistingParent(ModItems.BRONZE_AXE.getId().getPath(),ResourceLocation.withDefaultNamespace("item/handheld"))
                .texture("layer0",ResourceLocation.fromNamespaceAndPath(Patina.MODID,"item/handle"))
                .texture("layer1",ResourceLocation.fromNamespaceAndPath(Patina.MODID,"item/axe"));
        withExistingParent(ModItems.BRONZE_PICK.getId().getPath(),ResourceLocation.withDefaultNamespace("item/handheld"))
                .texture("layer0",ResourceLocation.fromNamespaceAndPath(Patina.MODID,"item/handle"))
                .texture("layer1",ResourceLocation.fromNamespaceAndPath(Patina.MODID,"item/pick"));
        withExistingParent(ModItems.BRONZE_SHOVEL.getId().getPath(),ResourceLocation.withDefaultNamespace("item/handheld"))
                .texture("layer0",ResourceLocation.fromNamespaceAndPath(Patina.MODID,"item/handle"))
                .texture("layer1",ResourceLocation.fromNamespaceAndPath(Patina.MODID,"item/shovel"));
        withExistingParent(ModItems.BRONZE_SWORD.getId().getPath(),ResourceLocation.withDefaultNamespace("item/handheld"))
                .texture("layer0",ResourceLocation.fromNamespaceAndPath(Patina.MODID,"item/handle"))
                .texture("layer1",ResourceLocation.fromNamespaceAndPath(Patina.MODID,"item/sword"));
        withExistingParent(ModItems.BRONZE_HOE.getId().getPath(),ResourceLocation.withDefaultNamespace("item/handheld"))
                .texture("layer0",ResourceLocation.fromNamespaceAndPath(Patina.MODID,"item/handle"))
                .texture("layer1",ResourceLocation.fromNamespaceAndPath(Patina.MODID,"item/hoe"));
        withExistingParent(ModItems.BRONZE_HELMET.getId().getPath(),ResourceLocation.withDefaultNamespace("item/generated"))
                .texture("layer0",ResourceLocation.withDefaultNamespace("item/iron_helmet"));
        withExistingParent(ModItems.BRONZE_CHESTPLATE.getId().getPath(),ResourceLocation.withDefaultNamespace("item/generated"))
                .texture("layer0",ResourceLocation.withDefaultNamespace("item/iron_chestplate"));
        withExistingParent(ModItems.BRONZE_LEGGINGS.getId().getPath(),ResourceLocation.withDefaultNamespace("item/generated"))
                .texture("layer0",ResourceLocation.withDefaultNamespace("item/iron_leggings"));
        withExistingParent(ModItems.BRONZE_BOOTS.getId().getPath(),ResourceLocation.withDefaultNamespace("item/generated"))
                .texture("layer0",ResourceLocation.withDefaultNamespace("item/iron_boots"));
        //endregion bronze
        //region dragon
        simpleItem(ModItems.DRAGON_HELMET);
        simpleItem(ModItems.DRAGON_CHESTPLATE);
        simpleItem(ModItems.DRAGON_LEGGINGS);
        //simpleItem(ModItems.DRAGON_BOOTS);
        //endregion dragon
        withExistingParent(ModBlocks.CHORUS_CABLE.getId().getPath(),modLoc("block/chorus_cable"+"_inventory"));
        withExistingParent(ModBlocks.CHARGE_CABLE.getId().getPath(),modLoc("block/charge_cable"+"_inventory"));

        simpleItem(ModItems.FOOD_MEATBALLS);
        simpleItem(ModItems.FOOD_HONEY_NUGGETS);
        simpleItem(ModItems.FOOD_CHILI);
        simpleItem(ModItems.FOOD_ICECREAM);
        simpleItem(ModItems.FOOD_BURGER);
        simpleItem(ModItems.FOOD_SKEWERS);
        simpleItem(ModItems.FOOD_SWEETS);
        simpleItem(ModItems.FOOD_APPLE_PIE);
        simpleItem(ModItems.FOOD_BLINK_BROWNIE);
        simpleItem(ModItems.FOOD_TRIPLE_MEAT_TREAT);
        simpleItem(ModItems.FOOD_PIEROGI);
        simpleItem(ModItems.FOOD_OMELETTE);
        simpleItem(ModItems.FOOD_LOAF);
    }

    private void simpleItem(RegistryObject<Item> item)
    {
        withExistingParent(item.getId().getPath(),
               ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
               ResourceLocation.fromNamespaceAndPath(Patina.MODID, item.getId().getPath()).withPrefix("item/"));
    }
    private void handheldItem(RegistryObject<Item> item)
    {
        withExistingParent(item.getId().getPath(),
               ResourceLocation.withDefaultNamespace("item/handheld")).texture("layer0",
               ResourceLocation.fromNamespaceAndPath(Patina.MODID, item.getId().getPath()).withPrefix("item/"));
    }
    private ItemModelBuilder folderItem(Item item, String folder)
    {
        return withExistingParent(item.getDescriptionId(),
               ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
               ResourceLocation.fromNamespaceAndPath(Patina.MODID,"item/" + folder + "/" + item.getDescriptionId()));

    }

}
