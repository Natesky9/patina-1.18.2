package com.natesky9.patina.Block.ApplianceIcebox;

import com.natesky9.patina.init.ModBlocks;
import com.natesky9.patina.init.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
                             Potion potion = PotionUtils.getPotion(stack);
                             return stack.isEdible() || stack.getItem() instanceof PotionItem;
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

            //for (int i = 0;i < 20;i++)
            //{
            //    ItemStack inSlot = blockentity.handler.getStackInSlot(i);
            //    if (!ItemHandlerHelper.canItemStacksStack(temp,inSlot)) continue;
            //    inSlot.grow(1);
            //    temp.shrink(1);
//
            //    break;
            //    //temp = blockentity.handler.insertItem(i,temp,false);
            //    //if (temp.getCount() <= 0) break;
            //}
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level,blockentity.getBlockPos()),
                pPlayer, ModBlocks.APPLIANCE_ICEBOX.get());
    }
}
