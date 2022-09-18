package com.natesky9.patina;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.bossevents.CustomBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.Map;
import java.util.Optional;

import static com.natesky9.patina.init.ModEnchantments.*;
import static net.minecraft.world.item.enchantment.Enchantments.*;

public class Incursion {
    Level incursionLevel;
    BlockPos incursionPos;
    AABB area;
    CustomBossEvent event;
    int lifetime;
    boolean valid;
    public Map<Enchantment,Enchantment> enchantmentOpposites = Map.of
            (
                    VANISHING_CURSE, SOULBOUND.get(),
                    //losing items to keeping them on death

                    ENVYCURSE.get(), ENVYBLESSING.get(),
                    GLUTTONYCURSE.get(), GLUTTONYBLESSING.get(),
                    //consuming more food to increased capacity
                    GREEDCURSE.get(), GREEDBLESSING.get(),
                    //reduced drops to rare drop table
                    LUSTCURSE.get(), LUSTBLESSING.get(),
                    //weakened hits to lifesteal
                    PRIDECURSE.get(), PRIDEBLESSING.get(),
                    //increased durability to extra boss damage
                    SLOTHCURSE.get(), SLOTHBLESSING.get(),
                    //_____ to shrugging off attacks?
                    WRATHCURSE.get(), WRATHBLESSING.get()
                    //take increased damage to dealing extra retaliation
            );

    //----------//
    public Incursion(Level level, BlockPos pos)
    {
        this(level, pos, 0);
    }
    public Incursion(Level level, BlockPos pos, int existingLifetime)
    {
        incursionLevel = level;
        incursionPos = pos;
        area = new AABB(incursionPos).inflate(16);
        lifetime = existingLifetime;
        valid = true;
        event = new CustomBossEvent(new ResourceLocation(Patina.MOD_ID),new TextComponent("incursion"));
        event.setMax(10);
    }
    //----------//
    public BlockPos position()
    {
        return incursionPos;
    }
    public void addValue(int value)
    {//add to the progress, return true if complete
        event.setValue(event.getValue()+value);
    }


    public void addPlayer(Player player)
    {
        if (player instanceof ServerPlayer serverPlayer)
        event.addPlayer(serverPlayer);
    }
    public void spawnMobs()
    {
        boolean spawnTick = (lifetime % 20) == 0;
        if (!spawnTick) return;
        int mobs = incursionLevel.getEntitiesOfClass(Monster.class,area).size();
        int x = (int)(Math.random()*32) - 16 + position().getX();
        int y = position().getY() + 3;
        int z = (int)(Math.random()*32) - 16 + position().getZ();
        if (mobs < 8)
        {
            Entity zombie = new Zombie(incursionLevel);
            zombie.setPos(x,y,z);
            zombie.setItemSlot(EquipmentSlot.CHEST,new ItemStack(Items.LEATHER_CHESTPLATE));
            incursionLevel.addFreshEntity(zombie);
        }
    }
    public void drawBoundary()
    {
        //TODO map blockpos values to draw particles along the floor?
        int incursionX = incursionPos.getX();
        int incursionY = incursionPos.getY();
        int incursionZ = incursionPos.getZ();
        for (int x = -16;x <= 16;x++)
        {
            for (int z = -16;z <= 16;z++)
            {
                if ((x == -16 || x == 16 || z == -16 || z == 16) && Math.random() > .75)
                    ((ServerLevel)(incursionLevel)).sendParticles(ParticleTypes.ENCHANT,
                            incursionX+x,incursionY,incursionZ+z,1,.5,1.5,.5,-.2);
            }
        }
    }
    public boolean inside(BlockPos pos)
    {
        return area.contains(pos.getX(),pos.getY(),pos.getZ());
    }
    public void success()
    {
        event.getPlayers().stream().forEach(player ->
        {
            incursionLevel.playSound(null,player.blockPosition(),SoundEvents.ENDER_DRAGON_GROWL,SoundSource.PLAYERS,1,1);
        });
        rewardPlayers();
        event.removeAllPlayers();
        valid = false;
    }
    public void fail()
    {
        event.getPlayers().stream().forEach(player ->
        {
            incursionLevel.playSound(null,player.blockPosition(), SoundEvents.WITCH_CELEBRATE, SoundSource.PLAYERS,1,1);
        });
        event.removeAllPlayers();
        valid = false;
    }

    public void rewardPlayers()
    {
        event.getPlayers().stream().forEach(player ->
        {
            //loop through all players in the incursion
            boolean flag = true;
            player.giveExperiencePoints(100);
            player.drop(new ItemStack(Items.DIAMOND),false);
            for (EquipmentSlot slot:EquipmentSlot.values())
            {
                //take only the first curse off
                if (!flag) continue;
                //loop through all slots
                ItemStack stack = player.getItemBySlot(slot);
                Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
                Optional<Enchantment> curse = enchantments.keySet().stream().filter(Enchantment::isCurse).findFirst();
                if (curse.isPresent())
                {
                    //remove the curse?
                    enchantments.remove(curse.get());
                    Component name = curse.get().getFullname(1);
                    MutableComponent text = new TextComponent("the curse of ").append(name).append(" has been purged");
                    Enchantment blessing = getOpposite(curse.get());
                    player.displayClientMessage(text,true);
                    EnchantmentHelper.setEnchantments(enchantments,stack);
                    stack.enchant(blessing,0);
                    flag = false;
                }
            }
        });
    }
    public Enchantment getOpposite(Enchantment enchantment)
    {
        return enchantmentOpposites.get(enchantment);
    }
    public boolean doTimeCheck()
    {
        int time = (int)incursionLevel.getDayTime() % 24000;
         return night(time);
    }
    public static boolean night(int time)
    {
        return (time <= 23000 && time >= 13000);
    }
}
