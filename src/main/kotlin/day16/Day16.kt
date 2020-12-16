@file:Suppress("EXPERIMENTAL_UNSIGNED_LITERALS")

package day16

import common.fileFromResources
import day16.Mode.*

enum class Mode {
    RULES, YOUR_TICKET, NEARBY_TICKETS
}

data class Rule(val name: String, val ranges: List<IntRange>)
data class Notes(val rules: List<Rule>, val yourTicket: List<Int>, val nearbyTickets: List<List<Int>>)

val RULE_REGEX = Regex("(\\d+)-(\\d+)")

fun main() {
    Day16.input?.let {
        val result1 = findScanningErrorsPart1(it)
        println("Part 1: $result1")

        val result2 = multiplyDeparturesPart2(it)
        println("Part 2: $result2")

    }
}

fun parse(lines: List<String>): Notes {
    val rules = mutableListOf<Rule>()
    val yourTicket = mutableListOf<Int>()
    val nearbyTickets = mutableListOf<List<Int>>()
    var mode = RULES

    lines.forEach { line ->
        when (line) {
            "your ticket:" -> mode = YOUR_TICKET
            "nearby tickets:" -> mode = NEARBY_TICKETS
            "" -> { /*noop*/
            }
            else -> {
                when (mode) {
                    RULES -> {
                        val name = line.substringBefore(":")
                        val ranges = RULE_REGEX.findAll(line).map {
                            val (from, to) = it.destructured
                            from.toInt()..to.toInt()
                        }.toList()
                        rules.add(Rule(name, ranges))
                    }
                    YOUR_TICKET -> {
                        val values = line.split(",").map { it.toInt() }
                        yourTicket.addAll(values)
                    }
                    NEARBY_TICKETS -> {
                        val values = line.split(",").map { it.toInt() }
                        nearbyTickets += values
                    }
                }
            }
        }
    }

    return Notes(rules, yourTicket, nearbyTickets)
}

fun findScanningErrorsPart1(lines: List<String>): Int {
    val notes = parse(lines)

    val values = notes.nearbyTickets.flatten()

    return values.filter {
        notes.rules.flatMap { it.ranges }.none { range ->
            range.contains(it)
        }
    }.sum()
}

fun multiplyDeparturesPart2(lines: List<String>): Long {
    val notes = parse(lines)
    val allRanges = notes.rules.flatMap { it.ranges }
    val validTickets = notes.nearbyTickets.filter { values ->
        values.all { value -> allRanges.any { it.contains(value) } }
    }

    val valuesForRules = (validTickets[0].indices).map { index ->
        validTickets.map { it[index] }
    }

    // A rule can fit multiple indices
    val rulesForIndex = valuesForRules.map { valuesForRule ->
        notes.rules.filter { rule ->
            valuesForRule.all { value ->
                rule.ranges.any { it.contains(value) }
            }
        }.toMutableList()
    }

    // But: There is gonna be one rule that only fits one index, one that fits 2, 3, ...
    // So we can deduct which rule belongs to which index
    val usedRules = mutableSetOf<Rule>()

    (1..rulesForIndex.size).forEach { count ->
        rulesForIndex.find() { it.size == count}?.let { rules ->
            rules.removeAll { it in usedRules }
            usedRules.addAll(rules)
        }
    }

    // Now, there should only be one rule left per index
    val rules = rulesForIndex.map { it.first() }
    val departureRules = rules.mapIndexedNotNull { index, rule ->
        if (rule.name.startsWith("departure")) index else null
    }

     return departureRules.map { notes.yourTicket[it] }.fold(1L) { acc, i -> acc * i }
}

object Day16 {
    val input by lazy {
        fileFromResources("day16/tickets.txt")?.readLines()
    }
}