package com.natesky9.datagen;

import com.natesky9.patina.Patina;
import com.natesky9.patina.init.ModEnchantments;
import com.natesky9.patina.painting.ModPaintings;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDatapackBuiltinEntriesProvider {


    public static DatapackBuiltinEntriesProvider Make(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper helper)
    {
        final RegistrySetBuilder builder = new RegistrySetBuilder();
        builder.add(Registries.PAINTING_VARIANT, ModPaintings::bootstrap);
        builder.add(Registries.ENCHANTMENT, ModEnchantments::bootstrap);
        return new DatapackBuiltinEntriesProvider(packOutput, lookupProvider, builder, Set.of(Patina.MODID));
    }

    //public RegistriesDatapackGenerator Datagen(PackOutput output, CompletableFuture<HolderLookup.Provider> holder)
    //{
//
    //    builder.add(Registries.PAINTING_VARIANT, ModPaintings::bootstrap);
    //    return new DatapackBuiltinEntriesProvider(packOutput,registries,builder, Set.of(Patina.MODID));
    //}

}
