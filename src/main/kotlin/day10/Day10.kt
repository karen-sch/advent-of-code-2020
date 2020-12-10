package day10

import common.fileFromResources
import java.lang.IllegalArgumentException

fun main() {
    Day10.input?.let {
        val result1 = get1JoltDiffTimes3JoltDiffs(it)
        println("Part 1: $result1")
    }
}

fun get1JoltDiffTimes3JoltDiffs(input: List<Int>): Int {
    var diff1 = 0
    var diff3 = 0

    val sorted = input.sorted()
    val jolts = listOf(0) + sorted + (sorted.last() + 3)


    (1..jolts.lastIndex).forEach { i ->

        when (jolts[i] - jolts[i - 1]) {
            1 -> diff1++
            3 -> diff3++
        }

    }

    return diff1 * diff3
}

object Day10 {
    val input by lazy {
        fileFromResources("day10/jolts.txt")?.readLines()?.map(String::toInt)
    }
}