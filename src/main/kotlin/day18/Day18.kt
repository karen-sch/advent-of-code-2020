package day18

import common.fileFromResources

sealed class Expression {
    data class Integer(val value: Int) : Expression()
    data class Operation(val left: Int, val operator: Operator, val right: Expression) : Expression()
}

enum class Operator {
    PLUS,
    TIMES;
}

fun Expression.evaluate(): Int {
    return when (this) {
        is Expression.Integer -> value

        is Expression.Operation -> {
            when (operator) {
                Operator.PLUS -> left + right.evaluate()
                Operator.TIMES -> left * right.evaluate()
            }
        }
    }
}


fun main() {
     val string = "2 * 3 + (4 * 5)"
     val result = parse(string)
     println(result)

     val string2 = "5 + (8 * 3 + 9 + 3 * 4 * 3)"
     val result2 = parse(string2)
     println(result2)

     val string3 = "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"
     val result3 = parse(string3)
     println(result3)

     val string4 = "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"
     val result4 = parse(string4)
     println(result4)

    Day18.input?.let {
        val result1 = part1(it)
        println("Part 1: $result1")
    }
}

fun part1(lines: List<String>): Long {
    return lines.fold(0L) { acc, line ->
        acc + parse(line)
    }

}


fun parse(string: String): Long {
    val input = string.replace("\\s", "")
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
                        null -> parse(string.substring(1))
                        Operator.PLUS -> right!! + parse(string.substring(i + 1))
                        Operator.TIMES -> right!! * parse(string.substring(i + 1))
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