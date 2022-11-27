package com.natesky9.patina.init;

import com.natesky9.patina.Patina;
import io.netty.handler.codec.mqtt.MqttProperties;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlockStateProperties {
    public static final IntegerProperty LEVEL = IntegerProperty.create("level",0,16);
}
