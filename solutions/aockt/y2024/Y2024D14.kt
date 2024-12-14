package aockt.y2024

import io.github.jadarma.aockt.core.Solution

class Y2024D14 : Solution {
    private val gridWidth = 101
    private val gridHeight = 103
    private val robotRegex = Regex("""p=(\d+),(\d+) v=(-?\d+),(-?\d+)""")

    data class Robot(var x: Int, var y: Int, val vx: Int, val vy: Int)

    override fun partOne(input: String): Any {
        val robots = input.lines().map { line ->
            val (px, py, vx, vy) = robotRegex.find(line)!!.destructured
            Robot(px.toInt(), py.toInt(), vx.toInt(), vy.toInt())
        }

        repeat(100) {
            robots.forEach { robot ->
                robot.x = (robot.x + robot.vx) % gridWidth
                robot.y = (robot.y + robot.vy) % gridHeight
                if (robot.x < 0) robot.x += gridWidth
                if (robot.y < 0) robot.y += gridHeight
            }
        }

        val midX = gridWidth / 2
        val midY = gridHeight / 2

        val quadrantCounts = IntArray(4)

        robots.forEach { robot ->
            if (robot.x == midX || robot.y == midY) {
                // Robots on the middle lines are discounted
                return@forEach
            }
            when {
                robot.x < midX && robot.y < midY -> quadrantCounts[0]++
                robot.x > midX && robot.y < midY -> quadrantCounts[1]++
                robot.x < midX && robot.y > midY -> quadrantCounts[2]++
                robot.x > midX && robot.y > midY -> quadrantCounts[3]++
            }
        }

        return quadrantCounts.reduce { acc, count -> acc * count }
    }

    override fun partTwo(input: String): Any {
        val robots = input.lines().map { line ->
            val (px, py, vx, vy) = robotRegex.find(line)!!.destructured
            Robot(px.toInt(), py.toInt(), vx.toInt(), vy.toInt())
        }

        var count = 0
        while(count < 10000) {
            //check that the robots are all in different positions and if they are then print the grid
            if (robots.distinctBy { it.x to it.y }.size == robots.size) {
                println("All robots are in different positions")
                println("After $count seconds:")
                println()
                printGrid(robots)
                println()
            }

            robots.forEach { robot ->
                robot.x = (robot.x + robot.vx) % gridWidth
                robot.y = (robot.y + robot.vy) % gridHeight
                if (robot.x < 0) robot.x += gridWidth
                if (robot.y < 0) robot.y += gridHeight
            }
            count++
        }
        return "Check the output above"
    }

    private fun printGrid(robots: List<Robot>) {
        val grid = Array(gridHeight) { CharArray(gridWidth) { '.' } }
        robots.forEach { robot -> grid[robot.y][robot.x] = '#' }
        grid.forEach { row -> println(row.joinToString("")) }
    }
}