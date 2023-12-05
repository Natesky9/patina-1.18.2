package com.natesky9.patina.init;

import com.natesky9.patina.entity.Armor.TemplateArmorModel;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;

public class ModModels {
    public static final TemplateArmorModel<Entity> templateArmorModel =
            new TemplateArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(TemplateArmorModel.LAYER_LOCATION));
}
