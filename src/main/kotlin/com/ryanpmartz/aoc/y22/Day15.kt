package com.ryanpmartz.aoc.y22

import com.ryanpmartz.aoc.common.AocDayNumber
import com.ryanpmartz.aoc.common.AocYear
import com.ryanpmartz.aoc.common.Point2D
import com.ryanpmartz.aoc.common.io.InputReader
import com.ryanpmartz.aoc.common.parsing.ints
import kotlin.math.absoluteValue

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

    fun coveredCoordinatesOnLine(pairs: Collection<Pair>, yCoordinate: Int): Set<Int> {
        var coveredCoords = mutableSetOf<Int>()

        for (pair in pairs) {
            val sensor = pair.sensor
            val beacon = pair.beacon

            val distanceToLine = (sensor.y - yCoordinate).absoluteValue
            if (distanceToLine <= pair.coverageRange) {
                val horizontalRange = (pair.coverageRange - distanceToLine)
                val minX = sensor.x - horizontalRange
                val maxX = sensor.x + horizontalRange

                println("$sensor is $distanceToLine from y line with range of ${pair.coverageRange}: [$minX, $maxX]")

                for (x in minX..maxX) {
                    coveredCoords.add(x)
                }

            }

            if (beacon.y == yCoordinate) {
                coveredCoords.remove(beacon.x)
            }
        }

        return coveredCoords
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