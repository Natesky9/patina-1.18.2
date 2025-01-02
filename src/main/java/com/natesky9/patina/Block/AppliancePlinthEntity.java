package com.natesky9.patina.Block;

import com.natesky9.patina.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AppliancePlinthEntity extends BlockEntity {
    ItemStackHandler handler;

    protected LazyOptional<IItemHandler> automationCapability = LazyOptional.empty();

    public AppliancePlinthEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.APPLIANCE_PLINTH_ENTITY.get(), pPos, pBlockState);
        handler = new ItemStackHandler(1)
        {
            @Override
            protected int getStackLimit(int slot, @NotNull ItemStack stack) {
                return 1;
            }

            @Override
            protected void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                getLevel().sendBlockUpdated(pPos,level.getBlockState(pPos),level.getBlockState(pPos),3);
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER)
        {
            return automationCapability.cast();
        }
        return super.getCapability(cap,side);
    }

    public ItemStack getStack() {
        return handler.getStackInSlot(0);
    }
    public void setStack(ItemStack pStack)
    {
        handler.insertItem(0,pStack,false);
        setChanged();
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag,pRegistries);
        return tag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("inventory",handler.serializeNBT(pRegistries));
        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        handler.deserializeNBT(pRegistries,pTag.getCompound("inventory"));
    }

    @Override
    public void onLoad() {
        super.onLoad();
        automationCapability = LazyOptional.of(() -> handler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        automationCapability.invalidate();
    }
}
