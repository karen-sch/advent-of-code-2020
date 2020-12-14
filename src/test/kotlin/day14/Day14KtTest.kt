package day14

import org.junit.Test
import kotlin.test.assertEquals

class Day14KtTest {

    @Test
    @ExperimentalUnsignedTypes
    fun `part1 works correctly`() {
        val mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"
        val memory = HashMap<ULong, ULong>()
        applyMaskPart1(8u, 11u, mask, memory)
        applyMaskPart1(7u, 101u, mask, memory)
        applyMaskPart1(8u, 0u, mask, memory)

        val result = memory.values.sum()
        assertEquals(165u, result)
    }

    @Test
    @ExperimentalUnsignedTypes
    fun `part 2 works correctly`() {
        val mask1 = "000000000000000000000000000000X1001X"
        val memory = HashMap<ULong, ULong>()
        applyMaskPart2(42u, 100u, mask1, memory)
        val mask2 = "00000000000000000000000000000000X0XX"
        applyMaskPart2(26u, 1u, mask2, memory)

        val result = memory.values.sum()
        assertEquals(208u, result)
    }
}