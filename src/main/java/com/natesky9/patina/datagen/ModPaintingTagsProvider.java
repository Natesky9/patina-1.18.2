package com.natesky9.patina.datagen;

import com.natesky9.patina.Patina;
import com.natesky9.patina.painting.ModPaintings;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModPaintingTagsProvider extends PaintingVariantTagsProvider {
    public ModPaintingTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper helper) {
        super(packOutput,lookupProvider, Patina.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(PaintingVariantTags.PLACEABLE)
                .add(ModPaintings.ALCHEMY_1)
                .add(ModPaintings.ALCHEMY_2)
                .add(ModPaintings.ALCHEMY_3)
                .add(ModPaintings.ALCHEMY_4)
                .add(ModPaintings.ALCHEMY_5)
                .add(ModPaintings.NOTCH);
    }
}
