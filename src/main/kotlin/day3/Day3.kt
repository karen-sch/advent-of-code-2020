package day3

import common.fileFromResources
import java.lang.IllegalArgumentException
import java.nio.charset.StandardCharsets

const val TREE = '#'
const val NO_TREE = '.'

fun countEncounteredTreesOnSlope(grid: List<String>): Int {
    val slope = generateRightNDown1Slope(3, grid)
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

private fun generateRightNDown1Slope(right: Int, grid: List<String>): Sequence<Pair<Int, Int>> {
    return generateSequence(right) { it + right }
        .take(grid.size - 1)
        .mapIndexed { index, i -> index + 1 to i % grid[0].length }
}

private fun generateRight1DownNSlope(down: Int, grid: List<String>): Sequence<Pair<Int, Int>> {
    return (down until grid.size step down).asSequence()
        .mapIndexed { index, i -> i to (index + 1) % grid[0].length }
}

fun countEncounteredTreesDifferentSlopes(grid: List<String>): Int {
    return listOf(
        generateRightNDown1Slope(1, grid),
        generateRightNDown1Slope(3, grid),
        generateRightNDown1Slope(5, grid),
        generateRightNDown1Slope(7, grid),
        generateRight1DownNSlope(2, grid)
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