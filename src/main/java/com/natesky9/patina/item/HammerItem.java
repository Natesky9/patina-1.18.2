package com.natesky9.patina.item;

import net.minecraft.sounds.SoundEvents;

public class HammerItem extends ToolItem {
    public HammerItem(Properties pProperties)
    {
        super(pProperties);
        useDuration = 20;
        sound = SoundEvents.SMITHING_TABLE_USE;
    }
    //----------//
}
