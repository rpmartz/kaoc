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
}