package com.natesky9.patina.Block.Benchmark;

import com.natesky9.patina.init.ModBlocks;
import com.natesky9.patina.init.ModMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.CombatRules;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;

public class ApplianceBenchmarkMenu extends AbstractContainerMenu {
    final Level level;
    final BlockPos pos;
    final ContainerData data;
    private final ContainerLevelAccess access;

    final LivingEntity entity;
    final Arrow arrow;
    final Explosion explosion;

    final Container armorSlots = new SimpleContainer(4)
    {
        @Override
        public boolean canPlaceItem(int p_18952_, ItemStack stack) {
            return stack.getItem() instanceof ArmorItem;
        }

        @Override
        public void setItem(int p_19162_, ItemStack stack) {
            super.setItem(p_19162_, stack);
            if (stack.getItem() instanceof ArmorItem armor)
            {
                SoundEvent event = armor.getEquipSound().get();
                level.playSound(null, pos,event, SoundSource.BLOCKS,1f,1f);
            }
        }

        @Override
        public void setChanged() {
            super.setChanged();
            ApplianceBenchmarkMenu.this.slotsChanged(this);
        }
    };

    public ApplianceBenchmarkMenu(int pContainerId, Inventory inv, Level level, BlockPos pos, ContainerData data)
    {
        super(ModMenuTypes.APPLIANCE_BENCHMARK_MENU.get(), pContainerId);
        this.pos =  pos;
        this.level = level;
        this.data = data;
        this.access = ContainerLevelAccess.create(level, pos);
        this.addSlot(new Slot(armorSlots,0,16, 6));
        this.addSlot(new Slot(armorSlots,1,16,25));
        this.addSlot(new Slot(armorSlots,2,16, 44));
        this.addSlot(new Slot(armorSlots,3,16,63));
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        this.addDataSlots(data);

        entity = new ArmorStand(EntityType.ARMOR_STAND,level);
        if (level instanceof ServerLevel server)
        {

            level.addFreshEntity(entity);
            arrow = new Arrow(EntityType.ARROW,level);
            arrow.setDeltaMovement(new Vec3(0,1,0));
            explosion = new Explosion(level,entity,entity.getX(),entity.getY(),entity.getZ(),1,false, Explosion.BlockInteraction.KEEP);
        }
        else
        {
            arrow = null;
            explosion = null;
        }
    }
    public ApplianceBenchmarkMenu(int pContainerId, Inventory inv, FriendlyByteBuf buf)
    {
        this(pContainerId, inv, inv.player.level(), inv.player.blockPosition(), new SimpleContainerData(6));
        //we don't do anything with the buffer here because I can't figure out where to write
    }


    @Override
    public void slotsChanged(Container p_38868_) {
        slots.get(0).getItem();

        entity.setItemSlot(EquipmentSlot.HEAD,slots.get(0).getItem().copy());
        entity.setItemSlot(EquipmentSlot.CHEST,slots.get(1).getItem().copy());
        entity.setItemSlot(EquipmentSlot.LEGS,slots.get(2).getItem().copy());
        entity.setItemSlot(EquipmentSlot.FEET,slots.get(3).getItem().copy());
        if (level instanceof ServerLevel server)
        {
            level.scheduleTick(pos,ModBlocks.APPLIANCE_BENCHMARK.get(),3);
        }
        super.slotsChanged(p_38868_);
    }
    float protectAgainst(ServerLevel server, DamageSource source)
    {
        float health = 100;
        health = CombatRules.getDamageAfterAbsorb(entity,health,source,
                (float)entity.getArmorValue(),(float)entity.getAttributeValue(Attributes.ARMOR_TOUGHNESS));
        float f3 = EnchantmentHelper.getDamageProtection(server, entity, source);
        if (f3 > 0)
            health = CombatRules.getDamageAfterMagicAbsorb(health, f3);
        return health;
    }
    public void calculate(Container container)
    {
        if (!(level instanceof ServerLevel server))
            return;//we should never be here on client

        float norm = protectAgainst(server,entity.damageSources().generic());
        float proj = protectAgainst(server,entity.damageSources().arrow(arrow,null));
        float fall = protectAgainst(server,entity.damageSources().fall());
        float fire = protectAgainst(server,entity.damageSources().lava());
        float blast = protectAgainst(server,entity.damageSources().explosion(explosion));
        float magic = protectAgainst(server,entity.damageSources().magic());



        //float fall = 100;
        ////fall = CombatRules.getDamageAfterAbsorb(entity,fall,entity.damageSources().fall(),
        ////        (float)entity.getArmorValue(),(float)entity.getAttributeValue(Attributes.ARMOR_TOUGHNESS));
        //float fire = 100;
        //fire = CombatRules.getDamageAfterAbsorb(entity,fire,entity.damageSources().inFire(),
        //        (float)entity.getArmorValue(),(float)entity.getAttributeValue(Attributes.ARMOR_TOUGHNESS));
        //float blast = 100;
        //blast = CombatRules.getDamageAfterAbsorb(entity,blast,entity.damageSources().explosion(explosion),
        //        (float)entity.getArmorValue(),(float)entity.getAttributeValue(Attributes.ARMOR_TOUGHNESS));
        //float magic = 100;
        //magic = CombatRules.getDamageAfterAbsorb(entity,magic,entity.damageSources().magic(),
        //        (float)entity.getArmorValue(),(float)entity.getAttributeValue(Attributes.ARMOR_TOUGHNESS));
        data.set(0,(int)(100-norm));
        data.set(1,(int)(100-proj));
        data.set(2,(int)(100-fall));
        data.set(3,(int)(100-fire));
        data.set(4,(int)(100-blast));
        data.set(5,(int)(100-magic));
        broadcastChanges();
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slot) {
        ItemStack stack = slots.get(slot).getItem();
        if (slot >= 4) {
            if (stack.is(Tags.Items.ARMORS_HELMETS)) {
                ItemStack hold = armorSlots.getItem(0);
                armorSlots.setItem(0, stack);
                this.slots.get(slot).set(hold);
            }
            if (stack.is(Tags.Items.ARMORS_CHESTPLATES)) {
                ItemStack hold = armorSlots.getItem(1);
                armorSlots.setItem(1, stack);
                this.slots.get(slot).set(hold);
            }
            if (stack.is(Tags.Items.ARMORS_LEGGINGS)) {
                ItemStack hold = armorSlots.getItem(2);
                armorSlots.setItem(2, stack);
                this.slots.get(slot).set(hold);
            }
            if (stack.is(Tags.Items.ARMORS_BOOTS)) {
                ItemStack hold = armorSlots.getItem(3);
                armorSlots.setItem(3, stack);
                this.slots.get(slot).set(hold);
            }
        }
        else {
            player.addItem(stack);
            armorSlots.setItem(slot,ItemStack.EMPTY);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, pos),
                player, ModBlocks.APPLIANCE_BENCHMARK.get());
    }

    private void addPlayerInventory(Inventory playerInventory)
    {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }
    private void addPlayerHotbar(Inventory playerInventory)
    {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.access.execute((level, pos) -> this.clearContainer(player,armorSlots));
        if (level instanceof ServerLevel server)
        {
            entity.kill();
        }
    }
}
