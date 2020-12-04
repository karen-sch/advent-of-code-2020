package day4

import common.fileFromResources
import org.junit.Test
import kotlin.test.assertEquals

class Day4KtTest {

    val input by lazy {
        fileFromResources("day4/passports_test.txt")
    }

    val input2 by lazy {
        fileFromResources("day4/passports_test2.txt")
    }

    @Test
    fun `correctly scounts all valid passports`() {
        val result = countValidPassports(input!!)
        assertEquals(2, result)
    }

    @Test
    fun `correctly scounts all valid passports with strict rules`() {
        val result = countValidPassports(input2!!)
        assertEquals(4, result)
    }
}