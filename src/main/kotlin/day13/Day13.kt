package day13

val buses = listOf<Long>(29, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 37, -1, -1, -1, -1, -1, 409, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 17, 13, 19, -1, -1, -1, 23, -1, -1, -1, -1, -1, -1, -1, 353, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 41)
const val earliestDeparture = 1000511L

fun main() {
    val result1 = part1(earliestDeparture, buses)
    println("Part 1: $result1")
    val result2 = part2(buses)
    println("Part 2: $result2")
}

fun part1(earliestDeparture: Long, input: List<Long>): Long {
    val buses = input.filterNot { it == -1L }

    var departureTime = earliestDeparture
    while (true) {
        val bus = buses.find { departureTime % it == 0L }
        if (bus != null) return bus * (departureTime - earliestDeparture)
        departureTime++
    }
}

fun part2(input: List<Long>): Long {
    val buses = input.mapIndexed { index, bus -> index to bus }.filter { it.second != -1L }
    var i = buses[0].second
    while (true) {
        val divisibleBuses = buses.filter { (index, bus) ->
            (i + index.toLong()) % bus == 0L
        }

        if (divisibleBuses.size == buses.size) {
            return i
        }

        i += divisibleBuses.fold(1L) { acc, pair -> acc * pair.second }
    }
}