package com.ryanpmartz.aoc.y15

import java.io.File
import java.nio.charset.Charset


object Day05 {

    private val vowels: Set<Char> = setOf('a', 'e', 'i', 'o', 'u')
    private val bannedPairs: Set<String> = setOf("ab", "cd", "pq", "xy")

    @JvmStatic
    fun main(args: Array<String>) {
        val instructions = File("src/main/resources/2015/day05.txt").readLines(Charset.defaultCharset())

        val count = instructions.stream()
            .filter { isValidTwo(it) }
            .count()

        println(count)
    }

    fun isValidTwo(line: String): Boolean {
        var bigrams = mutableSetOf<String>()

        var previousBigram: String? = null

        var hasRepeatingBigram = false
        var hasValidTrigram = false

        for (i in 1 until line.length) {
            var bigram = "" + line[i - 1] + line[i]
            var trigram: String?
            if (i >= 2) {
                trigram = line[i - 2] + bigram
                if (validTrigram(trigram)) {
                    hasValidTrigram = true
                }
            }

            if (bigrams.contains(bigram) && bigram != previousBigram) {
                hasRepeatingBigram = true
            } else {
                bigrams.add(bigram)
            }

            previousBigram = bigram
        }

        return hasValidTrigram && hasRepeatingBigram
    }

    private fun validTrigram(trigram: String): Boolean {
        return trigram[0] == trigram[2]
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

            if (first in vowels) {
                numVowels++
            }

            if (!hasPair) {
                hasPair = first == second
            }

        }

        if (line.last() in vowels) {
            numVowels++
        }

        return numVowels >= 3 && hasPair
    }
}