package com.ryanpmartz.aoc.y16

import com.ryanpmartz.aoc.common.AocDayNumber
import com.ryanpmartz.aoc.common.AocYear
import com.ryanpmartz.aoc.common.Point2D
import com.ryanpmartz.aoc.common.io.InputReader

enum class TurnDirection {
    RIGHT, LEFT
}

enum class CardinalDirection {
    NORTH, SOUTH, EAST, WEST;
}

data class Move(val direction: TurnDirection, val distance: Int)

fun parseMoves(line: String): List<Move> {
    val moves = line.split(", ")
    val parsedMoves = mutableListOf<Move>()

    for (move in moves) {
        val firstLetter = move.first().toString()
        val distance = move.substring(1).toInt()
        val direction = when (firstLetter) {
            "R" -> TurnDirection.RIGHT
            "L" -> TurnDirection.LEFT
            else -> throw RuntimeException("Unreachable")
        }

        parsedMoves.add(Move(direction, distance))
    }

    return parsedMoves

}

fun determineNewDirection(currentlyFacing: CardinalDirection, turnDirection: TurnDirection): CardinalDirection {
    // todo probably easiest to treat this as a 90 degree heading and use simple math
    when (turnDirection) {
        TurnDirection.RIGHT -> {
            return when (currentlyFacing) {
                CardinalDirection.NORTH -> CardinalDirection.EAST
                CardinalDirection.SOUTH -> CardinalDirection.WEST
                CardinalDirection.EAST -> CardinalDirection.SOUTH
                CardinalDirection.WEST -> CardinalDirection.NORTH
            }
        }

        TurnDirection.LEFT -> {
            return when (currentlyFacing) {
                CardinalDirection.NORTH -> CardinalDirection.WEST
                CardinalDirection.SOUTH -> CardinalDirection.EAST
                CardinalDirection.EAST -> CardinalDirection.NORTH
                CardinalDirection.WEST -> CardinalDirection.SOUTH
            }
        }

        else -> {
            throw RuntimeException("")
        }
    }
}

fun move(currentLocation: Point2D, direction: CardinalDirection, distance: Int): Point2D {
    return when (direction) {
        CardinalDirection.NORTH -> Point2D(currentLocation.x, currentLocation.y + distance)
        CardinalDirection.EAST -> Point2D(currentLocation.x + distance, currentLocation.y)
        CardinalDirection.SOUTH -> Point2D(currentLocation.x, currentLocation.y - distance)
        CardinalDirection.WEST -> Point2D(currentLocation.x - distance, currentLocation.y)
    }
}

object Day01 {

    @JvmStatic
    fun main(args: Array<String>) {
        val input = InputReader.readAll(AocYear.SIXTEEN, AocDayNumber.ONE)
        val moves = parseMoves(input)

        val visited = mutableSetOf<Point2D>()
        var location = Point2D(0, 0)
        var facing = CardinalDirection.NORTH

        for (move in moves) {
            val newHeading = determineNewDirection(facing, move.direction)
            facing = newHeading

            val destination = move(location, facing, move.distance)

            val pointsBetween = location.straightLineTo(destination)
            for (point in pointsBetween) {
                if (point != location && visited.contains(point)) {
                    println("Revisited $point. Distance: ${point.manhattanDistanceTo(Point2D(0, 0))}")
                }

                visited.add(point)
            }

            location = destination
        }

        println("Ended at $location")
        println("Distance: ${location.manhattanDistanceTo(Point2D(0, 0))}")
    }
}