package com.ryanpmartz.aoc.y23

import com.ryanpmartz.aoc.common.AocDayNumber
import com.ryanpmartz.aoc.common.AocYear
import com.ryanpmartz.aoc.common.io.InputReader
import java.lang.Integer.max

data class ScratchoffCard(val winningNumbers: Set<Int>, val cardNumbers: List<Int>) {

    fun calculateScore(): Int {
        var numMatches = 0
        for (cardNumber in cardNumbers) {
            if (winningNumbers.contains(cardNumber)) {
                numMatches = max(1, numMatches * 2)
            }
        }

        return numMatches
    }
}

object Day04 {

    private fun parseSpaceDelimitedNumbers(s: String): List<Int> {
        val textNumbers = s.trim().split(" ")



        return textNumbers.filter { it.isNotEmpty() }.map { it.toInt() }
    }

    private fun partOne(lines: List<String>) {
        val cards = mutableListOf<ScratchoffCard>()

        for (line in lines) {
            val numbers = line.substringAfter(":").trim()
            val components = numbers.split("|")

            val winningNumbers = parseSpaceDelimitedNumbers(components[0]).toSet()
            val cardNumbers = parseSpaceDelimitedNumbers(components[1])

            val card = ScratchoffCard(winningNumbers, cardNumbers)

            cards.add(card)

        }

        var total = 0
        for (card in cards) {
            total += card.calculateScore()
        }

        println(total)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val lines = InputReader.read(AocYear.TWENTY_THREE, AocDayNumber.FOUR)
        partOne(lines)
    }
}