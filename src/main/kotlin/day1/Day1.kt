package day1

import common.fileFromResources
import java.nio.charset.StandardCharsets

fun find2020SumAndMultiply(entries: List<Int>): Int {
    val seen = HashSet<Int>()
    for (x in entries) {
        val other = 2020 - x
        if (seen.contains(other)) {
            return x * other
        }
        seen.add(x)
    }
    throw IllegalArgumentException("No sum of 2020 found in input dataset")
}

fun main() {
    Day1.input?.let {
        val result = find2020SumAndMultiply(it)
        println(result)
    }
}

object Day1 {
    val input by lazy {
        fileFromResources("day1/entries.txt")
            ?.readLines(StandardCharsets.US_ASCII)
            ?.mapNotNull { it.toIntOrNull() }
    }
}