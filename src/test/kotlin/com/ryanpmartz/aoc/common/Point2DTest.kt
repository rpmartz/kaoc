package com.ryanpmartz.aoc.common

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

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

    @Test
    fun testPointsBetweenIncreasingX() {
        val p1 = Point2D(0, 0)
        val p2 = Point2D(3, 0)

        val expected = listOf(
            Point2D(0, 0),
            Point2D(1, 0),
            Point2D(2, 0),
            Point2D(3, 0)
        )

        val actual = p1.straightLineTo(p2)
        assertEquals(expected, actual)

    }

    @Test
    fun testPointsBetweenDecreasingX() {
        val p1 = Point2D(3, 0)
        val p2 = Point2D(0, 0)

        val expected = listOf(
            Point2D(3, 0),
            Point2D(2, 0),
            Point2D(1, 0),
            Point2D(0, 0),
        )

        val actual = p1.straightLineTo(p2)
        assertEquals(expected, actual)

    }

    @Test
    fun testPointsBetweenIncreasingY() {
        val p1 = Point2D(0, 0)
        val p2 = Point2D(0, 3)

        val expected = listOf(
            Point2D(0, 0),
            Point2D(0, 1),
            Point2D(0, 2),
            Point2D(0, 3)
        )

        val actual = p1.straightLineTo(p2)
        assertEquals(expected, actual)

    }

    @Test
    fun testPointsBetweenDecreasingY() {
        val p1 = Point2D(0, 3)
        val p2 = Point2D(0, 0)

        val expected = listOf(
            Point2D(0, 3),
            Point2D(0, 2),
            Point2D(0, 1),
            Point2D(0, 0),
        )

        val actual = p1.straightLineTo(p2)
        assertEquals(expected, actual)

    }
}