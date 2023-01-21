package com.natesky9.patina.item;

import net.minecraft.sounds.SoundEvents;

public class KnifeItem extends ToolItem {
    public KnifeItem(Properties pProperties)
    {
        super(pProperties);
        useDuration = 20;
        sound = SoundEvents.SHEEP_SHEAR;
    }
    //----------//
}
