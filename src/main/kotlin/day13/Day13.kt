package day13

val testBuses = listOf<Long>(7, 13, -1, -1, 59, -1, 31, 19)
val buses = listOf<Long>(
    29,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    37,
    -1,
    -1,
    -1,
    -1,
    -1,
    409,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    17,
    13,
    19,
    -1,
    -1,
    -1,
    23,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    353,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    -1,
    41
)

fun main() {
   val result2 = part2(buses)
    println("Part 2: $result2")

}

fun part2(buses: List<Long>): Long {
    var i = buses[0]
    var add = buses[0]
    while (true) {
        println("add: $add, i: $i")
        var divisible = true
        val divisibleBuses = mutableListOf<Long>()
        buses.forEachIndexed { index, bus ->
            if (bus != -1L) {
                val busIsDivisible = (i + index.toLong()) % bus == 0L
                divisible = divisible && busIsDivisible
                if (busIsDivisible) divisibleBuses.add(bus)
            }
        }

        if (divisible) {
            return i
        }
        add = divisibleBuses.reduce { acc, bus -> acc * bus }

        i += add
    }
}