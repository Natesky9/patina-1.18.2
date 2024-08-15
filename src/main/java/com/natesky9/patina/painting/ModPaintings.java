package com.natesky9.patina.painting;

import com.natesky9.patina.Patina;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;

public class ModPaintings {
    public static final ResourceKey<PaintingVariant> ALCHEMY_1 = create("alchemy");
    public static final ResourceKey<PaintingVariant> ALCHEMY_2 = create("crimson");
    public static final ResourceKey<PaintingVariant> ALCHEMY_3 = create("swiftness");
    public static final ResourceKey<PaintingVariant> ALCHEMY_4 = create("strength");
    public static final ResourceKey<PaintingVariant> ALCHEMY_5 = create("healing");
    public static final ResourceKey<PaintingVariant> NOTCH = create("notch");
    //
    public static void bootstrap(BootstrapContext<PaintingVariant> bootstrap)
    {
        register(bootstrap,ALCHEMY_1,2,2);
        register(bootstrap,ALCHEMY_2,2,2);
        register(bootstrap,ALCHEMY_3,3,1);
        register(bootstrap,ALCHEMY_4,3,1);
        register(bootstrap,ALCHEMY_5,3,1);
        register(bootstrap,NOTCH,5,5);
    }
    //

    //
    private static ResourceKey<PaintingVariant> create(String name)
    {
        return ResourceKey.create(Registries.PAINTING_VARIANT, ResourceLocation.fromNamespaceAndPath(Patina.MODID,name));
    }
    private static void register(BootstrapContext<PaintingVariant> bootstrap, ResourceKey<PaintingVariant> resource, int width, int height)
    {
        bootstrap.register(resource, new PaintingVariant(width,height,resource.location()));
    }
}
