package com.ryanpmartz.aoc.y23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CamelCardHandTest {

    @Test
    fun `test 5 of a kind`() {
        val hand = CamelCardHand("AAAAA", 1)
        assertEquals(FIVE_OF_A_KIND, hand.getScore())
    }

    @Test
    fun `test 4 of a kind`() {
        val hand = CamelCardHand("AAAAB", 1)
        assertEquals(FOUR_OF_A_KIND, hand.getScore())
    }

    @Test
    fun `test 3 of a kind`() {
        val hand = CamelCardHand("ABACA", 1)
        assertEquals(THREE_OF_A_KIND, hand.getScore())
    }

    @Test
    fun `test full house`() {
        val hand = CamelCardHand("999KK", 1)
        assertEquals(FULL_HOUSE, hand.getScore())
    }

    @Test
    fun `test two pair`() {
        val hand = CamelCardHand("ABCAB", 1)
        assertEquals(TWO_PAIR, hand.getScore())
    }

    @Test
    fun `test one pair`() {
        val hand = CamelCardHand("ABCDA", 1)
        assertEquals(ONE_PAIR, hand.getScore())
    }
}