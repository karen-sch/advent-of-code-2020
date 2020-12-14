@file:Suppress("EXPERIMENTAL_UNSIGNED_LITERALS")

package day14

import common.fileFromResources
import java.lang.IllegalArgumentException

val MASK_REGEX = Regex("([10X]+)")
val MEM_MASK = Regex("mem\\[(\\d+)] = (\\d+)")

@ExperimentalUnsignedTypes
fun main() {
    Day14.input?.let {
        val result1 = parseInput(it, ::applyMaskPart1)
        println("Part 1: $result1")

        val result2 = parseInput(it, ::applyMaskPart2)
        println("Part 2: $result2")
    }
}

@ExperimentalUnsignedTypes
fun parseInput(
    lines: List<String>,
    applyMask: (address: ULong, number: ULong, mask: String, memory: HashMap<ULong, ULong>) -> Unit
): ULong {
    val memory = HashMap<ULong, ULong>()
    var mask = ""

    lines.forEach { line ->
        if (line.startsWith("mask")) {
            MASK_REGEX.find(line)?.groupValues?.get(1)?.let {
                mask = it
            }
        } else if (line.startsWith("mem")) {
            MEM_MASK.find(line)?.let {
                val (address, value) = it.destructured
                applyMask(address.toULong(), value.toULong(), mask, memory)
            }
        }
    }

    return memory.values.sum()
}

@ExperimentalUnsignedTypes
fun applyMaskPart1(address: ULong, number: ULong, mask: String, memory: HashMap<ULong, ULong>) {
    var solution: ULong = 0u
    (0..35).forEach { bitAt ->
        val bit = (number shr bitAt) and 1u
        val maskAtIndex = mask[mask.lastIndex - bitAt]
        solution = if (maskAtIndex != 'X') {
            val maskValue = Integer.parseInt(maskAtIndex.toString()).toULong()
            solution or (maskValue shl bitAt)
        } else {
            solution or (bit shl bitAt)
        }
    }
    memory[address] = solution
}

@ExperimentalUnsignedTypes
fun applyMaskPart2(initalAddress: ULong, number: ULong, mask: String, memory: HashMap<ULong, ULong>) {
    var addresses = listOf<ULong>(0u)
    (0..35).forEach { bitAt ->
        val bit = (initalAddress shr bitAt) and 1u
        addresses = when (mask[mask.lastIndex - bitAt]) {
            '0' -> addresses.map { address ->
                address or (bit shl bitAt)
            }
            '1' -> addresses.map { address ->
                address or (1uL shl bitAt)
            }
            'X' -> addresses.flatMap { address ->
                listOf(
                    address or (0uL shl bitAt),
                    address or (1uL shl bitAt)
                )
            }
            else -> throw IllegalArgumentException()
        }
    }

    addresses.forEach { address ->
        memory[address] = number
    }
}

object Day14 {
    val input by lazy {
        fileFromResources("day14/input.txt")?.readLines()
    }
}