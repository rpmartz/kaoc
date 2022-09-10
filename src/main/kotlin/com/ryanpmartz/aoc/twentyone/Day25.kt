package com.ryanpmartz.aoc.twentyone

import com.ryanpmartz.aoc.common.Coordinate


enum class Direction {
    EAST, SOUTH
}

data class Cucumber(val direction: Direction)

data class Move(val src: Coordinate, val dst: Coordinate)

class SeafloorGrid(val state: MutableMap<Coordinate, Cucumber>, val maxX: Int, val maxY: Int) {

    fun runUntilStop() {
        val moves = mutableListOf<Move>()

        // all eastern facing move if they can
        for (x in 0..maxX) {
            for (y in 0..maxY) {
                val currentPosition = Coordinate(x, y)

                val positionHasCucumber = currentPosition in state
                if (positionHasCucumber && state[currentPosition]!!.direction == Direction.EAST) {
                    val updatedPosition = nextEasternLocation(currentPosition)
                    moves.add(Move(currentPosition, updatedPosition))
                }

            }
        }

        // update grid with moves
        for (move in moves) {
            state[move.dst] = state[move.src]!!
            state.remove(move.src)
        }

        // all southern facing move if they can
    }

    private fun nextEasternLocation(coordinate: Coordinate): Coordinate {
        if (coordinate.y + 1 > maxY) {
            return Coordinate(coordinate.x, 0)
        }

        return Coordinate(coordinate.x, coordinate.y + 1)
    }


}

object Day25 {

    @JvmStatic
    fun main(args: Array<String>) {

    }


}