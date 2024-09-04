package com.natesky9.datagen;

import com.natesky9.patina.init.ModEntityTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class ModEntityTagsProvider extends EntityTypeTagsProvider {
    public ModEntityTagsProvider(PackOutput p_256095_, CompletableFuture<HolderLookup.Provider> p_256572_)
    {
        super(p_256095_, p_256572_);
    }

    @Override
    protected void addTags(HolderLookup.Provider p_255894_)
    {
        //add to arthropods
        this.tag(EntityTypeTags.ARTHROPOD)
                .add(ModEntityTypes.BEE_BOSS.get())
                .add(ModEntityTypes.SPIDER_BOSS.get())
                .add(ModEntityTypes.SPIDER_NEST.get());
        this.tag(EntityTypeTags.FALL_DAMAGE_IMMUNE)
                .add(ModEntityTypes.BEE_BOSS.get())
                .add(ModEntityTypes.SPIDER_NEST.get())
                .add(ModEntityTypes.SPIDER_BOSS.get());
    }
}
