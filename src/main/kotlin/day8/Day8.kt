package day8

import common.fileFromResources

fun main() {
    Day8.input?.let {
        val result = executeInstructionsPart1(it.readLines())
        println("Part 1: $result")

        val result2 = executeInstructionsPart2(it.readLines())
        println("Part 2: $result2")
    }
}

fun executeInstructionsPart1(lines: List<String>): Int {
    val alreadyExecuted = HashSet<Int>()
    var currentIndex = 0
    var accumulator = 0
    while (currentIndex !in alreadyExecuted) {
        alreadyExecuted.add(currentIndex)
        val line = lines[currentIndex]
        val (instruction, value) = line.split(" ")

        when (instruction) {
            "nop" -> currentIndex++
            "acc" -> {
                accumulator += value.toInt()
                currentIndex++
            }
            "jmp" -> {
                currentIndex += value.toInt()
            }
        }
    }

    return accumulator
}

fun executeInstructionsPart2(lines: List<String>): Int {
    var nthNopOrJmp = 0

    while (true) {
        val alreadyExecuted = HashSet<Int>()
        var currentIndex = 0
        var accumulator = 0
        var countNopAndJmp = 0

        while (currentIndex !in alreadyExecuted) {
            alreadyExecuted.add(currentIndex)
            if (currentIndex >= lines.size || lines[currentIndex].isBlank()) {
                // program terminates
                return accumulator
            }
            val line = lines[currentIndex]
            var (instruction, value) = line.split(" ")

            if (instruction == "nop" || instruction == "jmp"){
                if (countNopAndJmp == nthNopOrJmp) {
                    instruction = if (instruction == "nop") "jmp" else "nop"
                }
                countNopAndJmp++
            }

            when (instruction) {
                "nop" -> currentIndex++
                "acc" -> {
                    accumulator += value.toInt()
                    currentIndex++
                }
                "jmp" -> {
                    currentIndex += value.toInt()
                }
            }
        }
        nthNopOrJmp++
    }

}


object Day8 {
    val input by lazy {
        fileFromResources("day8/instructions.txt")
    }
}