package com.natesky9.patina.Block.MachineAlembic;

import com.natesky9.patina.Block.Template.MachineTemplateEntity;
import com.natesky9.patina.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Stream;

public class MachineAlembicEntity extends MachineTemplateEntity {
    final int inputPotion = 0;
    final int inputIngredient = 1;
    final int inputFuel = 2;
    public int connected;
    public int progress;
    public int progressMax;
    public int fuel;
    public int leftover;
    public Item reagent;
    public static final int dataSlots = 5;
    public static final int slots = 3;

    static List<Direction> horizontal = new ArrayList<>();
    static {
        horizontal.add(Direction.NORTH);
        horizontal.add(Direction.EAST);
        horizontal.add(Direction.SOUTH);
        horizontal.add(Direction.WEST);
    }



    //private LazyOptional<IItemHandler> outputHandler = LazyOptional.empty();
    public MachineAlembicEntity(BlockPos pos, BlockState state) {
        super(pos, state,3);
        this.connected = 0;
        this.progress = 0;
        this.progressMax = 20;
        this.fuel = 0;
        this.leftover = 0;
        this.reagent = Items.AIR;
    }

    @Override
    protected boolean mySlotValid(int slot, @NotNull ItemStack stack) {
        return switch (slot)
        {
            case inputPotion -> BrewingRecipeRegistry.hasOutput(stack,new ItemStack(reagent))
                    || reagent == Items.AIR && BrewingRecipeRegistry.isValidInput(stack);
            case inputIngredient -> BrewingRecipeRegistry.isValidIngredient(stack);
            case inputFuel -> stack.is(Items.BLAZE_POWDER);
            default -> false;
        };

    }

    @Override
    protected ContainerData createData() {
        return new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index)
                {
                    case 0 -> connected;
                    case 1 -> progress;
                    case 2 -> progressMax;
                    case 3 -> fuel;
                    case 4 -> leftover;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index)
                {
                    case 0 -> connected = value;
                    case 1 -> progress = value;
                    case 2 -> progressMax = value;
                    case 3 -> fuel = value;
                    case 4 -> leftover = value;
                }
            }

            @Override
            public int getCount() {
                return dataSlots;
            }
        };
    }



    @Override
    protected int mySlotLimit(int slot) {
        return switch (slot)
        {
            case inputPotion, inputIngredient -> 1;
            case inputFuel -> 64;
            default -> 0;
        };
    }

    @Override
    protected void myContentsChanged() {

    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.patina.machine_alembic");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new MachineAlembicMenu(pContainerId,pInventory,this,this.data);
    }

    @Override
    protected void MachineTick() {
        if (!(level instanceof ServerLevel)) return;
        ItemStack ingredient = itemStackHandler.getStackInSlot(inputIngredient);
        if (leftover < 1 && BrewingRecipeRegistry.isValidIngredient(ingredient))
        {//refil the reagent
            reagent = ingredient.getItem();
            if (ingredient.hasCraftingRemainingItem())
            {
                itemStackHandler.setStackInSlot(inputIngredient, ingredient.getCraftingRemainingItem());
                level.playSound(null, worldPosition, SoundEvents.POINTED_DRIPSTONE_DRIP_WATER, SoundSource.BLOCKS, .5F, .5F);
                //setChanged();
            }
            else ingredient.shrink(1);
            leftover += 3 + connected;
        }
        ItemStack potion = itemStackHandler.getStackInSlot(inputPotion);
        ItemStack addition = new ItemStack(reagent);
        if (BrewingRecipeRegistry.hasOutput(potion, addition) && leftover > 0)
        {//craft the potion
            progress++;
            if (progress >= progressMax)
            {
                //do the actual crafting
                itemStackHandler.setStackInSlot(inputPotion, BrewingRecipeRegistry.getOutput(potion, addition));
                leftover--;
                //trigger the addons randomly
                int delay = 0;
                Collections.shuffle(horizontal);
                for (Direction direction: horizontal)
                {
                    BlockPos pos = worldPosition.relative(direction);
                    if (level.getBlockState(pos).is(ModBlocks.ADDON_ALEMBIC.get()))
                    {
                        delay += 8;
                        level.scheduleTick(pos, ModBlocks.ADDON_ALEMBIC.get(), delay);
                    }
                }
                level.playSound(null, worldPosition, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, .5F, .5F);
                progress = 0;
            }
        }
        else
            progress = 0;


    }
    public void setNeighbors(Level level, BlockPos pos)
    {
        connected = 0;
        for (Direction direction: Direction.values())
        {
            if (!direction.getAxis().isHorizontal()) continue;
            if (level.getBlockState(pos.relative(direction)).is(ModBlocks.ADDON_ALEMBIC.get()))
                connected++;
        }
        connected = Math.min(connected,3);
    }
    public void setNeighbors(LevelAccessor level, BlockPos pos)
    {
        connected = 0;
        for (Direction direction: Direction.values())
        {
            if (!direction.getAxis().isHorizontal()) continue;
            if (level.getBlockState(pos.relative(direction)).is(ModBlocks.ADDON_ALEMBIC.get()))
                connected++;
        }
        connected = Math.min(connected,3);
    }

    @Override
    public void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("progress", progress);
        pTag.putInt("connected", connected);
        pTag.putInt("fuel", fuel);
        pTag.putInt("leftover", leftover);
        pTag.putInt("reagent", BuiltInRegistries.ITEM.getId(reagent));
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        progress = tag.getInt("progress");
        connected = tag.getInt("connected");
        fuel = tag.getInt("fuel");
        leftover = tag.getInt("leftover");
        reagent = BuiltInRegistries.ITEM.byId(tag.getInt("reagent"));
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction side) {
        return super.getCapability(cap, side);
    }
}
