package com.natesky9.patina.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;

public class FertilizerItem extends Item {
    public FertilizerItem(Properties pProperties)
    {super(pProperties);}

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        BlockPos pos = pContext.getClickedPos();
        Level level = pContext.getLevel();
        BlockState blockState = level.getBlockState(pos);

        if (blockState.getBlock() == Blocks.WHEAT) {
            level.setBlockAndUpdate(pos, Blocks.CARROTS.defaultBlockState());
        }
        return InteractionResult.CONSUME;
    }
}
