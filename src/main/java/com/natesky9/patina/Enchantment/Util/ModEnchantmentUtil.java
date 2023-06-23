package com.natesky9.patina.Enchantment.Util;

import com.natesky9.patina.init.ModEnchantments;
import com.natesky9.patina.init.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.*;
import java.util.function.Supplier;

import static com.natesky9.patina.init.ModEnchantments.*;
import static net.minecraft.world.item.enchantment.Enchantments.*;

public class ModEnchantmentUtil {
    public static final List<Enchantment> curses = List.of
            (
                    CURSEENVY.get(),
                    CURSEGREED.get(),
                    CURSEGLUTTONY.get(),
                    CURSELUST.get(),
                    CURSESLOTH.get(),
                    CURSEPRIDE.get(),
                    CURSEWRATH.get(),
                    Enchantments.BINDING_CURSE,
                    VANISHING_CURSE
            );
    public Map<Enchantment,Enchantment> enchantmentOpposites;
    {
        enchantmentOpposites = new HashMap<>();
        enchantmentOpposites.put(VANISHING_CURSE, BLESSINGSOULBOUND.get());
        enchantmentOpposites.put(CURSEENVY.get(), BLESSINGENVY.get());
        enchantmentOpposites.put(CURSEGLUTTONY.get(), BLESSINGGLUTTONY.get());
        enchantmentOpposites.put(CURSEGREED.get(), BLESSINGGREED.get());
        enchantmentOpposites.put(CURSELUST.get(), BLESSINGLUST.get());
        enchantmentOpposites.put(CURSEPRIDE.get(), BLESSINGPRIDE.get());
        enchantmentOpposites.put(CURSESLOTH.get(), BLESSINGSLOTH.get());
        enchantmentOpposites.put(CURSEWRATH.get(), BLESSINGWRATH.get());
    }

    public static void netherCursePlayer(LivingHurtEvent event)
    {
        //nether curse items when hurt by wither skeleton
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (!(player.getLevel().dimension() == Level.NETHER)) return;
        if (!(event.getSource().getEntity() instanceof WitherSkeleton)) return;
        if (player.getInventory().hasAnyMatching(itemStack -> itemStack.is(ModItems.CHARM_WARDING.get())))
        {
            player.displayClientMessage(Component.translatable("charm_warding"),true);
            return;
        }
        curseItem(player);
    }
    public static void curseItem(Player player)
    {
        List<ItemStack> items = new ArrayList<>();
        items.addAll(player.getInventory().armor);
        items.addAll(player.getInventory().items);
        items.addAll(player.getInventory().offhand);

        Collections.shuffle(items);
        for (ItemStack item: items)
        {
            //if it's not enchantable, skip
            //if (!(item.isEnchanted())) continue;
            int index = (int)(Math.random()*curses.size());
            Enchantment curse = curses.get(index);
            if (curse.canEnchant(item))
            {
                item.enchant(curse, 1);
                for (float i = 0;i < 5;i++)
                    player.level.playSound(null,player.blockPosition(), SoundEvents.SILVERFISH_AMBIENT, SoundSource.PLAYERS,
                            .5F,i/10);

                break;
            }
        }
    }
}
