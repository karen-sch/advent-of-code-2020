@file:Suppress("EXPERIMENTAL_UNSIGNED_LITERALS")

package day14

import common.fileFromResources

/*
mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
mem[8] = 11
mem[7] = 101
mem[8] = 0
 */
val MASK_REGEX = Regex("([10X]+)")
val MEM_MASK = Regex("mem\\[(\\d+)\\] = (\\d+)")

@ExperimentalUnsignedTypes
fun main() {
    Day14.input?.let {
        val result1 = parseInputPart1(it)
        println("Part 1: $result1")

        val result2 = parseInputPart2(it)
        println("Part 2: $result2")
    }
}


@ExperimentalUnsignedTypes
fun parseInputPart1(lines: List<String>): ULong {
    val memory = HashMap<ULong, ULong>()
    var mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"

    lines.forEach { line ->
        if (line.startsWith("mask")) {
            MASK_REGEX.find(line)?.groupValues?.get(1)?.let {
                mask = it
            }
        } else if (line.startsWith("mem")) {
            MEM_MASK.find(line)?.let {
                val (address, value) = it.destructured
                val maskedValue = applyMaskPart1(value.toULong(), mask)
                memory[address.toULong()] = maskedValue
            }
        }
    }

    return memory.values.sum()
}

@ExperimentalUnsignedTypes
fun parseInputPart2(lines: List<String>): ULong {
    val memory = HashMap<ULong, ULong>()
    var mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"

    lines.forEach { line ->
        if (line.startsWith("mask")) {
            MASK_REGEX.find(line)?.groupValues?.get(1)?.let {
                mask = it
            }
        } else if (line.startsWith("mem")) {
            MEM_MASK.find(line)?.let {
                val (address, value) = it.destructured
                applyMaskPart2(address.toULong(), value.toULong(), mask, memory)
            }
        }
    }

    return memory.values.sum()
}

@ExperimentalUnsignedTypes
fun applyMaskPart2(address: ULong, number: ULong, mask: String, memory: HashMap<ULong, ULong>) {
    val one: ULong = 1u
    //var solution: ULong = 0u
    val solutions = mutableListOf<ULong>(0u)
    (0..35).forEach { bitAt ->
        val bit = (address shr bitAt) and one
        val maskAtIndex = mask[mask.lastIndex - bitAt]
        when (maskAtIndex) {
            '0' -> {
                solutions.forEachIndexed { i, solution ->
                    solutions[i] = solution or (bit shl bitAt)
                }
            }
            '1' -> {
                solutions.forEachIndexed { i, solution ->
                    solutions[i] = solution or (1.toULong() shl bitAt)
                }
            }
            'X' -> {
                val additionalSolutions = mutableListOf<ULong>()
                solutions.forEachIndexed { i, solution ->
                    solutions[i] = solution or (0.toULong() shl bitAt)
                    additionalSolutions.add(solution or (1.toULong() shl bitAt))
                }
                solutions.addAll(additionalSolutions)
            }
        }
    }

    solutions.forEach { solution ->
        memory[solution] = number
    }

}

@ExperimentalUnsignedTypes
fun applyMaskPart1(number: ULong, mask: String): ULong {
    val one: ULong = 1u
    var solution: ULong = 0u
    (0..35).forEach { bitAt ->
        val bit = (number shr bitAt) and one
        val maskAtIndex = mask[mask.lastIndex - bitAt]
        solution = if (maskAtIndex != 'X') {
            val maskValue = Integer.parseInt(maskAtIndex.toString()).toULong()
            solution or (maskValue shl bitAt)
        } else {
            solution or (bit shl bitAt)
        }
    }
    return solution
}

object Day14 {
    val input by lazy {
        fileFromResources("day14/input.txt")?.readLines()
    }
}