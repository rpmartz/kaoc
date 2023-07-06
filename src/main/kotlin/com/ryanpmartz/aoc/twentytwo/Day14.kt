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

    private val SAND_ORIGIN_POINT = Point2D(500, 0)

    private val GRID = mutableMapOf<Point2D, GridContents>()

    private var MAX_DEPTH = 0
    private var NUM_SETTLED_SAND = 0

    @JvmStatic
    fun main(args: Array<String>) {
        setUpGrid()
        var canFitMore = true
        while (canFitMore) {
            canFitMore = dropSand()
        }

        println("Sand has started falling into the abyss. Total grains fit: $NUM_SETTLED_SAND")
    }

    private fun dropSand(): Boolean {
        var currentLocation = SAND_ORIGIN_POINT
        while (true) {
            val straightDown = Point2D(currentLocation.x, currentLocation.y + 1)
            val diagonalLeft = straightDown.moveLeft()
            val diagonalRight = straightDown.moveRight()

            if (canMoveTo(straightDown)) {
                println("Moving from $currentLocation to $straightDown")
                currentLocation = straightDown
                if (currentLocation.y > MAX_DEPTH) {
                    println("$currentLocation exceeds MAX_DEPTH: $MAX_DEPTH")
                    return false
                }

            } else if (canMoveTo(diagonalLeft)) {
                print("Moving from $currentLocation to $diagonalLeft")
                currentLocation = diagonalLeft
            } else if (canMoveTo(diagonalRight)) {
                print("Moving from $currentLocation to $diagonalRight")
                currentLocation = diagonalRight
            } else {
                GRID[currentLocation] = GridContents.SAND
                NUM_SETTLED_SAND += 1
                print("Sand settled at $currentLocation. Total grains settled: $NUM_SETTLED_SAND")
                return true
            }
        }

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

                // need way to figure out when sand falls into the abyss
                MAX_DEPTH = maxOf(MAX_DEPTH, start.y, end.y)

                val pointsBetween = start.pointsBetween(end)
                pointsBetween.forEach {
                    if (it.x == 500) {
                        println("Rock at $it")
                    }
                    GRID[it] = GridContents.ROCK
                }
            }

        }
    }

    private fun canMoveTo(point: Point2D): Boolean {
        return GRID[point] != GridContents.SAND && GRID[point] != GridContents.ROCK
    }

}