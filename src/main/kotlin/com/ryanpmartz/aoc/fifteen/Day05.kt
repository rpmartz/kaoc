package com.ryanpmartz.aoc.fifteen

import java.io.File
import java.nio.charset.Charset


class Trigram(val letters: String) {

    init {
        if (letters.length != 3) {
            throw RuntimeException("$letters is not exactly 3 letters")
        }
    }

    fun leftBigram(): List<String> {
        return listOf(letters[0].toString(), letters[1].toString())
    }

    fun rightBigram(): List<String> {
        return listOf(letters[1].toString(), letters[2].toString())
    }

    fun satisfiesRepetitionRequirement(): Boolean {
        val firstLetter = letters[0].toString()
        val secondLetter = letters[1].toString()
        val thirdLetter = letters[2].toString()

        return (firstLetter == thirdLetter)
    }


}

object Day05 {

    private val vowels: Set<Char> = setOf('a', 'e', 'i', 'o', 'u')
    private val bannedPairs: Set<String> = setOf("ab", "cd", "pq", "xy")

    @JvmStatic
    fun main(args: Array<String>) {
        val instructions = File("src/main/resources/2015/day05.txt").readLines(Charset.defaultCharset())

        println(isValidTwo("line"))
    }

    fun isValidTwo(line: String): Boolean {
        var previousTrigram: String? = null

        return false
    }

    fun isValidOne(line: String): Boolean {
        var numVowels = 0
        var hasPair = false

        for (i in 0 until line.length - 1) {
            val first = line.get(i)
            val second = line.get(i + 1)

            val asStr = first.toString() + second.toString()
            if (asStr in bannedPairs) {
                return false
            }

            if(first in vowels) {
                numVowels++
            }

            if(!hasPair) {
                hasPair = first == second
            }

        }

        if(line.last() in vowels) {
            numVowels++
        }

        return numVowels >= 3 && hasPair
    }
}