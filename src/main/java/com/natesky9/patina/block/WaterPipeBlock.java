package com.natesky9.patina.block;

import com.natesky9.patina.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.Nullable;

public class WaterPipeBlock extends PipeBlock {

    public WaterPipeBlock(Properties pProperties) {
        super(.2F, pProperties);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockGetter level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();

        BlockState state = super.getStateForPlacement(pContext);

        for (Direction direction:Direction.values())
        {
            BlockState target = level.getBlockState(blockpos.relative(direction));
            state = state.setValue(PROPERTY_BY_DIRECTION.get(direction),
                    target.is(this) || target.is(ModBlocks.WATER_VALVE.get())
                            || target.is(Blocks.CAULDRON) || target.is(Blocks.WATER_CAULDRON));
        }
        return state;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(NORTH,EAST,SOUTH,WEST,UP,DOWN);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        boolean flag = pNeighborState.is(this) || pNeighborState.is(ModBlocks.WATER_VALVE.get())
                || pNeighborState.is(Blocks.CAULDRON) || pNeighborState.is(Blocks.WATER_CAULDRON);
        return pState.setValue(PROPERTY_BY_DIRECTION.get(pDirection),flag);
    }
}
