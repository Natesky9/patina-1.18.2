package com.natesky9.patina.datagen;

import com.natesky9.patina.Patina;
import com.natesky9.patina.init.ModBlocks;
import com.natesky9.patina.init.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Patina.MODID, existingFileHelper);
    }

    @Override
    public void registerModels() {
        simpleItem(ModItems.BEE_FRAGMENT_1.get());
        simpleItem(ModItems.BEE_FRAGMENT_2.get());
        simpleItem(ModItems.BEE_FRAGMENT_3.get());
        simpleItem(ModItems.BEE_FRAGMENT_4.get());
        simpleItem(ModItems.BEE_FRAGMENT_A.get());
        simpleItem(ModItems.BEE_FRAGMENT_B.get());
        handheldItem(ModItems.BEE_SWORD.get());
        handheldItem(ModItems.BEE_SHIELD.get());

        simpleItem(ModItems.PIG_FRAGMENT_1.get());
        simpleItem(ModItems.PIG_FRAGMENT_2.get());
        simpleItem(ModItems.PIG_FRAGMENT_3.get());
        simpleItem(ModItems.PIG_FRAGMENT_4.get());
        simpleItem(ModItems.PIG_FRAGMENT_A.get());
        simpleItem(ModItems.PIG_FRAGMENT_B.get());
        handheldItem(ModItems.PIG_SWORD.get());
        handheldItem(ModItems.PIG_CROSSBOW.get());

        //withExistingParent(ModBlocks.HONEY_PUDDLE.get().getDescriptionId(),modLoc("block/honey_puddle"));
    }


    private ItemModelBuilder simpleItem(Item item)
    {
        return withExistingParent(item.getDescriptionId(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Patina.MODID,"item/" + item));
    }
    private ItemModelBuilder handheldItem(Item item)
    {
        return withExistingParent(item.getDescriptionId(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Patina.MODID,"item/" + item));
    }
    private ItemModelBuilder folderItem(Item item, String folder)
    {
        return withExistingParent(item.getDescriptionId(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Patina.MODID,"item/" + folder + "/" + item.getDescriptionId()));

    }

}
