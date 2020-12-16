package day15

fun main() {
    val result1 = findNthSpokenNumber(listOf(1, 0, 15, 2, 10, 13), 2020)
    println("Part 1: $result1")

    val result2 = findNthSpokenNumber(listOf(1, 0, 15, 2, 10, 13), 30000000)
    println("Part 2: $result2")
}

fun findNthSpokenNumber(numbers: List<Int>, n: Int): Int {
    val numberToLastSpoken = HashMap<Int, Int>()
    var lastSpoken = 0

    (0 until n).forEach { i ->
        if (i < numbers.size) {
            lastSpoken = numbers[i]
            numberToLastSpoken[lastSpoken] = i
        } else {
            if (!numberToLastSpoken.contains(lastSpoken)) {
                numberToLastSpoken[lastSpoken] = i - 1
                lastSpoken = 0
            } else {
                val previousIndex = numberToLastSpoken.getValue(lastSpoken)
                numberToLastSpoken[lastSpoken] = i - 1
                lastSpoken = (i - 1) - previousIndex
            }
        }
    }
    return lastSpoken
}