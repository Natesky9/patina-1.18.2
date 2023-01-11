package com.natesky9.patina.block;

import com.natesky9.patina.Patina;
import com.natesky9.patina.init.ModBlocks;
import com.natesky9.patina.init.ModStats;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.scores.Objective;

public class TelechorusBlock extends Block {
    public TelechorusBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!(pPlayer instanceof ServerPlayer)) return InteractionResult.CONSUME;
        Direction direction = pPlayer.getDirection();

        BlockPos pos = traverse(pLevel,pPos.relative(direction),direction);
        if (!pLevel.getBlockState(pos).is(ModBlocks.TELECHORUS.get()))
            return fail(pLevel,pPlayer);

        float posX = pos.getX()+.5F;
        float posY = pos.getY()+1F;
        float posZ = pos.getZ()+.5F;

        pPlayer.teleportTo(posX,posY,posZ);
        pLevel.playSound(null, pPos, SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.AMBIENT, .5F, 1F);
        pPlayer.resetStat(Stats.CUSTOM.get(ModStats.HINT_TELECHORUS.get()));
        return InteractionResult.CONSUME;
    }
    private static BlockPos traverse(Level level, BlockPos pos, Direction direction)
    {
        BlockState state = level.getBlockState(pos);
        if (!(state.is(ModBlocks.TELECHORUS.get()) || state.is(ModBlocks.CHORUS_CABLE.get()))) return pos;
        //System.out.println("traverse");
        Direction blacklist = direction.getOpposite();
        BlockPos destination;
        for (Direction dir: Direction.values())
        {
            if (dir == blacklist) continue;
            destination = pos.relative(dir);
            state = level.getBlockState(destination);
            if (state.is(ModBlocks.CHORUS_CABLE.get()))
            {
                destination = traverse(level,destination,dir);
                state = level.getBlockState(destination);
            }
            if (state.is(ModBlocks.TELECHORUS.get()))
            {
                //System.out.println("end");
                return destination;
            }
        }

        return pos;
    }
    InteractionResult fail(Level pLevel, Player pPlayer)
    {
        if (pPlayer instanceof ServerPlayer player) {
            //failed teleport
            pLevel.playSound(null, player.blockPosition(), SoundEvents.UI_BUTTON_CLICK, SoundSource.BLOCKS, 1, 1);
            player.awardStat(ModStats.HINT_TELECHORUS.get());

            Stat stat = Stats.CUSTOM.get(ModStats.HINT_TELECHORUS.get());

            int hints = player.getStats().getValue(stat);
            Component component;
            switch (hints) {
                case 1 -> component = new TranslatableComponent("hint_telechorus_1", Patina.MOD_ID);
                case 2 -> component = new TranslatableComponent("hint_telechorus_2", Patina.MOD_ID);
                case 3 -> component = new TranslatableComponent("hint_telechorus_3", Patina.MOD_ID);
                case 4 -> component = new TranslatableComponent("hint_telechorus_4",Patina.MOD_ID);
                default -> component = new TranslatableComponent("hint_telechorus_5",Patina.MOD_ID);
            }
            player.displayClientMessage(component,true);

        }
        return InteractionResult.PASS;
    }
}
