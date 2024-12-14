package aockt.y2024

import io.github.jadarma.aockt.core.Solution

class Y2024D12 : Solution {

    override fun partOne(input: String): Any {
        val grid = input.lines().map { it.toCharArray() }
        val visited = Array(grid.size) { BooleanArray(grid[0].size) }
        var totalSum = 0

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (!visited[i][j]) {
                    val (perimeter, count) = floodFill(grid, visited, i, j, grid[i][j])
                    totalSum += perimeter * count
                }
            }
        }

        return totalSum
    }

    override fun partTwo(input: String): Any {
        val grid = input.lines().map { it.toCharArray() }
        val visited = Array(grid.size) { BooleanArray(grid[0].size) }
        var totalSum = 0

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (!visited[i][j]) {
                    val (perimeter, count) = floodFillSides(grid, visited, i, j, grid[i][j])
                    val sides = countSides(perimeter)
                    totalSum += sides * count
                }
            }
        }

        return totalSum
    }

    private fun floodFill(grid: List<CharArray>, visited: Array<BooleanArray>, x: Int, y: Int, char: Char): Pair<Int, Int> {
        val directions = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
        val stack = mutableListOf(Pair(x, y))
        var perimeter = 0
        var count = 0

        while (stack.isNotEmpty()) {
            val (cx, cy) = stack.removeAt(stack.size - 1)
            if (cx !in grid.indices || cy !in grid[0].indices || visited[cx][cy] || grid[cx][cy] != char) {
                continue
            }

            visited[cx][cy] = true
            count++

            for ((dx, dy) in directions) {
                val nx = cx + dx
                val ny = cy + dy
                if (nx !in grid.indices || ny !in grid[0].indices || grid[nx][ny] != char) {
                    perimeter++
                } else if (!visited[nx][ny]) {
                    stack.add(Pair(nx, ny))
                }
            }
        }

        return Pair(perimeter, count)
    }

    private fun floodFillSides(grid: List<CharArray>, visited: Array<BooleanArray>, x: Int, y: Int, char: Char): Pair<List<Pair<Int, Int>>, Int> {
        val directions = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
        val stack = mutableListOf(Pair(x, y))
        val perimeter = mutableListOf<Pair<Int, Int>>()
        var count = 0

        while (stack.isNotEmpty()) {
            val (cx, cy) = stack.removeAt(stack.size - 1)
            if (cx !in grid.indices || cy !in grid[0].indices || visited[cx][cy] || grid[cx][cy] != char) {
                continue
            }

            visited[cx][cy] = true
            count++

            for ((dx, dy) in directions) {
                val nx = cx + dx
                val ny = cy + dy
                if (nx !in grid.indices || ny !in grid[0].indices || grid[nx][ny] != char) {
                    perimeter.add(Pair(cx, cy))
                } else if (!visited[nx][ny]) {
                    stack.add(Pair(nx, ny))
                }
            }
        }

        return Pair(perimeter, count)
    }

    private fun countSides(perimeter: List<Pair<Int, Int>>): Int {
        if (perimeter.isEmpty()) return 0

        val uniqueEdges = mutableSetOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()

        for (i in perimeter.indices) {
            val current = perimeter[i]
            val neighbors = listOf(
                Pair(current.first, current.second + 1),
                Pair(current.first + 1, current.second),
                Pair(current.first, current.second - 1),
                Pair(current.first - 1, current.second)
            )

            for (neighbor in neighbors) {
                if (perimeter.contains(neighbor)) {
                    val edge = if (current.first < neighbor.first || (current.first == neighbor.first && current.second < neighbor.second)) {
                        Pair(current, neighbor)
                    } else {
                        Pair(neighbor, current)
                    }
                    uniqueEdges.add(edge)
                }
            }
        }

        return uniqueEdges.size
    }
}