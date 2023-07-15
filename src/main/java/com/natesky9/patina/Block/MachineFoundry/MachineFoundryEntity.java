package com.natesky9.patina.Block.MachineFoundry;

import com.natesky9.patina.Block.Template.MachineTemplateEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MachineFoundryEntity extends MachineTemplateEntity implements MenuProvider {
    public static final int slots = 3;
    public MachineFoundryEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(pWorldPosition, pBlockState, slots);
    }

    @Override
    protected boolean mySlotValid(int slot, @NotNull ItemStack stack) {
        return false;
    }

    @Override
    protected ContainerData createData() {
        return null;
    }

    @Override
    protected void myContentsChanged() {

    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.patina.machine_foundry");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new MachineFoundryMenu(pContainerId,pInventory,this);
    }

    @Override
    protected void MachineTick() {

    }
}