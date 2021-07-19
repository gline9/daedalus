package com.gline9.daedalus

import spock.lang.Specification

class BoardSpec extends Specification
{
    void "should display 1x1 board"()
    {
        given:

        def board = new Board(singleDigitBoard(5))

        when:

        def display = board.display()

        then:

        display == """\
*---*
| 5 |
*---*
"""
    }

    void "should display horizontal board"()
    {
        given:

        def boardData = Stub(BoardData) {
            getDimensions() >> new Dimensions(2, 1)
            getValue(new Coordinates(0, 0)) >> 2
            getValue(new Coordinates(1, 0)) >> 3
        }

        def board = new Board(boardData)

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

        def boardData = Stub(BoardData) {
            getDimensions() >> new Dimensions(1, 2)
            getValue(new Coordinates(0, 0)) >> 2
            getValue(new Coordinates(0, 1)) >> 3
        }

        def board = new Board(boardData)

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

        def boardData = Stub(BoardData) {
            getDimensions() >> new Dimensions(2, 2)
            getValue(new Coordinates(0, 0)) >> 2
            getValue(new Coordinates(1, 0)) >> 3
            getValue(new Coordinates(0, 1)) >> 5
            getValue(new Coordinates(1, 1)) >> 7
        }

        def board = new Board(boardData)

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

        def board = new Board(singleDigitBoard(11))

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

        def board = new Board(singleDigitBoard(101))

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

        def board = new Board(singleDigitBoard(null))

        when:

        def display = board.display()

        then:

        display == """\
*---*
|   |
*---*
"""
    }


    void "should forward board value sets to board data"()
    {
        given:

        def boardData = Mock(BoardData)
        def board = new Board(boardData)

        when:

        board.setValue(2, 3, 5)

        then:

        1 * boardData.setValue(new Coordinates(2, 3), 5)
    }

    private singleDigitBoard(Integer value)
    {
        return Stub(BoardData) {
            getDimensions() >> new Dimensions(1, 1)
            getValue(_) >> value
        }
    }
}
