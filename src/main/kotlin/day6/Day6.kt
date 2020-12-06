package day6

import common.fileFromResources


fun main() {
    Day6.input?.let {
        val text = it.readText()
        val sum = parseGroupsGetAnswersByAnyone(text)
        println("Part 1: $sum")

        val sum2 = parseGroupsGetAnswersByEveryone(text)
        println("Part 2: $sum2")
    }
}

fun parseGroupsGetAnswersByAnyone(fileContent: String): Int {
    return fileContent.split(Regex("\n\n")).map {
        it.replace(Regex("\\s"), "").toCharArray().toSet()
    }.sumBy { it.size }
}

fun parseGroupsGetAnswersByEveryone(fileContent: String): Int {
    return fileContent.split(Regex("\n\n")).map { group ->
        val charToCount = HashMap<Char, Int>()
        val people = group.split(Regex("\n")).filter { it.isNotBlank() }
        people.forEach { answerForPerson ->
            answerForPerson.forEach { char ->
                charToCount[char] = charToCount[char]?.plus(1) ?: 1
            }
        }

        charToCount.values.count { it == people.size }
    }.sum()
}

object Day6 {
    val input by lazy {
        fileFromResources("day6/groups.txt")
    }
}