package com.natesky9.patina.init;

import com.natesky9.patina.entity.Armor.*;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;

public class ModModels {
    public static final TemplateArmorModel<Entity> templateArmorModel =
            new TemplateArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(TemplateArmorModel.LAYER_LOCATION));
    public static final UmbraArmorModel<Entity> umbraArmorModel =
            new UmbraArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(UmbraArmorModel.LAYER_LOCATION));
    public static final CrystalPrimeArmorModel<Entity> crystalArmorModel =
            new CrystalPrimeArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(CrystalPrimeArmorModel.LAYER_LOCATION));
    public static final CrystalAnimaArmorModel<Entity> animaArmorModel =
            new CrystalAnimaArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(CrystalAnimaArmorModel.LAYER_LOCATION));
    public static final CrystalFerusArmorModel<Entity> ferusArmorModel =
            new CrystalFerusArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(CrystalFerusArmorModel.LAYER_LOCATION));
    public static final CrystalFortisArmorModel<Entity> fortisArmorModel =
            new CrystalFortisArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(CrystalFortisArmorModel.LAYER_LOCATION));

}
