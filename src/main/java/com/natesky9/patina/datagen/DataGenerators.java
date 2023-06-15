package com.natesky9.patina.datagen;

import com.natesky9.patina.datagen.ModBlockStateProvider;
import com.natesky9.patina.datagen.ModItemModelProvider;
import com.natesky9.patina.datagen.ModLootTableProvider;
import com.natesky9.patina.datagen.ModRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;

public class DataGenerators {
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();

        generator.addProvider(true, new ModRecipeProvider(packOutput));
        generator.addProvider(true, ModLootTableProvider.create(packOutput));
        generator.addProvider(true, new ModBlockStateProvider(packOutput,helper));
        generator.addProvider(true, new ModItemModelProvider(packOutput, helper));
    }
}
