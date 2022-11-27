package com.natesky9.patina;

import com.google.gson.JsonObject;
import com.natesky9.patina.init.ModEnchantments;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GreedCurseSubtractionModifier extends LootModifier {
    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    //TODO: remove this
    protected GreedCurseSubtractionModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        DamageSource source = context.getParamOrNull(LootContextParams.DAMAGE_SOURCE);
        //if there's no killer, return normal loot
        if (source == null) return generatedLoot;
        Entity entity = source.getEntity();
        //if it's not a player, return normal loot
        if (entity.getType() != EntityType.PLAYER) return generatedLoot;

        boolean greedCursed = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.GREEDCURSE.get(), (LivingEntity) entity) > 0;
        if (greedCursed)
            generatedLoot.remove(0);
        return generatedLoot;
    }
    //
    public static class Serializer extends GlobalLootModifierSerializer<GreedCurseSubtractionModifier>
    {
        @Override
        public GreedCurseSubtractionModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] conditionsIn) {
            return new GreedCurseSubtractionModifier(conditionsIn);
        }

        @Override
        public JsonObject write(GreedCurseSubtractionModifier instance) {
            return makeConditions(instance.conditions);
        }
    }
}
