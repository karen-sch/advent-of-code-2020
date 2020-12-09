package day9

import common.fileFromResources

fun main() {
    Day9.input?.let {
        val result = findFirstNonSumNumber(it)
        println("Part 1: $result")
    }
}

fun findFirstNonSumNumber(numbers: List<Long>, preambleLength: Int = 25): Long {
    (preambleLength until numbers.size).forEach { i ->
        val number = numbers[i]

        val possibleSummands = (i-preambleLength until i).map { numbers[it] }
        var found = false

        val seen = HashSet<Long>()
        possibleSummands.forEach findSum@{ summand ->

            if (seen.contains(number - summand)) {
                found = true
                return@findSum
            }
            seen.add(summand)

        }

        if (!found) return number
    }

    return -1
}

object Day9 {
    val input by lazy {
        fileFromResources("day9/numbers.txt")?.readLines()?.map(String::toLong)
    }
}