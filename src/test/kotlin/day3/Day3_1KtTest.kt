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
        val result = countEncounteredTrees(input!!)
        assertEquals(7, result)
    }
}