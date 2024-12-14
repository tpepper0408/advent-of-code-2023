package aockt.y2022

import io.github.jadarma.aockt.core.Solution

class Y2022D09 : Solution {
    override fun partOne(input: String): Any {
        return processInput(input, 2)
    }

    override fun partTwo(input: String): Any {
        return processInput(input, 10)
    }

    private fun processInput(input: String, knotCount: Int): Int {
        val visitedPositions = mutableSetOf<Pair<Int, Int>>()
        val knots = MutableList(knotCount) { Pair(0, 0) }
        visitedPositions.add(knots.last())

        input.lines().forEach { line ->
            val (direction, steps) = line.split(" ")
            repeat(steps.toInt()) {
                moveKnots(knots, direction)
                visitedPositions.add(knots.last())
            }
        }

        return visitedPositions.size
    }

    private fun moveKnots(knots: MutableList<Pair<Int, Int>>, direction: String) {
        knots[0] = when (direction) {
            "U" -> Pair(knots[0].first, knots[0].second + 1)
            "D" -> Pair(knots[0].first, knots[0].second - 1)
            "L" -> Pair(knots[0].first - 1, knots[0].second)
            "R" -> Pair(knots[0].first + 1, knots[0].second)
            else -> knots[0]
        }

        for (i in 1 until knots.size) {
            knots[i] = determineTailPosition(knots[i - 1], knots[i])
        }
    }

    private fun determineTailPosition(currentPosition: Pair<Int, Int>, tailPosition: Pair<Int, Int>): Pair<Int, Int> {
        val (currentX, currentY) = currentPosition
        val (tailX, tailY) = tailPosition

        if (currentPosition == tailPosition) return tailPosition

        val newTailX = when {
            currentX > tailX -> tailX + 1
            currentX < tailX -> tailX - 1
            else -> tailX
        }

        val newTailY = when {
            currentY > tailY -> tailY + 1
            currentY < tailY -> tailY - 1
            else -> tailY
        }

        if (Pair(newTailX, newTailY) == currentPosition) {
            return tailPosition
        }

        return Pair(newTailX, newTailY)
    }
}