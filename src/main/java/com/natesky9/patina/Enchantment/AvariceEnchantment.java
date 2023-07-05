package com.natesky9.patina.Enchantment;

import com.natesky9.patina.init.ModEnchantments;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import java.util.ArrayList;
import java.util.Collection;

public class AvariceEnchantment extends Enchantment {
    public AvariceEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }
    @Override
    public Component getFullname(int pLevel) {
        return Component.translatable(this.getDescriptionId()).withStyle(ChatFormatting.DARK_PURPLE);
    }
    public static void doEffect(LivingDropsEvent event)
    {
        //10% chance to trigger
        if (Math.random() > 0.1) return;
        Entity enemy = event.getEntity();
        if (!(enemy instanceof LivingEntity mob)) return;
        Entity entity = event.getSource().getEntity();
        //check if player
        if (!(entity instanceof Player player)) return;
        //check if player is greed blessed
        if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.BLESSINGGREED.get(), player) == 0) return;


        player.displayClientMessage(Component.translatable("patina.avarice_trigger"),true);
        entity.level.playSound(null,entity.blockPosition(),SoundEvents.AMETHYST_BLOCK_CHIME,
                SoundSource.PLAYERS,0.5F,0.5F);

        //double the drops?
        Collection<ItemEntity> drops = event.getDrops();
        if (drops.isEmpty())
        {
            //I don't know if I like this
            //maybe I should just access transform the method in LivingEntity, and use that
            ResourceLocation resourcelocation = mob.getLootTable();
            LootTable loottable = mob.level.getServer().getLootTables().get(resourcelocation);
            LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerLevel)mob.level))
                    .withRandom(RandomSource.create()).withParameter(LootContextParams.THIS_ENTITY, mob)
                    .withParameter(LootContextParams.ORIGIN, mob.position())
                    .withParameter(LootContextParams.DAMAGE_SOURCE, event.getSource())
                    .withOptionalParameter(LootContextParams.KILLER_ENTITY, event.getSource().getEntity())
                    .withOptionalParameter(LootContextParams.DIRECT_KILLER_ENTITY, event.getSource().getDirectEntity());

            LootContext ctx = lootcontext$builder.create(LootContextParamSets.ENTITY);
            int i = 0;
            do
            {
                i++;
                for (ItemStack item: loottable.getRandomItems(ctx))
                {
                    i = 10;
                    mob.spawnAtLocation(item);
                }
            }
            while(i < 10);
            return;
        }
        Collection<ItemEntity> greed = new ArrayList<>();
        for (ItemEntity item:drops)
        {
            //custom item entity type, which floats towards the player?
            Vec3 vec3 = new Vec3(0,.1,0);
            item.setDeltaMovement(vec3);
            ItemEntity copy = item.copy();
            greed.add(copy);
        }
        drops.addAll(greed);
    }
    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }

    @Override
    protected boolean checkCompatibility(Enchantment pOther) {
        return !pOther.isCurse();
    }
}
