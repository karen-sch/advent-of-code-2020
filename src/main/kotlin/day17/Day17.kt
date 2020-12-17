package day17

import common.fileFromResources
import java.awt.event.AdjustmentEvent
import java.lang.IllegalArgumentException

private const val ACTIVE = '#'
private const val INACTIVE = '.'


fun main() {

    Day17.input?.let {
        val result1 = part1(it)
        println("Part 1: $result1")

        val result2 = part2(it)
        println("Part 2: $result2")
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
    var map = parseInputPart1(lines, directions)
    repeat(6) {
        map = applyPatternPart1(map, directions)
    }
    return countActiveCubesPart1(map)
}

fun parseInputPart1(lines: List<String>, directions: List<List<Int>>): HashMap<Int, HashMap<Int, HashMap<Int, Char>>> {
    val map = HashMap<Int, HashMap<Int, HashMap<Int, Char>>>()
    lines.mapIndexed { y, line ->
        line.mapIndexed { x, char ->
            val xMap = map.getOrPut(x) {
                HashMap()
            }
            val yMap = xMap.getOrPut(y) {
                HashMap()
            }
            yMap[0] = char

            directions.forEach { (xDelta, yDelta, zDelta) ->
                val value = map[x + xDelta]?.get(y + yDelta)?.get(zDelta)
                if (value == null) {
                    map.getOrPut(x + xDelta) { HashMap() }
                        .getOrPut(y + yDelta) { HashMap() }[zDelta] = INACTIVE
                }
            }
        }
    }
    return map
}

fun applyPatternPart1(map: HashMap<Int, HashMap<Int, HashMap<Int, Char>>>, directions: List<List<Int>>):
        HashMap<Int, HashMap<Int, HashMap<Int, Char>>> {
    val newMap = HashMap<Int, HashMap<Int, HashMap<Int, Char>>>()
    map.entries.forEach { (x, xMap) ->
        xMap.entries.forEach { (y, yMap) ->
            yMap.entries.forEach { (z, char) ->
                val adjacent = directions.map { (xDelta, yDelta, zDelta) ->
                    val value = map[x + xDelta]?.get(y + yDelta)?.get(z + zDelta)
                    if (value == null && newMap[x + xDelta]?.get(y + yDelta)?.get(z + zDelta) == null) {
                        newMap.getOrPut(x + xDelta) { HashMap() }
                            .getOrPut(y + yDelta) { HashMap() }[z + zDelta] = INACTIVE
                    }
                    value ?: INACTIVE
                }
                newMap.getOrPut(x) { HashMap() }.getOrPut(y) { HashMap() }[z] = getNewValue(char, adjacent)
            }
        }
    }

    return newMap
}

fun countActiveCubesPart1(map: HashMap<Int, HashMap<Int, HashMap<Int, Char>>>): Int {
    return map.values.sumBy { xMap ->
        xMap.values.sumBy { yMap -> yMap.values.count { it == ACTIVE } }
    }
}

fun part2(lines: List<String>): Int {
    val directions = mutableListOf<List<Int>>()
    listOf(-1, 0, 1).forEach { x ->
        listOf(-1, 0, 1).forEach { y ->
            listOf(-1, 0, 1).forEach { z ->
                listOf(-1, 0, 1).forEach { w ->
                    if (!(x == 0 && y == 0 && z == 0 && w == 0)) {
                        directions.add(listOf(x, y, z, w))
                    }
                }
            }
        }
    }
    var map = parseInputPart2(lines, directions)
    repeat(6) {
        map = applyPatternPart2(map, directions)
    }
    return countActiveCubesPart2(map)
}

fun parseInputPart2(
    lines: List<String>,
    directions: List<List<Int>>
): HashMap<Int, HashMap<Int, HashMap<Int, HashMap<Int, Char>>>> {
    val map = HashMap<Int, HashMap<Int, HashMap<Int, HashMap<Int, Char>>>>()
    lines.mapIndexed { y, line ->
        line.mapIndexed { x, char ->
            val xMap = map.getOrPut(x) {
                HashMap()
            }
            val yMap = xMap.getOrPut(y) {
                HashMap()
            }
            val zMap = yMap.getOrPut(0) {
                HashMap()
            }
            zMap[0] = char

            directions.forEach { (xDelta, yDelta, zDelta, wDelta) ->
                val value = map[x + xDelta]?.get(y + yDelta)?.get(zDelta)?.get(wDelta)
                if (value == null) {
                    map.getOrPut(x + xDelta) { HashMap() }
                        .getOrPut(y + yDelta) { HashMap() }
                        .getOrPut(zDelta) { HashMap() }[wDelta] = INACTIVE
                }
            }
        }
    }
    return map
}

fun applyPatternPart2(map: HashMap<Int, HashMap<Int, HashMap<Int, HashMap<Int, Char>>>>, directions: List<List<Int>>):
        HashMap<Int, HashMap<Int, HashMap<Int, HashMap<Int, Char>>>> {
    val newMap = HashMap<Int, HashMap<Int, HashMap<Int, HashMap<Int, Char>>>>()
    map.entries.forEach { (x, xMap) ->
        xMap.entries.forEach { (y, yMap) ->
            yMap.entries.forEach { (z, zMap) ->
                zMap.entries.forEach { (w, char) ->
                    val adjacent = directions.map { (xDelta, yDelta, zDelta, wDelta) ->
                        val value = map[x + xDelta]?.get(y + yDelta)?.get(z + zDelta)?.get(w + wDelta)
                        if (value == null && newMap[x + xDelta]?.get(y + yDelta)?.get(z + zDelta)
                                ?.get(w + wDelta) == null
                        ) {
                            newMap.getOrPut(x + xDelta) { HashMap() }
                                .getOrPut(y + yDelta) { HashMap() }.getOrPut(z + zDelta) { HashMap() }[w + wDelta] =
                                INACTIVE
                        }
                        value ?: INACTIVE
                    }
                    newMap.getOrPut(x) { HashMap() }.getOrPut(y) { HashMap() }.getOrPut(z) { HashMap() }[w] =
                        getNewValue(char, adjacent)
                }
            }
        }
    }

    return newMap
}

fun countActiveCubesPart2(map: HashMap<Int, HashMap<Int, HashMap<Int, HashMap<Int, Char>>>>): Int {
    return map.values.sumBy { xMap ->
        xMap.values.sumBy { yMap -> yMap.values.sumBy { zMap -> zMap.values.count { it == ACTIVE } } }
    }
}

fun getNewValue(char: Char, adjacent: List<Char>): Char {
    return when (char) {
        ACTIVE -> if (adjacent.count { it == ACTIVE } in (2..3)) ACTIVE else INACTIVE
        INACTIVE -> if (adjacent.count { it == ACTIVE } == 3) ACTIVE else INACTIVE
        else -> throw IllegalArgumentException("Illegal char $char")
    }
}

object Day17 {
    val input by lazy {
        fileFromResources("day17/cubes.txt")?.readLines()
    }
}