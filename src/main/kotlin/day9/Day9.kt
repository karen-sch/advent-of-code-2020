package day9

import common.fileFromResources
import java.lang.IllegalArgumentException

fun main() {
    Day9.input?.let {
        val result = findFirstNonSumNumber(it)
        println("Part 1: $result")

        val result2 = findContiguousSetThatSumUpTo(result, it)
        println("Part 2: $result2")
    }
}

fun findFirstNonSumNumber(numbers: List<Long>, preambleLength: Int = 25): Long {
    (preambleLength until numbers.size).forEach { i ->
        val number = numbers[i]
        val possibleSummands = (i - preambleLength until i).map { numbers[it] }.toSet()

        if (possibleSummands.none() {
                possibleSummands.minus(it).contains(number - it)
            }) return number
    }

    throw IllegalArgumentException("No non-sum number found for input data")
}

fun findContiguousSetThatSumUpTo(sum: Long, numbers: List<Long>): Long {
    numbers.forEachIndexed { i, number ->
        var j = i + 1
        val summands = mutableListOf(number)
        while (summands.sum() < sum && j < numbers.size) {
            summands.add(numbers[j])
            j++
        }
        if (summands.sum() == sum) {
            return summands.minOrNull()!! + summands.maxOrNull()!!
        }
    }
    throw IllegalArgumentException("No contiguous set that sums up to $sum found in input data")
}

object Day9 {
    val input by lazy {
        fileFromResources("day9/numbers.txt")?.readLines()?.map(String::toLong)
    }
}