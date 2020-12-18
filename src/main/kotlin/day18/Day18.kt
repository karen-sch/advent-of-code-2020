package day18

import common.fileFromResources

enum class Operator {
    PLUS,
    TIMES;
}

fun main() {
    Day18.input?.let {
        val result1 = part1(it)
        println("Part 1: $result1")
    }
}

fun part1(lines: List<String>): Long {
    return lines.fold(0L) { acc, line ->
        acc + parsePart1(line)
    }
}


fun parsePart1(input: String): Long {
    var right: Long? = null
    var operator: Operator? = null
    var opening = 0
    var closing = 0

    input.forEachIndexed { i, c ->
        when {
            c.isDigit() -> {
                if (opening == closing) {
                    if (right == null) {
                        right = "$c".toLong()
                    } else {
                        when (operator) {
                            Operator.PLUS -> right = right!! + "$c".toInt()
                            Operator.TIMES -> right = right!! * "$c".toInt()
                        }
                    }
                }
            }
            c == '+' -> {
                if (opening == closing) {
                    operator = Operator.PLUS
                }
            }
            c == '*' -> {
                if (opening == closing) {
                    operator = Operator.TIMES
                }
            }
            c == '(' -> {
                if (opening == closing) {
                    right = when (operator) {
                        null -> parsePart1(input.substring(1))
                        Operator.PLUS -> right!! + parsePart1(input.substring(i + 1))
                        Operator.TIMES -> right!! * parsePart1(input.substring(i + 1))
                    }
                }
                opening++
            }
            c == ')' -> {
                if (opening == closing) {
                    return right!!
                } else {
                    closing++
                }
            }
        }
    }

    return right!!
}


object Day18 {
    val input by lazy {
        fileFromResources("day18/math.txt")?.readLines()
    }
}