package com.ryanpmartz.aoc.y23

import com.ryanpmartz.aoc.common.AocDayNumber
import com.ryanpmartz.aoc.common.AocYear
import com.ryanpmartz.aoc.common.io.InputReader
import java.lang.Integer.max
import java.util.*

data class ScratchoffCard(val cardId: Int, val winningNumbers: Set<Int>, val cardNumbers: List<Int>) {

    fun calculateScore(): Int {
        var score = 0
        for (cardNumber in cardNumbers) {
            if (winningNumbers.contains(cardNumber)) {
                score = max(1, score * 2)
            }
        }

        return score
    }

    fun numMatches(): Int {
        var numMatches = 0
        for (cardNumber in cardNumbers) {
            if (winningNumbers.contains(cardNumber)) {
                numMatches++
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

    private fun parseLine(l: String): ScratchoffCard {
        val cardId = l.substringAfter("Card ").substringBefore(":")
        val numbers = l.substringAfter(":").trim()
        val components = numbers.split("|")

        val winningNumbers = parseSpaceDelimitedNumbers(components[0]).toSet()
        val cardNumbers = parseSpaceDelimitedNumbers(components[1])

        return ScratchoffCard(cardId.trim().toInt(), winningNumbers, cardNumbers)
    }

    private fun partOne(lines: List<String>) {
        val cards = lines.map { parseLine(it) }

        var total = 0
        for (card in cards) {
            total += card.calculateScore()
        }

        println(total)
    }

    private fun partTwo(lines: List<String>) {
        val cards = lines.map { parseLine(it) }

        val memo = mutableMapOf<Int, Int>()

        val cardMap = mutableMapOf<Int, ScratchoffCard>()

        var totalCards = 0L
        for (card in cards) {
            memo[card.cardId] = card.numMatches()
            cardMap[card.cardId] = card
        }

        val cardStack = ArrayDeque(cards)

        while (cardStack.any()) {
            totalCards += 1L
            val card = cardStack.pop()
            val numMatches = memo[card.cardId]
            for (x in 1..numMatches!!) {
                val wonCardId = card.cardId + x

                cardStack.push(cardMap[wonCardId]!!)
            }
        }

        println(totalCards)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val lines = InputReader.read(AocYear.TWENTY_THREE, AocDayNumber.FOUR)
        partOne(lines)
        partTwo(lines)
    }
}