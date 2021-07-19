package com.gline9.daedalus

import spock.lang.Specification

class BoardDataImplSpec extends Specification
{
    void "should allow entering in new values"()
    {
        given:

        def board = new BoardDataImpl(new Dimensions(3, 3))

        when:

        board.setValue(new Coordinates(1, 2), 3)

        then:

        board.getValue(new Coordinates(1, 2)) == 3
    }

    void "should not allow modification of values outside bounds of the board"()
    {
        given:

        def board = new BoardDataImpl(new Dimensions(3, 3))

        when:

        board.setValue(new Coordinates(x, y), 3)

        then:

        thrown(IndexOutOfBoundsException)

        where:

        x  | y
        -1 | 0
        0  | -1
        -1 | -1
        1  | 3
        3  | 1
        3  | 3
    }

    void "should create an empty board if no data provided"()
    {
        given:

        def emptyBoard = new BoardDataImpl(new Dimensions(2, 2))

        expect:

        emptyBoard.getValue(new Coordinates(0, 0)) == null
        emptyBoard.getValue(new Coordinates(0, 1)) == null
        emptyBoard.getValue(new Coordinates(1, 0)) == null
        emptyBoard.getValue(new Coordinates(1, 1)) == null
    }

    void "should not allow changing original squares"()
    {
        given:

        def board = new BoardDataImpl(new Dimensions(1, 1), [1])

        when:

        board.setValue(new Coordinates(0, 0), 3)

        then:

        1 == board.getValue(new Coordinates(0, 0))

    }

    void "should allow changing user set squares"()
    {
        given:

        def board = new BoardDataImpl(new Dimensions(1, 1), [null])

        when:

        board.setValue(new Coordinates(0, 0), 2)
        board.setValue(new Coordinates(0, 0), 3)

        then:

        board.getValue(new Coordinates(0, 0)) == 3
    }

    void "should allow unsetting values"()
    {
        given:

        def board = new BoardDataImpl(new Dimensions(1, 1))

        when:

        board.setValue(new Coordinates(0, 0), 2)
        board.setValue(new Coordinates(0, 0), null)

        then:

        board.getValue(new Coordinates(0, 0)) == null
    }
}
