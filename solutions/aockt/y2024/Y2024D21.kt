package aockt.y2024

import io.github.jadarma.aockt.core.Solution
import java.util.LinkedList
import java.util.Queue

class Y2024D21 : Solution {
    private val keypad = arrayOf(
        charArrayOf('7', '8', '9'),
        charArrayOf('4', '5', '6'),
        charArrayOf('1', '2', '3'),
        charArrayOf(' ', '0', 'A')
    )

    private val directions = listOf(
        Pair(-1, 0) to "^", // Up
        Pair(1, 0) to "v",  // Down
        Pair(0, -1) to "<", // Left
        Pair(0, 1) to ">"   // Right
    )

    private val directionPad = arrayOf(
        charArrayOf(' ', '^', 'A'),
        charArrayOf('<', 'v', '>')
    )

    private val directionPadDirections = listOf(
        Pair(-1, 0) to "^", // Up
        Pair(1, 0) to "v",  // Down
        Pair(0, -1) to "<", // Left
        Pair(0, 1) to ">"   // Right
    )

    override fun partOne(input: String): Any {
        val combinations = input.lines()
        val initialDirections = findButtonDirectionsForKeyPad(combinations)
        val optimizedDirections = optimizeDirections(initialDirections, 3)
        return optimizedDirections.keys
            .onEach {
                val directions = optimizedDirections[it]
                println("Combination: $it - Instructions $directions")
            }
            .sumOf {  it.filter { it != 'A' }.toInt() *
                optimizedDirections[it]!!.size }
    }

    private fun optimizeDirections(
        initialDirections: HashMap<String, MutableList<String>>,
        depth: Int
    ): HashMap<String, MutableList<String>> {
        val optimizedDirections = hashMapOf<String, MutableList<String>>()

        for ((combination, directions) in initialDirections) {
            val optimized = mutableListOf<String>()
            val tempDirections = mutableListOf<String>()

            for (direction in directions) {
                if (direction == "A") {
                    tempDirections.sort()
                    optimized.addAll(tempDirections)
                    optimized.add("A")
                    tempDirections.clear()
                } else {
                    tempDirections.add(direction)
                }
            }

            // Add any remaining directions after the last 'A'
            tempDirections.sort()
            optimized.addAll(tempDirections)

            optimizedDirections[combination] = optimized
        }

        return if (depth > 1) {
            val nextDirections = findButtonDirectionsForDirectionsPad(optimizedDirections)
            optimizeDirections(nextDirections, depth - 1)
        } else {
            optimizedDirections
        }
    }

    private fun findButtonDirectionsForKeyPad(
        combinations: List<String>
    ): HashMap<String, MutableList<String>> {
        val directionList = hashMapOf<String, MutableList<String>>()
        var currentPos = Pair(3, 2) // Starting position on 'A'

        for (combination in combinations) {
            for (char in combination) {
                val targetPos = findPosition(char)
                val path = findShortestPath(currentPos, targetPos)
                directionList.getOrPut(combination) { mutableListOf() }.addAll(path)
                directionList[combination]?.add("A") // Press 'A' to enter the number
                currentPos = targetPos
            }
        }

        return directionList
    }

    private fun findButtonDirectionsForDirectionsPad(
        initialDirections: HashMap<String, MutableList<String>>
    ): HashMap<String, MutableList<String>> {
        val directionPadList = hashMapOf<String, MutableList<String>>()
        var currentPos = Pair(0, 2) // Starting position on 'A'

        for ((combination, directions) in initialDirections) {
            for (direction in directions) {
                val targetPos = findDirectionPadPosition(direction)
                val path = findShortestPathOnDirectionPad(currentPos, targetPos)
                directionPadList.getOrPut(combination) { mutableListOf() }.addAll(path)
                directionPadList[combination]?.add("A") // Press 'A' to enter the direction
                currentPos = targetPos
            }
        }

        return directionPadList
    }

    override fun partTwo(input: String): Any {
        // Implement part two if needed
        return ""
    }

    private fun findPosition(char: Char): Pair<Int, Int> {
        for (i in keypad.indices) {
            for (j in keypad[i].indices) {
                if (keypad[i][j] == char) {
                    return Pair(i, j)
                }
            }
        }
        throw IllegalArgumentException("Invalid character: $char")
    }

    private fun findDirectionPadPosition(direction: String): Pair<Int, Int> {
        for (i in directionPad.indices) {
            for (j in directionPad[i].indices) {
                if (directionPad[i][j].toString() == direction) {
                    return Pair(i, j)
                }
            }
        }
        throw IllegalArgumentException("Invalid direction: $direction")
    }

    private fun findShortestPath(start: Pair<Int, Int>, end: Pair<Int, Int>): List<String> {
        val queue: Queue<Triple<Int, Int, List<String>>> = LinkedList()
        val visited = mutableSetOf<Pair<Int, Int>>()
        queue.add(Triple(start.first, start.second, emptyList()))

        while (queue.isNotEmpty()) {
            val (x, y, path) = queue.poll()

            if (x to y == end) return path
            if (x to y in visited) continue

            visited.add(x to y)

            for ((direction, dirString) in directions) {
                val nx = x + direction.first
                val ny = y + direction.second

                if (nx in keypad.indices && ny in keypad[0].indices && keypad[nx][ny] != ' ' && (nx to ny !in visited)) {
                    queue.add(Triple(nx, ny, path + dirString))
                }
            }
        }

        return emptyList() // Should never reach here if the input is valid
    }

    private fun findShortestPathOnDirectionPad(start: Pair<Int, Int>, end: Pair<Int, Int>): List<String> {
        val queue: Queue<Triple<Int, Int, List<String>>> = LinkedList()
        val visited = mutableSetOf<Pair<Int, Int>>()
        queue.add(Triple(start.first, start.second, emptyList()))

        while (queue.isNotEmpty()) {
            val (x, y, path) = queue.poll()

            if (x to y == end) return path
            if (x to y in visited) continue

            visited.add(x to y)

            for ((direction, dirString) in directionPadDirections) {
                val nx = x + direction.first
                val ny = y + direction.second

                if (nx in directionPad.indices && ny in directionPad[0].indices && directionPad[nx][ny] != ' ' && (nx to ny !in visited)) {
                    queue.add(Triple(nx, ny, path + dirString))
                }
            }
        }

        return emptyList() // Should never reach here if the input is valid
    }
}