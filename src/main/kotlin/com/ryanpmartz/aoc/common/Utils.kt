package com.ryanpmartz.aoc.common

import kotlin.math.abs

fun manhattanDistance(c1: Coordinate, c2: Coordinate): Int {
    return abs(c1.x - c2.x) + abs(c1.y - c2.y)
}