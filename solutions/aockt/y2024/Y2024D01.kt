package aockt.y2024

import io.github.jadarma.aockt.core.Solution
import kotlin.math.abs

class Y2024D01 :Solution {
    override fun partOne(input: String): Int {
        val (leftList, rightList) = parseInputToPairs(input)
        return calculateSumOfDifferences(rightList, leftList)
    }

    override fun partTwo(input: String): Any {
        val (leftList, rightList) = parseInputToPairs(input)
        return calculateProductSum(leftList, rightList)
    }

    /**
     * Parse input to two lists of integers where the left list and right list
     * are returned in their original order.
     * The input comes in the form:
     * 3   4
     * 4   3
     * 2   5
     * 1   3
     * 3   9
     * 3   3
     */
    private fun parseInputToPairs(input: String) = input.splitToSequence('\n')
        .map { line ->
            val (a, b) = line.split(("\\s+").toRegex()).map { it.toInt() }
            a to b
        }
        .unzip()

    /**
     * Calculate the sum of the differences between the two lists after
     * sorting them into numerical order.
     */
    private fun calculateSumOfDifferences(
        rightList: List<Int>,
        leftList: List<Int>
    ): Int {
        var sum = 0
        val sortedRightList = rightList.sorted()
        leftList.sorted().forEachIndexed { index, value ->
            val difference = abs(value - sortedRightList[index])
            sum += difference
        }

        return sum
    }

    /**
     * For each element in the left list, calculate the product of the element
     * and the number of times it appears in the right list.
     */
    private fun calculateProductSum(
        leftList: List<Int>,
        rightList: List<Int>
    ): Int {
        var sum = 0
        leftList.forEach {
            sum += it * rightList.count { right -> it == right }
        }
        return sum
    }
}