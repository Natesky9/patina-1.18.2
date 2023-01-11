package com.natesky9.patina.block;

import com.natesky9.patina.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.Nullable;

public class ChorusCableBlock extends PipeBlock {

    public ChorusCableBlock(Properties properties) {
        super(.25F,properties);
    }
    protected boolean chorusNetwork(BlockGetter level, BlockPos pos)
    {
        Block block = level.getBlockState(pos).getBlock();
        return block == ModBlocks.CHORUS_CABLE.get() || block == ModBlocks.TELECHORUS.get();
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockGetter level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();

        BlockState state = super.getStateForPlacement(pContext);

        for (Direction direction:Direction.values())
        {
            BlockState relative = level.getBlockState(blockpos.relative(direction));
            boolean flag = relative.is(this) || relative.is(ModBlocks.TELECHORUS.get());
            state = state.setValue(PROPERTY_BY_DIRECTION.get(direction),flag);
        }
        return state;
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        boolean neighbor = pNeighborState.is(this) || pNeighborState.is(ModBlocks.TELECHORUS.get());
        return pState.setValue(PROPERTY_BY_DIRECTION.get(pDirection), neighbor);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(NORTH,EAST,SOUTH,WEST,UP,DOWN);
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        int neighbors = 0;
        for (Direction dir:Direction.values())
        {
            BlockPos pos = pPos.relative(dir);
            if (pLevel.getBlockState(pos).getBlock() == ModBlocks.CHORUS_CABLE.get())
            {
                neighbors++;
            }
        }
    return neighbors <= 2;
    }

}
