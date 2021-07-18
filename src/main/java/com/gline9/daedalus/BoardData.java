package com.gline9.daedalus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardData
{
    private final Dimensions dimensions;
    private final Map<Coordinates, Integer> coordinateMap = new HashMap<>();

    public BoardData(Dimensions dimensions)
    {
        this.dimensions = dimensions;
    }

    public BoardData(Dimensions dimensions, List<Integer> data)
    {
        this.dimensions = dimensions;
        for (int x = 0; x < dimensions.getWidth(); x++)
        {
            for (int y = 0; y < dimensions.getHeight(); y++)
            {
                this.coordinateMap.put(new Coordinates(x, y), data.get(x + y * dimensions.getWidth()));
            }
        }
    }

    public Dimensions getDimensions()
    {
        return dimensions;
    }

    public Integer getValue(int x, int y)
    {
        return coordinateMap.get(new Coordinates(x, y));
    }

    public void setValue(int x, int y, Integer value)
    {
        coordinateMap.put(new Coordinates(x, y), value);
    }

    private static record Coordinates(int x, int y) {}
}
