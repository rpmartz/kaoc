package com.ryanpmartz.aoc.y23

import com.ryanpmartz.aoc.common.AocDayNumber
import com.ryanpmartz.aoc.common.AocYear
import com.ryanpmartz.aoc.common.Point2D
import com.ryanpmartz.aoc.common.io.InputReader

data class NumberOnBoard(val startIndex: Point2D, val textValue: StringBuffer) {

    fun intValue(): Int {
        return Integer.valueOf(textValue.toString())
    }

    // (0, 0)
    fun points(): Set<Point2D> {
        val ps = mutableSetOf<Point2D>()

        for (i in 0 until textValue.length) {
            ps.add(Point2D(this.startIndex.x, this.startIndex.y + i))
        }

        return ps
    }

    fun neighbors(): Set<Point2D> {
        val neighbors = mutableSetOf<Point2D>()
        for (point in this.points()) {
            neighbors.addAll(point.allNeighbors())
        }

        return neighbors
    }

}


object Day03 {

    @JvmStatic
    fun main(args: Array<String>) {
        val lines = InputReader.read(AocYear.TWENTY_THREE, AocDayNumber.THREE)

        val symbolLocations = mutableSetOf<Point2D>()
        val numbers = mutableSetOf<NumberOnBoard>()

        var previousChar: Char? = null
        var numberStringBuffer: StringBuffer = StringBuffer()
        var currentNumStartPoint: Point2D? = null

        lines.forEachIndexed { x, line ->

            if (currentNumStartPoint != null) {
                val number = NumberOnBoard(currentNumStartPoint!!, numberStringBuffer)
                numbers.add(number)

                currentNumStartPoint = null
            }
            // there is no previous character for a new line
            previousChar = null

            line.forEachIndexed { y, character ->

                if (character != '.' && !character.isDigit()) {
                    if (character == '*') {
                        symbolLocations.add(Point2D(x, y))
                    }

                    if (currentNumStartPoint != null) {
                        val number = NumberOnBoard(currentNumStartPoint!!, numberStringBuffer)
                        numbers.add(number)

                        currentNumStartPoint = null
                    }
                } else if (character.isDigit() && (previousChar == null || !previousChar?.isDigit()!!)) {
                    numberStringBuffer = StringBuffer()
                    numberStringBuffer.append(character.toString())

                    currentNumStartPoint = Point2D(x, y)
                } else if (character.isDigit() && previousChar != null && previousChar?.isDigit()!!) {
                    numberStringBuffer.append(character.toString())
                } else if (!character.isDigit() && (previousChar != null && previousChar?.isDigit()!!)) {
                    val number = NumberOnBoard(currentNumStartPoint!!, numberStringBuffer)
                    numbers.add(number)

                    currentNumStartPoint = null
                }

                previousChar = character

            }

        }

        var total = 0

        for (number in numbers) {
            var numAdjacentSymbols = 0

            val neighbors = number.neighbors()

            for (symbolLocation in symbolLocations) {
                if (neighbors.contains(symbolLocation)) {
                    numAdjacentSymbols += 1
                    break
                }
            }


        }


        val symbolMap = mutableMapOf<Point2D, MutableList<NumberOnBoard>>()

        for (symbolLocation in symbolLocations) {
            for (number in numbers) {
                if (symbolLocation in number.neighbors()) {
                    val currentNumbers = symbolMap.getOrDefault(symbolLocation, ArrayList())
                    currentNumbers.add(number)

                    symbolMap.put(symbolLocation, currentNumbers)
                }
            }

        }

        for (entry in symbolMap.entries) {
            val nums = entry.value
            if (nums.size == 2) {
                total += nums[0].intValue() * nums[1].intValue()
            }
        }

        println(total)
    }
}