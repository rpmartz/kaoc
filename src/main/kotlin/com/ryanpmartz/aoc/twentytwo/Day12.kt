package com.ryanpmartz.aoc.twentytwo

import com.ryanpmartz.aoc.common.AocDayNumber
import com.ryanpmartz.aoc.common.AocYear
import com.ryanpmartz.aoc.common.io.InputReader

data class Point(val x: Int, val y: Int) {

    fun cardinalNeighbors(): Set<Point> {
        return setOf(
            Point(x + 1, y),
            Point(x - 1, y),
            Point(x, y + 1),
            Point(x, y - 1)
        )
    }

    fun allNeighbors(): Set<Point> {
        val neighbors = mutableSetOf<Point>()
        val offsets = setOf(-1, 0, 1)
        for (i in offsets) {
            for (j in offsets) {
                if (i == 0 && j == 0) {
                    continue
                }
                neighbors.add(Point(x + i, y + j))
            }
        }

        return neighbors
    }
}

object Day12 {

    @JvmStatic
    fun main(args: Array<String>) {
        val lines = InputReader.read(AocYear.TWENTY_TWO, AocDayNumber.TWELVE)

        for (line in lines) {
            println(line)
        }

    }

    fun parseGrid(lines: List<String>) {

    }
}