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

fun countDifferentWays(input: List<Int>): Int {
    val sorted = input.sorted()
    val jolts = listOf(0) + sorted + (sorted.last() + 3)


    val permutations = ArrayList<List<Int>>()

    generatePermutations(jolts, emptyList(), permutations)

    permutations.forEach { println(it) }

    return permutations.size
}

fun generatePermutations(jolts: List<Int>, currentPermutation: List<Int>, permutations: MutableList<List<Int>>) {
    if (jolts.size == 1) {
        permutations.add(currentPermutation + jolts.first())
        return
    }

    if (jolts.size > 1 && jolts[1] - jolts.first() in 1..3) {
        val newPermutations = currentPermutation + listOf(jolts.first())
        generatePermutations(jolts.drop(1), newPermutations, permutations)
    }

    if (jolts.size > 2 && jolts[2] - jolts.first() in 1..3) {
        val newPermutations = currentPermutation + listOf(jolts.first())
        generatePermutations(jolts.drop(2), newPermutations, permutations)
    }

    if (jolts.size > 3 && jolts[3] - jolts.first() in 1..3) {
        val newPermutations = currentPermutation + listOf(jolts.first())
        generatePermutations(jolts.drop(3), newPermutations, permutations)
    }

}

object Day10 {
    val input by lazy {
        fileFromResources("day10/jolts.txt")?.readLines()?.map(String::toInt)
    }
}