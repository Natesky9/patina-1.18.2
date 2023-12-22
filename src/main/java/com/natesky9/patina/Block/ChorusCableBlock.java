package com.natesky9.patina.Block;

import com.mojang.serialization.MapCodec;
import com.natesky9.patina.init.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

public class ChorusCableBlock extends PipeBlock {
    public static final MapCodec<ChorusCableBlock> CODEC = simpleCodec(ChorusCableBlock::new);
    public ChorusCableBlock(Properties pProperties) {
        super(.25F,pProperties);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockGetter level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();

        BlockState state = defaultBlockState();
        for (Direction direction:Direction.values())
        {
            BlockState relative = level.getBlockState(pos.relative(direction));
            boolean flag = relative.is(this)
                    || (relative.is(ModBlocks.CHORUS_TELEPORTER.get()) && direction != Direction.DOWN)
                    || relative.is(Blocks.END_STONE_BRICKS);
            state = state.setValue(PROPERTY_BY_DIRECTION.get(direction),flag);
        }
        return state;
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        boolean neighbor = pNeighborState.is(this)
                || (pNeighborState.is(ModBlocks.CHORUS_TELEPORTER.get()) && pDirection != Direction.DOWN)
                || pNeighborState.is(Blocks.END_STONE_BRICKS);
        return pState.setValue(PROPERTY_BY_DIRECTION.get(pDirection),neighbor);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(NORTH,SOUTH,EAST,WEST,UP,DOWN);
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        int neighbors = 0;
        for (BooleanProperty property: PROPERTY_BY_DIRECTION.values())
        {
            neighbors += pState.getValue(property) ? 1:0;
        }
        if (neighbors > 2)
        {
            if (pLevel instanceof ClientLevel)
                Minecraft.getInstance().player.displayClientMessage(
                        Component.literal("This cable feels crowded"),true);
            return false;
        }

        for (Direction direction:Direction.values())
        {
            neighbors = 0;
            BlockPos pos = pPos.relative(direction);
            BlockState adjacentState = pLevel.getBlockState(pos);
            if (pLevel.getBlockState(pos).is(ModBlocks.CHORUS_CABLE.get()))
            {
                for (BooleanProperty property: PROPERTY_BY_DIRECTION.values())
                {
                    neighbors += adjacentState.getValue(property) ? 1:0;
                }
                if (neighbors > 1)
                {
                    if (pLevel instanceof ClientLevel)
                        Minecraft.getInstance().player.displayClientMessage(
                                Component.literal("A neighboring cable feels crowded"),true);
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected MapCodec<? extends PipeBlock> codec() {
        return CODEC;
    }
}
