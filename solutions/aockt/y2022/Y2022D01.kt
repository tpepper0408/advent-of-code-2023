package aockt.y2022

import io.github.jadarma.aockt.core.Solution

/**
 * Solution for AoC 2022 Day 1: "Calorie Counting"
 */
class Y2022D01 : Solution {

    /**
     * Sums all groups of numbers
     */
    override fun partOne(input: String): Int {
        return input.splitToSequence("\n\n")
            .map { it.splitToSequence("\n").map { it.toInt() }.sum() }
            .max()
    }

    /**
     * Sums the three largest groups of numbers
     */
    override fun partTwo(input: String): Int {
        return input.splitToSequence("\n\n")
            .map { it.splitToSequence("\n").map { it.toInt() }.sum() }
            .sortedDescending()
            .take(3)
            .sum()
    }
}