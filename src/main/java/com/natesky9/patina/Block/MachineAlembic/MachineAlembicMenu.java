package com.natesky9.patina.Block.MachineAlembic;

import com.natesky9.patina.Block.Template.MachineTemplateMenu;
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
import org.jetbrains.annotations.Nullable;

public class MachineAlembicMenu extends AbstractContainerMenu {
    final MachineAlembicEntity blockEntity;
    final Level level;
    final ContainerData data;
    public MachineAlembicMenu(int pContainerId, Inventory inv, FriendlyByteBuf buf)
    {
        this(pContainerId, inv, inv.player.level().getBlockEntity(buf.readBlockPos()),
                new SimpleContainerData(MachineAlembicEntity.dataSlots));
    }
    public MachineAlembicMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data)
    {
        super(ModMenuTypes.MACHINE_ALEMBIC_MENU.get(), pContainerId);
        checkContainerSize(inv, MachineAlembicEntity.slots);
        blockEntity = (MachineAlembicEntity) entity;
        this.level = inv.player.level();
        this.data = data;
        addDataSlots(data);
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(handler ->
        {
            this.addSlot(new SlotItemHandler(handler, 0,23,33));
            this.addSlot(new SlotItemHandler(handler, 1, 80, 33)
            {
                @Override
                public int getMaxStackSize() {return 1;}
            });
            this.addSlot(new SlotItemHandler(handler, 2, 137, 33));
        });
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), pPlayer, ModBlocks.MACHINE_ALEMBIC.get());
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
