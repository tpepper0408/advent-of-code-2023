package aockt.y2022

import io.github.jadarma.aockt.core.Solution

class Y2022D03 : Solution {

    override fun partOne(input: String): Int {
        return input.splitToSequence("\n")
            .map { line ->
                val middle = line.length / 2
                val firstHalf = line.substring(0, middle).toSet()
                val secondHalf = line.substring(middle).toSet()
                firstHalf.intersect(secondHalf).firstOrNull()?.let { convertToCode(it) } ?: 0
            }
            .sum()
    }

    private fun convertToCode(it: Char) : Int {
        return when (it) {
            in 'a'..'z' -> it - 'a' + 1
            in 'A'..'Z' -> it - 'A' + 27
            else -> 0
        }
    }

    override fun partTwo(input: String): Int {
        return input.splitToSequence("\n")
            .chunked(3)
            .map { group ->
                if (group.size == 3) {
                    val commonChar = group[0].toSet()
                        .intersect(group[1].toSet())
                        .intersect(group[2].toSet())
                        .firstOrNull()
                    commonChar?.let { convertToCode(it) } ?: 0
                } else {
                    0
                }
            }.sum()
    }
}