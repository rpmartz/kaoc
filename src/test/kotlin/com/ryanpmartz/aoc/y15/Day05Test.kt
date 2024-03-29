package com.ryanpmartz.aoc.y15

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Day05Test {

    @Test
    fun testNiceExamples() {
        assertTrue(Day05.isValidOne("aaa"))
        assertTrue(Day05.isValidOne("ugknbfddgicrmopn"))
        assertTrue(Day05.isValidOne("ugknbfddgicrmopn"))
    }

    @Test
    fun testNaughtyExamples() {
        assertFalse(Day05.isValidOne("jchzalrnumimnmhp"))
        assertFalse(Day05.isValidOne("haegwjzuvuyypxyu"))
        assertFalse(Day05.isValidOne("dvszwmarrgswjxmb"))

    }

    @Test
    fun testPartTwoNiceExamples() {
        assertTrue(Day05.isValidTwo("qjhvhtzxzqqjkmpb"))
        assertTrue(Day05.isValidTwo("xxyxx"))
    }

    @Test
    fun testPartTwoNaughtyExamples() {
        assertFalse(Day05.isValidTwo("uurcxstgmygtbstg"))
        assertFalse(Day05.isValidTwo("ieodomkazucvgmuy"))
    }
}