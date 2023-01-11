package com.natesky9.patina;

import net.minecraft.util.StringRepresentable;

public enum Fish implements StringRepresentable {
    NONE("none"),
    SALMON("salmon"),
    COD("cod"),
    TROPICAL("tropical"),
    PUFFER("puffer");

    private final String name;

    private Fish(String name)
    {
        this.name = name;
    }
    @Override
    public String getSerializedName() {
        return this.name;
    }
}
