package com.natesky9.patina.block;

import com.natesky9.patina.Fish;
import com.natesky9.patina.init.ModBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class WaterValveBlock extends Block {
    private static final Property FISH_PROPERTY = ModBlockStateProperties.FISH;
    private static final Property FACING = BlockStateProperties.FACING;

    public WaterValveBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Direction face = pContext.getHorizontalDirection();
        return super.getStateForPlacement(pContext).setValue(FACING,face);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING,FISH_PROPERTY);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack stack = pPlayer.getItemInHand(pHand);
        Item item = stack.getItem();
        boolean empty = pState.getValue(FISH_PROPERTY) == Fish.NONE;
        if (item.equals(Items.COD_BUCKET) && empty)
        {
            stack.shrink(1);
            pPlayer.spawnAtLocation(Items.BUCKET);
            pLevel.setBlock(pPos,pState.setValue(FISH_PROPERTY,Fish.COD),1);
        }
        else if (item.equals(Items.SALMON_BUCKET) && empty)
        {
            stack.shrink(1);
            pPlayer.spawnAtLocation(Items.BUCKET);
            pLevel.setBlock(pPos,pState.setValue(FISH_PROPERTY, Fish.SALMON),1);
        }
        else if (item.equals(Items.TROPICAL_FISH_BUCKET) && empty)
        {
            stack.shrink(1);
            pPlayer.spawnAtLocation(Items.BUCKET);
            pLevel.setBlock(pPos,pState.setValue(FISH_PROPERTY,Fish.TROPICAL),1);
        }
        else if (item.equals(Items.PUFFERFISH_BUCKET) && empty)
        {
            stack.shrink(1);
            pPlayer.spawnAtLocation(Items.BUCKET);
            pLevel.setBlock(pPos,pState.setValue(FISH_PROPERTY,Fish.PUFFER),1);
        }
        else if (item.equals(Items.BUCKET) && !empty)
        {
            stack.shrink(1);
            pPlayer.spawnAtLocation(Items.COD_BUCKET);
            pLevel.setBlock(pPos,pState.setValue(FISH_PROPERTY,Fish.NONE),1);
        }

        return InteractionResult.CONSUME;
    }
}
