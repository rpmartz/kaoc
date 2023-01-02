package com.ryanpmartz.aoc.common

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class Point2DTest {

    @Test
    fun testGettingCardinalNeighbors() {
        val p = Point2D(5, 4)
        val actual = p.cardinalNeighbors()
        val expected = setOf(Point2D(4, 4), Point2D(6, 4), Point2D(5, 5), Point2D(5, 3))

        assertEquals(expected, actual)
    }

    @Test
    fun testManhattanDistance() {
        val p1 = Point2D(7, 9)
        val p2 = Point2D(3, 4)

        val expected = 9
        val actual = p1.manhattanDistanceTo(p2)

        assertEquals(expected, actual)
    }
}