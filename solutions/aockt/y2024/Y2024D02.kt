package aockt.y2024

import io.github.jadarma.aockt.core.Solution

class Y2024D02 : Solution {
    override fun partOne(input: String): Int {
        return input.splitToSequence("\n")
            .map { checkSafety(it) }
            .map { if (it) 1 else 0 }
            .sum()
    }

    /**
     * The input is safe if it is a list of integers separated by spaces that
     * are in either ascending or descending order
     * AND adjacent numbers are no less than 1 and no more than 3 apart
     */
    private fun checkSafety(rawInputString: String) : Boolean {
        val rowToCheck = rawInputString.split(" ").map { it.toInt() }

        val isAscending = rowToCheck.zipWithNext().all { (a, b) -> a <= b }
        val isDescending = rowToCheck.zipWithNext().all { (a, b) -> a >= b }

        if (!isAscending && !isDescending) {
            return false
        }
        if (isAscending) {
            return rowToCheck.zipWithNext().all { (a, b) -> b - a in 1..3 }
        }
        return rowToCheck.zipWithNext().all { (a, b) -> a - b in 1..3 }
    }

    override fun partTwo(input: String): Long {
        return input.splitToSequence("\n")
            .sumOf { line ->
                if (checkSafety(line)) 1L
                else {
                    val rowToCheck = line.split(" ")
                    rowToCheck.indices.firstOrNull { i ->
                        checkSafety(
                            rowToCheck.filterIndexed { index, _ -> index != i }
                            .joinToString(" ")
                        )
                    }?.let { 1L } ?: 0L
                }
            }
    }
}