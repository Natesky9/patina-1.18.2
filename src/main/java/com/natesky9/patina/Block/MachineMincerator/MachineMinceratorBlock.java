package com.natesky9.patina.Block.MachineMincerator;

import com.natesky9.patina.Block.Template.MachineTemplateBlock;
import com.natesky9.patina.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class MachineMinceratorBlock extends MachineTemplateBlock
{
    //block stuff
    @Override
    public BlockEntityType<MachineMinceratorEntity> getBlockEntityType()
    {return ModBlockEntities.MACHINE_MINCERATOR_ENTITY.get();}

    //constructor
    public MachineMinceratorBlock(Properties p_49224_) {
        super(p_49224_);
    }

    //Block Entity stuff
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState)
    {//do something when this blockentity is created
        return new MachineMinceratorEntity(pPos, pState);
    }
}
