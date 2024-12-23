package aockt.y2024

import io.github.jadarma.aockt.core.Solution
import java.util.LinkedList
import java.util.Queue

class Y2024D20 : Solution {
    private val debug = true
    private val cutOff = when (debug) {
        true -> 50
        false -> 100
    }

    override fun partOne(input: String): Any {
        val grid = parseGrid(input)
        val (start, end) = findStartAndEnd(grid)
        val baselineSteps = findShortestPath(grid, start, end)
        val cheats = findCheatPaths(grid, start, end, baselineSteps, 2)
        return cheats.filterValues { it >= cutOff }
            .onEach { if (debug) println(it) }
            .count()
    }

    override fun partTwo(input: String): Any {
        val grid = parseGrid(input)
        val (start, end) = findStartAndEnd(grid)
        val baselineSteps = findShortestPath(grid, start, end)
        val cheats = findCheatPaths(grid, start, end, baselineSteps, 20)
        return cheats.filterValues { it >= cutOff }
            .onEach { if (debug) println(it) }
            .count()
    }

    private fun findShortestPath(grid: Array<CharArray>, start: Pair<Int, Int>, end: Pair<Int, Int>): Int {
        val directions = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
        val queue: Queue<Triple<Int, Int, Int>> = LinkedList()
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

                if (nx in grid.indices && ny in grid[0].indices && grid[nx][ny] != '#' && (nx to ny !in visited)) {
                    queue.add(Triple(nx, ny, steps + 1))
                }
            }
        }

        return -1 // Return -1 if no path is found
    }

    private fun findCheatPaths(
        grid: Array<CharArray>,
        start: Pair<Int, Int>,
        end: Pair<Int, Int>,
        baselineSteps: Int,
        cheatSteps: Int
    ): HashMap<Pair<Pair<Int, Int>, Pair<Int, Int>>, Int> {
        val directions = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
        val cheatPaths = hashMapOf<Pair<Pair<Int, Int>, Pair<Int, Int>>, Int>()

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == '#') {
                    val queue: Queue<Triple<Int, Int, Int>> = LinkedList()
                    val visited = mutableSetOf<Pair<Int, Int>>()
                    queue.add(Triple(i, j, 0))

                    while (queue.isNotEmpty()) {
                        val (x, y, steps) = queue.poll()

                        if (steps in 1..cheatSteps && grid[x][y] == '.') {
                            val cheatKey = Pair(Pair(i, j), Pair(x, y))
                            if (!cheatPaths.containsKey(cheatKey)) {
                                val stepsWithCheat = findShortestPathWithCheat(grid, start, end, cheatKey)
                                if (stepsWithCheat != -1) {
                                    cheatPaths[cheatKey] = baselineSteps - stepsWithCheat
                                }
                            }
                        }

                        if (steps < cheatSteps) {
                            for ((dx, dy) in directions) {
                                val nx = x + dx
                                val ny = y + dy

                                if (nx in grid.indices && ny in grid[0].indices && (nx to ny !in visited)) {
                                    visited.add(nx to ny)
                                    queue.add(Triple(nx, ny, steps + 1))
                                }
                            }
                        }
                    }
                }
            }
        }

        return cheatPaths
    }

    private fun findShortestPathWithCheat(
        grid: Array<CharArray>,
        start: Pair<Int, Int>,
        end: Pair<Int, Int>,
        cheat: Pair<Pair<Int, Int>, Pair<Int, Int>>
    ): Int {
        val directions = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
        val queue: Queue<Triple<Int, Int, Int>> = LinkedList()
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

                if (nx in grid.indices && ny in grid[0].indices) {
                    if (grid[nx][ny] != '#' || (nx to ny == cheat.first) || (nx to ny == cheat.second)) {
                        queue.add(Triple(nx, ny, steps + 1))
                    }
                }
            }
        }

        return -1 // Return -1 if no path is found
    }

    private fun parseGrid(input: String): Array<CharArray> {
        return input.lines().map { it.toCharArray() }.toTypedArray()
    }

    private fun findStartAndEnd(grid: Array<CharArray>): Pair<Pair<Int, Int>, Pair<Int, Int>> {
        var start = Pair(0, 0)
        var end = Pair(0, 0)
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                when (grid[i][j]) {
                    'S' -> start = Pair(i, j)
                    'E' -> end = Pair(i, j)
                }
            }
        }
        return Pair(start, end)
    }
}