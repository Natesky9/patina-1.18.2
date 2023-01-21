package com.natesky9.patina.item;

import net.minecraft.sounds.SoundEvents;

public class ChiselItem extends ToolItem {
    public ChiselItem(Properties pProperties)
    {
        super(pProperties);
        useDuration = 20;
        sound = SoundEvents.ANVIL_USE;
    }
    //----------//
}
