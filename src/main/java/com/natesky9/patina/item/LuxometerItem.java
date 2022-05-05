package com.natesky9.patina.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class LuxometerItem extends Item {
    public LuxometerItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(stack, level, entity, pSlotId, pIsSelected);

        int light = getLightLevel(level,entity);
        BlockPos block = entity.blockPosition();
        boolean valid = level.getBlockState(block).isAir();
        if (light < 10 && valid)
        {
            level.setBlockAndUpdate(block, Blocks.TORCH.defaultBlockState());
        }
    }

    protected int getLightLevel(BlockGetter level, Entity entity)
    {
        Vec3 pos = entity.getPosition(0);
        BlockPos block = new BlockPos(pos);
        int light = level.getLightEmission(block);
        return light;
    }
}
