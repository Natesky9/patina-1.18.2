package com.natesky9.patina.init;

import com.natesky9.patina.Fish;
import com.natesky9.patina.Patina;
import io.netty.handler.codec.mqtt.MqttProperties;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlockStateProperties {
    public static final IntegerProperty LEVEL = IntegerProperty.create("level",0,16);
    public static final Property FISH = EnumProperty.create("fish",Fish.class);
}
