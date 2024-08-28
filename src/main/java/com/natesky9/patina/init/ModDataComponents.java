package com.natesky9.patina.init;

import com.natesky9.patina.NBTcomponents.ArcanaComponent;
import com.natesky9.patina.NBTcomponents.EnergyComponent;
import com.natesky9.patina.Patina;
import com.natesky9.patina.NBTcomponents.StorageComponent;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS =
            DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE.key(), Patina.MODID);
    //
    public static final RegistryObject<DataComponentType<StorageComponent>> STORAGE_COMPONENT = DATA_COMPONENTS.register("storage",
            () -> DataComponentType.<StorageComponent>builder().persistent(StorageComponent.CODEC)
                    .networkSynchronized(StorageComponent.STREAM_CODEC).build());
    public static final RegistryObject<DataComponentType<EnergyComponent>> ENERGY_COMPONENT = DATA_COMPONENTS.register("energy",
            () -> DataComponentType.<EnergyComponent>builder().persistent(EnergyComponent.CODEC)
                    .networkSynchronized(EnergyComponent.STREAM_CODEC).build());
    public static final RegistryObject<DataComponentType<ArcanaComponent>> Arcana_COMPONENT = DATA_COMPONENTS.register("arcana",
            () -> DataComponentType.<ArcanaComponent>builder().persistent(ArcanaComponent.CODEC)
                    .networkSynchronized(ArcanaComponent.STREAM_CODEC).build());
    //this is replaced by PotionContents in 1.20, woohoo!
    //public static final RegistryObject<DataComponentType<PotionComponent>> POTION = DATA_COMPONENTS.register("potion",
    //        () -> DataComponentType.<PotionComponent>builder().persistent(PotionComponent.CODEC)
    //                .networkSynchronized(Potion.STREAM_CODEC).build());
    //
    //private static <T> DataComponentType<T> register(String string, UnaryOperator<DataComponentType.Builder<T>> type)
    //{
    //    return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, string, type.apply(DataComponentType.builder()).build());
    //}
    public static void register(IEventBus eventBus)
    {
        DATA_COMPONENTS.register(eventBus);
    }
}
