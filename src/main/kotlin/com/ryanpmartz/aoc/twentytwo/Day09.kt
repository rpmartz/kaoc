package com.ryanpmartz.aoc.twentytwo

import com.ryanpmartz.aoc.common.AocDayNumber
import com.ryanpmartz.aoc.common.AocYear
import com.ryanpmartz.aoc.common.Coordinate
import com.ryanpmartz.aoc.common.io.InputReader
import kotlin.math.absoluteValue

data class Move(val direction: String, val numSteps: Int)
data class Rope(val knots: List<Coordinate>, val visitedCoords: Set<Coordinate>)

object Day09 {

    @JvmStatic
    fun main(args: Array<String>) {
        val lines = InputReader.read(AocYear.TWENTY_TWO, AocDayNumber.NINE)
        val moves = buildMoveList(lines)

        var rope = Rope(
            listOf(
                Coordinate(0, 0),
                Coordinate(0, 0),
                Coordinate(0, 0),
                Coordinate(0, 0),
                Coordinate(0, 0),
                Coordinate(0, 0),
                Coordinate(0, 0),
                Coordinate(0, 0),
                Coordinate(0, 0),
                Coordinate(0, 0),
                Coordinate(0, 0),
                Coordinate(0, 0),
                Coordinate(0, 0),
                Coordinate(0, 0),
            ), setOf(Coordinate(0, 0))
        )


        for (move in moves) {
            for (i in move.numSteps downTo 0) {
                when (move.direction) {
                    "U" -> rope = moveUp(rope)
                    "D" -> rope = moveDown(rope)
                    "L" -> rope = moveLeft(rope)
                    "R" -> rope = moveRight(rope)
                }
            }
        }

        println(rope.visitedCoords.size)

    }

    fun moveUp(rope: Rope): Rope {
        val head = rope.knots.first()
        val rest = rope.knots.drop(1)

        val newHead = Coordinate(head.x, head.y + 1)
        val newList = mutableListOf(newHead)

        updateRestOfList(newHead, rest, newList)

        val visited = hashSetOf<Coordinate>()
        visited.addAll(rope.visitedCoords)
        visited.add(newList.last())

        return Rope(newList, visited)
    }

    fun moveDown(rope: Rope): Rope {
        val head = rope.knots.first()
        val rest = rope.knots.drop(1)

        val newHead = Coordinate(head.x, head.y - 1)
        val newList = mutableListOf(newHead)

        updateRestOfList(newHead, rest, newList)

        val visited = hashSetOf<Coordinate>()
        visited.addAll(rope.visitedCoords)
        visited.add(newList.last())

        return Rope(newList, visited)
    }

    fun moveRight(rope: Rope): Rope {
        val head = rope.knots.first()
        val rest = rope.knots.drop(1)

        val newHead = Coordinate(head.x + 1, head.y)
        val newList = mutableListOf(newHead)

        updateRestOfList(newHead, rest, newList)

        val visited = hashSetOf<Coordinate>()
        visited.addAll(rope.visitedCoords)
        visited.add(newList.last())

        return Rope(newList, visited)
    }

    fun isAdjacent(p1: Coordinate, p2: Coordinate): Boolean {
        val xDiff = (p2.x - p1.x).absoluteValue
        val yDiff = (p2.y - p1.y).absoluteValue

        return (xDiff <= 1 && yDiff <= 1)
    }

    fun updateRestOfList(newHeadCoord: Coordinate, rope: List<Coordinate>, newList: MutableList<Coordinate>) {
        var lastKnotsNewPosition = newHeadCoord
        for (coord in rope) {
            if (isAdjacent(coord, lastKnotsNewPosition)) {
                newList.add(coord)
                lastKnotsNewPosition = coord
            } else {
                newList.add(lastKnotsNewPosition)
            }
        }

    }

    fun moveLeft(rope: Rope): Rope {
        val head = rope.knots.first()
        val rest = rope.knots.drop(1)

        val newHead = Coordinate(head.x + 1, head.y)
        val newList = mutableListOf(newHead)

        updateRestOfList(newHead, rest, newList)

        val visited = hashSetOf<Coordinate>()
        visited.addAll(rope.visitedCoords)
        visited.add(newList.last())

        return Rope(newList, visited)
    }

    fun buildMoveList(lines: List<String>): List<Move> {
        val moves = mutableListOf<Move>()

        for (line in lines) {
            val splitRes = line.split(" ")
            val direction = splitRes[0]
            val numSteps = splitRes[1].toInt()
            moves.add(Move(direction, numSteps))
        }

        return moves
    }
}