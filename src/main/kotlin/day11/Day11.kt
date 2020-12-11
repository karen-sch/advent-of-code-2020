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

fun mapOccupiedAndEmpty(char: Char, seats: List<Char>, occupiedToEmpty: Int): Char {
    return when (char) {
        OCCUPIED -> if (seats.count { it == OCCUPIED } >= occupiedToEmpty) EMPTY else OCCUPIED
        EMPTY -> if (seats.none { it == OCCUPIED }) OCCUPIED else EMPTY
        else -> throw IllegalArgumentException("Illegal char $char")
    }
}

fun applyPattern1(lines: List<String>): List<String> {
    val validYs = lines.indices
    val validXs = lines.first().indices
    return lines.mapIndexed { i, line ->
        line.mapIndexed { j, char ->
            if (char == FLOOR) {
                FLOOR
            } else {
                val adjacentCoords = directions.map { (y, x) -> i + y to j + x }
                    .filter { (y, x) -> y in validYs && x in validXs }
                val adjacentSeats = adjacentCoords.map { (y, x) -> lines[y][x] }

                mapOccupiedAndEmpty(char, adjacentSeats, 4)
            }
        }.toCharArray().let { String(it) }
    }
}

fun applyPattern2(lines: List<String>): List<String> {
    val validYs = lines.indices
    val validXs = lines.first().indices

    return lines.mapIndexed { i, line ->
        line.mapIndexed { j, char ->
            if (char == FLOOR) {
                FLOOR
            } else {
                val seen = directions.mapNotNull { (dirY, dirX) ->
                    generateSequence(1) { it + 1 }
                        .map { i + dirY * it to j + dirX * it }
                        .takeWhile { (y, x) -> y in validYs && x in validXs }
                        .map { (y, x) -> lines[y][x] }
                        .find { it != FLOOR }
                }
                mapOccupiedAndEmpty(char, seen, 5)
            }
        }.toCharArray().let { String(it) }
    }
}

object Day11 {
    val input by lazy {
        fileFromResources("day11/seats.txt")?.readLines()
    }
}