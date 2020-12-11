package day11

import common.fileFromResources
import java.lang.IllegalArgumentException

private const val OCCUPIED = '#'
private const val EMPTY = 'L'
private const val FLOOR = '.'

val directions = setOf(
    -1 to -1, -1 to 0, -1 to 1,
    0 to -1, 0 to 1,
    1 to -1, 1 to 0, 1 to 1
)

fun main() {
    Day11.input?.let {
        val result1 = countOccupiedSeats(it, ::applyPattern1)
        println("Part 1: $result1")

        val result2 = countOccupiedSeats(it, ::applyPattern2)
        println("Part 2: $result2")
    }
}

fun countOccupiedSeats(lines: List<String>, pattern: (List<String>) -> List<String>): Int {
    var before = lines
    var after = pattern(before)

    while (before != after) {
        before = after
        after = pattern(after)
    }

    return after.sumBy { it.count { char -> char == OCCUPIED } }
}

fun matchChars(char: Char, seats: List<Char>, occupiedToEmpty: Int): Char {
    return when (char) {
        OCCUPIED -> if (seats.count { it == OCCUPIED } >= occupiedToEmpty) EMPTY else OCCUPIED
        EMPTY -> if (seats.none { it == OCCUPIED }) OCCUPIED else EMPTY
        FLOOR -> FLOOR
        else -> throw IllegalArgumentException("Illegal char $char")
    }
}

fun applyPattern1(lines: List<String>): List<String> {
    val validYs = lines.indices
    val validXs = lines.first().indices
    return lines.mapIndexed { i, line ->
        line.mapIndexed { j, char ->
            val adjacentCoords = directions.map { (y, x) -> i + y to j + x }
                .filter { (y, x) -> y in validYs && x in validXs }
            val adjacentSeats = adjacentCoords.map { (y, x) -> lines[y][x] }

            matchChars(char, adjacentSeats, 4)
        }.toCharArray().let { String(it) }
    }
}

fun applyPattern2(lines: List<String>): List<String> {
    val validYs = lines.indices
    val validXs = lines.first().indices

    return lines.mapIndexed { i, line ->
        line.mapIndexed { j, char ->
            val seen = directions.mapNotNull { (addToY, addToX) ->
                var y: Int
                var x: Int
                var mul = 1
                do {
                    y = i + addToY * mul
                    x = j + addToX * mul
                    mul++
                } while (y in validYs && x in validXs && lines[y][x] == FLOOR)

                if (y in validYs && x in validXs) {
                    lines[y][x]
                } else null
            }
            matchChars(char, seen, 5)
        }.toCharArray().let { String(it) }
    }
}

object Day11 {
    val input by lazy {
        fileFromResources("day11/seats.txt")?.readLines()
    }
}