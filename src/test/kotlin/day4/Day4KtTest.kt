package day4

import common.fileFromResources
import org.junit.Test
import kotlin.test.assertEquals

class Day4KtTest {

    private val input by lazy {
        fileFromResources("day4/passports_test.txt")
    }

    private val input2 by lazy {
        fileFromResources("day4/passports_test2.txt")
    }

    @Test
    fun `correctly counts all valid passports`() {
        val result = countValidPassports(input!!)
        assertEquals(2, result)
    }

    @Test
    fun `correctly counts all valid passports with strict rules`() {
        val result = countValidPassportsStrict(input2!!)
        assertEquals(4, result)
    }
}