package com.ryanpmartz.aoc.twentyone

import com.ryanpmartz.aoc.common.AocDayNumber
import com.ryanpmartz.aoc.common.AocYear
import com.ryanpmartz.aoc.common.Coordinate
import com.ryanpmartz.aoc.common.io.InputReader


enum class Direction {
    EAST, SOUTH
}

data class Cucumber(val direction: Direction)

data class Move(val src: Coordinate, val dst: Coordinate)

class SeafloorGrid(val state: MutableMap<Coordinate, Cucumber>, val maxX: Int, val maxY: Int) {

    fun runUntilStop(): Int {
        var numSteps = 0
        var atLeastOneMoved = true
        while (atLeastOneMoved) {

            if (numSteps % 100 == 0) {
                println("$numSteps completed")
            }

            val eastMoves = runStep(Direction.EAST, ::nextEasternLocation)
            val southMoves = runStep(Direction.SOUTH, ::nextSouthernLocation)

            numSteps++
            atLeastOneMoved = (eastMoves + southMoves) > 0
        }

        return numSteps
    }

    fun runStep(stepPhase: Direction, nextCoordinateFinder: (Coordinate) -> Coordinate): Int {
        val moves = mutableListOf<Move>()

        // all eastern facing move if they can
        for (x in 0..maxX) {
            for (y in 0..maxY) {
                val currentPosition = Coordinate(x, y)

                val positionHasCucumber = currentPosition in state
                if (positionHasCucumber && state[currentPosition]!!.direction == stepPhase) {
                    val updatedPosition = nextCoordinateFinder(currentPosition)
                    moves.add(Move(currentPosition, updatedPosition))
                }

            }
        }

        // update grid with moves
        for (move in moves) {
            state[move.dst] = state[move.src]!!
            state.remove(move.src)
        }

        return moves.size
    }

    private fun nextEasternLocation(coordinate: Coordinate): Coordinate {
        if (coordinate.y + 1 > maxY) {
            return Coordinate(coordinate.x, 0)
        }

        return Coordinate(coordinate.x, coordinate.y + 1)
    }

    private fun nextSouthernLocation(coordinate: Coordinate): Coordinate {
        if (coordinate.x + 1 > maxX) {
            return Coordinate(0, coordinate.y)
        }

        return Coordinate(coordinate.x + 1, coordinate.y)
    }
}

object Day25 {

    private val EAST_FACING_CUCUMBER = Cucumber(Direction.EAST)
    private val SOUTH_FACING_CUCUMBER = Cucumber(Direction.SOUTH)

    @JvmStatic
    fun main(args: Array<String>) {
        val lines = InputReader.read(AocYear.TWENTY_ONE, AocDayNumber.TWENTY_FIVE)
        val grid = parseGrid(lines)

        val movesUntilSpace = grid.runUntilStop()
        println(movesUntilSpace)
    }

    fun parseGrid(lines: List<String>): SeafloorGrid {
        val initialState = mutableMapOf<Coordinate, Cucumber>()

        var maxX = 0
        var maxY = 0

        lines.forEachIndexed { x, line ->
            line.forEachIndexed { y, c ->
                val asStr = c.toString()
                maxY = y
                if (asStr == ">") {
                    initialState[Coordinate(x, y)] = EAST_FACING_CUCUMBER
                } else if (asStr == "v") {
                    initialState[Coordinate(x, y)] = SOUTH_FACING_CUCUMBER
                } else if (asStr != ".") {
                    throw RuntimeException("Expected one of [., >, v] but got $asStr")
                }
            }

            maxX = x
        }

        return SeafloorGrid(initialState, maxX, maxY)
    }


}