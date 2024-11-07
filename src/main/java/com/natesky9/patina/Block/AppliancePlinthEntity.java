package com.natesky9.patina.Block;

import com.natesky9.patina.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AppliancePlinthEntity extends BlockEntity {
    ItemStackHandler handler;

    public AppliancePlinthEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.APPLIANCE_PLINTH_ENTITY.get(), pPos, pBlockState);
        handler = new ItemStackHandler(1)
        {
            @Override
            protected int getStackLimit(int slot, @NotNull ItemStack stack) {
                return 1;
            }
        };
    }
    //public void effects()
    //{
    //    double x = getBlockPos().getX() + .5;
    //    double y = getBlockPos().getY() + 2;
    //    double z = getBlockPos().getZ() + .5;
    //    double xs = x+Math.random()-.5;
    //    double ys = y+Math.random()-.5;
    //    double zs = z+Math.random()-.5;
//
    //    if (level instanceof ServerLevel server)
    //        server.sendParticles(ParticleTypes.ENCHANT, x, y, z, 50, 0,0,2,.5);
    //    //level.addParticle(ParticleTypes.LARGE_SMOKE, x, y, z, x,y,z);
    //    //level.addParticle(ParticleTypes.ENCHANT,x,y,z,xs,ys,zs);
    //}

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
}
