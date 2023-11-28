package com.ryanpmartz.aoc.y22

import com.ryanpmartz.aoc.y22.Day15.coveredCoordinatesOnLine
import com.ryanpmartz.aoc.y22.Day15.parsePairsFromLines
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day15Test {

    private val lines = listOf(
        "Sensor at x=2, y=18: closest beacon is at x=-2, y=15",
        "Sensor at x=9, y=16: closest beacon is at x=10, y=16",
        "Sensor at x=13, y=2: closest beacon is at x=15, y=3",
        "Sensor at x=12, y=14: closest beacon is at x=10, y=16",
        "Sensor at x=10, y=20: closest beacon is at x=10, y=16",
        "Sensor at x=14, y=17: closest beacon is at x=10, y=16",
        "Sensor at x=8, y=7: closest beacon is at x=2, y=10",
        "Sensor at x=2, y=0: closest beacon is at x=2, y=10",
        "Sensor at x=0, y=11: closest beacon is at x=2, y=10",
        "Sensor at x=20, y=14: closest beacon is at x=25, y=17",
        "Sensor at x=17, y=20: closest beacon is at x=21, y=22",
        "Sensor at x=16, y=7: closest beacon is at x=15, y=3",
        "Sensor at x=14, y=3: closest beacon is at x=15, y=3",
        "Sensor at x=20, y=1: closest beacon is at x=15, y=3"
    )


    @Test
    fun `test parsing input`() {
        val pairs = parsePairsFromLines(lines)
        assertEquals(pairs.size, 14)

        val pair = pairs.filter {
            (it.sensor.x == 14) && (it.sensor.y == 3)
        }[0]

        assertEquals(pair.coverageRange, 1)

    }

    @Test
    fun `test line coverage`() {
        val pairs = parsePairsFromLines(lines)
        val coveredCoords = coveredCoordinatesOnLine(pairs, 10)

        val expected = mutableSetOf<Int>()
        for (i in -2..24) {
            if (!coveredCoords.contains(i)) {
                println("Expected $i to be covered but it is not in `coveredCoords`")
            }

            expected.add(i)
        }

        for (i in coveredCoords) {
            if (!expected.contains(i)) {
                println("$i is in `coveredCoords` and is not expected")
            }
        }

        assertEquals(26, coveredCoords.sorted().size)
    }
}