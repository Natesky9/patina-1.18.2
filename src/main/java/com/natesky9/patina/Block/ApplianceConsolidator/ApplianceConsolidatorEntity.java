package com.natesky9.patina.Block.ApplianceConsolidator;

import com.natesky9.patina.Item.EssenceItem;
import com.natesky9.patina.init.ModBlockEntities;
import com.natesky9.patina.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class ApplianceConsolidatorEntity extends BlockEntity {
    int value;
    public ApplianceConsolidatorEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(ModBlockEntities.APPLIANCE_CONSOLIDATOR_ENTITY.get(), p_155229_, p_155230_);
        value = 0;
    }
    public void addValue(Player player)
    {
        player.experienceLevel--;
        player.totalExperience = player.totalExperience - player.getXpNeededForNextLevel();

        this.value += EssenceItem.valuePerLevel(player.experienceLevel);
        System.out.println("value is : " + player.experienceLevel);
    }
    public void resetValue()
    {
        this.value = 0;
    }
    public void fire(ServerLevel level, BlockPos pos)
    {
        if (value < 42)
        {
            level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, .5F, .1F);
            level.sendParticles(ParticleTypes.SMOKE, (double)pos.getX()-1,(double)pos.getY()+1,(double)pos.getZ()-1,
                    8,0D,0D,.1D,0D);
            ExperienceOrb.award(level, new Vec3(pos.getX(),pos.getY()+1,pos.getZ()), value);
            resetValue();
            return;
        }
        ItemStack stack = new ItemStack(ModItems.ESSENCE.get());
        EssenceItem.setCrude(stack,false);
        EssenceItem.setValue(stack, value);
        ItemEntity entity = new ItemEntity(level, pos.getX(), pos.getY()+1, pos.getZ(), stack);
        level.addFreshEntity(entity);
        resetValue();

        level.playSound(null, pos, SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS, .5F, .5F);
        level.sendParticles(ParticleTypes.HAPPY_VILLAGER, pos.getX(),pos.getY()+1,pos.getZ(),
        8,1D,1D,1D,1D);

    }
}
