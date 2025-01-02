package com.natesky9.patina.Block;

import com.mojang.serialization.MapCodec;
import com.natesky9.patina.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EssenceCauldronBlock extends AbstractCauldronBlock {
    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL;
    static final VoxelShape INSIDE = box(2.0, 4.0, 2.0, 14.0, 15, 14.0);
    static final VoxelShape SHAPE = Shapes.join(Shapes.block(), Shapes.or(
            box(0.0, 0.0, 4.0, 16.0, 3.0, 12.0),
            box(4.0, 0.0, 0.0, 12.0, 3.0, 16.0),
            box(2.0, 0.0, 2.0, 14.0, 3.0, 14.0), INSIDE), BooleanOp.ONLY_FIRST);

    public EssenceCauldronBlock(Properties pProperties, CauldronInteraction.InteractionMap pInteractions) {
        super(pProperties, pInteractions);
    }

    public static VoxelShape getSHAPE() {
        return SHAPE;
    }

    @Override
    public boolean isFull(BlockState pState) {
        return pState.getValue(LEVEL) == 15;
    }

    public boolean isEmpty(BlockState pState)
    {
        return pState.getValue(LEVEL) == 0;
    }

    @Override
    protected void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        int level = pState.getValue(LEVEL);
        //only affect players
        if (pEntity instanceof Player player && !isEmpty(pState))
        {
            pLevel.setBlock(pPos, ModBlocks.ESSENCE_CAULDRON.get().defaultBlockState().setValue(LEVEL,level-1),2);

            player.giveExperiencePoints(1);
        }
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        //do something different
        if (pEntity instanceof ExperienceOrb orb && !isFull(pState))
        {
            int level = pState.getValue(LEVEL);
            System.out.println("orb value: " + orb.value);
            pLevel.setBlock(pPos, ModBlocks.ESSENCE_CAULDRON.get().defaultBlockState().setValue(LEVEL,level+1),2);
            orb.value--;
            if (orb.value <= 0)
                orb.discard();

        }
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
