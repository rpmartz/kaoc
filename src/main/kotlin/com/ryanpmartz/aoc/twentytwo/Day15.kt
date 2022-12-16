package com.ryanpmartz.aoc.twentytwo

import com.ryanpmartz.aoc.common.AocDayNumber
import com.ryanpmartz.aoc.common.AocYear
import com.ryanpmartz.aoc.common.Coordinate
import com.ryanpmartz.aoc.common.io.InputReader
import com.ryanpmartz.aoc.common.parsing.ints

typealias Sensor = Coordinate
typealias Beacon = Coordinate
object Day15 {

    @JvmStatic
    fun main(args: Array<String>) {
        val transfomer: (line: String) -> Pair<Sensor, Beacon> = {
            val coordinates = ints(it)
            val sensor = Sensor(coordinates[0], coordinates[1])
            val beacon = Beacon(coordinates[2], coordinates[3])

            Pair(sensor, beacon)
        }

        val lines = InputReader.read(AocYear.TWENTY_TWO, AocDayNumber.FIFTEEN, transfomer)
        lines.forEach { println(it) }




    }
}