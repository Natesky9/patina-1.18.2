package com.natesky9.patina.Block.ApplianceIcebox;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ApplianceIceboxBlock extends BaseEntityBlock {
    public ApplianceIceboxBlock(Properties pProperties) {
        super(pProperties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ApplianceIceboxEntity(pPos,pState);
    }
}