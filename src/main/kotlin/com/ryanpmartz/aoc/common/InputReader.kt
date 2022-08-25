package com.ryanpmartz.aoc.common

import java.io.File

object InputReader {

    private const val PATH_PREFIX = "src/main/resources"

    fun read(year: AocYear, day: AocDayNumber): List<String> {
        return File("$PATH_PREFIX/${year.value}/day${day.value}.txt").readLines()
    }
}