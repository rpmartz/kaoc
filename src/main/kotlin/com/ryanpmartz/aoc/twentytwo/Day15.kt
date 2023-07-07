package com.ryanpmartz.aoc.twentytwo

import com.ryanpmartz.aoc.common.AocDayNumber
import com.ryanpmartz.aoc.common.AocYear
import com.ryanpmartz.aoc.common.Point2D
import com.ryanpmartz.aoc.common.io.InputReader
import com.ryanpmartz.aoc.common.parsing.ints

data class Pair(val sensor: Point2D, val beacon: Point2D) {

    val coverageRange =  sensor.manhattanDistanceTo(beacon)
}

object Day15 {

    private const val Y = 200000

    @JvmStatic
    fun main(args: Array<String>) {
        val lines = InputReader.read(AocYear.TWENTY_TWO, AocDayNumber.FIFTEEN)
        val pairs = parsePairsFromLines(lines)

        val ranges = mutableSetOf<IntRange>()

        for(pair in pairs) {
            val s = pair.sensor
            val distanceToLine = s.manhattanDistanceTo(Point2D(s.x, Y))
            if(distanceToLine <= pair.coverageRange) {
                val horizontalRange = (pair.coverageRange - distanceToLine)
                val minX = s.x - horizontalRange
                val maxX = s.x + horizontalRange

                ranges.add(IntRange(minX, maxX))
            }
        }


        val coveredPoints = mutableSetOf<Int>()
        for (range in ranges) {
            for (i in range) {
                coveredPoints.add(i)
            }
        }

        println(coveredPoints.size)

    }

    fun parsePairsFromLines(lines: List<String>): Set<Pair> {
        val pairs = mutableSetOf<Pair>()
        for (line in lines) {
            val coords = ints(line)
            val sensor = Point2D(coords[0], coords[1])
            val beacon = Point2D(coords[2], coords[3])

            pairs.add(Pair(sensor, beacon))
        }

        return pairs
    }


}