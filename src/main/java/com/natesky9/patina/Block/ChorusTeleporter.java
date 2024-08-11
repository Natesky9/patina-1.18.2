package com.natesky9.patina.Block;

import com.natesky9.patina.init.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class ChorusTeleporter extends Block {
    public ChorusTeleporter(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult p_60508_) {
        if (!(pPlayer instanceof ServerPlayer player)) return InteractionResult.CONSUME;
        Direction direction = player.getDirection();

        BlockPos target = pPos.relative(direction);
        if (pLevel.getBlockState(target).is(Blocks.END_STONE_BRICKS))
            pPos = target;
        BlockPos pos = traverse(pLevel,pPos,direction);
        if (pos == null)
        {
            pLevel.playSound(null, pPos, SoundEvents.SHULKER_AMBIENT, SoundSource.PLAYERS);
            return InteractionResult.CONSUME;
        }
        //if it's the same position
        if (pos.getX() == pPos.getX() && pos.getY() == pPos.getY() && pos.getZ() == pPos.getZ())
        {
            pLevel.playSound(null,pPos,SoundEvents.SHULKER_BULLET_HIT, SoundSource.PLAYERS);
            return InteractionResult.CONSUME;
        }
        pLevel.playSound(null,pos,SoundEvents.CHORUS_FRUIT_TELEPORT,SoundSource.PLAYERS);
        player.teleportTo(pos.getX() + .5F, pos.getY() + 1F, pos.getZ() + .5F);
        return InteractionResult.CONSUME;
    }

    private static BlockPos traverse(Level level, BlockPos pPos, Direction direction)
    {
        BlockPos pos = pPos.relative(direction);
        BlockState state = level.getBlockState(pos);
        if (!(state.is(ModBlocks.CHORUS_CABLE.get()) || state.is(ModBlocks.CHORUS_TELEPORTER.get()))) return null;

        BlockPos destination;
        Direction blacklist = direction.getOpposite();
        for (Direction dir:Direction.values())
        {
            if (dir == blacklist) continue;
            destination = pos.relative(dir);
            state = level.getBlockState(destination);
            if (state.is(ModBlocks.CHORUS_CABLE.get()))
            {
                destination = traverse(level,pos,dir);
                if (destination == null) continue;
                state = level.getBlockState(destination);
            }
            if (state.is(ModBlocks.CHORUS_TELEPORTER.get()))
            {
                if (dir != Direction.DOWN)
                    return destination;
            }
            if (state.is(Blocks.END_STONE_BRICKS) || state.is(Blocks.END_STONE_BRICK_STAIRS))
            {
                BlockPos adjacent = destination.relative(dir);
                BlockState pass = level.getBlockState(adjacent);
                if (pass.is(ModBlocks.CHORUS_TELEPORTER.get()))
                    return adjacent;
            }
        }
        return null;
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        for (Direction direction:Direction.values())
        {
            int neighbors = 0;
            BlockPos pos = pPos.relative(direction);
            BlockState adjacentState = pLevel.getBlockState(pos);
            if (pLevel.getBlockState(pos).is(ModBlocks.CHORUS_CABLE.get()))
            {
                for (BooleanProperty property: PipeBlock.PROPERTY_BY_DIRECTION.values())
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
}
