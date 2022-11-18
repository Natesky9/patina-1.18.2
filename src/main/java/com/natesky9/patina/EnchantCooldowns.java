package com.natesky9.patina;

import com.google.common.collect.Maps;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.Iterator;
import java.util.Map;

public class EnchantCooldowns {
    private final Map<Player,Map<Enchantment,CooldownInstance>> playerCooldowns = Maps.newHashMap();
    private int tickCount;


    public boolean isOnCooldown(Player player, Enchantment enchantment)
    {
        return this.getCooldownPercent(player, enchantment,0.0F) > 0.0F;
    }
    public float getCooldownPercent(Player player, Enchantment enchantment, float partialTicks)
    {
        Map<Enchantment,CooldownInstance> map = this.playerCooldowns.get(player);
        if (map == null) return 0.0F;
        EnchantCooldowns.CooldownInstance instance = this.playerCooldowns.get(player).get(enchantment);
        if (instance != null)
        {
            float f = (float)(instance.endTime - instance.startTime);
            float f1 = (float)instance.endTime - ((float)this.tickCount + partialTicks);
            return Mth.clamp(f1/f,0.0F,1.0F);
        }
        else
        {
            return 0.0F;
        }
    }
    public void tick()
    {
        ++this.tickCount;
        if (!this.playerCooldowns.isEmpty())
        {
            for (Map.Entry<Player, Map<Enchantment, CooldownInstance>> playerMapEntry : playerCooldowns.entrySet()) {
                Player player = playerMapEntry.getKey();
                Iterator<Map.Entry<Enchantment, CooldownInstance>> iterator = playerMapEntry.getValue().entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Enchantment, CooldownInstance> entry = iterator.next();

                    if ((entry.getValue()).endTime <= this.tickCount) {
                        iterator.remove();
                        this.onCooldownEnded(player, entry.getKey());
                    }
                }
            }
        }
    }
    public void addCooldown(Player player, Enchantment enchantment, int ticks)
    {
        if (playerCooldowns.containsKey(player))
        {
            //if it exists in the list
            if (isOnCooldown(player, enchantment)) return;
            playerCooldowns.get(player).put(enchantment, new EnchantCooldowns.CooldownInstance(this.tickCount, this.tickCount + ticks));
        }
        else
            {
            Map<Enchantment, CooldownInstance> fresh = Maps.newHashMap();
            playerCooldowns.put(player, fresh);
            playerCooldowns.get(player).put(enchantment,new EnchantCooldowns.CooldownInstance(this.tickCount,this.tickCount + ticks));
            }
        //this.playerCooldowns.get(player).put(enchantment, new EnchantCooldowns.CooldownInstance(this.tickCount,this.tickCount + ticks));
        this.onCooldownStarted(player, enchantment,ticks);

    }
    protected void onCooldownStarted(Player player, Enchantment enchantment, int ticks)
    {//can consolidate?
        if (enchantment instanceof TriggeredEnchant triggeredEnchant)
        {
            triggeredEnchant.onCooldown(player);
        }
    }
    protected void onCooldownEnded(Player player, Enchantment enchantment)
    {//can consolidate?
        if (enchantment instanceof  TriggeredEnchant triggeredEnchant)
        {
            triggeredEnchant.offCooldown(player);
        }
    }
    static class CooldownInstance
    {
        final int startTime;
        final int endTime;
        CooldownInstance(int startTime, int endTime)
        {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}
