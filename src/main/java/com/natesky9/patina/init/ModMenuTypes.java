package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import com.natesky9.patina.block.Custom.MachineCustomMenu;
import com.natesky9.patina.block.BlastCauldron.MachineBlastCauldronMenu;
import com.natesky9.patina.block.SmokerGrindstone.MachineSmokerGrindstoneMenu;
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
    public static final RegistryObject<MenuType<MachineCustomMenu>> CUSTOM_MACHINE_MENU =
            registerMenuType(MachineCustomMenu::new,"custom_machine_menu");
    public static final RegistryObject<MenuType<MachineBlastCauldronMenu>> MACHINE_BLAST_CAULDRON_MENU =
            registerMenuType(MachineBlastCauldronMenu::new,"machine_blast_cauldron_menu");
    public static final RegistryObject<MenuType<MachineSmokerGrindstoneMenu>> MACHINE_SMOKER_GRINDSTONE_MENU =
            registerMenuType(MachineSmokerGrindstoneMenu::new,"machine_smoker_grindstone_menu");
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