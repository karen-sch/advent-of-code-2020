package day4

import java.io.File

val heightRegex = Regex("(\\d+)(in|cm)")
val hairColorRegex = Regex("#[0-9a-f]+")
val validEyeColors = setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
const val passportIdLength = 9

class PassportParser(
    private val strictParsing: Boolean = true,
    private val requireCountryId: Boolean = false
) {
    private var birthYear: Int? = null
    private var issueYear: Int? = null
    private var expirationYear: Int? = null
    private var height: String? = null
    private var hairColor: String? = null
    private var eyeColor: String? = null
    private var passportId: String? = null
    private var countryId: String? = null

    private val passports = mutableListOf<Passport>()

    fun parse(file: File): List<Passport> {
        passports.clear()
        file.forEachLine { line ->
            if (line.isEmpty()) {
                addCurrentPassportIfValidAndReset()
            } else {
                line.split(" ").forEach { attribute ->
                    val key = attribute.substringBefore(":")
                    val value = attribute.substringAfter(":")
                    when (key) {
                        "byr" -> parseBirthYear(value)
                        "iyr" -> parseIssueYear(value)
                        "eyr" -> parseExpirationYear(value)
                        "hgt" -> parseHeight(value)
                        "hcl" -> parseHairColor(value)
                        "ecl" -> parseEyeColor(value)
                        "pid" -> parsePassportId(value)
                        "cid" -> parseCountryId(value)
                    }
                }
            }
        }

        addCurrentPassportIfValidAndReset()
        return passports.toList()
    }

    private fun parseBirthYear(value: String) {
        val by = value.toInt()
        if (by in 1920..2002 || !strictParsing) {
            birthYear = by
        }
    }

    private fun parseIssueYear(value: String) {
        val iy = value.toInt()
        if (!strictParsing || iy in 2010..2020) {
            issueYear = iy
        }
    }

    private fun parseExpirationYear(value: String) {
        val ey = value.toInt()
        if (!strictParsing || ey in 2020..2030) {
            expirationYear = ey
        }
    }

    private fun parseHeight(value: String) {
        if (!strictParsing) {
            height = value
            return
        }
        heightRegex.matchEntire(value)?.let { match ->
            val (heightString, unit) = match.destructured
            val heightInt = heightString.toInt()
            if ((unit == "cm" && heightInt in 150..193)
                || (unit == "in" && heightInt in 59..76)
            ) {
                height = value
            }
        }
    }

    private fun parseHairColor(value: String) {
        if (!strictParsing) {
            hairColor = value
            return
        }
        val match = hairColorRegex.matchEntire(value)
        if (match != null) {
            hairColor = value
        }
    }

    private fun parseEyeColor(value: String) {
        if (!strictParsing || value in validEyeColors) {
            eyeColor = value
        }
    }

    private fun parsePassportId(value: String) {
        if (!strictParsing || value.length == passportIdLength) {
            passportId = value
        }
    }

    private fun parseCountryId(value: String) {
        countryId = value
    }

    private fun addCurrentPassportIfValidAndReset() {
        if (birthYear != null && issueYear != null && expirationYear != null && height != null && hairColor != null
            && eyeColor != null && passportId != null && (countryId != null || !requireCountryId)
        ) {
            val passport = Passport(
                birthYear!!, issueYear!!, expirationYear!!, height!!, hairColor!!,
                eyeColor!!, passportId!!, countryId
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
    }
}