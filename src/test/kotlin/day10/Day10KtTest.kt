package day10

import common.fileFromResources
import org.junit.Test
import kotlin.test.assertEquals

class Day10KtTest {

    private val input by lazy {
        fileFromResources("day10/test_jolts.txt")?.readLines()?.map(String::toInt)!!
    }

    @Test
    fun `part1 works correctly`() {
        val result = get1JoltDiffTimes3JoltDiffs(input)
        assertEquals(220, result)
    }

    @Test
    fun `part 2 works correctly`() {
        val result = countDifferentWays(input)
        assertEquals(19208, result)
    }

}