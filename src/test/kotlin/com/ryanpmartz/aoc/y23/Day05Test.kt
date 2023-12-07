package com.ryanpmartz.aoc.y23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Day05Test {

    @Test
    fun `test checking for key contained in series`() {
        val series = SourceToDestinationSeries(50, 98, 2)
        assertTrue(series.containsKey(98))
        assertTrue(series.containsKey(99))

    }

    @Test
    fun `test checking for key not in series`() {
        val series = SourceToDestinationSeries(50, 98, 2)
        assertFalse(series.containsKey(100))
    }

    @Test
    fun `test getting key with no offset`() {
        val series = SourceToDestinationSeries(50, 98, 2)
        assertEquals(50, series.getValue(98))
    }

    @Test
    fun `test getting key with offset`() {
        val series = SourceToDestinationSeries(50, 98, 2)
        assertEquals(51, series.getValue(99))
    }
}