package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import com.natesky9.patina.effect.EatingEffect;
import com.natesky9.patina.effect.VenomEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOD_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Patina.MOD_ID);

    public static final RegistryObject<MobEffect> VENOM = MOD_EFFECTS.register("venom",
            () -> new VenomEffect(MobEffectCategory.HARMFUL,123123));
    public static final RegistryObject<MobEffect> EATING = MOD_EFFECTS.register("eating",
            () -> new EatingEffect(MobEffectCategory.BENEFICIAL,444444));

    public static void register(IEventBus eventBus){
        MOD_EFFECTS.register(eventBus);
    }
}
