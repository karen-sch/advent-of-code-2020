package day5

import org.junit.Test
import kotlin.test.assertEquals

class Day5KtTest {

    @Test
    fun `returns correct seat coordinates and id`() {

        val seatId1 = getSeatId("BFFFBBFRRR")
        assertEquals(567, seatId1)


        val seatId2 = getSeatId("FFFBBBFRRR")
        assertEquals(119, seatId2)


        val seatId3 = getSeatId("BBFFBBFRLL")
        assertEquals(820, seatId3)
    }
}