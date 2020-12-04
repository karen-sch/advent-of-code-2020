package day4

import common.fileFromResources
import java.io.File

data class Passport(
    val birthYear: Int, val issueYear: Int, val expirationYear: Int, val height: String, val hairColor: String,
    val eyeColor: String, val passportId: String, val countryId: String?
)


fun countValidPassports(passportsFile: File): Int {
    val parser = PassportParser(strictParsing = false)
    return parser.parse(passportsFile).size
}

fun countValidPassportsStrict(passportsFile: File): Int {
    val parser = PassportParser(strictParsing = true)
    return parser.parse(passportsFile).size
}

fun main() {
    Day4.input?.let {
        val result1 = countValidPassports(it)
        println("part 1: $result1")

        val result2 = countValidPassportsStrict(it)
        println("part 2: $result2")
    }
}

object Day4 {
    val input by lazy {
        fileFromResources("day4/passports.txt")
    }
}