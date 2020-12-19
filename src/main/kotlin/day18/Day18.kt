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

        val result2 = part2(it)
        println("Part 2: $result2")
    }
}

fun part1(lines: List<String>): Long {
    return lines.fold(0L) { acc, line ->
        acc + parsePart1(line)
    }
}

fun part2(lines: List<String>): Long {
    return lines.fold(0L) { acc, line ->
        acc + parsePart2(line)
    }
}

fun parsePart1(input: String): Long {
    var right: Long? = null
    var operator: Operator? = null
    var opening = 0
    var closing = 0

    input.forEachIndexed { i, c ->
        when {
            c.isDigit() -> if (opening == closing) {
                if (right == null) {
                    right = "$c".toLong()
                } else {
                    when (operator) {
                        Operator.PLUS -> right = right!! + "$c".toInt()
                        Operator.TIMES -> right = right!! * "$c".toInt()
                    }
                }
            }
            c == '+' -> if (opening == closing) {
                operator = Operator.PLUS
            }
            c == '*' -> if (opening == closing) {
                operator = Operator.TIMES
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
            c == ')' -> if (opening == closing) {
                return right!!
            } else {
                closing++
            }
        }
    }

    return right!!
}

fun parsePart2(input: String): Long {
    val parenthesesRegex = Regex("\\(([^()]+)\\)")
    var innerParentheses = input

    while (parenthesesRegex.containsMatchIn(innerParentheses)) {
        innerParentheses = parenthesesRegex.replace(innerParentheses) { match ->
            val (inner) = match.destructured
            evaluateNoParentheses(inner)
        }
    }
    return evaluateNoParentheses(innerParentheses).toLong()
}

fun evaluateNoParentheses(input: String): String {
    val plusRegex = Regex("(\\d+) \\+ (\\d+)")
    val timesRegex = Regex("(\\d+) \\* (\\d+)")
    var plusEvaluated = input

    while (plusRegex.containsMatchIn(plusEvaluated)) {
        plusEvaluated = plusEvaluated.replace(plusRegex) { match ->
            val (left, right) = match.destructured
            (left.toLong() + right.toLong()).toString()
        }
    }

    var timesEvaluated = plusEvaluated

    while (timesRegex.containsMatchIn(timesEvaluated)) {
        timesEvaluated = timesEvaluated.replace(timesRegex) { match ->
            val (left, right) = match.destructured
            (left.toLong() * right.toLong()).toString()
        }
    }

    return timesEvaluated
}


object Day18 {
    val input by lazy {
        fileFromResources("day18/math.txt")?.readLines()
    }
}