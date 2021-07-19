package com.gline9.daedalus;

public interface BoardData
{
    Dimensions getDimensions();

    Integer getValue(Coordinates coordinates);

    void setValue(Coordinates coordinates, Integer value);
}
