package day9

import common.fileFromResources

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

fun findContiguousSetThatSumUpTo(sum: Long, numbers: List<Long>): Long {
    numbers.forEachIndexed { index, summand1 ->
        var currentSum = summand1
        var index2 = index + 1
        val summands = mutableListOf<Long>()
        summands.add(summand1)
        while (currentSum < sum && index2 < numbers.size) {
            currentSum += numbers[index2]
            summands.add(numbers[index2])
            index2++
        }
        if (currentSum == sum) {
            return summands.minOrNull()!! + summands.maxOrNull()!!
        }
    }

    return -1
}

object Day9 {
    val input by lazy {
        fileFromResources("day9/numbers.txt")?.readLines()?.map(String::toLong)
    }
}