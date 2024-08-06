package com.natesky9.patina.Block.ApplianceWardrobe;

import com.natesky9.patina.ArmorSlot;
import com.natesky9.patina.init.ModBlocks;
import com.natesky9.patina.init.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class ApplianceWardrobeMenu extends AbstractContainerMenu {
    final Level level;
    final ApplianceWardrobeEntity blockEntity;
    //final EquipmentSlot head;
    //final EquipmentSlot chest;
    //final EquipmentSlot legs;
    //final EquipmentSlot feet;

    public ApplianceWardrobeMenu(int pContainerId, Inventory inv, FriendlyByteBuf buf)
    {
        this(pContainerId, inv, inv.player.level().getBlockEntity(buf.readBlockPos()));
    }
    public ApplianceWardrobeMenu(int pContainerId, Inventory inv, BlockEntity entity)
    {
        super(ModMenuTypes.APPLIANCE_WARDROBE_MENU.get(), pContainerId);
        blockEntity = (ApplianceWardrobeEntity) entity;
        level = inv.player.level();
        //add wardrobe slots
        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER,null).ifPresent(handler ->
        {
            for (int i=0;i<20;i++)
            {
                int row = i/5;
                this.addSlot(new SlotItemHandler(handler, i,80+i%5*18,8+i/5*18)
                {
                    @Override
                    public boolean mayPlace(@NotNull ItemStack stack) {

                        if (!(stack.getItem() instanceof ArmorItem armor)) return row == 0;
                        return armor.getType().ordinal() == row;
                    }

                    @Override
                    public int getMaxStackSize() {
                        return 1;
                    }
                });
            }
        });
        //add armor slots
        for(int i = 0; i < 4; ++i) {
            this.addSlot(new ArmorSlot(inv, 39-i, 8, 8 + i * 18,i));
        }
        //add player inventory
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(inv, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
        //add player hotbar
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(inv, i, 8 + i * 18, 142));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        ItemStack temp;
        if (pIndex >= 0 && pIndex < 20)//click inside wardrobe
        {
            temp = blockEntity.handler.getStackInSlot(pIndex);
            if (temp.isEmpty()) return ItemStack.EMPTY;//nothing got moved
            int row = pIndex/5;
            int armorIndex = 20+row;
            ItemStack equipped = this.getSlot(armorIndex).getItem();
            //moveItemStackTo(temp,pIndex,pIndex,false);
            //moveItemStackTo(equipped,armorIndex,armorIndex,false);
            //blockEntity.handler.setStackInSlot(pIndex,equipped);
            this.getSlot(pIndex).setByPlayer(equipped);
            this.getSlot(armorIndex).setByPlayer(temp);
            playEquipSound(temp);
            }
        if (pIndex >= 20 && pIndex < 24)
        {
            ItemStack clicked = this.getSlot(pIndex).getItem();
            if (clicked.isEmpty()) return ItemStack.EMPTY;
            int row = pIndex-20;
            for (int i=0;i < 5;i++)
            {
                int slot = 5*row+i;
                ItemStack armor = blockEntity.handler.getStackInSlot(slot);
                if (!armor.isEmpty()) continue;
                playEquipSound(clicked);
                blockEntity.handler.setStackInSlot(slot,clicked);
                this.getSlot(pIndex).setByPlayer(ItemStack.EMPTY);
                return ItemStack.EMPTY;
            }
            pPlayer.addItem(clicked);
            playEquipSound(clicked);
            this.getSlot(pIndex).set(ItemStack.EMPTY);
        }
        if (pIndex >=24)
        {
            ItemStack stack = this.getSlot(pIndex).getItem();
            int row = 0;
            if (stack.getItem() instanceof ArmorItem armor)
                row = armor.getType().ordinal();
            //place in wardrobe
            boolean done = moveItemStackTo(stack,5*row,5*row+5,false);
            if (done) return ItemStack.EMPTY;
            //for (int i=0;i < 5;i++)
            //{
            //    int slot = 5*row+i;
            //    ItemStack inSlot = blockEntity.handler.getStackInSlot(slot);
            //    if (!inSlot.isEmpty()) continue;
            //    blockEntity.handler.insertItem(slot,stack,false);
            //    //blockEntity.handler.setStackInSlot(slot,stack);
            //    //this.getSlot(pIndex).getItem().shrink(1);
            //    //this.getSlot(pIndex).set(this.getSlot(pIndex).getItem().shrink(1)););
            //    return ItemStack.EMPTY;
            //}
            int playerslot = 20+row;

            ItemStack equipped = this.getSlot(playerslot).getItem();
            if (equipped.isEmpty())
            {
                this.getSlot(playerslot).set(stack);
                this.getSlot(pIndex).set(ItemStack.EMPTY);
                playEquipSound(stack);
                return ItemStack.EMPTY;
            }
            //else
            //{
            //
            //    temp = this.getSlot(playerslot).getItem();
            //    this.getSlot(playerslot).set(stack);
            //    this.getSlot(pIndex).set(temp);
            //}
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level,blockEntity.getBlockPos()),
                pPlayer, ModBlocks.APPLIANCE_WARDROBE.get());
    }
    private void playEquipSound(ItemStack stack)
    {
        if (stack.getItem() instanceof ArmorItem armorItem)
        {
            blockEntity.getLevel().playSound(null,blockEntity.getBlockPos(),armorItem.getEquipSound().get(), SoundSource.PLAYERS);
        }
        else blockEntity.getLevel().playSound(null,blockEntity.getBlockPos(), SoundEvents.WOOD_PLACE, SoundSource.PLAYERS);

    }
}
