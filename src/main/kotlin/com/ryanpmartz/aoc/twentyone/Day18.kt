package com.ryanpmartz.aoc.twentyone

import com.ryanpmartz.aoc.common.AocDayNumber
import com.ryanpmartz.aoc.common.AocYear
import com.ryanpmartz.aoc.common.ThreeDPoint
import com.ryanpmartz.aoc.common.io.InputReader

// TODO reimplement this in a language that has structural pattern matching for comparison
// initial thought is to model this as a binary tree where each leaf is Either<Int, List<Int>>


object Day18 {

    @JvmStatic
    fun main(args: Array<String>) {

        val transfomer: (line: String) -> ThreeDPoint = {
            val components: List<String> = it.split("x")
            ThreeDPoint(components[0].toInt(), components[1].toInt(), components[2].toInt())
        }

        val lines = InputReader.read(AocYear.FIFTEEN, AocDayNumber.TWO, transfomer)
        lines.forEach { println(it) }
    }
}