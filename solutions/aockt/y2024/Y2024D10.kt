package aockt.y2024

import io.github.jadarma.aockt.core.Solution
import java.util.*

class Y2024D10 : Solution {
    override fun partOne(input: String): Any {
        val grid = input.lines().map { line -> line.map { it.toString().toInt() } }
        var totalScore = 0

        for (y in grid.indices) {
            for (x in grid[y].indices) {
                if (grid[y][x] == 0) {
                    totalScore += calculateTrailheadScore(grid, x, y)
                }
            }
        }

        return totalScore
    }

    /**
     * The score is calculated by finding the number of times
     * there is a path from the start point to the end point where a path only steps up in increments of +1.
     * The starting point is 0 and the end point is 9. I want the number of 9s that can be reached from the starting point.
     * The input grid is a 2D grid of integers. The x and y are the coordinates of the starting point to check.
     */
    private fun calculateTrailheadScore(grid: List<List<Int>>, x: Int, y: Int): Int {
        val visited = mutableSetOf<Pair<Int, Int>>()
        val queue = mutableListOf(Pair(x, y))
        var score = 0

        while (queue.isNotEmpty()) {
            val (currentX, currentY) = queue.removeFirst()
            if (visited.contains(Pair(currentX, currentY))) continue
            visited.add(Pair(currentX, currentY))

            if (grid[currentY][currentX] == 9) {
                score++
                continue
            }

            if (currentX + 1 < grid[currentY].size && grid[currentY][currentX + 1] == grid[currentY][currentX] + 1) {
                queue.add(Pair(currentX + 1, currentY))
            }
            if (currentX - 1 >= 0 && grid[currentY][currentX - 1] == grid[currentY][currentX] + 1) {
                queue.add(Pair(currentX - 1, currentY))
            }
            if (currentY + 1 < grid.size && grid[currentY + 1][currentX] == grid[currentY][currentX] + 1) {
                queue.add(Pair(currentX, currentY + 1))
            }
            if (currentY - 1 >= 0 && grid[currentY - 1][currentX] == grid[currentY][currentX] + 1) {
                queue.add(Pair(currentX, currentY - 1))
            }
        }

        return score
    }

    override fun partTwo(input: String): Any {
        val grid = input.lines().map { line -> line.map { it.toString().toInt() } }
        var totalPaths = 0

        for (y in grid.indices) {
            for (x in grid[y].indices) {
                if (grid[y][x] == 0) {
                    totalPaths += bfs(grid, x, y)
                }
            }
        }

        return totalPaths
    }

    private fun bfs(grid: List<List<Int>>, startX: Int, startY: Int): Int {
        val directions = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
        val queue: Queue<Triple<Int, Int, List<Pair<Int, Int>>>> = LinkedList()
        val visitedPaths = mutableSetOf<List<Pair<Int, Int>>>()
        queue.add(Triple(startX, startY, listOf(Pair(startX, startY))))

        while (queue.isNotEmpty()) {
            val (x, y, path) = queue.poll()
            val nextValue = grid[y][x] + 1

            if (nextValue > 9) {
                if (path !in visitedPaths) {
                    visitedPaths.add(path)
                }
                continue
            }

            for ((dx, dy) in directions) {
                val newX = x + dx
                val newY = y + dy
                if (newX in grid[0].indices && newY in grid.indices && grid[newY][newX] == nextValue) {
                    queue.add(Triple(newX, newY, path + Pair(newX, newY)))
                }
            }
        }

        return visitedPaths.size
    }
}