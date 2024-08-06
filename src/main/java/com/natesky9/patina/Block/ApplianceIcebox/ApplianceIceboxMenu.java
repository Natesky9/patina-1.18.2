package com.natesky9.patina.Block.ApplianceIcebox;

import com.natesky9.patina.init.ModBlocks;
import com.natesky9.patina.init.ModMenuTypes;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class ApplianceIceboxMenu extends AbstractContainerMenu {
    final Level level;
    final ApplianceIceboxEntity blockentity;
    public ApplianceIceboxMenu(int pContainerId, Inventory inv, FriendlyByteBuf buf)
    {
        this(pContainerId, inv, inv.player.level().getBlockEntity(buf.readBlockPos()));
    }
    public ApplianceIceboxMenu(int pContainerId, Inventory inv, BlockEntity entity)
    {
        super(ModMenuTypes.APPLIANCE_ICEBOX_MENU.get(), pContainerId);
        blockentity = (ApplianceIceboxEntity) entity;
        level = inv.player.level();
        //add slots
        this.blockentity.getCapability(ForgeCapabilities.ITEM_HANDLER,null).ifPresent(handler ->
        {
            for (int i=0;i<20;i++)//add fridge slots
            {
                this.addSlot(new SlotItemHandler(handler,i,80+i%5*18,i/5*18)
                     {
                         @Override
                         public boolean mayPlace(@NotNull ItemStack stack)
                         {
                             PotionContents contents = stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
                             FoodProperties food = stack.get(DataComponents.FOOD);
                             return contents != PotionContents.EMPTY || food != null;
                         }
                     }
                );
            }

            for (int i = 0; i < 3; ++i)//add player inventory
            {
                for (int l = 0; l < 9; ++l)
                {
                    this.addSlot(new Slot(inv, l + i * 9 + 9, 8 + l * 18, 94 + i * 18));
                }
            }
            for (int i = 0; i < 9; ++i)//add player hotbar
            {
                this.addSlot(new Slot(inv, i, 8 + i * 18, 152));
            }
        });
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex)
    {
        ItemStack temp;
        int playerslot = pIndex-20;
        if (pIndex >= 0 && pIndex < 20)//click inside fridge
        {
            temp = blockentity.handler.getStackInSlot(pIndex);
            pPlayer.getInventory().add(temp);
            return ItemStack.EMPTY;
        }
        if (pIndex >= 20)//clicked in inventory
        {
            Slot slot = this.getSlot(pIndex);
            temp = slot.getItem();
            boolean flag = moveItemStackTo(this.getSlot(pIndex).getItem(),0,19,false);
            //slot.set(ItemStack.EMPTY);
            return ItemStack.EMPTY;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level,blockentity.getBlockPos()),
                pPlayer, ModBlocks.APPLIANCE_ICEBOX.get());
    }
}
