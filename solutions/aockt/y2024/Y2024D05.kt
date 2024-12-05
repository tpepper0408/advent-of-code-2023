package aockt.y2024

import io.github.jadarma.aockt.core.Solution

class Y2024D05 : Solution {
    override fun partOne(input: String): Int {
        val (rulesRaw, updatesRaw) = input.splitToSequence("\n\n").take(2).toList()
        val rules = rulesRaw.splitToSequence("\n")
            .map { Regex("(\\d+)\\|(\\d+)").find(it)!!.destructured.let { (a, b) -> a.toInt() to b.toInt() } }

        return updatesRaw.splitToSequence("\n")
            .map { it.split(",") }
            .map { it.map { it.toInt() } }
            .filter { followsRules(it, rules) }
            .map { it[it.size/2] }
            .sum()
    }

    override fun partTwo(input: String): Int {
        val (rulesRaw, updatesRaw) = input.splitToSequence("\n\n").take(2).toList()
        val rules = rulesRaw.splitToSequence("\n")
            .map { Regex("(\\d+)\\|(\\d+)").find(it)!!.destructured.let { (a, b) -> a.toInt() to b.toInt() } }

        return updatesRaw.splitToSequence("\n")
            .map { it.split(",") }
            .map { it.map { it.toInt() } }
            .filter { !followsRules(it, rules) }
            .map { makeFollowRules(it, rules) }
            .map { it[it.size/2] }
            .sum()
    }

    private fun makeFollowRules(it: List<Int>, rules: Sequence<Pair<Int, Int>>) : List<Int> {
        val result = it.toMutableList()

        result.sortWith { a, b ->
            when {
                rules.any { it.first == a && it.second == b } -> -1
                rules.any { it.first == b && it.second == a } -> 1
                else -> 0
            }
        }

        return result
    }

    private fun followsRules(update: List<Int>, rules: Sequence<Pair<Int,Int>>): Boolean {
        for ((firstNumber, secondNumber) in rules) {
            val firstNumberIndex = update.indexOf(firstNumber)
            val secondNumberIndex = update.indexOf(secondNumber)

            //if I find the numbers and they don't follow the rule, there's a problem
            if (firstNumberIndex != -1 && secondNumberIndex != -1) {
                if (firstNumberIndex > secondNumberIndex) {
                    return false
                }
            }
        }
        return true
    }

}