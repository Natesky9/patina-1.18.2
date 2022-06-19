package com.natesky9.patina.block.Template;

import net.minecraft.world.inventory.ContainerData;

public class MachineEntityContainerData implements ContainerData {
    int value1;
    int value2;
    int value3;
    int value4;
    int count;
    public MachineEntityContainerData(int field1)
    {
        this.value1 = field1;
        count = 1;
    }
    public MachineEntityContainerData(int field1, int field2)
    {
        this(field1);
        this.value2 = field2;
        count = 2;
    }
    public MachineEntityContainerData(int field1, int field2, int field3)
    {
        this(field1,field2);
        this.value3 = field3;
        count = 3;
    }
    public MachineEntityContainerData(int field1, int field2, int field3, int field4)
    {
        this(field1,field2,field3);
        this.value4 = field4;
        count = 4;
    }
    @Override
    public int get(int index) {
        return switch (index) {
            case 0 -> this.value1;
            case 1 -> this.value2;
            case 2 -> this.value3;
            case 3 -> this.value4;
            default -> 0;
        };
    }

    @Override
    public void set(int index, int value) {
        switch (index) {
            case 0 -> this.value1 = value;
            case 1 -> this.value2 = value;
            case 2 -> this.value3 = value;
            case 3 -> this.value4 = value;
        };
    }

    @Override
    public int getCount() {
        return count;
    }
}
