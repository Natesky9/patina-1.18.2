package com.natesky9.patina.datagen;

import com.natesky9.patina.Patina;
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
        simpleItem(ModItems.BEE_FRAGMENT_1);
        simpleItem(ModItems.BEE_FRAGMENT_2);
        simpleItem(ModItems.BEE_FRAGMENT_3);
        simpleItem(ModItems.BEE_FRAGMENT_4);
        simpleItem(ModItems.BEE_FRAGMENT_A);
        simpleItem(ModItems.BEE_FRAGMENT_B);
        simpleItem(ModItems.BEE_FRAGMENT_C);
        simpleItem(ModItems.BEE_FRAGMENT_D);
        handheldItem(ModItems.BEE_SWORD);
        handheldItem(ModItems.BEE_SHIELD);

        simpleItem(ModItems.PIG_FRAGMENT_1);
        simpleItem(ModItems.PIG_FRAGMENT_2);
        simpleItem(ModItems.PIG_FRAGMENT_3);
        simpleItem(ModItems.PIG_FRAGMENT_4);
        simpleItem(ModItems.PIG_FRAGMENT_A);
        simpleItem(ModItems.PIG_FRAGMENT_B);
        simpleItem(ModItems.PIG_FRAGMENT_C);
        simpleItem(ModItems.PIG_FRAGMENT_D);
        handheldItem(ModItems.PIG_SWORD);
        handheldItem(ModItems.PIG_CROSSBOW);

        simpleItem(ModItems.CHARM_CONTRABAND);
        simpleItem(ModItems.CHARM_DETONATION);
        simpleItem(ModItems.CHARM_EXPERIENCE);
        simpleItem(ModItems.CHARM_WARDING);
        simpleItem(ModItems.CHARM_ALCHEMY);
        simpleItem(ModItems.CHARM_FERTILITY);
        simpleItem(ModItems.CHARM_VITALITY);

        simpleItem(ModItems.CHARM_FRAGMENT);
    }


    private void simpleItem(RegistryObject<Item> item)
    {
        withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Patina.MODID, item.getId().getPath()).withPrefix("item/"));
    }
    private void handheldItem(RegistryObject<Item> item)
    {
        withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Patina.MODID, item.getId().getPath()).withPrefix("item/"));
    }
    private ItemModelBuilder folderItem(Item item, String folder)
    {
        return withExistingParent(item.getDescriptionId(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Patina.MODID,"item/" + folder + "/" + item.getDescriptionId()));

    }

}
