package com.natesky9.patina.Block.Benchmark;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class ApplianceBenchmarkBlock extends Block {
    public static final MapCodec<ApplianceBenchmarkBlock> CODEC = simpleCodec(ApplianceBenchmarkBlock::new);
    private static final Component CONTAINER_TITLE = Component.translatable("container.appliance_benchmark");


    public ApplianceBenchmarkBlock(Properties p_49224_) {
        super(p_49224_);
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult p_60508_) {
        if (level.isClientSide())
            return InteractionResult.SUCCESS;
        else
        {
            player.openMenu(state.getMenuProvider(level, pos));
            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    protected MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        ContainerData data = new SimpleContainerData(6);
        return new SimpleMenuProvider(
                (id, inv, c) -> new ApplianceBenchmarkMenu(id, inv, level, pos,data), CONTAINER_TITLE
        );
    }

    @Override
    protected void tick(BlockState p_222945_, ServerLevel level, BlockPos pos, RandomSource p_222948_) {
        super.tick(p_222945_, level, pos, p_222948_);
        Player player = level.getNearestPlayer(pos.getX(),pos.getY(),pos.getZ(),3,false);
        if (player == null)
            return;
        if (player.containerMenu instanceof ApplianceBenchmarkMenu menu)
        {
            menu.calculate(menu.armorSlots);
        }
    }
}
