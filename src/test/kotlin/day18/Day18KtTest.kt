package day18

import org.junit.Test
import kotlin.test.assertEquals

class Day18KtTest {

    @Test
    fun `part1 works correctly`() {
        val string = "2 * 3 + (4 * 5)"
        val result = parsePart1(string)
        assertEquals(26, result)

        val string2 = "5 + (8 * 3 + 9 + 3 * 4 * 3)"
        val result2 = parsePart1(string2)
        assertEquals(437, result2)

        val string3 = "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"
        val result3 = parsePart1(string3)
        assertEquals(12240, result3)

        val string4 = "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"
        val result4 = parsePart1(string4)
        assertEquals(13632, result4)
    }

    @Test
    fun `part2 works correctly`() {
        val string = "2 * 3 + (4 * 5)"
        val result = parsePart2(string)
        assertEquals(46, result)

        val string2 = "5 + (8 * 3 + 9 + 3 * 4 * 3)"
        val result2 = parsePart2(string2)
        assertEquals(1445, result2)

        val string3 = "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"
        val result3 = parsePart2(string3)
        assertEquals(669060, result3)

        val string4 = "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"
        val result4 = parsePart2(string4)
        assertEquals(23340, result4)
    }
}