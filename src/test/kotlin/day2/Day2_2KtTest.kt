package day2

import org.junit.Test
import kotlin.test.assertEquals

class Day2_2KtTest {

    @Test
    fun `how many by position matches returns correct number of matches`() {
        val passwordRules = listOf(
            PasswordRule(1, 3, 'a', "abcde"),
            PasswordRule(1, 3, 'b', "cdefg"),
            PasswordRule(2, 9, 'c', "ccccccccc")
        )
        val result = howManyMatchPositionRule(passwordRules)
        assertEquals(1, result)
    }

    @Test
    fun `no matches works correctly`() {
        val passwordRules = listOf(
            PasswordRule(2, 3, 'a', "abcde"),
            PasswordRule(1, 3, 'b', "cdcfg"),
            PasswordRule(2, 9, 'c', "cccccccccc")
        )
        val result = howManyMatchPositionRule(passwordRules)
        assertEquals(0, result)
    }
}