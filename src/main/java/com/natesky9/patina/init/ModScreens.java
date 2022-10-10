package com.natesky9.patina.init;

import com.natesky9.patina.block.AnvilSmithing.MachineAnvilSmithingScreen;
import com.natesky9.patina.block.BeaconGrindstone.MachineBeaconGrindstoneScreen;
import com.natesky9.patina.block.BlastCauldron.MachineBlastCauldronScreen;
import com.natesky9.patina.block.CauldronBrewing.MachineCauldronBrewingScreen;
import com.natesky9.patina.block.CauldronSmoker.MachineCauldronSmokerScreen;
import com.natesky9.patina.block.GrindstoneBarrel.MachineGrindstoneBarrelScreen;
import com.natesky9.patina.block.SmokerGrindstone.MachineSmokerGrindstoneScreen;
import com.natesky9.patina.block.Template.MachineTemplateScreen;
import com.natesky9.patina.item.PowderPouch.PowderPouchScreen;
import net.minecraft.client.gui.screens.MenuScreens;

public class ModScreens
{
    public static void register() {
        MenuScreens.register(ModMenuTypes.MACHINE_TEMPLATE_MENU.get(), MachineTemplateScreen::new);

        MenuScreens.register(ModMenuTypes.MACHINE_ANVIL_SMITHING_MENU.get(), MachineAnvilSmithingScreen::new);
        MenuScreens.register(ModMenuTypes.MACHINE_BEACON_GRINDSTONE_MENU.get(), MachineBeaconGrindstoneScreen::new);
        MenuScreens.register(ModMenuTypes.MACHINE_BLAST_CAULDRON_MENU.get(), MachineBlastCauldronScreen::new);
        MenuScreens.register(ModMenuTypes.MACHINE_CAULDRON_BREWING_MENU.get(), MachineCauldronBrewingScreen::new);
        MenuScreens.register(ModMenuTypes.MACHINE_CAULDRON_SMOKER_MENU.get(), MachineCauldronSmokerScreen::new);
        MenuScreens.register(ModMenuTypes.MACHINE_GRINDSTONE_BARREL_MENU.get(), MachineGrindstoneBarrelScreen::new);
        MenuScreens.register(ModMenuTypes.MACHINE_SMOKER_GRINDSTONE_MENU.get(), MachineSmokerGrindstoneScreen::new);
        MenuScreens.register(ModMenuTypes.POWDER_POUCH_MENU.get(), PowderPouchScreen::new);
    }
}
