package com.natesky9.patina.Item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jline.utils.Colors;

import java.awt.*;

public class RustableItem {
    public static int getRust(ItemStack stack)
    {
        int rust = stack.getOrCreateTag().getInt("oxidation");
        rust = rust > 255 ? 255 : rust < 0 ? 0 : rust;
        return getTint(rust);
    }
    public static int getTint(int rust)
    {
        //creative use of bitshifting to make an orange -> green gradient
        return ((255-rust) << 16) + (140 << 8) + 70;
    }
    public static void rust(ItemStack stack)
    {
        if (Math.random() > .05) return;
        stack.getOrCreateTag().putInt("oxidation",Math.min(255,stack.getOrCreateTag().getInt("oxidation")+1));
    }
    public static int getSheen(Level level)
    {
        if (level == null) return 0;
        float day = (int)(level.getDayTime() % 24000);
        int sheen = Color.HSBtoRGB((float)(day/240),.5F,1F);
        System.out.println(sheen);
        return sheen;
    }
}
