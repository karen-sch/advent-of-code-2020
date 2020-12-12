package day12

import common.fileFromResources
import kotlin.math.absoluteValue

val directions = mapOf('E' to (0 to 1), 'W' to (0 to -1), 'N' to (1 to 0), 'S' to (-1 to 0))
val cardinals = listOf('E', 'S', 'W', 'N')

fun main() {
    Day12.input?.let {
        val result1 = part1(it)
        println("Part 1: $result1")

        val result2 = part2(it)
        println("Part 2: $result2")
    }
}

fun part1(lines: List<String>): Int {
    var direction = 'E'
    var coords = 0 to 0

    lines.forEach { line ->
        val action = line[0]
        val value = line.substring(1).toInt()
        when (action) {
            'F' -> {
                coords = move(coords, direction, value)
            }
            in cardinals -> {
                coords = move(coords, action, value)
            }
            in setOf('L', 'R') -> {
                var delta = value / 90
                if (action == 'L') delta = -delta
                val index = cardinals.indexOf(direction)
                direction = cardinals[Math.floorMod(index + delta, 4)]
            }
        }
    }

    return coords.first.absoluteValue + coords.second.absoluteValue
}

fun part2(lines: List<String>): Int {
    var coords = 0 to 0
    var waypoint = 1 to 10

    lines.forEach { line ->
        val action = line[0]
        val value = line.substring(1).toInt()
        when (action) {
            'F' -> {
                coords = moveToWaypoint(coords, waypoint, value)
            }
            in cardinals -> {
                waypoint = move(waypoint, action, value)
            }
            in setOf('L', 'R') -> {
                var delta = value / 90

                repeat(delta) {
                    when (action) {
                        'L' -> waypoint = waypoint.second to -waypoint.first
                        'R' -> waypoint = -waypoint.second to waypoint.first
                    }
                }
            }
        }
    }

    return coords.first.absoluteValue + coords.second.absoluteValue
}

fun move(current: Pair<Int, Int>, someplace: Char, value: Int): Pair<Int, Int> {
    val d = directions.getValue(someplace)
    return (current.first + (d.first * value) to current.second + (d.second * value))
}

fun moveToWaypoint(current: Pair<Int, Int>, waypoint: Pair<Int, Int>, value: Int): Pair<Int, Int> {
    return (current.first + (waypoint.first * value) to current.second + (waypoint.second * value))
}

object Day12 {
    val input by lazy {
        fileFromResources("day12/navigation.txt")?.readLines()
    }
}