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
}