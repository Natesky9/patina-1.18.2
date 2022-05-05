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
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluids;
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
        BlockPos north = blockpos.north();
        BlockPos east = blockpos.east();
        BlockPos south = blockpos.south();
        BlockPos west = blockpos.west();
        BlockPos up = blockpos.above();
        BlockPos down = blockpos.below();
        boolean blocknorth = chorusNetwork(level,north);
        boolean blockeast = chorusNetwork(level,east);
        boolean blocksouth = chorusNetwork(level,south);
        boolean blockwest = chorusNetwork(level,west);
        boolean blockup = chorusNetwork(level,up);
        boolean blockdown = chorusNetwork(level,down);

        return super.getStateForPlacement(pContext)
                .setValue(NORTH,blocknorth)
                .setValue(EAST,blockeast)
                .setValue(SOUTH,blocksouth)
                .setValue(WEST,blockwest)
                .setValue(UP,blockup)
                .setValue(DOWN,blockdown);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        boolean flag = pNeighborState.is(this) || pNeighborState.is(ModBlocks.TELECHORUS.get());
        return pState.setValue(PROPERTY_BY_DIRECTION.get(pDirection), flag);
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
