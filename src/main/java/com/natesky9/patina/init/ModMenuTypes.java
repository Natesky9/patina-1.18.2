package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import com.natesky9.patina.block.Template.MachineTemplateMenu;
import com.natesky9.patina.block.AnvilSmithing.MachineAnvilSmithingMenu;
import com.natesky9.patina.block.BeaconGrindstone.MachineBeaconGrindstoneMenu;
import com.natesky9.patina.block.BlastCauldron.MachineBlastCauldronMenu;
import com.natesky9.patina.block.CauldronBrewing.MachineCauldronBrewingMenu;
import com.natesky9.patina.block.CauldronSmoker.MachineCauldronSmokerMenu;
import com.natesky9.patina.block.GrindstoneBarrel.MachineGrindstoneBarrelMenu;
import com.natesky9.patina.block.SmokerGrindstone.MachineSmokerGrindstoneMenu;
import com.natesky9.patina.item.PowderPouch.PowderPouchMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes
{
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, Patina.MOD_ID);
    //--------------------------------------------------//
    public static final RegistryObject<MenuType<MachineTemplateMenu>> MACHINE_TEMPLATE_MENU =
            registerMenuType(MachineTemplateMenu::new,"machine_template_menu");

    public static final RegistryObject<MenuType<MachineAnvilSmithingMenu>> MACHINE_ANVIL_SMITHING_MENU =
            registerMenuType(MachineAnvilSmithingMenu::new,"custom_machine_menu");
    public static final RegistryObject<MenuType<MachineBeaconGrindstoneMenu>> MACHINE_BEACON_GRINDSTONE_MENU =
            registerMenuType(MachineBeaconGrindstoneMenu::new,"machine_beacon_grindstone_menu");
    public static final RegistryObject<MenuType<MachineBlastCauldronMenu>> MACHINE_BLAST_CAULDRON_MENU =
            registerMenuType(MachineBlastCauldronMenu::new,"machine_blast_cauldron_menu");
    public static final RegistryObject<MenuType<MachineCauldronBrewingMenu>> MACHINE_CAULDRON_BREWING_MENU =
            registerMenuType(MachineCauldronBrewingMenu::new,"machine_cauldron_brewing_menu");
    public static final RegistryObject<MenuType<MachineCauldronSmokerMenu>> MACHINE_CAULDRON_SMOKER_MENU =
            registerMenuType(MachineCauldronSmokerMenu::new,"machine_cauldron_smoker_menu");
    public static final RegistryObject<MenuType<MachineGrindstoneBarrelMenu>> MACHINE_GRINDSTONE_BARREL_MENU =
            registerMenuType(MachineGrindstoneBarrelMenu::new,"machine_grindstone_barrel_menu");
    public static final RegistryObject<MenuType<MachineSmokerGrindstoneMenu>> MACHINE_SMOKER_GRINDSTONE_MENU =
            registerMenuType(MachineSmokerGrindstoneMenu::new,"machine_smoker_grindstone_menu");

    public static final RegistryObject<MenuType<PowderPouchMenu>> POWDER_POUCH_MENU =
            registerMenuType(PowderPouchMenu::new,"powder_pouch_menu");
    //--------------------------------------------------//
    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name)
    {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }
    public static void register(IEventBus eventBus)
    {
        MENUS.register(eventBus);
    }
}
