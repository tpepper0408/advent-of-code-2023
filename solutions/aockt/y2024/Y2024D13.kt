package aockt.y2024

import io.github.jadarma.aockt.core.Solution

class Y2024D13 : Solution {

    private val buttonAPattern = Regex("""Button A: X\+(\d+), Y\+(\d+)""")
    private val buttonBPattern = Regex("""Button B: X\+(\d+), Y\+(\d+)""")
    private val prizePattern = Regex("""Prize: X=(\d+), Y=(\d+)""")

    override fun partOne(input: String): Any {
        return input.splitToSequence("\n\n")
            .map { group ->
                val lines = group.lines()
                val buttonA = buttonAPattern.find(lines[0])!!.destructured.toList().map { it.toLong() }
                val buttonB = buttonBPattern.find(lines[1])!!.destructured.toList().map { it.toLong() }
                val prize = prizePattern.find(lines[2])!!.destructured.toList().map { it.toLong() }
                Game(buttonA[0], buttonA[1], buttonB[0], buttonB[1], prize[0], prize[1])
            }.map { it.fewestTokens() }
            .filter { it > 0 }
            .sum()
    }

    override fun partTwo(input: String): Any {
        return input.splitToSequence("\n\n")
            .map { group ->
                val lines = group.lines()
                val buttonA = buttonAPattern.find(lines[0])!!.destructured.toList().map { it.toLong() }
                val buttonB = buttonBPattern.find(lines[1])!!.destructured.toList().map { it.toLong() }
                val prize = prizePattern.find(lines[2])!!.destructured.toList().map { it.toLong() + 10000000000000 }
                Game(buttonA[0], buttonA[1], buttonB[0], buttonB[1], prize[0], prize[1])
            }.map { it.fewestTokens() }
            .filter { it > 0 }
            .sum()
    }

    data class Game(val aButtonX: Long, val aButtonY: Long, val bButtonX: Long, val bButtonY: Long, val prizeX: Long, val prizeY: Long) {
        fun fewestTokens(): Long {
            val bPressesNeeded = (prizeX * aButtonY - prizeY * aButtonX) / (bButtonX * aButtonY - bButtonY * aButtonX)
            val remainingXWhenBPressed = prizeX - bPressesNeeded * bButtonX

            //if we can't reach the prize with the remaining presses of button A
            if (remainingXWhenBPressed % aButtonX != 0L) return 0

            val aPressesNeeded = remainingXWhenBPressed / aButtonX
            val reachable = aPressesNeeded * aButtonY + bPressesNeeded * bButtonY == prizeY
            return if (reachable) 3 * aPressesNeeded + bPressesNeeded else 0
        }
    }
}