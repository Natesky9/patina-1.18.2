package com.natesky9.patina.block.GrindstoneBarrel;

import com.natesky9.patina.block.BlastCauldron.MachineBlastCauldronEntity;
import com.natesky9.patina.block.Template.MachineEntityContainerData;
import com.natesky9.patina.block.Template.MachineTemplateEntity;
import com.natesky9.patina.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.stream.Collectors;

public class MachineGrindstoneBarrelEntity extends MachineTemplateEntity implements MenuProvider {
    public static final int slots = 2;
    public final ContainerData data;
    private int enchantFluid = 0;
    private int enchantFluidMax = 100;
    private int progress = 0;
    private int progressMax = 20;

    public MachineGrindstoneBarrelEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(pWorldPosition, pBlockState,slots);
        //this.data = new MachineEntityContainerData(progress,progressMax,enchantFluid,enchantFluidMax);
        this.data = createData();
        //System.out.println("data is: " + this.data);
    }
    //
    @Override
    protected boolean mySlotValid(int slot, @NotNull ItemStack stack)
    {
        return switch (slot)
            {
            case 0 -> true;
            case 1 -> true;
            default -> false;
            };
    }

    @Override
    protected ContainerData createData() {
        return new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> MachineGrindstoneBarrelEntity.this.progress;
                    case 1 -> MachineGrindstoneBarrelEntity.this.progressMax;
                    case 2 -> MachineGrindstoneBarrelEntity.this.enchantFluid;
                    case 3 -> MachineGrindstoneBarrelEntity.this.enchantFluidMax;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value)
            {
                switch (index) {
                    case 0 -> MachineGrindstoneBarrelEntity.this.progress = value;
                    case 1 -> MachineGrindstoneBarrelEntity.this.progressMax = value;
                    case 2 -> MachineGrindstoneBarrelEntity.this.enchantFluid = value;
                    case 3 -> MachineGrindstoneBarrelEntity.this.enchantFluidMax = value;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    protected void myContentsChanged() {

    }

    @Override
    public Component getDisplayName() {return new TranslatableComponent("block.patina.machine_grindstone_barrel");}

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new MachineGrindstoneBarrelMenu(pContainerId,pInventory,this,this.data);
    }
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side)
    {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap,side);
    }


    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("progress",progress);
        pTag.putInt("enchantFluid",enchantFluid);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        progress = tag.getInt("progress");
        enchantFluid = tag.getInt("enchantFluid");
    }
    //machine stuff
    @Override
    protected void MachineTick()
    {
        //System.out.println("machine tick");
        if (hasRecipe() && hasNotReachedStackLimit())
        {
            progress++;
            if (progress >= progressMax)
            {
                System.out.println("craft");
                craftItem();
                resetProgress();
            }
        }
        else resetProgress();
        setChanged(this.level,this.worldPosition,this.getBlockState());
    }
    void resetProgress()
    {
        this.progress = 0;
    }

    private boolean hasRecipe()
    {
        ItemStack itemstack = itemStackHandler.getStackInSlot(0);
        boolean hasFirstSlot = itemstack.getCount() > 0;
        if (hasFirstSlot)
        {
            if (itemstack.getItem() == Items.GLASS_BOTTLE && enchantFluid > 10) {
                progressMax = 20;
                return true;
            }
            if (itemstack.getItem() == Items.EXPERIENCE_BOTTLE && enchantFluid < enchantFluidMax) {
                progressMax = 20;
                return true;
            }
            if (itemstack.getItem() == ModItems.CLOTH.get() && enchantFluid > 25){
                progressMax = 160;
                return true;
            }
            if (itemstack.getItem() == Items.GOLDEN_APPLE && enchantFluid >= enchantFluidMax) {
                progressMax = 800;
                return true;
            }
            if (!EnchantmentHelper.getEnchantments(itemstack).isEmpty()) {
            progressMax = 80;
            return true;
            }
        }
        return false;
    }
    private void craftItem()
    {
        ItemStack slot1 = itemStackHandler.getStackInSlot(0);
        //

        if (slot1.getItem() == Items.GLASS_BOTTLE)
        {
            enchantFluid -= 10;
            itemStackHandler.extractItem(0,1,false);
            itemStackHandler.insertItem(1,new ItemStack(Items.EXPERIENCE_BOTTLE),false);
            if (level instanceof ServerLevel serverLevel)
                serverLevel.playSound(null,getBlockPos(), SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS,
                        .5f,(float)Math.random()/2+.5f);
            return;
        }
        if (slot1.getItem() == Items.EXPERIENCE_BOTTLE)
        {
            enchantFluid += 10;
            itemStackHandler.extractItem(0,1,false);
            itemStackHandler.insertItem(1,new ItemStack(Items.GLASS_BOTTLE),false);
            if (level instanceof ServerLevel serverLevel)
                serverLevel.playSound(null,getBlockPos(), SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS,
                        .5f,(float)Math.random()/2+.5f);
            return;
        }
        if (slot1.getItem() == ModItems.CLOTH.get())
        {
            enchantFluid -= 25;
            itemStackHandler.extractItem(0,1,false);
            itemStackHandler.insertItem(1,new ItemStack(ModItems.MAGIC_CLOTH.get()),false);
            if (level instanceof ServerLevel serverLevel)
                serverLevel.playSound(null,getBlockPos(),SoundEvents.WOOL_PLACE,SoundSource.BLOCKS,
                        .5f,(float)Math.random()/2+.5f);
        }
        if (slot1.getItem() == Items.GOLDEN_APPLE)
        {
            enchantFluid -= enchantFluidMax;
            itemStackHandler.extractItem(0,1,false);
            itemStackHandler.insertItem(1,new ItemStack(Items.ENCHANTED_GOLDEN_APPLE),false);
            if (level instanceof ServerLevel serverLevel)
                serverLevel.playSound(null,getBlockPos(), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS,
                        .5f,(float)Math.random()/2+.5f);
            return;
        }
        //play the grind sound for everything else
        if (level instanceof ServerLevel serverLevel)
        {//spawn orbs for now
            serverLevel.playSound(null,getBlockPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS,
                    .5f,(float)Math.random()/2+.5f);
        }

        //get the first enchantment
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(slot1);
        Map.Entry<Enchantment, Integer> first = map.entrySet().iterator().next();
        Enchantment enchantType = first.getKey();
        Integer enchantLevel = first.getValue();
        map.remove(enchantType,enchantLevel);
        //allow the barrel to overflow for now, it's not a big deal
        enchantFluid += enchantLevel;

        //remove the enchantments, and restore them
        slot1.removeTagKey("StoredEnchantments");
        slot1.removeTagKey("Enchantments");
        EnchantmentHelper.setEnchantments(map,slot1);

        //if there are still enchantments on it, skip
        if (!EnchantmentHelper.getEnchantments(slot1).isEmpty()) return;

        itemStackHandler.extractItem(0,1,false);
        if (slot1.getItem() instanceof EnchantedBookItem enchantedBookItem)
        itemStackHandler.insertItem(1, new ItemStack(Items.BOOK), false);
        else
        itemStackHandler.insertItem(1,slot1,false);
    }
    private boolean hasNotReachedStackLimit()
    {
        boolean fluid = enchantFluid < enchantFluidMax;
        ItemStack itemStack = itemStackHandler.getStackInSlot(0);
        Item getitem = itemStack.getItem();
        boolean item = itemStackHandler.getStackInSlot(1).getCount() < itemStack.getMaxStackSize();
        boolean drain = getitem == Items.GLASS_BOTTLE || getitem == Items.GOLDEN_APPLE;
        return (fluid || drain) && item;
    }
}
