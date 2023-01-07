package com.ryanpmartz.aoc.twentytwo

import com.ryanpmartz.aoc.common.AocDayNumber
import com.ryanpmartz.aoc.common.AocYear
import com.ryanpmartz.aoc.common.Point2D
import com.ryanpmartz.aoc.common.io.InputReader
import com.ryanpmartz.aoc.common.parseInts

data class Sensor(val location: Point2D, val closestBeacon: Point2D) {

    val distanceToClosestBeacon = location.manhattanDistanceTo(closestBeacon)
}

object Day15 {

    @JvmStatic
    fun main(args: Array<String>) {
        val lines = InputReader.read(AocYear.TWENTY_TWO, AocDayNumber.FIFTEEN)

        val sensors = mutableListOf<Sensor>()
        for (line in lines) {
            val coords = parseInts(line)
            sensors.add(Sensor(Point2D(coords[0], coords[1]), Point2D(coords[2], coords[3])))
        }

        // need to find rightmost point on row where y = 200000
        var rightmostXCoordinate = 0

        for (sensor in sensors) {
            val distanceToRow = kotlin.math.abs(sensor.location.y - 200000)
            val rightMostIntercept = sensor.location.x + (sensor.location.x - distanceToRow)
            if (rightMostIntercept > rightmostXCoordinate) {
                println("${sensor.location} with radius ${sensor.distanceToClosestBeacon} has rightMostIntercept with y = 200000 at $rightMostIntercept")
                rightmostXCoordinate = rightMostIntercept
            }
        }

        var uncoveredPoints = 0
        for (i in 0..rightmostXCoordinate) {
            val point = Point2D(i, 200000)
            for (sensor in sensors) {
                val distanceToSensor = point.manhattanDistanceTo(sensor.location)
                if (distanceToSensor <= sensor.distanceToClosestBeacon) {
                    break
                }
            }

            uncoveredPoints++
        }

        println(uncoveredPoints)


    }
}