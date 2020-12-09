package day9

import common.fileFromResources
import org.junit.Test
import kotlin.test.assertEquals

class Day9KtTest {

    private val input by lazy {
        fileFromResources("day9/test_numbers.txt")?.readLines()?.map(String::toLong)!!
    }

    @Test
    fun `part1 works correctly`() {
        val result = findFirstNonSumNumber(input, 5)
        assertEquals(127, result)
    }

    @Test
    fun `part 2 works correctly`() {

        assertEquals(true, true)
    }

}