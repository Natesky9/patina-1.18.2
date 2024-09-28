package com.natesky9.patina.Block.MachineFoundry;

import com.natesky9.patina.Recipe.ModRecipeBookType;
import com.natesky9.patina.Recipe.FoundryRecipe;
import com.natesky9.patina.init.ModBlocks;
import com.natesky9.patina.init.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

import static com.natesky9.patina.Block.MachineFoundry.MachineFoundryEntity.*;

public class MachineFoundryMenu extends RecipeBookMenu<RecipeInput, FoundryRecipe> {
    final MachineFoundryEntity blockEntity;
    final Level level;
    final ContainerData data;

    public MachineFoundryMenu(int pContainerId, Inventory inv, FriendlyByteBuf buf)
    {
        this(pContainerId,inv,inv.player.level().getBlockEntity(buf.readBlockPos()),
                new SimpleContainerData(MachineFoundryEntity.dataSlots));

    }
    public MachineFoundryMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data)
    {
        super(ModMenuTypes.MACHINE_FOUNDRY_MENU.get(), pContainerId);
        checkContainerSize(inv, MachineFoundryEntity.slots);
        blockEntity = (MachineFoundryEntity) entity;
        this.level = inv.player.level();
        this.data = data;
        addDataSlots(data);
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(
                handler -> {
                    this.addSlot(new SlotItemHandler(handler, input,28,17));
                    this.addSlot(new SlotItemHandler(handler, catalyst,56,17));
                    this.addSlot(new SlotItemHandler(handler, fuel,42,56));
                    this.addSlot(new SlotItemHandler(handler, output,116, 35));
                }
        );
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player pPlayer)
    {
        return stillValid(ContainerLevelAccess.create(level,blockEntity.getBlockPos()),
                pPlayer, ModBlocks.MACHINE_FOUNDRY.get());
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
    public boolean isCrafting()
    {
        return data.get(0) > 0;
    }
    public int getProgress()
    {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int width = 24;
        return progress * width / maxProgress;
    }

    @Override
    public void fillCraftSlotsStackedContents(StackedContents p_40117_) {

    }

    @Override
    public void clearCraftingContent() {

    }

    @Override
    public boolean recipeMatches(RecipeHolder<FoundryRecipe> p_297792_) {
        return false;
    }

    @Override
    public int getResultSlotIndex() {
        return 2;
    }

    @Override
    public int getGridWidth() {
        return 2;
    }

    @Override
    public int getGridHeight() {
        return 2;
    }

    @Override
    public int getSize() {
        return 4;
    }

    @Override
    public RecipeBookType getRecipeBookType() {
        return ModRecipeBookType.FOUNDRY;
    }

    @Override
    public boolean shouldMoveToInventory(int p_150635_) {
        return false;
    }
}
