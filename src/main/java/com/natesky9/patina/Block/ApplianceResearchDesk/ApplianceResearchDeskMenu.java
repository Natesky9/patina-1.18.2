package com.natesky9.patina.Block.ApplianceResearchDesk;

import com.natesky9.patina.init.ModBlocks;
import com.natesky9.patina.init.ModMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ApplianceResearchDeskMenu extends AbstractContainerMenu {
    final Level level;
    final BlockPos pos;
    final ContainerData data;
    final Player player;
    private final ContainerLevelAccess access;

    public ApplianceResearchDeskMenu(int pContainerId, Inventory inv, Level level, BlockPos pos, ContainerData data)
    {
        super(ModMenuTypes.APPLIANCE_RESEARCH_DESK_MENU.get(),pContainerId);
        this.pos = pos;
        this.level = level;
        this.player = inv.player;
        this.data = data;
        this.access = ContainerLevelAccess.create(level,pos);
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        this.addDataSlots(data);
    }
    public ApplianceResearchDeskMenu(int pContainerId, Inventory inv, FriendlyByteBuf buf)
    {
        this(pContainerId,inv, inv.player.level(), inv.player.blockPosition(), new SimpleContainerData(0));
    }

    @Override
    public boolean clickMenuButton(Player player, int value) {
        System.out.println(value);
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player p_38941_, int p_38942_) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level,pos),player, ModBlocks.APPLIANCE_RESEARCH_DESK.get());
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
