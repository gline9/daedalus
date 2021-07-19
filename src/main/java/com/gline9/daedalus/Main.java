package com.gline9.daedalus;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main
{
    public static void main(String[] args)
    {
        Random rand = new Random();
        BoardData sudoku = new BoardDataImpl(new Dimensions(9, 9), IntStream.range(0, 81).map((__) -> rand.nextInt(9) + 1 ).mapToObj(Integer::valueOf).collect(Collectors.toList()));
        Board sudokuBoard = new Board(sudoku);
        System.out.println(sudokuBoard.display());
    }
}
