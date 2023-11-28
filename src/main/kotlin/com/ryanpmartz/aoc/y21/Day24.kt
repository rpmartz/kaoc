package com.ryanpmartz.aoc.y21

import com.ryanpmartz.aoc.common.AocDayNumber
import com.ryanpmartz.aoc.common.AocYear
import com.ryanpmartz.aoc.common.io.InputReader

object Day24 {

    @JvmStatic
    fun main(args: Array<String>) {
        val lines = InputReader.read(AocYear.TWENTY_ONE, AocDayNumber.TWENTY_FOUR)
        for (line in lines) {
            println(line)
        }
    }
}