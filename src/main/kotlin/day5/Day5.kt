package day5

import common.fileFromResources
import java.lang.IllegalArgumentException

val LOWER = Regex("[FL]")
val UPPER = Regex("[BR]")

fun getSeatId(seat: String): Int {
    if (seat.length != 10) throw IllegalArgumentException("seat has to have length 10")

    val binarySeat = seat.replace(LOWER, "0")
        .replace(UPPER, "1")

    return Integer.parseInt(binarySeat, 2)
}

fun getEmptySeat(occupiedSortedSeatIds: List<Int>): Int {
    val validSeatIds = occupiedSortedSeatIds.first()..occupiedSortedSeatIds.last()
    return validSeatIds.find { it !in occupiedSortedSeatIds }
        ?: throw IllegalArgumentException("No empty seat found in input")
}

fun main() {
    Day5.input?.let {
        val seatIds = it.readLines().map(::getSeatId).sorted()
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