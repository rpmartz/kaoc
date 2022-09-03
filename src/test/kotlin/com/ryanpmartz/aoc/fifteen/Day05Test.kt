package com.ryanpmartz.aoc.fifteen

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day05Test {

    @Test
    fun testNiceExamples() {
        assertTrue(Day05.isValidOne("aaa"))
        assertTrue(Day05.isValidOne("ugknbfddgicrmopn"))
    }

    @Test
    fun testNaughtyExamples() {
        assertFalse(Day05.isValidOne("jchzalrnumimnmhp"))
        assertFalse(Day05.isValidOne("haegwjzuvuyypxyu"))
        assertFalse(Day05.isValidOne("dvszwmarrgswjxmb"))

    }
}