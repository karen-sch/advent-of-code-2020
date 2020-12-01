package day1

import org.junit.Test
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class Day1_2KtTest {

    @Test
    fun `find2020SumOf3AndMultiply finds sum`(){
        val entries = listOf(1721, 979, 366, 299, 675, 1456)
        val result = find2020SumOf3AndMultiply(entries)
        assertEquals(241861950, result)
    }

    @Test
    fun `find2020SumOf3AndMultiply throws exception without valid sum`(){
        val entries = listOf(1721, 366, 299, 675, 1456)
        assertFailsWith(IllegalArgumentException::class) {
            val result = find2020SumOf3AndMultiply(entries)
            print(result)
        }
    }

}