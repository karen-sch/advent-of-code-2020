package day7

import common.fileFromResources
import org.junit.Test
import kotlin.test.assertEquals

class Day7KtTest {

    val input by lazy {
        fileFromResources("day7/test_bags.txt")
    }

    val input2 by lazy {
        fileFromResources("day7/test_bags2.txt")
    }


    @Test
    fun `part1 works correctly`() {
        val bags = parseBags(input!!.readLines())

        val count = countContainingBags("shiny gold", bags)
        assertEquals(4, count)
    }

    @Test
    fun `part 2 works correctly`() {

        val bags = parseBags(input2!!.readLines())

        val count = countContainedBags("shiny gold", bags)
        assertEquals(126, count)

    }

}