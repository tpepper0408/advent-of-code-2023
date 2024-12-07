package aockt.y2022

import io.github.jadarma.aockt.core.Solution

class Y2022D04 : Solution {
    override fun partOne(input: String): Any {
        return input.splitToSequence("\n")
            .map { line ->
                val (range1, range2) = line.split(",").map {
                    val (start, end) = it.split("-").map(String::trim).map(String::toInt)
                    start..end
                }
                if (range1.contains(range2) || range2.contains(range1)) 1 else 0
            }
            .sum()
    }

    private fun IntRange.contains(other: IntRange): Boolean {
        return this.first <= other.first && this.last >= other.last
    }

    override fun partTwo(input: String): Any {
        return input.splitToSequence("\n")
            .map { line ->
                val (range1, range2) = line.split(",").map {
                    val (start, end) = it.split("-").map(String::trim).map(String::toInt)
                    start..end
                }
                if (range1.intersects(range2) || range2.intersects(range1)) 1 else 0
            }
            .sum()
    }

    private fun IntRange.intersects(other: IntRange): Boolean {
        return this.first <= other.last && this.last >= other.first
    }
}