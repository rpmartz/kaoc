package com.ryanpmartz.aoc.fifteen

import java.io.File
import java.nio.charset.Charset

object Day05 {

    val vowels: Set<Char> = setOf('a', 'e', 'i', 'o', 'u')
    val bannedPairs: Set<String> = setOf("ab", "cd", "pq", "xy")

    @JvmStatic
    fun main(args: Array<String>) {
        val instructions = File("src/main/resources/2015/day05.txt").readLines(Charset.defaultCharset())

        for (instruction in instructions) {
            println(isValid(instruction))
        }
    }
    
    fun isValid(line: String): Boolean {
        var numVowels = 0
        var hasPair = false

        for (i in 0 until line.length - 1) {
            val first = line.get(i)
            val second = line.get(i + 1)

            val asStr = "" + first.toString() + second.toString()
            if(asStr in bannedPairs) {
                return false
            }

            if(first in vowels) {
                numVowels++
            }
            if(second in vowels) {
                numVowels++
            }

            hasPair = first == second
        }

        return numVowels >= 3 && hasPair;
    }
}