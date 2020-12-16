@file:Suppress("EXPERIMENTAL_UNSIGNED_LITERALS")

package day16

import common.fileFromResources
import day16.Mode.*

enum class Mode {
    RULES, YOUR_TICKET, NEARBY_TICKETS
}

data class Notes(val rules: List<IntRange>, val nearbyTickets: List<List<Int>>)

val RULE_REGEX = Regex("(\\d+)-(\\d+)")

fun main() {
    Day16.input?.let {
        val result1 = findScanningErrorsPart1(it)
        println("Part 1: $result1")


    }
}

fun parsePart1(lines: List<String>): Notes {
    val rules = mutableListOf<IntRange>()
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
                        RULE_REGEX.findAll(line).forEach {
                            val (from, to) = it.destructured
                            rules.add(from.toInt()..to.toInt())
                        }
                    }
                    YOUR_TICKET -> {
                        //noop
                    }
                    NEARBY_TICKETS -> {
                        val values = line.split(",").map { it.toInt() }
                        nearbyTickets += values
                    }
                }
            }
        }
    }
    println("Rules")
    rules.forEach { println(it) }

    println("Tickets")
    nearbyTickets.forEach { println(it) }

    return Notes(rules, nearbyTickets)
}

fun findScanningErrorsPart1(lines: List<String>): Int {
    val notes = parsePart1(lines)

    val values = notes.nearbyTickets.flatten()

    return values.filter {
        notes.rules.none { range ->
            range.contains(it)
        }
    }.sum()
}

object Day16 {
    val input by lazy {
        fileFromResources("day16/tickets.txt")?.readLines()
    }
}