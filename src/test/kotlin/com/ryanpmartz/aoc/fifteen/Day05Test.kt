package com.ryanpmartz.aoc.fifteen

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day05Test {

    @Test
    fun testNiceExamples() {
        assertTrue(Day05.isValid("aaa"))
        assertTrue(Day05.isValid("ugknbfddgicrmopn"))
    }

    @Test
    fun testNaughtyExamples() {
        assertFalse(Day05.isValid("jchzalrnumimnmhp"))
        assertFalse(Day05.isValid("haegwjzuvuyypxyu"))
        assertFalse(Day05.isValid("dvszwmarrgswjxmb"))

    }
}