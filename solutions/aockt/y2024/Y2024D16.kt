package aockt.y2024

import io.github.jadarma.aockt.core.Solution
import java.util.PriorityQueue

class Y2024D16 : Solution {
    override fun partOne(input: String): Any {
        val grid = input.lines().map { it.toCharArray() }
        val start = findPosition(grid, 'S')
        val end = findPosition(grid, 'E')

        return findShortestPath(grid, start, end).first
    }

    override fun partTwo(input: String): Any {
        val grid = input.lines().map { it.toCharArray() }
        val start = findPosition(grid, 'S')
        val end = findPosition(grid, 'E')

        val (shortestPathScore, visitedStates) = findShortestPath(grid, start, end)
        return findTilesVisitedForPossiblePaths(grid, start, end, shortestPathScore, visitedStates).size
    }

    private fun findPosition(grid: List<CharArray>, char: Char): Position {
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == char) {
                    return Position(i, j)
                }
            }
        }
        throw IllegalArgumentException("Character $char not found in the grid")
    }

    private fun findShortestPath(grid: List<CharArray>, start: Position, end: Position): Pair<Int, Map<Pair<Position, Int>, Int>> {
        val directions = listOf(
            Direction(Position(0, 1), 0),  // East
            Direction(Position(1, 0), 1),  // South
            Direction(Position(0, -1), 2), // West
            Direction(Position(-1, 0), 3)  // North
        )

        val queue = PriorityQueue<State>(compareBy { it.cost })
        val visited = mutableMapOf<Pair<Position, Int>, Int>()

        queue.add(State(start, 0, 0)) // Start facing East

        while (queue.isNotEmpty()) {
            val current = queue.poll()

            if (current.position == end) {
                return Pair(current.cost, visited)
            }

            val stateKey = Pair(current.position, current.direction)
            if (visited[stateKey] != null && visited[stateKey]!! <= current.cost) {
                continue
            }

            visited[stateKey] = current.cost

            // Move forward
            val forwardPos = current.position + directions[current.direction].delta
            if (isValid(grid, forwardPos)) {
                queue.add(State(forwardPos, current.direction, current.cost + 1))
            }

            // Turn left
            val leftDirection = (current.direction + 3) % 4
            queue.add(State(current.position, leftDirection, current.cost + 1000))

            // Turn right
            val rightDirection = (current.direction + 1) % 4
            queue.add(State(current.position, rightDirection, current.cost + 1000))
        }

        throw IllegalStateException("No path found")
    }

    private fun findTilesVisitedForPossiblePaths(grid: List<CharArray>, start: Position, end: Position, shortestPathScore: Int, visitedStates: Map<Pair<Position, Int>, Int>): Set<Position> {
        val directions = listOf(
            Direction(Position(0, 1), 0),  // East
            Direction(Position(1, 0), 1),  // South
            Direction(Position(0, -1), 2), // West
            Direction(Position(-1, 0), 3)  // North
        )

        val queue = PriorityQueue<State>(compareByDescending { it.cost })
        val tilesVisited = mutableSetOf<Position>()

        queue.add(State(end, 0, shortestPathScore)) // Start from the end

        while (queue.isNotEmpty()) {
            val current = queue.poll()

            if (current.position == start) {
                tilesVisited.add(current.position)
                continue
            }

            val stateKey = Pair(current.position, current.direction)
            if (visitedStates[stateKey] != null && visitedStates[stateKey]!! == current.cost) {
                tilesVisited.add(current.position)

                // Move backward
                val backwardPos = current.position - directions[current.direction].delta
                if (isValid(grid, backwardPos)) {
                    queue.add(State(backwardPos, current.direction, current.cost - 1))
                }

                // Turn left
                val leftDirection = (current.direction + 1) % 4
                queue.add(State(current.position, leftDirection, current.cost - 1000))

                // Turn right
                val rightDirection = (current.direction + 3) % 4
                queue.add(State(current.position, rightDirection, current.cost - 1000))
            }
        }

        return tilesVisited
    }

    private fun isValid(grid: List<CharArray>, pos: Position): Boolean {
        return pos.x in grid.indices && pos.y in grid[0].indices && grid[pos.x][pos.y] != '#'
    }

    data class Position(val x: Int, val y: Int) {
        operator fun plus(other: Position) = Position(x + other.x, y + other.y)
        operator fun minus(other: Position) = Position(x - other.x, y - other.y)
    }

    data class Direction(val delta: Position, val index: Int)

    data class State(val position: Position, val direction: Int, val cost: Int)
}