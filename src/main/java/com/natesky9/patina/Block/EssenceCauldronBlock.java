package com.natesky9.patina.Block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluid;

public class EssenceCauldronBlock extends AbstractCauldronBlock {
    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL;

    public EssenceCauldronBlock(Properties pProperties, CauldronInteraction.InteractionMap pInteractions) {
        super(pProperties, pInteractions);
    }


    @Override
    public boolean isFull(BlockState pState) {
        return (Integer) pState.getValue(LEVEL) == 15;
    }

    @Override
    protected boolean canReceiveStalactiteDrip(Fluid pFluid) {
        return false;
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> codec() {
        return null;
    }

    @Override
    protected double getContentHeight(BlockState pState) {
        return (6 + (double)(Integer) pState.getValue(LEVEL) *9/15 / 16);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LEVEL);
    }
}
