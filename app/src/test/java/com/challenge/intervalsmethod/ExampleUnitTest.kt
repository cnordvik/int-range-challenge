package com.challenge.intervalsmethod

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val utilRanges = UtilRanges()
        var inclusiveRange: MutableSet<IntRange> = mutableSetOf(5..13, 2..8) // define any range here
        var exclusiveRange: MutableSet<IntRange> = mutableSetOf(4..10, 5..11) // define any range here
        var validRanges = utilRanges.getValidRanges(inclusiveRange, exclusiveRange)
        validRanges.forEach { println("${it.first}-${it.last}") }
        assertEquals(2, validRanges.size)
        assertEquals(2, validRanges.first().first)
        assertEquals(3, validRanges.first().last)
        assertEquals(12, validRanges.last().first)
        assertEquals(13, validRanges.last().last)

        inclusiveRange = mutableSetOf(5..20, 2..40) // define any range here
        exclusiveRange = mutableSetOf(1..10) // define any range here
        validRanges = utilRanges.getValidRanges(inclusiveRange, exclusiveRange)
        validRanges.forEach { println("${it.first}-${it.last}") }
        assertEquals(1, validRanges.size)
        assertEquals(11, validRanges.first().first)
        assertEquals(40, validRanges.first().last)

    }
}