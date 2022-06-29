package com.natesky9.patina.block.BeaconGrindstone;

import com.natesky9.patina.block.Template.MachineTemplateEntity;
import com.natesky9.patina.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

public class MachineBeaconGrindstoneEntity extends MachineTemplateEntity implements MenuProvider {
    public static final int slots = 4;
    public final ContainerData data;
    public static int dataSlots = 4;
    private int progress = 0;
    private int progressMax = 80;
    private int fuel = 0;
    private int fuelMax = 300;

    public MachineBeaconGrindstoneEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(pWorldPosition, pBlockState,slots);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index)
                        {
                            case 0 -> progress;
                            case 1 -> progressMax;
                            case 2 -> fuel;
                            case 3 -> fuelMax;
                            default -> 0;
                        };
            }

            @Override
            public void set(int index, int value) {
                switch (index)
                {
                    case 0 -> progress = value;
                    case 1 -> progressMax = value;
                    case 2 -> fuel = value;
                    case 3 -> fuelMax = value;
                }
            }

            @Override
            public int getCount() {
                return dataSlots;
            }
        };
    }

    @Override
    protected boolean mySlotValid(int slot, @NotNull ItemStack stack)
    {
        return switch (slot)
            {
            case 0 -> stack.getItem() == ModItems.MAGIC_SALT.get();
            case 1 -> stack.getItem() == ModItems.MAGIC_SALT.get() && itemStackHandler.getStackInSlot(1).getCount() == 0;
            case 2 -> stack.getItem() == ModItems.VOID_SALT.get();
            case 3 -> stack.getItem() == Items.GLOWSTONE_DUST;
            default -> false;
            };
    }

    @Override
    public Component getDisplayName() {return new TranslatableComponent("block.patina.machine_beacon_grindstone");}

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new MachineBeaconGrindstoneMenu(pContainerId,pInventory,this,this.data);
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
        pTag.putInt("fuel",fuel);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        progress = tag.getInt("progress");
        fuel = tag.getInt("fuel");
    }

    @Override
    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots()+1);
        for (int i=0;i < itemStackHandler.getSlots();i++)
        {
            inventory.setItem(i,itemStackHandler.getStackInSlot(i));
        }
        if (fuel > 100)
        inventory.setItem(itemStackHandler.getSlots(),new ItemStack(Items.GLOWSTONE_DUST,(fuel/100)));
        Containers.dropContents(this.level,this.worldPosition,inventory);
    }

    @Override
    protected void MachineTick() {
        //System.out.println("machine tick");
        if (this.level.isClientSide()) return;
        //move salt to the focus
        if (itemStackHandler.getStackInSlot(1).isEmpty() && itemStackHandler.getStackInSlot(0).is(ModItems.MAGIC_SALT.get()))
        {
            ItemStack salt = itemStackHandler.extractItem(0,1,false);
            itemStackHandler.insertItem(1,salt,false);
        }
        //recharge fuel
        if (itemStackHandler.getStackInSlot(3).is(Items.GLOWSTONE_DUST) && fuel <= 200)
        {
            itemStackHandler.extractItem(3,1,false);
            fuel += 100;
        }

        if (hasRecipe() && hasNotReachedStackLimit() && fuel > 0)
        {
            progress++;
            fuel--;
            //System.out.println("craft");
            if (progress >= progressMax)
            {
            craftItem();
            resetProgress();
            }
        }
        else
        {
            resetProgress();
        }
        setChanged();
    }
    private void resetProgress()
    {
        this.progress = 0;
    }

    private boolean hasRecipe()
    {
        boolean hasFirstSlot = itemStackHandler.getStackInSlot(1).getCount() > 0;
        boolean isSalt = itemStackHandler.getStackInSlot(1).getItem() == ModItems.MAGIC_SALT.get();

        return hasFirstSlot && isSalt;
    }
    private void craftItem()
    {
        //craft the item
        ItemStack getSalt = itemStackHandler.extractItem(1,1,false);

        itemStackHandler.insertItem(2, new ItemStack(ModItems.VOID_SALT.get()),false);
        //do the effect
        Potion getPotion = PotionUtils.getPotion(getSalt);
        if (getPotion.getEffects().size() == 0)
        {
            System.out.println("Error, blank salt!");
        }
        else
        {
            AABB area = new AABB(this.getBlockPos()).inflate(8);
            List<LivingEntity> entitiesInRange = this.getLevel().getEntitiesOfClass(LivingEntity.class, area);
            int count = entitiesInRange.size();
            System.out.println("Added effect to " + count);
            if (getLevel().isClientSide) return;
            for (LivingEntity entity: entitiesInRange)
            {
                for(MobEffectInstance mobeffectinstance : PotionUtils.getMobEffects(getSalt)) {
                    if (mobeffectinstance.getEffect().isInstantenous()) {
                        mobeffectinstance.getEffect().applyInstantenousEffect(entity, entity, entity, mobeffectinstance.getAmplifier(), 1.0D);
                    } else {
                        entity.addEffect(new MobEffectInstance(mobeffectinstance));
                    }
                }
            }

        }
    }
    private boolean hasNotReachedStackLimit()
    {
        return itemStackHandler.getStackInSlot(2).getCount() < itemStackHandler.getStackInSlot(1).getMaxStackSize();
    }
}
