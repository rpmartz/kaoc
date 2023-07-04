package com.ryanpmartz.aoc.twentytwo

import com.ryanpmartz.aoc.common.AocDayNumber
import com.ryanpmartz.aoc.common.AocYear
import com.ryanpmartz.aoc.common.Point2D
import com.ryanpmartz.aoc.common.io.InputReader
import com.ryanpmartz.aoc.common.parsePoint


object Day14 {

    enum class GridContents(val textRepresentation: String) {

        ROCK("#"),
        SAND("o"),
        OPEN(".")
    }


    private val grid = mutableMapOf<Point2D, GridContents>()

    @JvmStatic
    fun main(args: Array<String>) {
        setUpGrid()

    }

    private fun setUpGrid() {
        val lines = InputReader.read(AocYear.TWENTY_TWO, AocDayNumber.FOURTEEN)

        for (line in lines) {
            val coords = line.split("->")
            val points = coords
                .map { parsePoint(it.trim()) }

            val segments = points.zipWithNext()
            for (segment in segments) {
                val start = segment.first
                val end = segment.second

                val pointsBetween = start.pointsBetween(end)
                pointsBetween.forEach {
                    grid[it] = GridContents.ROCK
                }
            }

        }
    }


}