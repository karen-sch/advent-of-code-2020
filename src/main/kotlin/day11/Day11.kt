package day11

import common.fileFromResources
import java.lang.IllegalArgumentException

private const val OCCUPIED = '#'
private const val EMPTY = 'L'
private const val FLOOR = '.'

fun main() {
    Day11.input?.let {
        val result1 = countOccupiedSeats(it)
        println("Part 1: $result1")

        val result2 = countOccupiedSeats2(it)
        println("Part 2: $result2")
    }
}

fun countOccupiedSeats(lines: List<String>): Int {
    var before = lines
    var after = applyPattern(before)

    while (before != after) {
        before = after
        after = applyPattern(after)
    }

    return after.sumBy { it.count { char -> char == OCCUPIED } }
}

fun applyPattern(lines: List<String>): List<String> {
    return lines.mapIndexed { i, line ->
        line.mapIndexed { j, char ->
            val yIndices = ((i - 1).coerceAtLeast(0))..((i + 1).coerceAtMost(lines.lastIndex))
            val xIndices = ((j - 1).coerceAtLeast(0))..((j + 1).coerceAtMost(line.lastIndex))

            val adjacentCoords = yIndices.flatMap { y -> xIndices.map { x -> y to x } }.minus(i to j)
            val adjacentSeats = adjacentCoords.map { (y, x) -> lines[y][x] }

            matchChars(char, adjacentSeats, 4)
        }.toCharArray().let { String(it) }
    }
}

fun matchChars(char: Char, seats: List<Char>, occupiedToEmpty: Int): Char {
    return when (char) {
        OCCUPIED -> {
            if (seats.count { it == OCCUPIED } >= occupiedToEmpty) {
                EMPTY
            } else {
                OCCUPIED
            }
        }
        EMPTY -> {
            if (seats.none { it == OCCUPIED }) {
                OCCUPIED
            } else {
                EMPTY
            }
        }
        FLOOR -> FLOOR
        else -> throw IllegalArgumentException("Illegal char $char")
    }
}

fun countOccupiedSeats2(lines: List<String>): Int {
    var before = lines
    var after = applyPattern2(before)

    while (before != after) {
        before = after
        after = applyPattern2(after)
    }
    return after.sumBy { it.count { char -> char == OCCUPIED } }
}


fun applyPattern2(lines: List<String>): List<String> {
    val validYs = lines.indices
    val validXs = lines.first().indices

    val directions = setOf(
        -1 to -1, -1 to 0, -1 to 1,
        0 to -1, 0 to 1,
        1 to -1, 1 to 0, 1 to 1
    )
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
                } while (y in validYs && x in validXs && lines[y][x] == '.')

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