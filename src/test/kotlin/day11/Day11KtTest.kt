package day11

import common.fileFromResources
import org.junit.Test
import kotlin.test.assertEquals

class Day11KtTest {

    private val input by lazy {
        fileFromResources("day11/test_seats.txt")?.readLines()
    }

    @Test
    fun `part1 works correctly`() {
        val result = countOccupiedSeats(input!!)
        assertEquals(37, result)
    }

    @Test
    fun `part 2 works correctly`() {
        val result = countOccupiedSeats2(input!!)
        assertEquals(26, result)
    }

}