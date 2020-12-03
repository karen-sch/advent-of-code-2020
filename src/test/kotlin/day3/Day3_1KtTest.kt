package day3

import common.fileFromResources
import java.nio.charset.StandardCharsets
import kotlin.test.Test
import kotlin.test.assertEquals

class Day3_1KtTest {

    private val input by lazy {
        fileFromResources("day3/testtrees.txt")
            ?.readLines(StandardCharsets.US_ASCII)
    }

    @Test
    fun `correctly counts number of encountered trees`() {
        val result = countEncounteredTreesOnSlope(input!!)
        assertEquals(7, result)
    }

    @Test
    fun `correctly counts multiplied number of encountered trees in different slopes`() {
        val result = countEncounteredTreesDifferentSlopes(input!!)
        assertEquals(336, result)
    }
}