package day6

import common.fileFromResources

val DOUBLE_NEW_LINE = Regex("\n\n")

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
    return fileContent.split(DOUBLE_NEW_LINE).map {
        it.toCharArray().distinct().filterNot(Char::isWhitespace)
    }.sumBy { it.size }
}

fun parseGroupsGetAnswersByEveryone(fileContent: String): Int {
    return fileContent.split(DOUBLE_NEW_LINE).map { group ->
        val people = group.lines().filter { it.isNotBlank() }.map { it.toSet() }
        group.toCharArray().distinct().filterNot(Char::isWhitespace).filter { char ->
            people.all { person -> person.contains(char) }
        }
    }.sumBy { it.size }
}

object Day6 {
    val input by lazy {
        fileFromResources("day6/groups.txt")
    }
}