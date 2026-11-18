package com.tunombretuapellido.statistics.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/** Tests de la lógica de los contadores. */
class StatisticsTest {

    @Test
    fun decrementNeverGoesBelowZero() {
        assertEquals(0, Statistics.decrement(0))
        assertEquals(4, Statistics.decrement(5))
    }

    @Test
    fun incrementAddsOne() {
        assertEquals(1, Statistics.increment(0))
    }

    @Test
    fun percentageWithZeroTotalIsZero() {
        assertEquals(0.0, Statistics.percentage(0, 0), 0.0001)
    }

    @Test
    fun percentagesAddUpToOneHundred() {
        val counts = listOf(3, 1, 4, 2)
        val total = counts.sum()
        assertEquals(100.0, counts.sumOf { Statistics.percentage(it, total) }, 0.0001)
        assertEquals(30.0, Statistics.percentage(3, total), 0.0001)
    }

    @Test
    fun randomCountIsInRange() {
        repeat(1000) {
            assertTrue(Statistics.randomCount() in 0..Statistics.MAX_RANDOM)
        }
    }
}
