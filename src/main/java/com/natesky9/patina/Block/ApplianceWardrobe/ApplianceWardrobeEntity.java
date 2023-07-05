package com.natesky9.patina.Block.ApplianceWardrobe;

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

public class ApplianceWardrobeEntity extends BlockEntity implements MenuProvider {
    public ApplianceWardrobeEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.APPLIANCE_WARDROBE_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.patina.appliance_wardrobe");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new ApplianceWardrobeMenu(pContainerId,pPlayerInventory,this);
    }
}
