package com.natesky9.patina.datagen;

import com.natesky9.patina.init.ModItems;
import com.natesky9.patina.init.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.FRUIT)
                .add(Items.APPLE).add(Items.GLOW_BERRIES).add(Items.SWEET_BERRIES).add(Items.MELON_SLICE);
        this.tag(ModTags.MEAT)
                .add(Items.BEEF,Items.PORKCHOP,Items.CHICKEN,Items.MUTTON,Items.RABBIT,Items.COD,Items.SALMON,Items.TROPICAL_FISH)
                .add(Items.COOKED_BEEF).add(Items.COOKED_CHICKEN).add(Items.COOKED_COD).add(Items.COOKED_PORKCHOP)
                .add(Items.COOKED_MUTTON).add(Items.COOKED_RABBIT).add(Items.COOKED_SALMON);
        this.tag(ModTags.VEGETABLE)
                .add(Items.POTATO,Items.CARROT,Items.BEETROOT,Items.PUMPKIN,Items.DRIED_KELP,Items.CHORUS_FRUIT);
        this.tag(ModTags.GRAIN)
                .add(Items.WHEAT);
        this.tag(ModTags.SWEET)
                .add(Items.SUGAR).add(Items.HONEYCOMB).add(Items.HONEY_BOTTLE);
        this.tag(ModTags.CALCIUM)
                .add(Blocks.CALCITE.asItem())
                .add(Blocks.BONE_BLOCK.asItem())
                .add(Blocks.SKELETON_SKULL.asItem())
                .add(Blocks.WITHER_SKELETON_SKULL.asItem());
        this.tag(Tags.Items.DUSTS)
                .remove(Items.PRISMARINE_SHARD)
                .add(ModItems.POTION_SALT.get())
                .add(ModItems.VOID_SALT.get())
                .add(Items.GUNPOWDER);
        this.tag(Tags.Items.SEEDS)
                .add(Items.COCOA_BEANS).add(Items.NETHER_WART)
                .add(Items.TORCHFLOWER_SEEDS);
        //

    }
}
