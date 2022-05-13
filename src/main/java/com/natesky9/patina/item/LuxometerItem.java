package com.natesky9.patina.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;

public class LuxometerItem extends Item {
    public LuxometerItem(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(stack, level, entity, pSlotId, pIsSelected);

        BlockPos block = entity.blockPosition();
        int levelBrightness = level.getBrightness(LightLayer.BLOCK,block);
        BlockState blockstate = level.getBlockState(block);
        boolean dark = levelBrightness < 5;
        boolean empty = blockstate.isAir() || blockstate.is(BlockTags.REPLACEABLE_PLANTS);
        boolean sturdy = level.getBlockState(block).isFaceSturdy(level,block.below(), Direction.UP);
        if (dark && empty && sturdy)
        {
            level.setBlockAndUpdate(block, Blocks.TORCH.defaultBlockState());
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos usedon = pContext.getClickedPos();
        Direction face = pContext.getClickedFace();
        boolean sturdy = level.getBlockState(usedon).isFaceSturdy(level,usedon,face);
        if (sturdy)
        {
            BlockPlaceContext context = new BlockPlaceContext(pContext);
            if (pContext.getClickedFace()==Direction.UP)
                level.setBlockAndUpdate(usedon.relative(face),Blocks.TORCH.getStateForPlacement(context));
            else
            level.setBlockAndUpdate(usedon.relative(face),Blocks.WALL_TORCH.getStateForPlacement(context));
            return InteractionResult.CONSUME;
        }
        return InteractionResult.FAIL;
    }
}
