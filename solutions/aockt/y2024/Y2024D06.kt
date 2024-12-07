package aockt.y2024

import io.github.jadarma.aockt.core.Solution

class Y2024D06 : Solution {
    override fun partOne(input: String): Int {
        val grid = input.split("\n").map { it.toCharArray() }
        val directions = listOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1)) // Up, Right, Down, Left
        var currentDirection = 0 // Start facing up
        var currentPosition = Pair(0, 0)

        // Find the initial position of the `^` icon
        outer@ for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == '^') {
                    currentPosition = Pair(i, j)
                    break@outer
                }
            }
        }

        val visitedPositions = mutableSetOf(currentPosition)

        while (true) {
            val (x, y) = currentPosition
            val (dx, dy) = directions[currentDirection]
            val newX = x + dx
            val newY = y + dy

            // Check if the new position is out of bounds
            if (newX !in grid.indices || newY !in grid[0].indices) {
                break
            }

            // Check if the next position is a `#`
            if (grid[newX][newY] == '#') {
                currentDirection = (currentDirection + 1) % 4 // Rotate right by 90 degrees
                continue
            }

            // Move to the new position
            currentPosition = Pair(newX, newY)
            visitedPositions.add(currentPosition)
        }

        return visitedPositions.size
    }

    override fun partTwo(input: String): Int {
        val grid = input.split("\n").map { it.toCharArray() }
        val directions = listOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1)) // Up, Right, Down, Left
        val startingDirection = 0 // Start facing up
        var currentPosition = Pair(0, 0)

        // Find the initial position of the `^` icon
        outer@ for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == '^') {
                    currentPosition = Pair(i, j)
                    break@outer
                }
            }
        }

        var count = 0

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == '.') {
                    // Add a `#` to the current position
                    grid[i][j] = '#'

                    // Reset the position and direction
                    var tempDirection = startingDirection
                    var tempPosition = currentPosition
                    //have to track the position and the direction we're travelling in to know whether
                    // we're in a loop or not
                    val visitedStates = mutableSetOf(Pair(tempPosition, tempDirection))
                    var hitEdge = false

                    while (true) {
                        val (x, y) = tempPosition
                        val (dx, dy) = directions[tempDirection]
                        val newX = x + dx
                        val newY = y + dy

                        // Check if the new position is out of bounds
                        if (newX !in grid.indices || newY !in grid[0].indices) {
                            hitEdge = true
                            break
                        }

                        // Check if the next position is a `#`
                        if (grid[newX][newY] == '#') {
                            tempDirection = (tempDirection + 1) % 4 // Rotate right by 90 degrees
                            continue
                        }

                        // Move to the new position
                        tempPosition = Pair(newX, newY)

                        // Check if the state has already been visited
                        if (!visitedStates.add(Pair(tempPosition, tempDirection))) {
                            break
                        }
                    }

                    // If the `^` did not hit the edge, increment the count
                    if (!hitEdge) {
                        count++
                    }

                    // Remove the `#` from the current position
                    grid[i][j] = '.'
                }
            }
        }

        return count
    }
}