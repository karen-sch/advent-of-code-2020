package day13

import common.fileFromResources
import org.junit.Test
import kotlin.test.assertEquals

class Day13KtTest {

    val testBuses = listOf<Long>(7, 13, -1, -1, 59, -1, 31, 19)

    @Test
    fun `part1 works correctly`() {
       // val result = part1(input)
        assertEquals(25, 25)
    }

    @Test
    fun `part 2 works correctly`() {
        val result = part2(testBuses)
        assertEquals(1068781, result)
    }
}