package com.natesky9.patina;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;

import java.util.Arrays;
import java.util.stream.Stream;

public enum MultiBlockQuad implements StringRepresentable {
    NORTHEAST,
    NORTHWEST,
    SOUTHEAST,
    SOUTHWEST;

    public String toString()
    {
        return this.getSerializedName();
    }

    @Override
    public String getSerializedName() {
        switch (this)
        {
            case NORTHEAST -> {return "northeast";}
            case SOUTHEAST -> {return "southeast";}
            case SOUTHWEST -> {return "southwest";}
            case NORTHWEST -> {return "northwest";}
        }
        return "error";
    }
    public static MultiBlockQuad cornerFromDirections(Direction[] directions)
    {
        Stream<Direction> stream = Arrays.stream(directions);
        Stream<Direction> copy = Arrays.stream(directions);
        Direction lat = stream.filter(direction -> direction.getAxis() == Direction.Axis.Z).findFirst().get();
        Direction lon = copy.filter(direction -> direction.getAxis() == Direction.Axis.X).findFirst().get();
        return cornerFromDirections(lat,lon);
    }
    public static MultiBlockQuad cornerFromDirections(Direction first, Direction second)
    {
        if (first == Direction.NORTH)
        {
            return second == Direction.EAST ? NORTHEAST : NORTHWEST;
        }
        if (first == Direction.SOUTH)
        {
            return second == Direction.EAST ? SOUTHEAST : SOUTHWEST;
        }
        if (first == Direction.EAST)
        {
            return second == Direction.NORTH ? NORTHEAST : SOUTHEAST;
        }
        return second == Direction.NORTH ? NORTHWEST : SOUTHWEST;
    }
    public static Direction firstDirection(MultiBlockQuad corner)
    {
        return corner == NORTHEAST || corner == NORTHWEST ?
                Direction.NORTH : Direction.SOUTH;
    }
    public static Direction secondDirection(MultiBlockQuad corner)
    {
        return corner == NORTHEAST || corner == SOUTHEAST ?
                Direction.EAST : Direction.WEST;
    }
    public static BlockPos getTwo(BlockPos pos, MultiBlockQuad corner)
    {
        return pos.relative(MultiBlockQuad.firstDirection(corner));
    }
    public static BlockPos getThree(BlockPos pos, MultiBlockQuad corner)
    {
        return pos.relative(MultiBlockQuad.firstDirection(corner))
                .relative(MultiBlockQuad.secondDirection(corner));
    }
    public static BlockPos getFour(BlockPos pos, MultiBlockQuad corner)
    {
        return pos.relative(MultiBlockQuad.secondDirection(corner));
    }
}
