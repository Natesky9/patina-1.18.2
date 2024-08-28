package com.natesky9.patina.init;

import com.natesky9.patina.Block.ApplianceIcebox.ApplianceIceboxMenu;
import com.natesky9.patina.Block.ApplianceWardrobe.ApplianceWardrobeMenu;
import com.natesky9.patina.Block.Benchmark.ApplianceBenchmarkMenu;
import com.natesky9.patina.Block.MachineAlembic.MachineAlembicMenu;
import com.natesky9.patina.Block.MachineEnchanter.MachineEnchanterMenu;
import com.natesky9.patina.Block.MachineEvaporator.MachineEvaporatorMenu;
import com.natesky9.patina.Block.MachineFoundry.MachineFoundryMenu;
import com.natesky9.patina.Block.MachineMincerator.MachineMinceratorMenu;
import com.natesky9.patina.Block.MachineTextiler.MachineTextilerMenu;
import com.natesky9.patina.Patina;
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
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Patina.MODID);
    //--------------------------------------------------//
    //public static final RegistryObject<MenuType<MachineTemplateMenu>> MACHINE_TEMPLATE_MENU =
    //        registerMenuType(MachineTemplateMenu::new,"machine_template_menu");

    public static final RegistryObject<MenuType<MachineEvaporatorMenu>> MACHINE_EVAPORATOR_MENU =
            registerMenuType(MachineEvaporatorMenu::new,"machine_evaporator_menu");
    public static final RegistryObject<MenuType<MachineFoundryMenu>> MACHINE_FOUNDRY_MENU =
            registerMenuType(MachineFoundryMenu::new, "machine_foundry_menu");
    public static final RegistryObject<MenuType<MachineMinceratorMenu>> MACHINE_MINCERATOR_MENU =
            registerMenuType(MachineMinceratorMenu::new, "machine_mincerator_menu");
    public static final RegistryObject<MenuType<MachineEnchanterMenu>> MACHINE_ENCHANTER_MENU =
            registerMenuType(MachineEnchanterMenu::new, "machine_enchanter_menu");
    public static final RegistryObject<MenuType<MachineAlembicMenu>> MACHINE_ALEMBIC_MENU =
            registerMenuType(MachineAlembicMenu::new, "machine_alembic_menu");
    public static final RegistryObject<MenuType<MachineTextilerMenu>> MACHINE_TEXTILER_MENU =
            registerMenuType(MachineTextilerMenu::new, "machine_textiler_menu");

    public static final RegistryObject<MenuType<ApplianceWardrobeMenu>> APPLIANCE_WARDROBE_MENU =
            registerMenuType(ApplianceWardrobeMenu::new, "appliance_wardrobe_menu");
    public static final RegistryObject<MenuType<ApplianceBenchmarkMenu>> APPLIANCE_BENCHMARK_MENU =
            registerMenuType(ApplianceBenchmarkMenu::new, "appliance_benchmark_menu");
    public static final RegistryObject<MenuType<ApplianceIceboxMenu>> APPLIANCE_ICEBOX_MENU =
            registerMenuType(ApplianceIceboxMenu::new, "appliance_icebox_menu");

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
