package day5

import common.fileFromResources
import java.lang.IllegalArgumentException

fun getSeatId(seat: String): Int {
    if (seat.length != 10) throw IllegalArgumentException("seat has to have length 10")

    val binarySeat = seat.replace(Regex("[FL]"), "0")
        .replace(Regex("[BR]"), "1")

    return Integer.parseInt(binarySeat, 2)
}

fun getEmptySeat(seatIds: List<Int>): Int {
    val validSeatIds = seatIds.first()..seatIds.last()
    return validSeatIds.find { it !in seatIds } ?: throw IllegalArgumentException("No empty seat found in input")
}


fun main() {
    Day5.input?.let {
        val seatIds = it.readLines().map { seat ->
            getSeatId(seat)
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