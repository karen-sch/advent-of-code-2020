package day3

import common.fileFromResources
import java.lang.IllegalArgumentException
import java.nio.charset.StandardCharsets

const val TREE = '#'
const val NO_TREE = '.'

fun countEncounteredTreesOnSlope(grid: List<String>): Int {
    val slope = generateSequence(3) { it + 3 }
        .take(grid.size - 1)
        .mapIndexed { index, i -> index + 1 to i % grid[0].length }
    return slope.countEncounteredTrees(grid)
}

fun Sequence<Pair<Int, Int>>.countEncounteredTrees(
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

fun countEncounteredTreesDifferentSlopes(grid: List<String>): Int {
    val right1Down1Slope = generateSequence(1) { it + 1 }
        .take(grid.size - 1)
        .mapIndexed { index, i -> index + 1 to i % grid[0].length }

    val right3Down1Slope = generateSequence(3) { it + 3 }
        .take(grid.size - 1)
        .mapIndexed { index, i -> index + 1 to i % grid[0].length }

    val right5Down1Slope = generateSequence(5) { it + 5 }
        .take(grid.size - 1)
        .mapIndexed { index, i -> index + 1 to i % grid[0].length }

    val right7Down1Slope = generateSequence(7) { it + 7 }
        .take(grid.size - 1)
        .mapIndexed { index, i -> index + 1 to i % grid[0].length }

    val right1Down2Slope = (2 until grid.size step 2).asSequence()
        .mapIndexed { index, i -> i to (index + 1) % grid[0].length }

    return right1Down1Slope.countEncounteredTrees(grid) *
            right3Down1Slope.countEncounteredTrees(grid) *
            right5Down1Slope.countEncounteredTrees(grid) *
            right7Down1Slope.countEncounteredTrees(grid) *
            right1Down2Slope.countEncounteredTrees(grid)
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