package com.ryanpmartz.aoc.y23

import com.ryanpmartz.aoc.common.AocDayNumber
import com.ryanpmartz.aoc.common.AocYear
import com.ryanpmartz.aoc.common.io.InputReader
import java.util.concurrent.atomic.AtomicLong

data class SourceToDestinationSeries(val destinationStart: Long, val sourceStart: Long, val range: Long) {

    fun containsKey(key: Long): Boolean {
        return key >= sourceStart && key <= sourceStart + range - 1
    }

    fun getValue(key: Long): Long {
        val offset = key - sourceStart
        return destinationStart + offset
    }
}

data class ElementMap(val allSeries: List<SourceToDestinationSeries>) {

    fun get(key: Long): Long {
        for (series in allSeries) {
            if (series.containsKey(key)) {
                return series.getValue(key)
            }
        }

        return key
    }
}

data class SeedRange(val start: Long, val range: Long) {

    fun end(): Long {
        return start + range
    }
}

object Day05 {

    @JvmStatic
    fun main(args: Array<String>) {
        val lines = InputReader.read(AocYear.TWENTY_THREE, AocDayNumber.FIVE)

        val seedToSoilMap = ElementMap(parseSeries(lines.subList(3, 14)))
        val soilToFertilizerMap = ElementMap(parseSeries(lines.subList(15, 25)))
        val fertilizerToWaterMap = ElementMap(parseSeries(lines.subList(26, 69)))
        val waterToLightMap = ElementMap(parseSeries(lines.subList(70, 115)))
        val lightToTemperatureMap = ElementMap(parseSeries(lines.subList(116, 163)))
        val temperatureToHumidityMap = ElementMap(parseSeries(lines.subList(164, 192)))
        val humidityToLocationMap = ElementMap(parseSeries(lines.subList(193, 221)))

        val seedRanges = listOf(
            SeedRange(2019933646L, 2719986L),
            SeedRange(2982244904L, 337763798L),
            SeedRange(445440L, 255553492L),
            SeedRange(1676917594L, 196488200L),
            SeedRange(3863266382L, 36104375L),
            SeedRange(1385433279L, 178385087L),
            SeedRange(2169075746L, 171590090L),
            SeedRange(572674563L, 5944769L),
            SeedRange(835041333L, 194256900L),
            SeedRange(664827176L, 42427020),
        )

        val minSeen = AtomicLong(Long.MAX_VALUE)

        seedRanges.parallelStream().forEach { range ->
            for (seed in range.start until range.end()) {
                val soil = seedToSoilMap.get(seed)
                val fertilizer = soilToFertilizerMap.get(soil)
                val water = fertilizerToWaterMap.get(fertilizer)
                val light = waterToLightMap.get(water)
                val temperature = lightToTemperatureMap.get(light)
                val humidity = temperatureToHumidityMap.get(temperature)
                val location = humidityToLocationMap.get(humidity)

                if (location < minSeen.get()) {
                    minSeen.getAndSet(location)
                }
            }
        }

        println(minSeen.get())

    }

    fun parseSeries(lines: List<String>): List<SourceToDestinationSeries> {
        val series = mutableListOf<SourceToDestinationSeries>()
        for (line in lines) {
            if (line.isEmpty()) {
                continue
            }
            val components = line.split(" ")
            series.add(
                SourceToDestinationSeries(
                    components[0].toLong(),
                    components[1].toLong(),
                    components[2].toLong()
                )
            )
        }

        return series
    }


}