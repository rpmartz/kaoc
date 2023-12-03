package com.ryanpmartz.aoc.y23

import com.ryanpmartz.aoc.common.AocDayNumber
import com.ryanpmartz.aoc.common.AocYear
import com.ryanpmartz.aoc.common.Point2D
import com.ryanpmartz.aoc.common.io.InputReader

data class NumberOnBoard(val startIndex: Point2D, val length: Int, val textValue: String) {

}


object Day03 {

    @JvmStatic
    fun main(args: Array<String>) {
        val lines = InputReader.read(AocYear.TWENTY_THREE, AocDayNumber.THREE)

        val board = mutableMapOf<Point2D, String>()

        val symbolLocations = mutableSetOf<Point2D>()

        lines.forEachIndexed { x, line ->
            line.forEachIndexed { y, character ->
                val isEmptySpace = character == '.'
                if (!isEmptySpace) {
                    val currentPoint = Point2D(x, y)
                    if (character.isDigit()) {
                        // todo parse number
                        // case 1 - just started number
                        // case 2 - continuing number
                        // case 3 - finished parsing number
                    } else {
                        // symbol
                        println("Added $character to symbols")
                        symbolLocations.add(currentPoint)
                    }


                }

            }

        }
    }
}