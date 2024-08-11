package com.natesky9.patina.painting;

import com.natesky9.patina.Patina;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTINGS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, Patina.MODID);
    //
    public static final RegistryObject<PaintingVariant> ALCHEMY_1 = PAINTINGS.register("crimson",
            () -> new PaintingVariant(16,16,
                    ResourceLocation.fromNamespaceAndPath("patina","crimson")));
    public static final RegistryObject<PaintingVariant> ALCHEMY_2 = PAINTINGS.register("alchemy",
            () -> new PaintingVariant(16,16,
                    ResourceLocation.fromNamespaceAndPath("patina","alchemy")));

    public static final RegistryObject<PaintingVariant> SWIFTNESS = PAINTINGS.register("swiftness",
            () -> new PaintingVariant(32,16,
                    ResourceLocation.fromNamespaceAndPath("patina","swiftness")));
    public static final RegistryObject<PaintingVariant> STRENGTH = PAINTINGS.register("strength",
            () -> new PaintingVariant(32,16,
                    ResourceLocation.fromNamespaceAndPath("patina","strength")));
    public static final RegistryObject<PaintingVariant> HEALING = PAINTINGS.register("healing",
            () -> new PaintingVariant(32,16,
                    ResourceLocation.fromNamespaceAndPath("patina","healing")));
    //
    public static void register(IEventBus eventBus)
    {
        PAINTINGS.register(eventBus);
    }
}
