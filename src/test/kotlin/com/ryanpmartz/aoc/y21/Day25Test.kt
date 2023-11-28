package com.ryanpmartz.aoc.y21

import com.ryanpmartz.aoc.common.Coordinate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day25Test {

    private val EXAMPLE_BOARD = """
        v...>>.vv>
        .vv>>.vv..
        >>.>v>...v
        >>v>>.>.v.
        v>v.vv.v..
        >.>>..v...
        .vv..>.>v.
        v.v..>>v.v
        ....v..v.>
    """.trimIndent()


    @Test
    fun testExample() {
        val grid = Day25.parseGrid(EXAMPLE_BOARD.lines())

        assertEquals(8, grid.maxX)
        assertEquals(9, grid.maxY)
        assertEquals(Cucumber(Direction.SOUTH), grid.state[Coordinate(0, 0)])
        assertEquals(Cucumber(Direction.EAST), grid.state[Coordinate(8, 9)])
        assertEquals(58, grid.runUntilStop())
    }
}