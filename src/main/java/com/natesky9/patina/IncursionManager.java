package com.natesky9.patina;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.server.ServerLifecycleHooks;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IncursionManager extends SavedData {
    public final List<Incursion> incursions = new ArrayList<>();


    public IncursionManager()
    {

    }
    //----------//
    public static void tick(ServerLevel level)
    {
        IncursionManager manager = IncursionManager.get(level);

        //tick through players
        if (level.getDayTime() % 100 == 0)
        {
            if (Incursion.night((int) level.getDayTime() % 24000) && level.getMoonPhase() == 0) {
                level.players().stream().forEach(player ->
                {
                    if (!manager.isWithinIncursion(player.blockPosition()))
                    {

                        manager.createIncursion(level,player.blockPosition());
                    }
                });
            }
        }

        //tick through incursions
        Iterator<Incursion> iterator = manager.incursions.iterator();
        while (iterator.hasNext())
        {
            Incursion incursion = iterator.next();
            if (incursion.incursionLevel.equals(level))
            {
                //do incursion tick in here
                System.out.println("tick incursion");
                incursion.lifetime++;
                incursion.drawBoundary();
                manager.checkPlayers(incursion);
                incursion.spawnMobs();
                manager.doTimeCheck(incursion);
                if (!manager.doValidCheck(incursion)) iterator.remove();
            }
        }
    }
    public Incursion createIncursion(Level level, BlockPos pos)
    {
        Incursion incursion = new Incursion(level,pos);
        incursions.add(incursion);
        return incursion;
    }


    public void checkPlayers(Incursion incursion)
    {
        for (ServerPlayer player : incursion.event.getPlayers()) {
            if (!incursion.inside(player.blockPosition())) {
                System.out.println("removing player from incursion");
                incursion.event.removePlayer(player);
                break;
            }
        }
        incursion.incursionLevel.getEntitiesOfClass(ServerPlayer.class,new AABB(incursion.incursionPos).inflate(15)).stream().forEach(player ->
        {
            if (!incursion.event.getPlayers().contains(player))
            {
                System.out.println("adding player to incursion");
                incursion.addPlayer(player);
            }
        });
    }
    public boolean isWithinIncursion(BlockPos pos)
    {
        for (Incursion incursion:incursions)
        {
            if (incursion.inside(pos)) return true;
        }
        return false;
    }
    public boolean isNearbyIncursion(BlockPos pos, int distance)
    {
        //checks whether there's an incursion nearby
        for (Incursion incursion:incursions)
        {
            BlockPos incursionpos = incursion.incursionPos;
            double dist = incursionpos.distSqr(pos);
            if (dist < dist*dist) return true;
        }
        return false;
    }
    public Incursion getIncursion(Level level, BlockPos pos)
    {
        for (Incursion incursion:incursions)
        {
            if (incursion.inside(pos)) return incursion;
        }
        return null;
    }
    public Incursion findNearbyIncursion(Level level, BlockPos pos, int distance)
    {
        double max = distance*distance;
        Incursion nearest = null;
        for (Incursion incursion:incursions)
        {
            BlockPos incursionPos = incursion.incursionPos;
            double dist = incursionPos.distSqr(pos);
            if (dist < max)
            {
                max = dist;
                nearest = incursion;
            }
        }
        return nearest;
    }
    public void doTimeCheck(Incursion incursion)
    {
        if (!incursion.doTimeCheck())
        {//if the time is out
            System.out.println("excursion expired!");
            incursion.valid = false;
        }
    }
    public boolean doValidCheck(Incursion incursion)
    {
        if (!incursion.valid)
        {
            incursion.event.removeAllPlayers();
            incursion.fail();
            return false;
        }
        return true;
    }
    public void entityDie(LivingEntity entity)
    {
        //triggers when something dies within an incursion
        if (entity instanceof Player) return;
        BlockPos pos = entity.blockPosition();
        if (!isWithinIncursion(pos)) return;
        ServerLevel level = (ServerLevel)entity.level;
        Incursion incursion = getIncursion(level,entity.blockPosition());
        //particle
        level.sendParticles(ParticleTypes.SOUL,entity.getX(),entity.getY(),entity.getZ(),
                10,0,2,0,.2);
        incursion.addValue(1);
        System.out.println("entity died within incursion!");

        if (incursion.event.getProgress() == 1.0F)
        {
            incursion.success();
            incursion.event.removeAllPlayers();
        }
    }
        
    @Nonnull
    public static IncursionManager get(Level level)
    {
        if (level instanceof ServerLevel serverLevel)
        {
            return serverLevel.getDataStorage().computeIfAbsent(
                    IncursionManager::new,IncursionManager::new,"incursionmanager");
        }
        throw new RuntimeException("Don't run this clientside");
    }
    //----------//
    @Override
    public CompoundTag save(CompoundTag tag)
    {
        //list of compounds
        ListTag incursionList = new ListTag();
        incursions.forEach(incursion ->
        {
            //compound for each incursion
            BlockPos pos = incursion.incursionPos;
            CompoundTag incursionTag = new CompoundTag();
            String dimension = incursion.incursionLevel.dimension().toString();
            incursionTag.putString("level",dimension);
            incursionTag.putInt("x",pos.getX());
            incursionTag.putInt("y",pos.getY());
            incursionTag.putInt("z",pos.getZ());
            incursionTag.putInt("lifetime",incursion.lifetime);
            incursionList.add(incursionTag);
        });
        tag.put("incursions", incursionList);
        setDirty();
        return tag;
    }
    public IncursionManager(CompoundTag tag)
    {
        ListTag incursionList = tag.getList("incursions", Tag.TAG_COMPOUND);
        for (Tag t : incursionList)
        {
            CompoundTag incursionTag = (CompoundTag) t;
            String dimension = incursionTag.getString("level");
            ResourceKey<Level> key = ResourceKey.create(Registry.DIMENSION_REGISTRY,new ResourceLocation(dimension));
            Level level = ServerLifecycleHooks.getCurrentServer().getLevel(key);
            int x = incursionTag.getInt("x");
            int y = incursionTag.getInt("y");
            int z = incursionTag.getInt("z");
            int lifetime = incursionTag.getInt("lifetime");
            Incursion incursion = new Incursion(level, new BlockPos(x,y,z));
        }
    }
}
