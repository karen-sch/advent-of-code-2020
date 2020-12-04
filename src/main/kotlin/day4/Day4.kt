package day4

import common.fileFromResources
import java.io.File

data class Passport(
    val birthYear: Int, val issueYear: Int, val expirationYear: Int, val height: String, val hairColor: String,
    val eyeColor: String, val passportId: String, val countryId: String?
)


fun countValidPassports(passportsFile: File): Int {
    return passportsFile.parsePassports().size
}

fun main() {
    Day4.input?.let {
        val result = countValidPassports(it)
        println("part 1: $result")
    }
}

val heightRegex = Regex("(\\d+)(in|cm)")
val hairColorRegex = Regex("#[0-9a-f]+")
val validEyeColors = setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
const val passportIdLength = 9

fun File.parsePassports(requireCountryId: Boolean = false): List<Passport> {
    val passports = mutableListOf<Passport>()
    var birthYear: Int? = null
    var issueYear: Int? = null
    var expirationYear: Int? = null
    var height: String? = null
    var hairColor: String? = null
    var eyeColor: String? = null
    var passportId: String? = null
    var countryId: String? = null
    forEachLine { line ->
        if (line.isEmpty()) {
            if (birthYear != null && issueYear != null && expirationYear != null && height != null && hairColor != null
                && eyeColor != null && passportId != null
            ) {
                val passport = Passport(
                    birthYear!!,
                    issueYear!!,
                    expirationYear!!,
                    height!!,
                    hairColor!!,
                    eyeColor!!,
                    passportId!!,
                    countryId
                )
                passports.add(passport)
            }
            birthYear = null
            issueYear = null
            expirationYear = null
            height = null
            hairColor = null
            eyeColor = null
            passportId = null
            countryId = null

        } else {
            line.split(" ").forEach { attribute ->
                val key = attribute.substringBefore(":")
                val value = attribute.substringAfter(":")
                when (key) {
                    "byr" -> {
                        val by = value.toInt()
                        if (by in 1920..2002) {
                            birthYear = by
                        }

                    }
                    "iyr" -> {
                        val iy = value.toInt()
                        if (iy in 2010..2020) {
                            issueYear = iy
                        }
                    }
                    "eyr" -> {
                        val ey = value.toInt()
                        if (ey in 2020..2030) {
                            expirationYear = ey
                        }

                    }
                    "hgt" -> {
                        val match = heightRegex.matchEntire(value)
                        match?.let {
                            val (heightString, unit) = it.destructured
                            val heightNumber = heightString.toInt()
                            if ((unit == "cm" && heightNumber in 150..193)
                                || (unit == "in" && heightNumber in 59..76)
                            ) {
                                height = value
                            }
                        }


                    }
                    "hcl" -> {
                        val match = hairColorRegex.matchEntire(value)
                        if (match != null) {
                            hairColor = value
                        }
                    }
                    "ecl" -> {
                        if (value in validEyeColors){
                            eyeColor = value
                        }
                    }
                    "pid" -> {
                        if (value.length == passportIdLength) {
                            passportId = value
                        }
                    }
                    "cid" -> {
                        countryId = value
                    }
                }
            }

        }
    }

    if (birthYear != null && issueYear != null && expirationYear != null && height != null && hairColor != null
        && eyeColor != null && passportId != null
    ) {
        val passport = Passport(
            birthYear!!,
            issueYear!!,
            expirationYear!!,
            height!!,
            hairColor!!,
            eyeColor!!,
            passportId!!,
            countryId
        )
        passports.add(passport)
    }

    return passports
}

object Day4 {
    val input by lazy {
        fileFromResources("day4/passports.txt")
    }
}