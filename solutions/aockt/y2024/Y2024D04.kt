package aockt.y2024

import io.github.jadarma.aockt.core.Solution

class Y2024D04 : Solution {
    override fun partOne(input: String): Int {
        val grid = input.split("\n").map { it.toCharArray() }
        val word = "XMAS"
        val directions = listOf(
            Pair(0, 1), Pair(1, 0), Pair(1, 1), Pair(1, -1), // right, down, down-right, down-left
            Pair(0, -1), Pair(-1, 0), Pair(-1, -1), Pair(-1, 1) // left, up, up-left, up-right
        )
        var count = 0
        for (x in grid.indices) {
            for (y in grid[0].indices) {
                for ((dx, dy) in directions) {
                    if (isWordAt(grid, word, x, y, dx, dy)) {
                        count++
                    }
                }
            }
        }

        return count
    }

    override fun partTwo(input: String): Int {
        val grid = input.split("\n").map { it.toCharArray() }
        val word = "MAS"
        val directions = listOf(
            Pair(1, 1), Pair(1, -1), // down-right, down-left
            Pair(-1, 1), Pair(-1, -1) // up-right, up-left
        )

        var count = 0
        for (x in grid.indices) {
            for (y in grid[0].indices) {
                if (grid[x][y] == 'A') {
                    for ((dx1, dy1) in directions) {
                        if (isWordAt(grid, word, x - dx1, y - dy1, dx1, dy1)) {
                            for ((dx2, dy2) in directions) {
                                if ((dx1 != dx2 || dy1 != dy2) && isWordAt(grid, word, x - dx2, y - dy2, dx2, dy2)) {
                                    count++
                                }
                            }
                        }
                    }
                }
            }
        }

        //I'm not happy with this division,
        // I wanted to eliminate the duplicate check but my brain isn't working
        return count /2
    }

    private fun isWordAt(grid: List<CharArray>, word: String, x: Int, y: Int, dx: Int, dy: Int): Boolean {
        for (i in word.indices) {
            val nx = x + i * dx
            val ny = y + i * dy
            if (nx !in grid.indices || ny !in grid[0].indices || grid[nx][ny] != word[i]) {
                return false
            }
        }
        return true
    }
}