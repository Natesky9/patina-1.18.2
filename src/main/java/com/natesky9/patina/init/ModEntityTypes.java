package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import com.natesky9.patina.entity.BeeQueen.BeeQueen;
import com.natesky9.patina.entity.SpiderQueen.SpiderQueen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES
            =DeferredRegister.create(ForgeRegistries.ENTITIES, Patina.MOD_ID);
    //start entities
    public static final RegistryObject<EntityType<BeeQueen>> BEE_QUEEN =
            ENTITY_TYPES.register("bee_queen",
                    () -> EntityType.Builder.of(BeeQueen::new, MobCategory.MONSTER)
                            .sized(3,3)
                            .build(new ResourceLocation(Patina.MOD_ID,"bee_queen").toString()));
    public static final RegistryObject<EntityType<SpiderQueen>> SPIDER_QUEEN =
            ENTITY_TYPES.register("spider_queen",
                    () -> EntityType.Builder.of(SpiderQueen::new, MobCategory.MONSTER)
                            .sized(3,3)
                            .build(new ResourceLocation(Patina.MOD_ID,"spider_queen").toString()));
    //done with entities
    public static void register(IEventBus eventBus)
    {ENTITY_TYPES.register(eventBus);}

}
