package com.natesky9.patina.init;

import com.natesky9.patina.Block.ApplianceIcebox.ApplianceIceboxScreen;
import com.natesky9.patina.Block.ApplianceWardrobe.ApplianceWardrobeScreen;
import com.natesky9.patina.Block.MachineAlembic.MachineAlembicScreen;
import com.natesky9.patina.Block.MachineEnchanter.MachineEnchanterScreen;
import com.natesky9.patina.Block.MachineEvaporator.MachineEvaporatorScreen;
import com.natesky9.patina.Block.MachineFoundry.MachineFoundryScreen;
import net.minecraft.client.gui.screens.MenuScreens;

public class ModScreens
{
    public static void register()
    {
        MenuScreens.register(ModMenuTypes.APPLIANCE_ICEBOX_MENU.get(), ApplianceIceboxScreen::new);
        MenuScreens.register(ModMenuTypes.APPLIANCE_WARDROBE_MENU.get(), ApplianceWardrobeScreen::new);
        MenuScreens.register(ModMenuTypes.MACHINE_ALEMBIC_MENU.get(), MachineAlembicScreen::new);
        MenuScreens.register(ModMenuTypes.MACHINE_EVAPORATOR_MENU.get(), MachineEvaporatorScreen::new);
        MenuScreens.register(ModMenuTypes.MACHINE_ENCHANTER_MENU.get(), MachineEnchanterScreen::new);
        MenuScreens.register(ModMenuTypes.MACHINE_FOUNDRY_MENU.get(), MachineFoundryScreen::new);
    }
}
