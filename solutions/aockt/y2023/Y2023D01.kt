package aockt.y2023

import io.github.jadarma.aockt.core.Solution

class Y2023D01 : Solution {

    private fun parseInput(input: String): List<Int> =
            input
                .splitToSequence('\n')
            .map {processPart1(it) }
            .toList()

    private fun processPart1(it: String): Int {
        val numbers = it.toCharArray().filter { c -> c.isDigit() }
        return ("" + numbers.first() + numbers.last()).toInt()
    }

    override fun partOne(input: String) = parseInput(input).sum()

    override fun partTwo(input: String) = parseInputPart2(input).sum()

    private fun parseInputPart2(input: String): List<Int> =
        input
            .splitToSequence('\n')
            .map { processPart2Part2(it) }
            .toList()

    private fun processPart2(it: String) : Int {
        val pattern = "[0-9]|one|two|three|four|five|six|seven|eight|nine".toRegex()
        val allInstances = pattern.findAll(it)
        return (parseValue(allInstances.first().value) + parseValue(allInstances.last().value)).toInt()
    }

    private fun processPart2Part2(it: String) : Int {
        return processPart1(it.replace("one", "o1e")
            .replace("two", "t2o")
            .replace("three", "t3e")
            .replace("four", "f4r")
            .replace("five", "f5e")
            .replace("six", "s6x")
            .replace("seven", "s7n")
            .replace("eight", "e8t")
            .replace("nine", "n9e"))
    }

    private fun parseValue(value: String) : String {
        return when (value) {
            "one" -> "1"
            "two" -> "2"
            "three" -> "3"
            "four" -> "4"
            "five" -> "5"
            "six" -> "6"
            "seven" -> "7"
            "eight" -> "8"
            "nine" -> "9"
            else -> value
        }
    }
}