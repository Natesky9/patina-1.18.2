package com.natesky9.patina.Block.ApplianceIcebox;

import com.natesky9.patina.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ApplianceIceboxEntity extends BlockEntity implements MenuProvider {
    public ApplianceIceboxEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.APPLIANCE_ICEBOX_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.patina.appliance_icebox");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new ApplianceIceboxMenu(pContainerId,pPlayerInventory,this);
    }
}
