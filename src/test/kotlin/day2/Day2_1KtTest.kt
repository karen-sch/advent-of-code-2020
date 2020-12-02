package day2

import kotlin.test.Test
import kotlin.test.assertEquals

class Day2_1KtTest {

    @Test
    fun `how many min max matches returns correct number of matches`() {
        val passwordRules = listOf(
            PasswordRule(1, 3, 'a', "abcde"),
            PasswordRule(1, 3, 'b', "cdefg"),
            PasswordRule(2, 9, 'c', "ccccccccc")
        )
        val result = howManyMatchMinMaxRule(passwordRules)
        assertEquals(2, result)
    }

    @Test
    fun `no matches works correctly`() {
        val passwordRules = listOf(
            PasswordRule(2, 3, 'a', "abcde"),
            PasswordRule(1, 3, 'b', "cdefg"),
            PasswordRule(2, 9, 'c', "cccccccccc")
        )
        val result = howManyMatchMinMaxRule(passwordRules)
        assertEquals(0, result)
    }

}