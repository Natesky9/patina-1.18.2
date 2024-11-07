package com.natesky9.patina.Block;

import com.natesky9.patina.init.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

import java.util.List;

public class MachineCombinerBlock extends Block {
    public static final DirectionProperty DIRECTION = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty TRIGGERED = BlockStateProperties.TRIGGERED;

    public MachineCombinerBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    protected void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pNeighborBlock, BlockPos pNeighborPos, boolean pMovedByPiston) {
        boolean powered = pLevel.hasNeighborSignal(pPos);
        boolean triggered = pState.getValue(TRIGGERED);
        Direction direction = pState.getValue(DIRECTION);

        BlockPos input = pPos.relative(direction.getOpposite());
        BlockPos output = pPos.relative(direction);

        boolean construct = pLevel.getBlockState(input).is(ModBlocks.APPLIANCE_PLINTH.get()) && pLevel.getBlockState(output).is(ModBlocks.APPLIANCE_PLINTH.get());
        if (!construct) return;


        if (powered && !triggered)
        {
            pLevel.setBlock(pPos,pState
                    .setValue(TRIGGERED,true)
                    .setValue(DIRECTION,pState.getValue(DIRECTION)),2);
            pLevel.scheduleTick(pPos,this,4);
            pLevel.playSound(null,pPos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS,1,1);
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

        boolean construct = pLevel.getBlockState(inputPos).is(ModBlocks.APPLIANCE_PLINTH.get()) && pLevel.getBlockState(outputPos).is(ModBlocks.APPLIANCE_PLINTH.get());
        if (!construct) return;

        if (!(pLevel.getBlockEntity(inputPos) instanceof AppliancePlinthEntity inputPlinth)) return;
        if (!(pLevel.getBlockEntity(outputPos) instanceof AppliancePlinthEntity outputPlinth)) return;

        ItemStack input = inputPlinth.getStack();
        ItemStack output = outputPlinth.getStack();

        if (!(input.is(Items.ENCHANTED_BOOK))) return;
        //if (!(output.is(Items.ENCHANTED_BOOK))) return;

        ItemEnchantments inputEnchants = EnchantmentHelper.getEnchantmentsForCrafting(input);
        ItemEnchantments outputEnchants = EnchantmentHelper.getEnchantmentsForCrafting(output);

        //do enchant combining here?
        //if (inputEnchants.size() != outputEnchants.size()) return;

        boolean same = inputEnchants.size() == 1 && outputEnchants.size() == 1 &&
                inputEnchants.keySet().containsAll(outputEnchants.keySet());
        EnchantmentHelper.updateEnchantments(output,(mutable ->
        {
            if (same)
                mutable.keySet().forEach(holder ->
                {
                    System.out.println(EnchantmentHelper.getItemEnchantmentLevel(holder,output));
                    System.out.println("max level of: " + holder.value().getMaxLevel());
                    mutable.upgrade(holder,outputEnchants.getLevel(holder)+1);
                    //
                    boolean overloaded = outputEnchants.getLevel(holder)+1 > holder.value().getMaxLevel();
                    if (overloaded)
                    {
                        output.set(DataComponents.LORE,new ItemLore(List.of(Component.literal("The enchantment is overloaded, " +
                                "going over it's natural values. Currently it seems to do nothing, " +
                                "but the laws of magic are not to be toyed with..").withStyle(ChatFormatting.DARK_PURPLE))));
                        pLevel.sendParticles(ParticleTypes.FLAME,outputPos.getX()+.5,outputPos.getY()+1,outputPos.getZ()+.5,
                                30,0,0,0,.25);
                    }
                });
            else
                inputEnchants.keySet().forEach(holder -> mutable.set(holder,
                        Math.max(mutable.getLevel(holder),inputEnchants.getLevel(holder))));
        }));

        pLevel.sendParticles(ParticleTypes.INSTANT_EFFECT,outputPos.getX()+.5,outputPos.getY()+1,outputPos.getZ()+.5,
                15,0,0,0,1);

        inputPlinth.handler.extractItem(0,1,false);
        inputPlinth.handler.insertItem(0,Items.PAPER.getDefaultInstance(),false);
        outputPlinth.handler.setStackInSlot(0,output);
        pLevel.sendBlockUpdated(inputPos,inputPlinth.getBlockState(),inputPlinth.getBlockState(),3);
        pLevel.sendBlockUpdated(outputPos,outputPlinth.getBlockState(),outputPlinth.getBlockState(),3);
        //do combiny stuff here
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        //TODO:change with blockstate test
        Direction direction = pState.getValue(DIRECTION);

        BlockPos inputPos = pPos.relative(direction.getOpposite());
        BlockPos outputPos = pPos.relative(direction);
        int x = inputPos.getX()-outputPos.getX();
        int z = inputPos.getZ()-outputPos.getZ();

        boolean construct = pLevel.getBlockState(inputPos).is(ModBlocks.APPLIANCE_PLINTH.get()) && pLevel.getBlockState(outputPos).is(ModBlocks.APPLIANCE_PLINTH.get());
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
