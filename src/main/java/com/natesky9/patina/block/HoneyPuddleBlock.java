package com.natesky9.patina.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class HoneyPuddleBlock extends Block {
    private final VoxelShape PUDDLE_1 = Block.box(0,0,0,16,1,16);
    private final VoxelShape PUDDLE_2 = Block.box(0,0,0,16,1,16);
    private final VoxelShape PUDDLE_3 = Block.box(0,0,0,16,1,16);

    public HoneyPuddleBlock(Properties p_49795_) {
        super(p_49795_);
    }


    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        pLevel.destroyBlock(pPos,false);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return PUDDLE_1;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        pLevel.destroyBlock(pPos,false);
        return InteractionResult.CONSUME;
    }

    @Override
    public float getSpeedFactor()
    {return .3F;}

    @Override
    public float getJumpFactor()
    {return .5F;}
}
