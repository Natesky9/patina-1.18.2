package com.natesky9.patina.datagen;

import com.natesky9.patina.init.ModItems;
import com.natesky9.patina.init.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Items.FRUIT)
                .add(Items.APPLE).add(Items.GLOW_BERRIES).add(Items.SWEET_BERRIES).add(Items.MELON_SLICE);
        this.tag(ModTags.Items.MEAT)
                .add(Items.BEEF,Items.PORKCHOP,Items.CHICKEN,Items.MUTTON,Items.RABBIT,Items.COD,Items.SALMON,Items.TROPICAL_FISH);
        this.tag(ModTags.Items.VEGETABLE)
                .add(Items.POTATO,Items.CARROT,Items.BEETROOT,Items.PUMPKIN,Items.DRIED_KELP,Items.CHORUS_FRUIT);
        this.tag(ModTags.Items.GRAIN)
                .add(Items.WHEAT).add(ModItems.SLEET_WHEAT.get());
        this.tag(ModTags.Items.SWEET)
                .add(Items.SUGAR).add(Items.HONEYCOMB).add(Items.HONEY_BOTTLE);
        //
    }
}
