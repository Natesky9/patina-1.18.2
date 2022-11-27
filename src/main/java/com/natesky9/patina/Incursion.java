package com.natesky9.patina;

import com.natesky9.patina.init.ModEnchantments;
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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.*;
import java.util.function.Supplier;

import static com.natesky9.patina.init.ModEnchantments.*;
import static net.minecraft.world.item.enchantment.Enchantments.*;

public class Incursion {
    Level incursionLevel;
    BlockPos incursionPos;
    IncursionType incursionType;
    AABB area;
    CustomBossEvent event;
    int lifetime;
    boolean valid;
    public static List<Enchantment> curses = new ArrayList<>();
    {
        curses.add(BINDING_CURSE);
        curses.add(VANISHING_CURSE);
        for (Supplier<Enchantment> enchant:deadlySins)
        {
            curses.add(enchant.get());
        }
    };

    public Map<IncursionType,EntityType<?>> IncursionTypes;
    {
        //TODO: make this a list of entities instead of singles
        IncursionTypes = new HashMap<>();
        IncursionTypes.put(IncursionType.UNDEAD, EntityType.ZOMBIE);
        IncursionTypes.put(IncursionType.OSSIFIED,EntityType.SKELETON);
        IncursionTypes.put(IncursionType.ARACHNID,EntityType.SPIDER);
        IncursionTypes.put(IncursionType.COVEN,EntityType.WITCH);
        IncursionTypes.put(IncursionType.RAIDER,EntityType.PILLAGER);
        IncursionTypes.put(IncursionType.CANINE,EntityType.WOLF);
        IncursionTypes.put(IncursionType.ALIEN,EntityType.ENDERMAN);
        IncursionTypes.put(IncursionType.AQUATIC,EntityType.GUARDIAN);
    }
    private IncursionType ChooseIncursionType()
    {//picks a random incursion
        return IncursionType.values()[(int)(Math.random()*IncursionType.values().length)];
    }

    public Map<Enchantment,Enchantment> enchantmentOpposites;
    {
        enchantmentOpposites = new HashMap<>();
        enchantmentOpposites.put(VANISHING_CURSE, SOULBOUND.get());
        enchantmentOpposites.put(ENVYCURSE.get(), ENVYBLESSING.get());
        enchantmentOpposites.put(GLUTTONYCURSE.get(), GLUTTONYBLESSING.get());
        enchantmentOpposites.put(GREEDCURSE.get(), GREEDBLESSING.get());
        enchantmentOpposites.put(LUSTCURSE.get(), LUSTBLESSING.get());
        enchantmentOpposites.put(PRIDECURSE.get(), PRIDEBLESSING.get());
        enchantmentOpposites.put(SLOTHCURSE.get(), SLOTHBLESSING.get());
        enchantmentOpposites.put(WRATHCURSE.get(), WRATHBLESSING.get());
    }
    public List<ItemEntity> items;


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
        incursionType = ChooseIncursionType();
        items = new ArrayList<>();

        items.add(new ItemEntity(incursionLevel,incursionPos.getX(),incursionPos.getY(),incursionPos.getZ(),
                new ItemStack(Items.DIAMOND)));
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
    public void addItems(Collection<ItemEntity> collection)
    {
        items.addAll(collection);
    }
    public void spawnMobs()
    {
        if (!valid) return;
        boolean spawnTick = (lifetime % 20) == 0;
        if (!spawnTick) return;
        int mobs = incursionLevel.getEntitiesOfClass(Monster.class,area).size();
        int x = (int)(Math.random()*32) - 16 + position().getX();
        int y = position().getY() + 3;
        int z = (int)(Math.random()*32) - 16 + position().getZ();
        if (mobs < 8)
        {
            EntityType<?> type = IncursionTypes.get(incursionType);
            Entity entity = type.create(incursionLevel);
            //Entity entity = new Zombie(incursionLevel);
            entity.setPos(x,y,z);
            incursionLevel.addParticle(ParticleTypes.POOF,x,y,z,0,1,0);
            //do armor and stuff here
            entity.setItemSlot(EquipmentSlot.CHEST,new ItemStack(Items.LEATHER_CHESTPLATE));
            incursionLevel.addFreshEntity(entity);
        }
    }
    public void drawBoundary()
    {
        //TODO map blockpos values to draw particles along the floor?
        int incursionX = incursionPos.getX();
        int incursionY = incursionPos.getY();
        int incursionZ = incursionPos.getZ();
        ServerLevel level = (ServerLevel) incursionLevel;
        //mark the center
        level.sendParticles(ParticleTypes.ELECTRIC_SPARK,incursionX,incursionY,incursionZ,
                1,.5,.5,.5,0);
        for (int x = -16;x <= 16;x++)
        {
            for (int z = -16;z <= 16;z++)
            {
                if ((x == -16 || x == 16 || z == -16 || z == 16) && Math.random() > .75)
                    level.sendParticles(ParticleTypes.ENCHANT,
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
        event.setName(new TextComponent("Incursion Successful"));
        valid = false;
        lifetime = 0;

        //drop all the collected items at the start
        items.stream().forEach(itemEntity ->
        {
            incursionLevel.addFreshEntity(itemEntity);
            itemEntity.setPos(Vec3.atCenterOf(incursionPos));
        });
    }
    public void fail()
    {
        event.getPlayers().stream().forEach(player ->
        {
            incursionLevel.playSound(null,player.blockPosition(), SoundEvents.ENDERMITE_DEATH, SoundSource.PLAYERS,1,1);
        });
        event.setName(new TextComponent("Incursion Failed"));
        valid = false;
        lifetime = 0;
    }

    public void rewardPlayers()
    {
        event.getPlayers().stream().forEach(player ->
        {
            //loop through all players in the incursion
            boolean flag = true;
            player.giveExperiencePoints(100);
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
        long time = incursionLevel.getDayTime();
         return night(time);
    }
    public static boolean night(long time)
    {
        time = time % 24000;
        return (time <= 23000 && time >= 13000);
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
                player.level.playSound(null,player.blockPosition(),SoundEvents.SILVERFISH_AMBIENT,SoundSource.PLAYERS,
                        .5F,i/10);

                break;
            }
        }
    }
}
