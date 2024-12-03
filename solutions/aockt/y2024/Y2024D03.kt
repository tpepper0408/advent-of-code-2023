package aockt.y2024

import io.github.jadarma.aockt.core.Solution

class Y2024D03 :Solution {
    override fun partOne(input: String): Int {
        val regex = """mul\((\d+),(\d+)\)""".toRegex()
        return regex.findAll(input)
            .map { matchResult ->
                val (a, b) = matchResult.destructured
                a.toInt() * b.toInt()
            }
            .sum()
    }


    override fun partTwo(input: String): Int {
        val multiplicationRegex = """mul\((\d+),(\d+)\)""".toRegex()
        val doRegex = """do\(\)""".toRegex()
        val dontRegex = """don't\(\)""".toRegex()

        var inDoState = true
        var sum = 0

        val allInstructionsIndex = """do\(\)|don't\(\)|mul\((\d+),(\d+)\)""".toRegex()
        allInstructionsIndex.findAll(input).forEach { matchResult ->
            when {
                doRegex.matches(matchResult.value) -> inDoState = true
                dontRegex.matches(matchResult.value) -> inDoState = false
                inDoState && multiplicationRegex.matches(matchResult.value) -> {
                    val (a, b) = matchResult.destructured
                    sum += a.toInt() * b.toInt()
                }
            }
        }

        return sum
    }
}