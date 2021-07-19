package com.gline9.daedalus;

import java.util.*;

public class BoardDataImpl implements BoardData
{
    private final Dimensions dimensions;
    private final Map<Coordinates, Integer> coordinateMap = new HashMap<>();
    private final Set<Coordinates> originalCoordinates = new HashSet<>();

    public BoardDataImpl(Dimensions dimensions)
    {
        this.dimensions = dimensions;
    }

    public BoardDataImpl(Dimensions dimensions, List<Integer> data)
    {
        this.dimensions = dimensions;
        for (int x = 0; x < dimensions.getWidth(); x++)
        {
            for (int y = 0; y < dimensions.getHeight(); y++)
            {
                this.coordinateMap.put(new Coordinates(x, y), data.get(x + y * dimensions.getWidth()));
                if (null != this.coordinateMap.get(new Coordinates(x, y)))
                {
                    this.originalCoordinates.add(new Coordinates(x, y));
                }
            }
        }
    }

    @Override
    public Dimensions getDimensions()
    {
        return dimensions;
    }

    @Override
    public Integer getValue(Coordinates coordinates)
    {
        return coordinateMap.get(coordinates);
    }

    @Override
    public void setValue(Coordinates coordinates, Integer value)
    {
        if (coordinates.x() < 0 || coordinates.x() >= dimensions.getWidth() || coordinates.y() < 0 || coordinates.y() >= dimensions.getHeight())
        {
            throw new IndexOutOfBoundsException(
                    String.format("Coordinate (%d, %d) is outside bounds of board size (%d, %d)", coordinates.x(), coordinates.y(), dimensions.getWidth(), dimensions.getHeight()));
        }

        if (originalCoordinates.contains(coordinates))
        {
            return;
        }

        coordinateMap.put(coordinates, value);
    }

}
