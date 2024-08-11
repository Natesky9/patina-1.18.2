package com.natesky9.patina.Item.pouches;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class LighterItem extends PouchItem {
    public LighterItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    boolean canAccept(ItemStack self, ItemStack other) {
        return other.is(Items.TORCH);
    }

    @Override
    int maxCount() {
        return 128;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected)
    {
        BlockPos pos = entity.blockPosition();
        int brightness = level.getBrightness(LightLayer.BLOCK, pos);
        BlockState state = level.getBlockState(pos);
        boolean dark = brightness < 5;
        boolean empty = state.isAir() || state.is(BlockTags.REPLACEABLE);
        boolean sturdy = level.getBlockState(pos.below()).isFaceSturdy(level,pos, Direction.UP);
        if (dark && empty && sturdy && getCount(stack) > 0)
        {
            subCount(stack,1);
            level.playSound(null,pos, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundSource.PLAYERS,.5f,.5f);
            level.setBlockAndUpdate(pos, Blocks.TORCH.defaultBlockState());
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction face = context.getClickedFace();
        ItemStack stack = context.getItemInHand();
        boolean sturdy = level.getBlockState(pos).isFaceSturdy(level,pos,face);
        if (sturdy && getCount(stack) > 0)
        {
            subCount(stack,1);
            BlockPlaceContext blockContext = new BlockPlaceContext(context);
            BlockState state = face == Direction.UP ? Blocks.TORCH.getStateForPlacement(blockContext) : Blocks.WALL_TORCH.getStateForPlacement(blockContext);
            level.setBlockAndUpdate(pos.relative(face),state);
            level.playSound(null,pos, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundSource.PLAYERS,.5f,.5f);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.FAIL;
    }
    //int getCharges(ItemStack stack)
    //{
    //    return stack.getOrCreateTag().getInt("charges");
    //}
    //void addCharges(ItemStack stack, int count)
    //{
    //    int old = getCharges(stack);
    //    stack.getOrCreateTag().putInt("charges", old + count);
    //}

    //@Override
    //public boolean overrideOtherStackedOnMe(ItemStack self, ItemStack other, Slot p_150894_, ClickAction click, Player p_150896_, SlotAccess slot) {
    //    if (canAccept(self, other))
    //    {
    //        int toMove = Math.min(maxCharges()-getCharges(self),other.getCount());
    //        other.shrink(toMove);
    //        addCharges(self,toMove);
    //        return true;
    //    }
    //    if (other.isEmpty() && click == ClickAction.SECONDARY)
    //    {
    //        int count = Math.min(64,getCharges(self));
    //        addCharges(self,-count);
    //        ItemStack fresh = new ItemStack(Items.TORCH,count);
    //        slot.set(fresh);
    //        return true;
    //    }
    //    return super.overrideOtherStackedOnMe(self, other, p_150894_, click, p_150896_, slot);
    //}

}
