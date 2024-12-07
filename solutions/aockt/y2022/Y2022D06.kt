package aockt.y2022

import io.github.jadarma.aockt.core.Solution

class Y2022D06 : Solution {
    override fun partOne(input: String): Any {
        return findFirstOccurrenceForWindowSize(input, 4) // Return -1 if no such occurrence is found
    }

    private fun findFirstOccurrenceForWindowSize(input: String, windowSize: Int): Int {
        for (i in 0..input.length - windowSize) {
            val window = input.substring(i, i + windowSize)
            if (window.toSet().size == windowSize) {
                return i + windowSize
            }
        }
        return -1
    }

    override fun partTwo(input: String): Any {
        return findFirstOccurrenceForWindowSize(input, 14)
    }
}