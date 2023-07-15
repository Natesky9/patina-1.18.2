package com.natesky9.patina.Block;

import net.minecraft.client.renderer.texture.Tickable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.Nullable;

public class CopperCableBlock extends PipeBlock {
    public CopperCableBlock(Properties pProperties) {
        super(.25F, pProperties);
    }
    public static IntegerProperty CHARGE = IntegerProperty.create("charge",0,15);

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockGetter level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();

        BlockState state = defaultBlockState();
        for (Direction direction:Direction.values())
        {
            BlockState relative = level.getBlockState(pos.relative(direction));
            boolean flag = relative.is(this);
            state = state.setValue(PROPERTY_BY_DIRECTION.get(direction),flag);
        }
        return state;
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        boolean neighbor = pNeighborState.is(this);
        return pState.setValue(PROPERTY_BY_DIRECTION.get(pDirection),neighbor);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(NORTH,SOUTH,EAST,WEST,UP,DOWN);
        pBuilder.add(CHARGE);
    }



    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {

        int charge = pState.getValue(CHARGE);
        float amp = (float) charge/20;
        if (charge > 0)
            pLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK,pPos.getX()+.5,pPos.getY()+.5,pPos.getZ()+.5
                    ,1,.1,.1,.1,amp);
        for (Direction direction:Direction.values())
        {
            BlockState neighbor = pLevel.getBlockState(pPos.relative(direction));
            if (!neighbor.is(this)) continue;
            int neighborCharge = neighbor.getValue(CHARGE);
            if (charge >= (neighborCharge+2))
            {
                pLevel.setBlock(pPos,pState.setValue(CHARGE,charge-1),2);
                pLevel.setBlock(pPos.relative(direction),neighbor.setValue(CHARGE,neighborCharge+1),2);
            }
        }
    }
}
