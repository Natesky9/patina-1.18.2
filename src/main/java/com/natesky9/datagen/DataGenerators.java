package com.natesky9.datagen;

import com.natesky9.patina.Patina;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DataGenerators {
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(true, new ModAdvancementProvider(packOutput,lookupProvider,helper,
                List.of(new ModAdvancementGenerator())));
        generator.addProvider(true, new ModRecipeProvider(packOutput,lookupProvider));
        generator.addProvider(true, ModLootTableProvider.create(packOutput,lookupProvider));
        generator.addProvider(event.includeServer(), new ModGlobalLootModifiersProvider(packOutput,lookupProvider));
        generator.addProvider(true, new ModBlockStateProvider(packOutput,helper));
        generator.addProvider(true, new ModItemModelProvider(packOutput, helper));

        DatapackBuiltinEntriesProvider datapack = ModDatapackBuiltinEntriesProvider.Make(packOutput,lookupProvider, helper);
        generator.addProvider(true, datapack);
        lookupProvider = datapack.getRegistryProvider();
        //generator.addProvider(true, ModPaintings.(packOutput,lookupProvider,helper));
        //generator.addProvider(true, ModEnchantments.DataGen(packOutput,lookupProvider,helper));
        BlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(packOutput, lookupProvider, Patina.MODID, helper);
        EntityTypeTagsProvider entityTypeTagsProvider = new ModEntityTagsProvider(packOutput, lookupProvider);
        generator.addProvider(true, blockTagsProvider);
        generator.addProvider(true, new ModItemTagsProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), Patina.MODID, helper));
        generator.addProvider(true, new ModPaintingTagsProvider(packOutput,lookupProvider, helper));
        generator.addProvider(true, entityTypeTagsProvider);
    }
}
