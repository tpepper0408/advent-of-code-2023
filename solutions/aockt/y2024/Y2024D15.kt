package aockt.y2024

import io.github.jadarma.aockt.core.Solution

class Y2024D15 : Solution {
    override fun partOne(input: String): Any {
        val (mapInput, movesInput) = input.split("\n\n")
        val grid = mapInput.lines().map { it.toCharArray() }
        val moves = movesInput.trim()

        var robot = Position(0, 0)
        val boxes = mutableSetOf<Position>()
        val walls = mutableSetOf<Position>()

        // Initialize positions of robot, boxes, and walls
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                when (grid[i][j]) {
                    '@' -> robot = Position(i, j)
                    'O' -> boxes.add(Position(i, j))
                    '#' -> walls.add(Position(i, j))
                }
            }
        }

        // Define movement directions
        val directions = mapOf(
            '^' to Position(-1, 0),
            'v' to Position(1, 0),
            '<' to Position(0, -1),
            '>' to Position(0, 1)
        )

        // Simulate the robot's movements
        for (move in moves) {
            val direction = directions[move] ?: continue
            val newRobotPos = robot + direction

            if (canMove(newRobotPos, direction, boxes, walls)) {
                moveBoxes(newRobotPos, direction, boxes)
                robot = newRobotPos
            }
        }

        printFinalMap(grid, robot, boxes)

        // Calculate the sum of the box GPS coordinates
        return boxes.sumOf { (it.x * 100) + it.y }
    }

    private fun printFinalMap(grid: List<CharArray>, robot: Position, boxes: MutableSet<Position>) {
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                when {
                    Position(i, j) == robot -> print('@')
                    Position(i, j) in boxes -> print('O')
                    else -> print(grid[i][j])
                }
            }
            println()
        }
    }

    private fun canMove(
        newRobotPos: Position,
        direction: Position,
        boxes: Set<Position>,
        walls: Set<Position>
    ): Boolean {
        if (newRobotPos in walls) return false

        var nextPos = newRobotPos
        while (nextPos in boxes) {
            nextPos += direction
            if (nextPos in walls) return false
        }

        return true
    }

    private fun moveBoxes(newRobotPos: Position, direction: Position, boxes: MutableSet<Position>) {
        var nextPos = newRobotPos
        val boxPositionsToUpdate = mutableListOf<Position>()

        while (nextPos in boxes) {
            boxPositionsToUpdate.add(nextPos)
            nextPos += direction
        }

        boxes.removeAll(boxPositionsToUpdate.toSet())
        boxes.addAll(boxPositionsToUpdate.map { it + direction })
    }

    override fun partTwo(input: String): Any {
        val (mapInput, movesInput) = input.split("\n\n")
        val grid = mapInput.lines().map { it.toCharArray() }
        val moves = movesInput.trim()

        var robot = Position(0, 0)
        val boxes = mutableSetOf<Box>()
        val walls = mutableSetOf<Position>()

        // Initialize positions of robot, boxes, and walls where the width of every wall, box and free space
        // is 2 cells wide but remains the same height as the original map
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == '#') {
                    walls.add(Position(i, j * 2))
                    walls.add(Position(i, j * 2 + 1))
                } else if (grid[i][j] == 'O') {
                    boxes.add(Box(Position(i, j * 2), Position(i, j * 2 + 1)))
                } else if (grid[i][j] == '@') {
                    robot = Position(i, j * 2)
                }
            }
        }

        printMap(grid.size, grid[0].size * 2, robot, walls, boxes)

        // Define movement directions
        val directions = mapOf(
            '^' to Position(-1, 0),
            'v' to Position(1, 0),
            '<' to Position(0, -1),
            '>' to Position(0, 1)
        )

        val printProgress = grid.size < 20
        // Simulate the robot's movements
        for (move in moves) {
            val direction = directions[move] ?: continue
            val newRobotPos = robot + direction

            if (canMoveBigBoxes(newRobotPos, direction, boxes, walls)) {
                moveBigBoxes(newRobotPos, direction, boxes)
                robot = newRobotPos
                if (printProgress) printMap(grid.size, grid[0].size * 2, robot, walls, boxes)
            }
        }

        printMap(grid.size, grid[0].size * 2, robot, walls, boxes)

        // Calculate the sum of the box GPS coordinates
        return boxes.sumOf { (it.topLeft.x * 100) + it.topLeft.y }
    }

    private fun printMap(
        rows: Int,
        cols: Int,
        robot: Position,
        walls: MutableSet<Position>,
        boxes: MutableSet<Box>
    ) {
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                when {
                    Position(i, j) == robot -> print('@')
                    Position(i, j) in walls -> print('#')
                    boxes.any { it.topLeft == Position(i, j) } -> print('[')
                    boxes.any { it.bottomRight == Position(i, j) } -> print(']')
                    else -> print('.')
                }
            }
            println()
        }
    }

    /**
     * Check whether the robot can move, given that the boxes take up a width of 2 cells.
     * The position of boxes needs to check the second part of the box as well.
     */
    private fun canMoveBigBoxes(
        newRobotPos: Position,
        direction: Position,
        boxes: MutableSet<Box>,
        walls: MutableSet<Position>
    ): Boolean {
        if (newRobotPos in walls) return false

        var nextPos = newRobotPos
        while (boxes.any { it.topLeft == nextPos || it.bottomRight == nextPos }) {
            nextPos += direction
            if (nextPos in walls) return false
        }
        return true
    }

    private fun moveBigBoxes(
        newRobotPos: Position,
        direction: Position,
        boxes: MutableSet<Box>
    ) {
        val boxPositionsToUpdate = mutableListOf<Box>()

        // Find all boxes that need to be moved
        var nextPos = newRobotPos
        while (boxes.any { it.topLeft == nextPos || it.bottomRight == nextPos }) {
            val box = boxes.first { it.topLeft == nextPos || it.bottomRight == nextPos }
            boxPositionsToUpdate.add(box)
            nextPos += direction
        }

        // Move the boxes
        boxes.removeAll(boxPositionsToUpdate.toSet())
        val movedBoxes = boxPositionsToUpdate.map { Box(it.topLeft + direction, it.bottomRight + direction) }
        if (movedBoxes.none { movedBox -> boxes.any { it.topLeft == movedBox.topLeft || it.bottomRight == movedBox.bottomRight } }) {
            boxes.addAll(movedBoxes)
        } else {
            boxes.addAll(boxPositionsToUpdate) // Revert the move if it causes overlap
        }
    }

    data class Position(val x: Int, val y: Int) {
        operator fun plus(other: Position) = Position(x + other.x, y + other.y)
    }

    data class Box(val topLeft: Position, val bottomRight: Position)
}