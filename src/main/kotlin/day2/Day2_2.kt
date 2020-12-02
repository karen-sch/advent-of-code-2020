package day2

fun howManyMatchPositionRule(passwordRules: List<PasswordRule>): Int {
    var matches = 0
    for (rule in passwordRules) {
        if ((rule.password[rule.num1 - 1] == rule.letter)
                .xor(rule.password[rule.num2 - 1] == rule.letter)
        ) {
            matches++
        }
    }
    return matches
}

fun main() {
    Day2.input?.let {
        val result = howManyMatchPositionRule(it)
        println(result)
    }
}