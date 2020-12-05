package day5

import common.fileFromResources
import java.lang.IllegalArgumentException

fun getRowAndColumn(seat: String): Pair<Int, Int> {
    if (seat.length != 10) throw IllegalArgumentException("seat has to have length 10")

    var rowRange = 0..127
    var columnRange = 0..7

    seat.forEachIndexed { i, c ->
        if (i < 7) {
            // row
            val middle = (rowRange.first + rowRange.last) / 2
            when (c) {
                'F' -> {
                    rowRange = rowRange.first..middle
                }
                'B' -> {
                    rowRange = (middle + 1)..rowRange.last
                }
            }

        } else {
            // column
            val middle = (columnRange.first + columnRange.last) / 2
            when (c) {
                'L' -> {
                    columnRange = columnRange.first..middle
                }
                'R' -> {
                    columnRange = (middle + 1)..columnRange.last
                }
            }

        }
    }

    return rowRange.first to columnRange.first
}

fun getSeatId(row: Int, column: Int): Int {
    return row * 8 + column
}

fun getEmptySeat(seatIds: List<Int>): Int {
    val validSeatIds = seatIds.first()..seatIds.last()
    return validSeatIds.find { it !in seatIds } ?: throw IllegalArgumentException("No empty seat found in input")
}


fun main() {
    Day5.input?.let {
        val seatIds = it.readLines().map { seat ->
            getRowAndColumn(seat)
        }.map { (row, column) ->
            getSeatId(row, column)
        }.sorted()
        val highestSeatId = seatIds.last()
        val emptySeatId = getEmptySeat(seatIds)

        println("Part 1, highest seat id: $highestSeatId")
        println("Part 2, empty seat with id: $emptySeatId")
    }
}

object Day5 {
    val input by lazy {
        fileFromResources("day5/seats.txt")
    }
}