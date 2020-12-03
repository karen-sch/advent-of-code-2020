package day3

import common.fileFromResources
import java.lang.IllegalArgumentException
import java.nio.charset.StandardCharsets

const val TREE = '#'
const val NO_TREE = '.'

fun countEncounteredTrees(grid: List<String>) : Int {

    return generateSequence(3) { it + 3 }
        .take(grid.size - 1)
        .mapIndexed { index, i -> index + 1 to  i % grid[0].length }
        .fold(0) { treeCount, (y, x) ->
            when (val char = grid[y][x]) {
                TREE -> treeCount + 1
                NO_TREE -> treeCount
                else -> throw IllegalArgumentException("Illegal char $char at x$x y$y")
            }
        }
}

fun main() {
    Day3.input?.let {
        println(countEncounteredTrees(it))
    }
}

object Day3 {
    val input by lazy {
        fileFromResources("day3/trees.txt")
            ?.readLines(StandardCharsets.US_ASCII)
    }
}