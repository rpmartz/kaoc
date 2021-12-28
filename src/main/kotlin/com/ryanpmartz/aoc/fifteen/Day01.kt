package com.ryanpmartz.aoc.fifteen

import java.io.File
import java.nio.charset.Charset

object Day01 {

    @JvmStatic
    fun main(args: Array<String>) {
        val instructions = File("src/main/resources/day01.txt").readText(Charset.defaultCharset())

        var floor = 0
        var enteredBasement = false
        var count = 0
        for (c in instructions.asSequence()) {
            count += 1
            if (c == ')') {
                floor -= 1
            }
            else {
                floor += 1
            }

            if(!enteredBasement && floor < 0) {
                enteredBasement = true
                println("Entered basement at step ${count}")
            }
        }

        println(floor)

    }
}