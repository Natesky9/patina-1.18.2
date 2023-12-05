package com.natesky9.patina.Block;

import com.natesky9.patina.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class GravestoneBlockEntity extends BlockEntity {
    private NonNullList<ItemStack> items;
    public GravestoneBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(ModBlockEntities.GRAVESTONE_ENTITY.get(), p_155229_, p_155230_);
        this.items = NonNullList.create();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("count",this.items.size());
        ContainerHelper.saveAllItems(tag, this.items);
    }

    @Override
    public void load(CompoundTag p_155245_) {
        super.load(p_155245_);
        int count = p_155245_.getInt("count");
        this.items = NonNullList.withSize(count, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(p_155245_, this.items);
    }
    public void add(ItemStack stack)
    {
        this.items.add(stack);
    }

    public void drops()
    {
        SimpleContainer inventory = new SimpleContainer(this.items.size());
        for (int i=0;i < this.items.size();i++)
        {
            inventory.setItem(i,this.items.get(i));
        }
        Containers.dropContents(this.level,this.worldPosition,inventory);
    }
}
