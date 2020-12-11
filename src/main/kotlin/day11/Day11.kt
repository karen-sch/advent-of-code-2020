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

        val result2 = ""
        println("Part 2: $result2")
    }
}

fun countOccupiedSeats(lines: List<String>): Int {
    var before = lines
    var after = applyPattern(before)

    while (before != after){
        before = after
        after = applyPattern(after)
    }

   return after.sumBy { it.count { char -> char ==  OCCUPIED} }
}

fun applyPattern(lines: List<String>) : List<String>{
   return lines.mapIndexed {  i, line ->
        line.mapIndexed { j, char ->
            val yIndices = ((i - 1).coerceAtLeast(0))..((i + 1).coerceAtMost(lines.lastIndex))
            val xIndices = ((j - 1).coerceAtLeast(0))..((j + 1).coerceAtMost(line.lastIndex))

            val adjacentCoords = yIndices.flatMap { y -> xIndices.map { x -> y to x } }.minus(i to j)
            val adjacentSeats = adjacentCoords.map { (y, x) -> lines[y][x] }

            when (char) {
                OCCUPIED -> {
                    if (adjacentSeats.count { it == OCCUPIED } >= 4){
                        EMPTY
                    } else {
                        OCCUPIED
                    }
                }
                EMPTY -> {
                    if (adjacentSeats.none { it ==  OCCUPIED }){
                        OCCUPIED
                    } else {
                        EMPTY
                    }
                }
                FLOOR -> FLOOR
                else -> throw IllegalArgumentException("Illegal char $char at line $i pos $j")
            }
        }.toCharArray().let { String(it) }
    }
}


object Day11 {
    val input by lazy {
        fileFromResources("day11/seats.txt")?.readLines()
    }
}