package day7

import common.fileFromResources

val INNER_BAG = Regex("(\\d) (\\w+ \\w+) bag[s]?")
val OUTER_BAG = Regex("(\\w+ \\w+) bags")

data class Bag(val color: String, val canContain: List<Pair<Int, String>>)

typealias BagMap = Map<String, Bag>

fun main() {
    Day7.input?.let {
        val bags = parseBags(it.readLines())
        val startColor = "shiny gold"
        val part1 = countContainingBags(startColor, bags)
        println("Part 1: $part1")

        val part2 = countContainedBags(startColor, bags)
        println("Part 2: $part2")
    }
}

fun countContainingBags(color: String, bags: BagMap): Int {
    val containingBags = findContainingBagsRecursive(color, bags)
    return containingBags.size
}

fun findContainingBagsRecursive(color: String, bags: BagMap): Set<Bag> {
    val foundBags = bags.values.filter { bag -> bag.canContain.any { it.color == color } }
    return if (foundBags.isEmpty()) emptySet()
    else foundBags.union(foundBags.flatMap { findContainingBagsRecursive(it.color, bags) })
}

fun countContainedBags(color: String, bags: BagMap): Int {
    val outer = bags.getValue(color)
    return findContainedBagsRecursive(outer, bags)
}

fun findContainedBagsRecursive(bag: Bag, bags: BagMap): Int {
    return if (bag.canContain.isEmpty()) 0
    else {
        bag.canContain.sumBy { (count, color) ->
            val foundBag = bags.getValue(color)
            count + count * findContainedBagsRecursive(foundBag, bags)
        }
    }
}

fun parseBags(lines: List<String>): BagMap {
    val bagMap = HashMap<String, Bag>()
    lines.forEach { line ->
        val (outer, inner) = line.split("contain")
        OUTER_BAG.find(outer.trim())?.groupValues?.get(1)?.let { color ->
            val canContain = INNER_BAG.findAll(inner).mapNotNull { match ->
                val number = match.groupValues[1].toInt()
                number to match.groupValues[2]
            }.toList()
            bagMap[color] = Bag(color, canContain)
        }
    }
    return bagMap
}

object Day7 {
    val input by lazy {
        fileFromResources("day7/bags.txt")
    }
}

val Pair<Int, String>.color get() = this.second