package com.gline9.daedalus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board
{
    private final BoardData boardData;

    public Board(BoardData boardData)
    {
        this.boardData = boardData;
    }

    public void setValue(int x, int y, int value)
    {
        boardData.setValue(new Coordinates(x, y), value);
    }

    public String display()
    {
        return IntStream.range(0, boardData.getDimensions().getHeight())
                .mapToObj(this::buildRow)
                .collect(Collectors.joining(getRowDivider(), getRowDivider(), getRowDivider()));
    }

    private String buildRow(int rowNumber)
    {
        StringBuilder ret = new StringBuilder();

        ret.append(IntStream.range(0, boardData.getDimensions().getWidth())
                .mapToObj(i -> boardData.getValue(new Coordinates(i, rowNumber)))
                .map(this::getCellDisplay)
                .collect(Collectors.joining("|", "|", "|\n")));
        return ret.toString();
    }

    private String getCellDisplay(Integer value)
    {
        StringBuilder ret = new StringBuilder();
        String stringValue = Optional.ofNullable(value).map(String::valueOf).orElse(" ");
        if (stringValue.length() < 3)
        {
            ret.append(" ");
        }
        ret.append(stringValue);
        if (stringValue.length() < 2)
        {
            ret.append(" ");
        }

        return ret.toString();
    }

    private String getRowDivider()
    {
        return "*---".repeat(boardData.getDimensions().getWidth()) + "*\n";
    }
}
