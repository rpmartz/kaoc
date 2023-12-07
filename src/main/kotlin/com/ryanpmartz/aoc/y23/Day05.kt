package com.ryanpmartz.aoc.y23

import com.ryanpmartz.aoc.common.AocDayNumber
import com.ryanpmartz.aoc.common.AocYear
import com.ryanpmartz.aoc.common.io.InputReader

data class SourceToDestinationSeries(val destinationStart: Long, val sourceStart: Long, val range: Long) {

    fun containsKey(key: Long): Boolean {
        return key >= sourceStart && key <= sourceStart + range - 1
    }
}

object Day05 {

    @JvmStatic
    fun main(args: Array<String>) {
        val lines = InputReader.read(AocYear.TWENTY_THREE, AocDayNumber.FIVE)

        val seedToSoilLines = lines.subList(3, 14)

        val seedToFertilizerLines = lines.subList(15, 25)

        val fertilizerToWaterLines = lines.subList(26, 69)

        val waterToLightLines = lines.subList(70, 115)

        val lightToTemperatureLines = lines.subList(116, 163)

        val temperatureToHumidityMap = lines.subList(164, 192)

        val humidityToLocationMap = lines.subList(193, 221)
        println(humidityToLocationMap)

    }


}