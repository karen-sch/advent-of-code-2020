package day1

import org.junit.Test
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class Day1_1KtTest {

    @Test
    fun `find2020SumAndMultiply finds sum`(){
        val entries = listOf(1020, 3, 56, 99, 1000, 456, 999, 1001, 1020, 12345767)
        val result = find2020SumAndMultiply(entries)
        assertEquals(1020000, result)
    }

    @Test
    fun `find2020SumAndMultiply throws exception without valid sum`(){
        val entries = listOf(1020, 3, 56, 99, 456, 999, 1001, 1020, 12345767)
        assertFailsWith(IllegalArgumentException::class) {
            find2020SumAndMultiply(entries)
        }
    }

}