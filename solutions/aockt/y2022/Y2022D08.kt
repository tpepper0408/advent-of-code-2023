package aockt.y2022

import io.github.jadarma.aockt.core.Solution

class Y2022D08 : Solution {
    override fun partOne(input: String): Any {
        val grid = input.lines().map { it.map(Char::digitToInt) }
        val numRows = grid.size
        val numCols = grid[0].size
        var count = 0

        for (i in 0 until numRows) {
            for (j in 0 until numCols) {
                if (isVisible(grid, i, j, numRows, numCols)) {
                    count++
                }
            }
        }

        return count
    }

    override fun partTwo(input: String): Any {
        val grid = input.lines().map { it.map(Char::digitToInt) }
        val numRows = grid.size
        val numCols = grid[0].size
        val treeScores = mutableListOf<Int>()

        for (i in 0 until numRows) {
            for (j in 0 until numCols) {
                treeScores.add(calculateTreeScore(grid, i, j, numRows, numCols))
            }
        }
        return treeScores.maxOrNull()!!
    }

    /**
     * The tree score is the product of the number of visible trees in each of the four directions
     */
    private fun calculateTreeScore(grid: List<List<Int>>, xToCheck: Int, yToCheck: Int, numRows: Int, numCols: Int): Int {
        val directions = listOf(
            Pair(-1, 0), // Up
            Pair(1, 0), // Down
            Pair(0, -1), // Left
            Pair(0, 1) // Right
        )

        val score = directions.map { (dx, dy) ->
            var x = xToCheck + dx
            var y = yToCheck + dy
            var count = 0

            while (x in 0 until numRows && y in 0 until numCols) {
                count++
                if (grid[x][y] >= grid[xToCheck][yToCheck]) {
                    break
                }
                x += dx
                y += dy
            }
            count
        }.reduce(Int::times)
        return score
    }

    private fun isVisible(grid: List<List<Int>>, row: Int, col: Int, numRows: Int, numCols: Int): Boolean {
        val value = grid[row][col]

        // Check if the position is at the edge
        if (row == 0 || row == numRows - 1 || col == 0 || col == numCols - 1) return true

        // Check above
        if ((0 until row).all { grid[it][col] < value }) return true

        // Check below
        if ((row + 1 until numRows).all { grid[it][col] < value }) return true

        // Check left
        if ((0 until col).all { grid[row][it] < value }) return true

        // Check right
        if ((col + 1 until numCols).all { grid[row][it] < value }) return true

        return false
    }
}