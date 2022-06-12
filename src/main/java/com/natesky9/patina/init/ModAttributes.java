package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModAttributes {
    public static final DeferredRegister<Attribute> MOD_ATTRIBUTES
            = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, Patina.MOD_ID);

    public static final RegistryObject<Attribute> RANGED_DAMAGE = MOD_ATTRIBUTES.register("ranged_damage",
            () -> new RangedAttribute("Ranged Damage",1,0,1024)
                    .setSyncable(true));

    public static final RegistryObject<Attribute> MAGIC_DAMAGE = MOD_ATTRIBUTES.register("magic_damage",
            () -> new RangedAttribute("Magic Damage",1,0,1024)
                    .setSyncable(true));

    //
    public static void register(IEventBus eventBus) {MOD_ATTRIBUTES.register(eventBus);}
}
