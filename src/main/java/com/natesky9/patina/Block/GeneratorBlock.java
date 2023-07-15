package com.natesky9.patina.Block;

import com.natesky9.patina.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class GeneratorBlock extends Block {
    public GeneratorBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        for (Direction direction:Direction.values())
        {
            BlockPos pos = pPos.relative(direction);
            BlockState state = pLevel.getBlockState(pos);
            if (state.is(ModBlocks.CHARGE_CABLE.get()))
            {
                pLevel.setBlock(pos,state.setValue(CopperCableBlock.CHARGE,
                        Math.min(15,state.getValue(CopperCableBlock.CHARGE)+1)),2);
                pLevel.playSound(null,pPos, SoundEvents.COPPER_PLACE, SoundSource.BLOCKS,.5F,.5F);
            }
        }
        return InteractionResult.CONSUME;
    }
}
