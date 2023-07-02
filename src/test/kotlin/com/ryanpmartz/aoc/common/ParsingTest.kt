package com.ryanpmartz.aoc.common

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ParsingTest {

    @Test
    fun testParsingIntegersFromText() {
        val text = "Sensor at x=3658528, y=641189: closest beacon is at x=4097969, y=-110334"
        val actual = parseInts(text)
        val expected = listOf(3658528, 641189, 4097969, -110334)

        assertEquals(expected, actual)
    }
}