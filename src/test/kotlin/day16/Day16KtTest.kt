package day16

import common.fileFromResources
import org.junit.Test
import kotlin.test.assertEquals

class Day16KtTest {

    private val input by lazy {
        fileFromResources("day16/test_tickets.txt")?.readLines()
    }

    @Test
    fun `part1 works correctly`() {
        val result = findScanningErrorsPart1(input!!)
        assertEquals(71, result)
    }
}