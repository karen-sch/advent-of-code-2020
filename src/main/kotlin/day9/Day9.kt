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
    return (preambleLength..numbers.lastIndex).find { i ->
        val possibleSummands = (i - preambleLength until i).map { numbers[it] }.toSet()
        possibleSummands.none() {
            possibleSummands.minus(it).contains(numbers[i] - it)
        }
    }?.let { numbers[it] } ?: throw IllegalArgumentException("No non-sum number found for input data")
}

fun findContiguousSetThatSumUpTo(sum: Long, numbers: List<Long>): Long {
    var windowStart = 0
    var windowEnd = 0
    var windowSum = numbers[0]
    while (true) {
        when {
            windowSum < sum -> {
                windowEnd++
                windowSum += numbers[windowEnd]
            }
            windowSum > sum -> {
                windowSum -= numbers[windowStart]
                windowStart++
            }
            else -> {
                val window = (windowStart..windowEnd).map { numbers[it] }
                return window.minOrNull()!! + window.maxOrNull()!!
            }
        }
    }
}

object Day9 {
    val input by lazy {
        fileFromResources("day9/numbers.txt")?.readLines()?.map(String::toLong)
    }
}