package com.ryanpmartz.aoc.y23

import com.ryanpmartz.aoc.common.AocDayNumber
import com.ryanpmartz.aoc.common.AocYear
import com.ryanpmartz.aoc.common.io.InputReader

const val FIVE_OF_A_KIND = 20
const val FOUR_OF_A_KIND = 19
const val FULL_HOUSE = 18
const val THREE_OF_A_KIND = 17
const val TWO_PAIR = 16
const val ONE_PAIR = 15

private val cardValueMap = mapOf(
    '2' to 2,
    '3' to 3,
    '4' to 4,
    '5' to 5,
    '6' to 6,
    '7' to 7,
    '8' to 8,
    '9' to 9,
    'T' to 10,
    'J' to 11,
    'Q' to 12,
    'K' to 13,
    'A' to 14

)

data class CamelCardHand(val cards: String, val bid: Int) {

    val cardCounts = cards.groupingBy { it }.eachCount()

    fun getFirstCardScore(): Int {
        return cardValueMap[cards[0]]!!
    }

    fun secondCardScore(): Int {
        return cardValueMap[cards[1]]!!
    }

    fun thirdCardScore(): Int {
        return cardValueMap[cards[2]]!!
    }

    fun fourthCardScore(): Int {
        return cardValueMap[cards[3]]!!
    }

    fun fifthCardScore(): Int {
        return cardValueMap[cards[4]]!!
    }

    fun getScore(): Int {
        if (cardCounts.keys.size == 1) {
            return FIVE_OF_A_KIND
        } else if (cardCounts.keys.size == 2) {
            if (cardCounts.values.any { it == 4 }) {
                return FOUR_OF_A_KIND
            }

            return FULL_HOUSE
        } else if (cardCounts.keys.size == 3) {
            if (cardCounts.values.any { it == 3 }) {
                return THREE_OF_A_KIND
            }

            val frequencyCounts = cardCounts.values.groupingBy { it }.eachCount()
            if (frequencyCounts[2] == 2) {
                return TWO_PAIR
            }

            throw IllegalArgumentException("Cannot score $cards")

        } else if (cardCounts.values.any { it == 2 }) {
            return ONE_PAIR
        } else {
            // return high card
            return cards.map { cardValueMap[it]!! }.max()
        }

    }

}

object Day07 {

    @JvmStatic
    fun main(args: Array<String>) {
        val lines = InputReader.read(AocYear.TWENTY_THREE, AocDayNumber.SEVEN)

        var hands = mutableListOf<CamelCardHand>()
        for (line in lines) {
            val parts = line.split(" ")
            hands.add(CamelCardHand(parts[0], parts[1].toInt()))
        }

        val sorted = hands.sortedWith(
            compareBy(CamelCardHand::getScore)
                .then(compareBy(CamelCardHand::getFirstCardScore))
                .then(compareBy(CamelCardHand::secondCardScore))
                .then(compareBy(CamelCardHand::thirdCardScore))
                .then(compareBy(CamelCardHand::fourthCardScore))
                .then(compareBy(CamelCardHand::fifthCardScore))
        )

        var sum = 0
        for (i in sorted.indices) {
            val hand = sorted[i]
            println(hand.cards)
            sum += hand.bid * (i + 1)
        }

        println(sum)
        // 249855720 too high
    }
}