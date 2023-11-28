package com.ryanpmartz.aoc.y22

import com.ryanpmartz.aoc.common.Coordinate
import com.ryanpmartz.aoc.y22.Day09.buildMoveList
import com.ryanpmartz.aoc.y22.Day09.runSimulation
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day09Test {

    private val sample = """
    R 4
    U 4
    L 3
    D 1
    R 4
    D 1
    L 5
    R 2
    """.trimIndent()


    @Test
    fun testSample() {
        val rope = Rope(listOf(Coordinate(0, 0), Coordinate(0, 0)), mutableSetOf())
        val moves = buildMoveList(sample.lines())

        val result = runSimulation(moves, rope)

        assertEquals(13, result.visitedCoords.size)
    }
}