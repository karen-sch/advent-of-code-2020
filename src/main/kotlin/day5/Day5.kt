package day5

import common.fileFromResources
import java.io.File
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


fun main() {
    Day5.input?.let {
        val highestSeatId = it.readLines().map { seat ->
            getRowAndColumn(seat)
        }.map { (row, column) ->
            getSeatId(row, column)
        }.maxOrNull()

        println("Part 1, highest seat id: $highestSeatId")
    }
}

object Day5 {
    val input by lazy {
        fileFromResources("day5/seats.txt")
    }
}