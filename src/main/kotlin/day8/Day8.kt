package day8

import common.fileFromResources
import java.lang.IllegalArgumentException

const val NOP = "nop"
const val ACC = "acc"
const val JMP = "jmp"

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
    var index = 0
    var accumulator = 0
    while (index !in alreadyExecuted) {
        alreadyExecuted.add(index)
        val line = lines[index]
        val (instruction, value) = line.split(" ")

        when (instruction) {
            NOP -> index++
            ACC -> {
                accumulator += value.toInt()
                index++
            }
            JMP -> index += value.toInt()
        }
    }

    return accumulator
}

fun executeInstructionsPart2(lines: List<String>): Int {
    generateSequence(1) { it + 1 }.forEach { nopOrJmpToSwitch ->
        val alreadyExecuted = HashSet<Int>()
        var index = 0
        var accumulator = 0
        var nopOrJmpCount = 0

        while (index !in alreadyExecuted) {
            alreadyExecuted.add(index)
            if (index == lines.size) {
                // program terminates
                return accumulator
            }
            val line = lines[index]
            var (instruction, value) = line.split(" ")

            if (instruction in setOf(NOP, JMP)) {
                nopOrJmpCount++
                if (nopOrJmpCount == nopOrJmpToSwitch) {
                    instruction = if (instruction == NOP) JMP else NOP
                }
            }

            when (instruction) {
                NOP -> index++
                ACC -> {
                    accumulator += value.toInt()
                    index++
                }
                JMP -> index += value.toInt()
            }
        }
    }
    throw IllegalArgumentException("Program never terminates")
}


object Day8 {
    val input by lazy {
        fileFromResources("day8/instructions.txt")
    }
}