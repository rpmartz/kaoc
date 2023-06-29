package com.ryanpmartz.aoc.common

data class Point2D(val x: Int, val y: Int) {

    fun cardinalNeighbors(): Set<Point2D> {
        return setOf(
            Point2D(x + 1, y),
            Point2D(x - 1, y),
            Point2D(x, y + 1),
            Point2D(x, y - 1)
        )
    }

    fun allNeighbors(): Set<Point2D> {
        val neighbors = mutableSetOf<Point2D>()
        val offsets = setOf(-1, 0, 1)
        for (i in offsets) {
            for (j in offsets) {
                if (i == 0 && j == 0) {
                    continue
                }
                neighbors.add(Point2D(x + i, y + j))
            }
        }

        return neighbors
    }

    fun manhattanDistanceTo(p2: Point2D): Int {
        return kotlin.math.abs(this.x - p2.x) + kotlin.math.abs(this.y - p2.y)
    }

    fun moveRight(): Point2D {
        return copy(x = x + 1)
    }

    fun moveUp(): Point2D {
        return copy(y = y + 1)
    }

    fun moveLeft(): Point2D {
        return copy(x = x - 1)
    }

    fun moveDown(): Point2D {
        return copy(y = y - 1)
    }
}