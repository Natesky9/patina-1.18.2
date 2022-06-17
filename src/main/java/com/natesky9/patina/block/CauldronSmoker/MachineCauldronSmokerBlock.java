package com.natesky9.patina.block.CauldronSmoker;

import com.natesky9.patina.block.Template.MachineTemplateBlock;
import com.natesky9.patina.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class MachineCauldronSmokerBlock extends MachineTemplateBlock
{
    //block stuff
    @Override
    public BlockEntityType<MachineCauldronSmokerEntity> getBlockEntityType()
    {return ModBlockEntities.MACHINE_CAULDRON_SMOKER_ENTITY.get();}


    //constructor
    public MachineCauldronSmokerBlock(Properties p_49224_) {
        super(p_49224_);
    }

    //Block Entity stuff
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState)
    {//do something when this blockentity is created
        return new MachineCauldronSmokerEntity(pPos, pState);
    }

}
