package day10

import common.fileFromResources

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

fun countDifferentWays(input: List<Int>): Long {
    val sorted = input.sorted()
    val jolts = listOf(0) + sorted + (sorted.last() + 3)

    return generatePermutations(jolts, 0)
}

fun generatePermutations(jolts: List<Int>,  permutations: Long): Long {
    if (jolts.size == 1) {
        return permutations + 1
    }

    var sum = 0L
    if (jolts.size > 1 && jolts[1] - jolts.first() in 1..3) {
        sum += generatePermutations(jolts.drop(1), permutations)
    }

    if (jolts.size > 2 && jolts[2] - jolts.first() in 1..3) {
        sum += generatePermutations(jolts.drop(2), permutations)
    }

    if (jolts.size > 3 && jolts[3] - jolts.first() in 1..3) {
        sum += generatePermutations(jolts.drop(3), permutations)
    }

    return sum
}

object Day10 {
    val input by lazy {
        fileFromResources("day10/jolts.txt")?.readLines()?.map(String::toInt)
    }
}