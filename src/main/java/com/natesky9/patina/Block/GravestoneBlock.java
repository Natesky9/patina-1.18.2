package com.natesky9.patina.Block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.List;
import java.util.Optional;

public class GravestoneBlock {
    public static void create(LivingDropsEvent event)
    {
        if (!(event.getEntity() instanceof Player player)) return;
        Level level = event.getEntity().getLevel();
        BlockPos pos = event.getEntity().blockPosition();

        //shrink foods by 1
        for (ItemEntity entity:event.getDrops())
        {
            ItemStack stack = entity.getItem();
            if (stack.getItem().getFoodProperties(stack,null) != null)
                stack.shrink(1);
            //do tool and armor damage
            if (stack.getItem().isDamageable(stack))
            {
                stack.setDamageValue(Math.min(stack.getDamageValue()+stack.getMaxDamage()/5,stack.getMaxDamage()-1));
            }
        }

        Optional<ItemEntity> container = event.getDrops().stream().filter(itemEntity -> itemEntity.getItem().is(Items.BARREL)).findFirst();
        //if no barrel, skip
        if (container.isEmpty()) return;

        //remove 1 barrel
        event.getDrops().remove(container.get());
        container.get().getItem().shrink(1);
        event.getDrops().add(container.get());



        level.setBlock(pos, Blocks.BARREL.defaultBlockState(),3);
        BlockEntity barrel = level.getBlockEntity(pos);
        if (barrel == null) return;

        LazyOptional<IItemHandler> handler = barrel.getCapability(ForgeCapabilities.ITEM_HANDLER);
        handler.ifPresent(iItemHandler ->
        {
            List<ItemEntity> items = event.getDrops().stream().toList();
            for (ItemEntity entity:items)
            {
                ItemStack stack = entity.getItem();
                ItemStack remainder = ItemHandlerHelper.insertItem(iItemHandler,stack,false);
                if (remainder == ItemStack.EMPTY)
                    event.getDrops().remove(entity);
            }
        });
        //everything that doesn't fit should fall to the floor
    }
}
