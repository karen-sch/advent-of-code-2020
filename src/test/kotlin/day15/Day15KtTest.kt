package day15

import org.junit.Test
import kotlin.test.assertEquals

class Day15KtTest {

    @Test
    fun `part1 works correctly`() {
        val result1 = findNthSpokenNumber(listOf(0, 3, 6), 10)
        assertEquals(0, result1)

        val result2 = findNthSpokenNumber(listOf(0, 3, 6), 2020)
        assertEquals(436, result2)

        val result3 = findNthSpokenNumber(listOf(1, 3, 2), 2020)
        assertEquals(1, result3)

        val result4 = findNthSpokenNumber(listOf(2, 1, 3), 2020)
        assertEquals(10, result4)

        val result5 = findNthSpokenNumber(listOf(1, 2, 3), 2020)
        assertEquals(27, result5)
    }

    @Test
    fun `part2 works correctly`() {
        val result2 = findNthSpokenNumber(listOf(0, 3, 6), 30000000)
        assertEquals(175594, result2)

        val result3 = findNthSpokenNumber(listOf(1, 3, 2), 30000000)
        assertEquals(2578, result3)

        val result4 = findNthSpokenNumber(listOf(2, 1, 3), 30000000)
        assertEquals(3544142, result4)

        val result5 = findNthSpokenNumber(listOf(1, 2, 3), 30000000)
        assertEquals(261214, result5)
    }


}