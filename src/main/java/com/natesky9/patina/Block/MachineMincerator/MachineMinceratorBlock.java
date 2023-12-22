package com.natesky9.patina.Block.MachineMincerator;

import com.mojang.serialization.MapCodec;
import com.natesky9.patina.Block.Template.MachineTemplateBlock;
import com.natesky9.patina.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class MachineMinceratorBlock extends MachineTemplateBlock
{
    public static final MapCodec<MachineMinceratorBlock> CODEC = simpleCodec(MachineMinceratorBlock::new);
    public MachineMinceratorBlock(Properties p_49224_) {
        super(p_49224_);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntityType<MachineMinceratorEntity> getBlockEntityType()
    {return ModBlockEntities.MACHINE_MINCERATOR_ENTITY.get();}

    //Block Entity stuff
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState)
    {//do something when this blockentity is created
        return new MachineMinceratorEntity(pPos, pState);
    }
}
