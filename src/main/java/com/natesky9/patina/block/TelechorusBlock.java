package com.natesky9.patina.block;

import com.natesky9.patina.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

public class TelechorusBlock extends Block {
    public TelechorusBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        float entityPitch = pEntity.getXRot();
        float prev = pEntity.xRotO;
        //if the player is looking "down"
        //and it wasn't before
        if (entityPitch < 60 && prev >= 60) {
            //teleport 5 blocks in that direction
            Direction cardinal = pEntity.getDirection();
            BlockPos destination = pPos.above().relative(cardinal, 5);
            //teleport to center of block
            pEntity.teleportTo(destination.getX() + .50, destination.getY(), destination.getZ() + .50);
            pLevel.playSound(null, pPos, SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.AMBIENT, .5F, 1F);
        }
    }
}
