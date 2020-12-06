package day6

import org.junit.Test
import kotlin.test.assertEquals

class Day6KtTest {

    private val input =
        """
            abc

            a
            b
            c

            ab
            ac

            a
            a
            a
            a

            b
        """.trimIndent()

    @Test
    fun `parsing works correctly`() {
        val sum = parseGroupsGetAnswersByAnyone(input)

        assertEquals(11, sum)
    }

    @Test
    fun `part 2 works correctly`() {
        val sum = parseGroupsGetAnswersByEveryone(input)
        
        assertEquals(6, sum)
    }

}