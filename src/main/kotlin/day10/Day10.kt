package day10

import common.fileFromResources

fun main() {
    Day10.input?.let {
        val result1 = get1JoltDiffTimes3JoltDiffs(it)
        println("Part 1: $result1")

        val result2 = countDifferentWays(it)
        println("Part 2: $result2")
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

    return generatePermutations(jolts, mutableMapOf(), 0)
}

fun generatePermutations(jolts: List<Int>,
                         seenPermutations: MutableMap<Int, Long>,
                         sum: Long): Long {
    if (jolts.size == 1) {
        return sum + 1
    }

    return countPermutationsForIndex(jolts, 1, seenPermutations, sum) +
            countPermutationsForIndex(jolts, 2, seenPermutations, sum) +
            countPermutationsForIndex(jolts, 3, seenPermutations, sum)

}

private fun countPermutationsForIndex(
    jolts: List<Int>,
    index: Int,
    seenPermutations: MutableMap<Int, Long>,
    sum: Long
): Long {
    if (jolts.size > index && jolts[index] - jolts.first() in 1..3) {
        return seenPermutations.computeIfAbsent(jolts.size - index) {
            generatePermutations(jolts.drop(index), seenPermutations, sum)
        }
    }
    return 0
}

object Day10 {
    val input by lazy {
        fileFromResources("day10/jolts.txt")?.readLines()?.map(String::toInt)
    }
}