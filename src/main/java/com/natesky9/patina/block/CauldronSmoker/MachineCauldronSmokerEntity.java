package com.natesky9.patina.block.CauldronSmoker;

import com.natesky9.patina.block.BlastCauldron.MachineBlastCauldronEntity;
import com.natesky9.patina.block.Template.MachineTemplateEntity;
import com.natesky9.patina.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class MachineCauldronSmokerEntity extends MachineTemplateEntity implements MenuProvider {
    public static int slots = 3;
    public final ContainerData data;
    private int progress = 0;
    private int maxProgress = 80;

    public MachineCauldronSmokerEntity(BlockPos pWorldPosition, BlockState pBlockState)
    {
        super(pWorldPosition, pBlockState, slots);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index)
                        {
                            case 0 -> MachineCauldronSmokerEntity.this.progress;
                            case 1 -> MachineCauldronSmokerEntity.this.maxProgress;
                            default -> 0;
                        };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> MachineCauldronSmokerEntity.this.progress = value;
                    case 1 -> MachineCauldronSmokerEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    protected boolean mySlotValid(int slot, @NotNull ItemStack stack)
    {
        return switch (slot) {
            case 0 -> stack.getItem() == Items.POTION || stack.getItem() == Items.GLASS_BOTTLE;
            case 1 -> stack.getItem() == Items.CAMPFIRE || stack.getItem() == Items.SOUL_CAMPFIRE;
            case 2 -> stack.getItem() == ModItems.MAGIC_SALT.get();
            default -> false;
        };
    }

    @Override
    public Component getDisplayName() {return new TranslatableComponent("block.patina.machine_cauldron_smoker");}


    private boolean hasRecipe()
    {
        boolean hasFirstSlot = itemStackHandler.getStackInSlot(0).getCount() > 0;
        boolean valid = PotionUtils.getPotion(itemStackHandler.getStackInSlot(0)).getEffects().size() > 0;
        boolean hasSecondSlot = itemStackHandler.getStackInSlot(1).getCount() > 0;

        return hasFirstSlot && hasSecondSlot && valid;
    }
    private void craftItem()
    {
        //take a potion out, put a bottle in
        ItemStack slot1 = itemStackHandler.extractItem(0,1,false);
        itemStackHandler.insertItem(0,new ItemStack(Items.GLASS_BOTTLE),false);

        //take a campfire out
        itemStackHandler.extractItem(1,1,false);

        ItemStack salt = new ItemStack(ModItems.MAGIC_SALT.get());
        PotionUtils.setPotion(salt,PotionUtils.getPotion(slot1));
        itemStackHandler.insertItem(2,salt,false);
    }
    private boolean hasNotReachedStackLimit()
    {
        return itemStackHandler.getStackInSlot(2).getCount() == 0;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new MachineCauldronSmokerMenu(pContainerId,pInventory,this,this.data);
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
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemStackHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
    }

    public void drops()
    {
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for (int i=0;i < itemStackHandler.getSlots();i++)
        {
            inventory.setItem(i,itemStackHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level,this.worldPosition,inventory);
    }

    @Override
    protected void MachineTick() {
        //System.out.println("machine tick");
        if (hasRecipe() && hasNotReachedStackLimit()) {
            progress++;
            if (itemStackHandler.getStackInSlot(1).getItem() == Items.SOUL_CAMPFIRE)
                progress++;
            //System.out.println("craft");
            if (progress >= maxProgress) {
                craftItem();
                resetProgress();
            }
        }
        else
        {
            resetProgress();
        }

        setChanged(this.level,this.worldPosition,this.getBlockState());
    }

    private void resetProgress()
    {
        this.progress = 0;
    }
}

