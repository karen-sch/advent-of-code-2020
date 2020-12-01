package day1

fun find2020SumOf3AndMultiply(entries: List<Int>): Int {
    val sorted = entries.sorted()
    for ((i1, x) in sorted.withIndex()) {
        for ((i2, y) in sorted.subList(i1 + 1, sorted.size).withIndex()) {
            val xy = x + y
            if (xy > 2020) break
            for (z in sorted.subList(i2 + 1, sorted.size)) {
                val sum = xy + z
                if (sum > 2020) break
                if (sum == 2020) {
                    return x * y * z
                }
            }
        }
    }
    throw IllegalArgumentException("No sum of 2020 found in input dataset")
}

fun main() {
    Day1.input?.let {
        val result = find2020SumOf3AndMultiply(it)
        println(result)
    }
}
