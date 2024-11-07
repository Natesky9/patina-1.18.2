package com.natesky9.patina.Block;

import com.natesky9.patina.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class MachineExtractorBlock extends Block {
    public static final DirectionProperty DIRECTION = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty TRIGGERED = BlockStateProperties.TRIGGERED;

    public MachineExtractorBlock(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(DIRECTION, Direction.NORTH)
                .setValue(TRIGGERED,false));
    }

    @Override
    protected void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pNeighborBlock, BlockPos pNeighborPos, boolean pMovedByPiston) {
        boolean powered = pLevel.hasNeighborSignal(pPos);
        boolean triggered = pState.getValue(TRIGGERED);
        Direction direction = pState.getValue(DIRECTION);

        BlockPos input = pPos.relative(direction.getOpposite());
        BlockPos output = pPos.relative(direction);

        //if the output block is a cauldron or essence cauldron
        boolean isCauldron = pLevel.getBlockState(output).is(Blocks.CAULDRON)
                || pLevel.getBlockState(output).is(ModBlocks.ESSENCE_CAULDRON.get());
        boolean isPlinth = pLevel.getBlockEntity(output) instanceof AppliancePlinthEntity;

        boolean construct = pLevel.getBlockState(input).is(ModBlocks.APPLIANCE_PLINTH.get()) &&
                (isCauldron || isPlinth);
        if (!construct) return;

        if (powered && !triggered)
        {
            pLevel.setBlock(pPos,pState
                    .setValue(TRIGGERED,true)
                    .setValue(DIRECTION,pState.getValue(DIRECTION)),2);
            pLevel.scheduleTick(pPos,this,4);
        }
        if (!powered && triggered)
        {
            pLevel.setBlock(pPos, pState.setValue(TRIGGERED,false).setValue(DIRECTION,direction),2);
        }
    }

    @Override
    protected void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        Direction direction = pState.getValue(DIRECTION);

        BlockPos inputPos = pPos.relative(direction.getOpposite());
        BlockPos outputPos = pPos.relative(direction);

        //if the output block is a cauldron or essence cauldron
        boolean isCauldron = pLevel.getBlockState(outputPos).is(Blocks.CAULDRON)
                || pLevel.getBlockState(outputPos).is(ModBlocks.ESSENCE_CAULDRON.get());
        boolean isPlinth = pLevel.getBlockEntity(outputPos) instanceof AppliancePlinthEntity;

        boolean construct = pLevel.getBlockState(inputPos).is(ModBlocks.APPLIANCE_PLINTH.get()) &&
                (isCauldron || isPlinth);
        if (!construct) return;

        boolean ready = false;
        if (!(pLevel.getBlockEntity(inputPos) instanceof AppliancePlinthEntity inputPlinth)) return;
        AppliancePlinthEntity outputPlinth = null;
        BlockState outputCauldron = null;
        if (isPlinth)
        {
            outputPlinth = (AppliancePlinthEntity) pLevel.getBlockEntity(outputPos);
            ready = outputPlinth.getStack().is(Items.BOOK);
        }
        if (isCauldron)
        {
            outputCauldron = pLevel.getBlockState(outputPos);
            ready = outputCauldron.is(Blocks.CAULDRON) || outputCauldron.getValue(EssenceCauldronBlock.LEVEL) != 15 ;
        }
        boolean valid = isCauldron || isPlinth;
        if (!valid) return;
        if (!ready) return;

        ItemStack input = inputPlinth.getStack();

        if (!EnchantmentHelper.hasAnyEnchantments(input)) return;
        //only process enchantments
        ItemEnchantments enchants = EnchantmentHelper.getEnchantmentsForCrafting(input);
        Holder<Enchantment> first =  enchants.keySet().stream().findFirst().get();
        int level = EnchantmentHelper.getItemEnchantmentLevel(first,input);
        //remove one level from the first enchant
        EnchantmentHelper.updateEnchantments(input,(mutable ->
        {
            mutable.set(first,level-1);
            //mutable.removeIf((holder) -> holder.is(first));
            //if (level > 0)
            //    mutable.set(first,level-1);
        }));
        if (input.is(Items.ENCHANTED_BOOK) && !EnchantmentHelper.hasAnyEnchantments(input))
        {
            inputPlinth.handler.extractItem(0,1,false);
            inputPlinth.handler.insertItem(0,Items.BOOK.getDefaultInstance(),false);


        }
        pLevel.sendBlockUpdated(inputPos,inputPlinth.getBlockState(),inputPlinth.getBlockState(),3);
        if (pLevel instanceof ServerLevel server)
            server.sendParticles(ParticleTypes.EFFECT,outputPos.getX()+.5,outputPos.getY()+1,outputPos.getZ()+.5,
                    15,0,0,0,1);
        pLevel.playSound(null,pPos, SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS,.5f,.5f);

        if (isCauldron)
        {
            if (outputCauldron.is(Blocks.CAULDRON))
            {
                pLevel.setBlock(outputPos,ModBlocks.ESSENCE_CAULDRON.get().defaultBlockState()
                        .setValue(EssenceCauldronBlock.LEVEL,1),3);
                pLevel.playSound(null,pPos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS,1,.5f);
            }
            else
            {
                int current = outputCauldron.getValue(EssenceCauldronBlock.LEVEL);
                pLevel.setBlock(outputPos,ModBlocks.ESSENCE_CAULDRON.get().defaultBlockState()
                        .setValue(EssenceCauldronBlock.LEVEL,current+1),3);
                pLevel.playSound(null,pPos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS,1,
                        (float)outputCauldron.getValue(EssenceCauldronBlock.LEVEL)/30+.5f);
            }
        }
        else
        {
            outputPlinth.handler.extractItem(0,1,false);
            ItemStack stack = new ItemStack(Items.ENCHANTED_BOOK);
            stack.enchant(first,0);
            outputPlinth.handler.insertItem(0,stack,false);
        }
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        //TODO:change with blockstate test
        Direction direction = pState.getValue(DIRECTION);

        BlockPos inputPos = pPos.relative(direction.getOpposite());
        BlockPos outputPos = pPos.relative(direction);
        int x = inputPos.getX()-outputPos.getX();
        int z = inputPos.getZ()-outputPos.getZ();

        boolean isOutput = pLevel.getBlockState(outputPos).is(ModBlocks.APPLIANCE_PLINTH.get())
                || pLevel.getBlockState(outputPos).is(Blocks.CAULDRON)
                || pLevel.getBlockState(outputPos).is(ModBlocks.ESSENCE_CAULDRON.get());
        boolean construct = pLevel.getBlockState(inputPos).is(ModBlocks.APPLIANCE_PLINTH.get()) && isOutput;
        if (construct)
            pLevel.addParticle(ParticleTypes.ENCHANT, outputPos.getX()+0.5,
                outputPos.getY()+2,
                outputPos.getZ()+0.5,
                    ((float)x) , (1 - pRandom.nextFloat() - 1.0F), ((float)z) );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(DIRECTION).add(TRIGGERED);
    }

    @Override
    protected RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }
}
