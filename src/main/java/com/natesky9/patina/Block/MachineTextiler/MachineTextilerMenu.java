package com.natesky9.patina.Block.MachineTextiler;

import com.natesky9.patina.init.ModBlocks;
import com.natesky9.patina.init.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

import static com.natesky9.patina.Block.MachineTextiler.MachineTextilerEntity.input;
import static com.natesky9.patina.Block.MachineTextiler.MachineTextilerEntity.output;


public class MachineTextilerMenu extends AbstractContainerMenu {
    final MachineTextilerEntity blockEntity;
    final Level level;
    final ContainerData data;
    public MachineTextilerMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId,inv,inv.player.level().getBlockEntity(extraData.readBlockPos()),
                new SimpleContainerData(MachineTextilerEntity.dataSlots));
    }

    public MachineTextilerMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(ModMenuTypes.MACHINE_TEXTILER_MENU.get(), pContainerId);
        checkContainerSize(inv, MachineTextilerEntity.slots);
        blockEntity = (MachineTextilerEntity) entity;
        this.level = inv.player.level();
        this.data = data;
        addDataSlots(data);
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER,null).ifPresent(
                handler ->
                {
                    this.addSlot(new SlotItemHandler(handler, input,28,34));
                    this.addSlot(new SlotItemHandler(handler, output, 116,35));
                }
        );
    }

    @Override
    public ItemStack quickMoveStack(Player p_38941_, int p_38942_) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level,blockEntity.getBlockPos()),
                player, ModBlocks.MACHINE_TEXTILER.get());
    }

    private void addPlayerInventory(Inventory playerInventory)
    {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory)
    {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
