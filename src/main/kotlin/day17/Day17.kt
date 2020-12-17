package day17

import common.fileFromResources
import java.lang.IllegalArgumentException

private const val ACTIVE = '#'
private const val INACTIVE = '.'


fun main() {

    Day17.input?.let {
        val result1 = part1(it)
        println("Part 1: $result1")
    }
}

fun part1(lines: List<String>): Int {
    val directions = mutableListOf<List<Int>>()
    listOf(-1, 0, 1).forEach { x ->
        listOf(-1, 0, 1).forEach { y ->
            listOf(-1, 0, 1).forEach { z ->
                if (!(x == 0 && y == 0 && z == 0)) {
                    directions.add(listOf(x, y, z))
                }
            }
        }
    }
    val map = parseInputPart1(lines)
    repeat(6) {
        println(countActiveCubes(map))
        applyPatternPart1(map, directions)
    }
    return countActiveCubes(map)
}

fun parseInputPart1(lines: List<String>): HashMap<Int, HashMap<Int, HashMap<Int, Char>>> {
    val map = HashMap<Int, HashMap<Int, HashMap<Int, Char>>>()
    lines.mapIndexed { y, line ->
        line.mapIndexed { x, char ->
            val xMap = map.computeIfAbsent(x) {
                HashMap()
            }
            val yMap = xMap.computeIfAbsent(y) {
                HashMap()
            }
            yMap[0] = char
        }
    }
    return map
}

fun applyPatternPart1(map: HashMap<Int, HashMap<Int, HashMap<Int, Char>>>, directions: List<List<Int>>) {
    map.entries.forEach { (x, xMap) ->
        xMap.entries.forEach { (y, yMap) ->
            yMap.entries.forEach { (z, char) ->
                val adjacent = directions.map { (xDelta, yDelta, zDelta) ->
                    map.computeIfAbsent(x + xDelta) { HashMap() }
                        .computeIfAbsent(y + yDelta) { HashMap() }
                        .computeIfAbsent(z + zDelta) { INACTIVE }
                }
                val newValue = when (char) {
                    ACTIVE -> if (adjacent.count { it == ACTIVE } in (2..3)) ACTIVE else INACTIVE
                    INACTIVE -> if (adjacent.count { it == ACTIVE } == 3) ACTIVE else INACTIVE
                    else -> throw IllegalArgumentException("Illegal char $char")
                }
                yMap[z] = newValue
            }
        }
    }
}

fun countActiveCubes(map: HashMap<Int, HashMap<Int, HashMap<Int, Char>>>): Int {
    return map.values.sumBy { xMap ->
        xMap.values.sumBy { yMap -> yMap.values.count { it == ACTIVE } }
    }
}

object Day17 {
    val input by lazy {
        fileFromResources("day17/cubes.txt")?.readLines()
    }
}