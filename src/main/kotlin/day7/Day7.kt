package day7

import common.fileFromResources

val INNER_BAG = Regex("(\\d) (\\w+ \\w+) bag[s]?")
val OUTER_BAG = Regex("(\\w+ \\w+) bags")

data class Bag(val color: String, val canContain: List<Pair<Int, String>>)

fun main() {
    Day7.input?.let {
        val bags = parseBags(it.readLines())
        val part1 = countContainingBags("shiny gold", bags)
        println("Part 1: $part1")

        val part2 = countContainedBags("shiny gold", bags)
        println("Part 2: $part2")
    }
}

fun countContainingBags(color: String, bags: List<Bag>): Int {
    val containingBags = findContainingBagsRecursive(color, bags)
    return containingBags.size
}

fun findContainingBagsRecursive(color: String, bags: List<Bag>): Set<Bag> {
    val foundBags = bags.filter { bag -> bag.canContain.any { it.second == color } }
    return if (foundBags.isEmpty()) emptySet()
    else foundBags.union(foundBags.flatMap { findContainingBagsRecursive(it.color, bags) }.toSet())
}

fun countContainedBags(color: String, bags: List<Bag>): Int {
    val outer = bags.find { it.color == color }!!
    return findContainedBagsRecursive(outer, bags)
}

fun findContainedBagsRecursive(bag: Bag, bags: List<Bag>): Int {
    return if (bag.canContain.isEmpty()) 0
    else {
        bag.canContain.sumBy { (count, color) ->
            val foundBag = bags.find { it.color == color }
            count + count * findContainedBagsRecursive(foundBag!!, bags)
        }

    }
}

fun parseBags(lines: List<String>): List<Bag> {
    return lines.mapNotNull { line ->
        val (outer, inner) = line.split("contain")
        OUTER_BAG.find(outer.trim())?.groupValues?.get(1)?.let { color ->
            val canContain = INNER_BAG.findAll(inner).mapNotNull { match ->
                val number = match.groupValues[1].toInt()
                number to match.groupValues[2]
            }.toList()
            Bag(color, canContain)
        }
    }
}


object Day7 {
    val input by lazy {
        fileFromResources("day7/bags.txt")
    }
}