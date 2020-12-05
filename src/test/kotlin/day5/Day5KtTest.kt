package day5

import org.junit.Test
import kotlin.test.assertEquals

class Day5KtTest {

    @Test
    fun `returns correct seat coordinates and id`() {
        val (row1, column1) = getRowAndColumn("BFFFBBFRRR")
        assertEquals(70 to 7, row1 to column1)
        val seatId1 = getSeatId(row1, column1)
        assertEquals(567, seatId1)

        val (row2, column2) = getRowAndColumn("FFFBBBFRRR")
        assertEquals(14 to 7, row2 to column2)
        val seatId2 = getSeatId(row2, column2)
        assertEquals(119, seatId2)

        val (row3, column3) = getRowAndColumn("BBFFBBFRLL")
        assertEquals(102 to 4, row3 to column3)
        val seatId3 = getSeatId(row3, column3)
        assertEquals(820, seatId3)
    }
}