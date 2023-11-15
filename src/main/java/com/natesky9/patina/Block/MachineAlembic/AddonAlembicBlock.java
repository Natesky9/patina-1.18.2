package com.natesky9.patina.Block.MachineAlembic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class AddonAlembicBlock extends Block {
    public AddonAlembicBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.sendParticles(ParticleTypes.WITCH, pos.getX()+.5, pos.getY()+1, pos.getZ()+.5,
                8, 0, 0, 0, 0);
    }
}
