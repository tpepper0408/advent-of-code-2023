package aockt.y2024

import io.github.jadarma.aockt.core.Solution

class Y2024D07 : Solution {
    override fun partOne(input: String): Long {
        return input.splitToSequence("\n").map {
            val (expectedResult, parts) = it.split(": ")
            val partsList = parts.split(" ").map { it.toLong() }
            if (canBeCalculatedAddMultiply(partsList, expectedResult.toLong())) {
                expectedResult.toLong()
            } else {
                0
            }
        }.sum()
    }

    /**
     * For the given parts, try to use the addition and multiplication operators to calculate the expected result.
     * The equation is always evaluated from left to right, not according to the usual operator precedence.
     * If the equation can result in the expectedResult, return true. Otherwise, return false.
     * For example:
     *  [10, 19] can be calculated as 10 * 19 = 190.
     *  [81, 40, 27] can be calculated as 81 + 40 * 27 = 3267.
     *  [17, 5] cannot be calculated as 17 + 5 or 17 * 5 = 22.
     */
    private fun canBeCalculatedAddMultiply(partsList: List<Long>, expectedResult: Long): Boolean {
        return calculate(partsList, 0, partsList[0], expectedResult)
    }

    private fun calculate(
        partsList: List<Long>,
        index: Int,
        currentResult: Long,
        expectedResult: Long
    ): Boolean {
        if (index == partsList.size - 1) {
            return currentResult == expectedResult
        }

        val nextIndex = index + 1
        val nextValue = partsList[nextIndex]

        // Try addition
        if (calculate(partsList, nextIndex, currentResult + nextValue, expectedResult)) {
            return true
        }

        // Try multiplication
        if (calculate(partsList, nextIndex, currentResult * nextValue, expectedResult)) {
            return true
        }

        return false
    }

    override fun partTwo(input: String): Long {
        return input.splitToSequence("\n").map {
            val (expectedResult, parts) = it.split(": ")
            val partsList = parts.split(" ").map { it.toLong() }
            if (canBeCalculatedAddMultiplyConcatenate(partsList, expectedResult.toLong())) {
                expectedResult.toLong()
            } else {
                0
            }
        }.sum()
    }

    /**
     * For the given parts, try to use the addition and multiplication operators to calculate the expected result.
     * The concatenation operator is also allowed which is shown as ||.
     * The equation is always evaluated from left to right, not according to the usual operator precedence.
     * If the equation can result in the expectedResult, return true. Otherwise, return false.
     * For example:
     *  [10, 19] can be calculated as 10 * 19 = 190.
     *  [81, 40, 27] can be calculated as 81 + 40 * 27 = 3267.
     *  [17, 5] cannot be calculated as 17 + 5 or 17 * 5 or 17 || 5 = 22.
     *  [6, 8, 6, 15] can be calculated as 6 * 8 || 6 * 15 = 7290.
     */
    private fun canBeCalculatedAddMultiplyConcatenate(partsList: List<Long>, expectedResult: Long): Boolean {
        return calculateWithConcatenate(partsList, 0, partsList[0], expectedResult)
    }

    private fun calculateWithConcatenate(
        partsList: List<Long>,
        index: Int,
        currentResult: Long,
        expectedResult: Long
    ): Boolean {
        if (index == partsList.size - 1) {
            return currentResult == expectedResult
        }

        val nextIndex = index + 1
        val nextValue = partsList[nextIndex]

        // Try addition
        if (calculateWithConcatenate(partsList, nextIndex, currentResult + nextValue, expectedResult)) {
            return true
        }

        // Try multiplication
        if (calculateWithConcatenate(partsList, nextIndex, currentResult * nextValue, expectedResult)) {
            return true
        }

        // Try concatenation
        val concatenated = "$currentResult$nextValue".toLong()
        if (calculateWithConcatenate(partsList, nextIndex, concatenated, expectedResult)) {
            return true
        }

        return false
    }
}