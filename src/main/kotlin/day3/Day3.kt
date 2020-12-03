package day3

import common.fileFromResources
import java.lang.IllegalArgumentException
import java.nio.charset.StandardCharsets

const val TREE = '#'
const val NO_TREE = '.'

fun countEncounteredTreesOnSlope(grid: List<String>): Int {
    val slope = generateSlope(3, 1, grid)
    return slope.countEncounteredTrees(grid)
}

private fun Sequence<Pair<Int, Int>>.countEncounteredTrees(
    grid: List<String>
): Int {
    return this.fold(0) { treeCount, (y, x) ->
        when (val char = grid[y][x]) {
            TREE -> treeCount + 1
            NO_TREE -> treeCount
            else -> throw IllegalArgumentException("Illegal char $char at x$x y$y")
        }
    }
}

private fun generateSlope(right: Int, down: Int, grid: List<String>): Sequence<Pair<Int, Int>> {
    val width = grid[0].length
    val height = grid.size

    return generateSequence(down to right) {
        (it.first + down) to (it.second + right)
    }.takeWhile { it.first < height }
        .map { (down, right) -> down to right % width }
}

fun countEncounteredTreesDifferentSlopes(grid: List<String>): Int {
    return listOf(
        generateSlope(1, 1, grid),
        generateSlope(3, 1, grid),
        generateSlope(5, 1, grid),
        generateSlope(7, 1, grid),
        generateSlope(1, 2, grid)
    ).map { it.countEncounteredTrees(grid) }.reduce { acc, i -> acc * i }
}

fun main() {
    Day3.input?.let {
        println("part 1: " + countEncounteredTreesOnSlope(it))
        println("part 2: " + countEncounteredTreesDifferentSlopes(it))
    }
}

object Day3 {
    val input by lazy {
        fileFromResources("day3/trees.txt")
            ?.readLines(StandardCharsets.US_ASCII)
    }
}