package com.natesky9.patina.block;

import com.natesky9.patina.Incursion;
import com.natesky9.patina.IncursionManager;
import com.natesky9.patina.init.ModBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FeralLanternBlock extends Block {
    public static final DirectionProperty facing = BlockStateProperties.FACING;
    public static final IntegerProperty capacity = ModBlockStateProperties.LEVEL;
    protected static final VoxelShape AABB = Shapes.or(Block.box(4.5,0,4.5,11.5,9,11.5),
            Block.box(5.5,9,5.5,10.5,11,10.5));
    private final TextComponent errorNightSky = new TextComponent("Must be placed under the night sky");
    private final TextComponent errorNotFull = new TextComponent("The lantern is not yet full");

    public FeralLanternBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        CompoundTag tag = pContext.getItemInHand().getOrCreateTag();
        int count = tag.contains("count") ? tag.getInt("count"):0;
        count = Mth.clamp(count,0,16);
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();
        Player player = pContext.getPlayer();

        boolean full = count == 16;
        boolean night = Incursion.night(level.getDayTime());
        boolean sky = level.canSeeSky(pos);

        if (player != null)
        {
            if (!full)
            player.displayClientMessage(errorNotFull,true);
            if (!night || !sky)
            player.displayClientMessage(errorNightSky,true);
        }
        if (full && night && sky)
        {
            count = 0;
            //do incursion spawn stuff here
            //empty the souls and start incursion
            if (level instanceof ServerLevel)
            {
                IncursionManager manager = IncursionManager.get(level);
                manager.createIncursion(level, pos);
            }

            Random random = new Random();
            for (int i = 0; i < 16; i++)
                particle(level, pos, random);
            level.playSound(null, pos, SoundEvents.PHANTOM_AMBIENT, SoundSource.HOSTILE, 1, .1F);

        }
        BlockState blockstate = this.defaultBlockState()
                .setValue(facing,pContext.getHorizontalDirection())
                .setValue(capacity,count);

        return blockstate;
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        IncursionManager manager = IncursionManager.get(pLevel);
        Incursion incursion = manager.getIncursion(pLevel,pPos);
        if (incursion != null)
            incursion.fail();
    }

    @Override
    public List<ItemStack> getDrops(BlockState pState, LootContext.Builder pBuilder) {
        ItemStack drop = new ItemStack(this.asItem());
        drop.getOrCreateTag().putInt("count",pState.getValue(capacity));
        return List.of(drop);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(facing);
        pBuilder.add(capacity);
    }



    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, Random pRandom) {
        float chance = pState.getValue(capacity)/16F;
        if (pRandom.nextFloat() < chance)
            particle(pLevel,pPos,pRandom);
    }
    private void particle(Level level, BlockPos pos, Random random)
    {
        level.addParticle(ParticleTypes.SOUL,pos.getX()+.5,pos.getY()+.5,pos.getZ()+.5,
                random.nextDouble()/10-.05,random.nextFloat()/10,random.nextDouble()/10-.05);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return AABB;
    }
}
