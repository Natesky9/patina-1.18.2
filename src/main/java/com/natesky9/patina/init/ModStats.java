package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stats;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModStats {
    public static final DeferredRegister<ResourceLocation> MODSTATS
            = DeferredRegister.create(Registry.CUSTOM_STAT_REGISTRY,Patina.MOD_ID);
    //
    public static RegistryObject<ResourceLocation> HINT_TELECHORUS = MODSTATS.register("hint_telechorus",
            () -> new ResourceLocation(Patina.MOD_ID,"hint_telechorus"));
    //
    private static ResourceLocation makeStat(String name)
    {
        ResourceLocation location = new ResourceLocation(Patina.MOD_ID,name);
        //Registry.register(Registry.CUSTOM_STAT,name,location);
        MODSTATS.register(name,() -> location);
        Stats.CUSTOM.get(location);
        return location;
    }
    public static void register(IEventBus eventBus)
    {
        MODSTATS.register(eventBus);
    }
}
