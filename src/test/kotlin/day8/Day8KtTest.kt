package day8

import common.fileFromResources
import org.junit.Test
import kotlin.test.assertEquals

class Day8KtTest {

    private val input by lazy {
        fileFromResources("day8/test_instructions.txt")
    }

    @Test
    fun `part1 works correctly`() {
        val result = executeInstructionsPart1(input!!.readLines())
        assertEquals(5, result)
    }

    @Test
    fun `part 2 works correctly`() {
        val result = executeInstructionsPart2(input!!.readLines())
        assertEquals(8, result)
    }

}