package aockt.y2024

import io.github.jadarma.aockt.core.Solution

class Y2024D19 : Solution {
    override fun partOne(input: String): Any {
        val lines = input.lines()
        val combinations = lines[0].split(", ").map { it.trim() }
        val patterns = lines.drop(2)

        return patterns.count { pattern -> canFormPattern(pattern, combinations) }
    }

    override fun partTwo(input: String): Any {
        val lines = input.lines()
        val combinations = lines[0].split(", ").map { it.trim() }
        val patterns = lines.drop(2)

        return patterns.sumOf { pattern -> countWaysToFormPattern(pattern, combinations) }
    }

    private fun canFormPattern(pattern: String, combinations: List<String>): Boolean {
        val dp = BooleanArray(pattern.length + 1) { false }
        dp[0] = true

        for (i in 0..pattern.length) {
            if (dp[i]) {
                for (combination in combinations) {
                    if (i + combination.length <= pattern.length && pattern.substring(i, i + combination.length) == combination) {
                        dp[i + combination.length] = true
                    }
                }
            }
        }

        return dp[pattern.length]
    }

    private fun countWaysToFormPattern(pattern: String, combinations: List<String>): Long {
        val dp = LongArray(pattern.length + 1) { 0 }
        dp[0] = 1

        for (i in 0..pattern.length) {
            if (dp[i] > 0) {
                for (combination in combinations) {
                    if (i + combination.length <= pattern.length && pattern.substring(i, i + combination.length) == combination) {
                        dp[i + combination.length] += dp[i]
                    }
                }
            }
        }

        return dp[pattern.length]
    }
}