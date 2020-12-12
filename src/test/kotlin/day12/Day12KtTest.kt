package day12

import common.fileFromResources
import org.junit.Test
import kotlin.test.assertEquals

class Day12KtTest {

    private val input = listOf(
        "F10",
        "N3",
        "F7",
        "R90",
        "F11"
    )


    @Test
    fun `part1 works correctly`() {
        val result = part1(input)
        assertEquals(25, result)
    }

    @Test
    fun `part 2 works correctly`() {

    }

}