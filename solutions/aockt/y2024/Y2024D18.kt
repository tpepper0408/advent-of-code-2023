package aockt.y2024

import io.github.jadarma.aockt.core.Solution
import java.util.PriorityQueue

class Y2024D18 : Solution {
    override fun partOne(input: String): Any {
        val (gridSize, depth) = when (input.lines()[0]) {
            "5,4" -> Pair(7, 12)
            "63,28" -> Pair(71, 1024)
            else -> throw IllegalArgumentException("Unknown grid size")
        }
        val (grid, start, end) = initializeGrid(input, gridSize, depth)
        return findShortestPath(grid, start, end)
    }

    override fun partTwo(input: String): Any {
        val (gridSize, depth) = when (input.lines()[0]) {
            "5,4" -> Pair(7, 12)
            "63,28" -> Pair(71, 1024)
            else -> throw IllegalArgumentException("Unknown grid size")
        }
        val (grid, start, end) = initializeGrid(input, gridSize, depth)
        input.lines().drop(depth)
            .forEach{
                val (x, y) = it.split(",").map { it.toInt() }
                grid[x][y] = true
                if (findShortestPath(grid, start, end) == -1) {
                    return "$x,$y"
                }
            }
        return "No solution found"
    }

    private fun initializeGrid(input: String, gridSize: Int, depth: Int): Triple<Array<Array<Boolean>>, Pair<Int, Int>, Pair<Int, Int>> {
        val lines = input.lines()

        val grid = Array(gridSize) { Array(gridSize) { false } }

        for (i in 0..<depth) {
            val (x, y) = lines[i].split(",").map { it.toInt() }
            grid[x][y] = true
        }
        return Triple(grid, 0 to 0, gridSize - 1 to gridSize - 1)
    }

    private fun findShortestPath(grid: Array<Array<Boolean>>, start: Pair<Int, Int>, end: Pair<Int, Int>): Int {
        val directions = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
        val queue = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.third })
        val visited = mutableSetOf<Pair<Int, Int>>()

        queue.add(Triple(start.first, start.second, 0))

        while (queue.isNotEmpty()) {
            val (x, y, steps) = queue.poll()

            if (x to y == end) return steps
            if (x to y in visited) continue

            visited.add(x to y)

            for ((dx, dy) in directions) {
                val nx = x + dx
                val ny = y + dy

                if (nx in grid.indices && ny in grid[0].indices && !grid[nx][ny] && (nx to ny !in visited)) {
                    queue.add(Triple(nx, ny, steps + 1))
                }
            }
        }

        return -1 // Return -1 if no path is found
    }
}