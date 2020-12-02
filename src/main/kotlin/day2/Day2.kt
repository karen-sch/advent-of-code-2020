package day2

import common.fileFromResources
import java.nio.charset.StandardCharsets

fun howManyMatchRule(passwordRules: List<PasswordRule>): Int {
    var matches = 0
    for (rule in passwordRules) {
        val count = rule.password.count { it == rule.letter }
        if (count in rule.min..rule.max) matches++
    }
    return matches
}

fun main() {
    Day2.input?.let {
        val result = howManyMatchRule(it)
        println(result)
    }
}

data class PasswordRule(val min: Int, val max: Int, val letter: Char, val password: String)

object Day2 {
    private const val pattern = "(\\d+)-(\\d+) (\\w): (\\w+)"
    private val regex = Regex(pattern)
    val input by lazy {
        fileFromResources("day2/password.txt")
            ?.readLines(StandardCharsets.US_ASCII)
            ?.mapNotNull {
                val matches = regex.matchEntire(it)
                val min = matches?.groupValues?.get(1)?.toInt()
                val max = matches?.groupValues?.get(2)?.toInt()
                val letter = matches?.groupValues?.get(3)?.single()
                val password = matches?.groupValues?.get(4)

                if (min != null && max != null && letter != null && password != null){
                    PasswordRule(min, max, letter, password)
                } else null
            }
    }
}