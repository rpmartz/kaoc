package com.ryanpmartz.aoc.common.io

import com.ryanpmartz.aoc.common.AocDayNumber
import com.ryanpmartz.aoc.common.AocYear
import java.io.File
import java.nio.charset.Charset

object InputReader {

    private const val PATH_PREFIX = "src/main/resources"
    private val IDENTITY: (line: String) -> String = { s: String -> s }

    fun read(year: AocYear, day: AocDayNumber): List<String> {
        return read(year, day, IDENTITY)
    }

    fun readAll(year: AocYear, day: AocDayNumber): String {
        return File("$PATH_PREFIX/${year.value}/day${day.value}.txt").readText(Charset.defaultCharset())
    }

    fun <T> read(year: AocYear, day: AocDayNumber, transformFn: (line: String) -> T): List<T> {
        val lines = File("$PATH_PREFIX/${year.value}/day${day.value}.txt").readLines()
        return lines.map { line -> transformFn(line) }
    }
}