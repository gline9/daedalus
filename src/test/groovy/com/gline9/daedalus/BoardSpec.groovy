package com.gline9.daedalus

import spock.lang.Specification

class BoardSpec extends Specification
{
    void "should display 1x1 board"()
    {
        given:

        def board = new Board(new Dimensions(1, 1), [1])

        when:

        def display = board.display()

        then:

        display == """\
*---*
| 1 |
*---*
"""
    }

    void "should display board given any number"()
    {
        given:

        def board = new Board(new Dimensions(1, 1), [2])

        when:

        def display = board.display()

        then:

        display == """\
*---*
| 2 |
*---*
"""
    }

    void "should display horizontal board"()
    {
        given:

        def board = new Board(new Dimensions(2, 1), [2,3])

        when:

        def display = board.display()

        then:

        display == """\
*---*---*
| 2 | 3 |
*---*---*
"""
    }

    void "should display vertical board"()
    {
        given:

        def board = new Board(new Dimensions(1, 2), [2,3])

        when:

        def display = board.display()

        then:

        display == """\
*---*
| 2 |
*---*
| 3 |
*---*
"""
    }

    void "should display square board"()
    {
        given:

        def board = new Board(new Dimensions(2, 2), [2,3,5,7])

        when:

        def display = board.display()

        then:

        display == """\
*---*---*
| 2 | 3 |
*---*---*
| 5 | 7 |
*---*---*
"""
    }

    void "should display 2 digit numbers offset"()
    {
        given:

        def board = new Board(new Dimensions(1, 1), [11])

        when:

        def display = board.display()

        then:

        display == """\
*---*
| 11|
*---*
"""
    }

    void "should display 3 digit numbers with no spaces"()
    {
        given:

        def board = new Board(new Dimensions(1, 1), [101])

        when:

        def display = board.display()

        then:

        display == """\
*---*
|101|
*---*
"""
    }

    void "should display null as an empty cell"()
    {
        given:

        def board = new Board(new Dimensions(1, 1), [null])

        when:

        def display = board.display()

        then:

        display == """\
*---*
|   |
*---*
"""
    }

    void "should allow entering in new values"()
    {
        given:

        def board = new Board(new Dimensions(3, 3), [null, null, null, null, null, null, null, null, null])

        when:

        board.setValue(1, 2, 3)
        def display = board.display()

        then:

        display == """\
*---*---*---*
|   |   |   |
*---*---*---*
|   |   |   |
*---*---*---*
|   | 3 |   |
*---*---*---*
"""
    }

    void "should create an empty board if no data provided"()
    {
        given:

        def emptyBoard = new Board(new Dimensions(2, 2))

        when:

        def display = emptyBoard.display()

        then:

        display == """\
*---*---*
|   |   |
*---*---*
|   |   |
*---*---*
"""
    }

    void "should not allow modification of values outside bounds of the board"()
    {
        given:

        def board = new Board(new Dimensions(3, 3))

        when:

        board.setValue(x, y, 3)

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
}
