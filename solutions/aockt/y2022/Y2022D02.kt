package aockt.y2022

import io.github.jadarma.aockt.core.Solution

class Y2022D02 : Solution {
    override fun partOne(input: String): Int {
        return input.splitToSequence("\n")
            .map { it.split(" ") }
            .map { calculateScoreForOpponentAndMove(it[0], it[1]) }
            .sum()
    }

    override fun partTwo(input: String): Int {
        return input.splitToSequence("\n")
            .map { it.split(" ") }
            .map { calulateScoreForOpponentMoveAndExpectedOutcome(it[0], it[1]) }
            .sum()
    }

    private fun calulateScoreForOpponentMoveAndExpectedOutcome(opponentMoveRaw: String, expectedOutcomeRaw: String) : Int{
        val expectedOutcome = parseOutcome(expectedOutcomeRaw)
        val move = getMoveForOpponentAndOutcome(expectedOutcome, parseMove(opponentMoveRaw))
        return getScoreForMove(move) + getScoreForOutcome(expectedOutcome)
    }

    private fun parseMove(opponentMove: String) = when (opponentMove) {
        "A" -> Move.ROCK
        "B" -> Move.PAPER
        "C" -> Move.SCISSORS
        else -> Move.ROCK
    }

    private fun parseOutcome(directive: String) = when (directive) {
        "X" -> Outcome.LOSE
        "Y" -> Outcome.DRAW
        "Z" -> Outcome.WIN
        else -> Outcome.LOSE
    }

    private fun getMoveForOpponentAndOutcome(directive: Outcome, opponentMove: Move) = when (directive) {
        Outcome.LOSE -> when (opponentMove) {
            Move.ROCK -> Move.SCISSORS
            Move.PAPER -> Move.ROCK
            Move.SCISSORS -> Move.PAPER
        }
        Outcome.DRAW -> when (opponentMove) {
            Move.ROCK -> Move.ROCK
            Move.PAPER -> Move.PAPER
            Move.SCISSORS -> Move.SCISSORS
        }
        Outcome.WIN -> when (opponentMove) {
            Move.ROCK -> Move.PAPER
            Move.PAPER -> Move.SCISSORS
            Move.SCISSORS -> Move.ROCK
        }
    }

    private fun getScoreForMove(move: Move): Int {
        return when (move) {
            Move.ROCK -> 1
            Move.PAPER -> 2
            Move.SCISSORS -> 3
        }
    }

    private fun getScoreForOutcome(directive: Outcome): Int {
        return when (directive) {
            Outcome.LOSE -> 0
            Outcome.DRAW -> 3
            Outcome.WIN -> 6
        }
    }

    private fun calculateScoreForOpponentAndMove(opponentMoveRaw: String, strategicMoveRaw: String): Int {
        val opponentMove = parseMove(opponentMoveRaw)
        val strategicMove = parseStrategicMove(strategicMoveRaw)
        val outcome = calculateOutcomeForOpponentAndMove(opponentMove, strategicMove)

       return getScoreForMove(strategicMove) + getScoreForOutcome(outcome)
    }

    private fun parseStrategicMove(strategicMove: String) = when (strategicMove) {
        "X" -> Move.ROCK
        "Y" -> Move.PAPER
        "Z" -> Move.SCISSORS
        else -> Move.ROCK
    }

    private fun calculateOutcomeForOpponentAndMove(opponentMove: Move, strategicMove: Move) : Outcome {
        return when (opponentMove) {
            Move.ROCK -> when (strategicMove) {
                Move.ROCK -> Outcome.DRAW
                Move.PAPER -> Outcome.WIN
                Move.SCISSORS -> Outcome.LOSE
            }

            Move.PAPER -> when (strategicMove) {
                Move.ROCK -> Outcome.LOSE
                Move.PAPER -> Outcome.DRAW
                Move.SCISSORS -> Outcome.WIN
            }

            Move.SCISSORS -> when (strategicMove) {
                Move.ROCK -> Outcome.WIN
                Move.PAPER -> Outcome.LOSE
                Move.SCISSORS -> Outcome.DRAW
            }
        }
    }
}

private enum class Move(val value: String) {
    ROCK("A"), PAPER("B"), SCISSORS("C")
}

private enum class Outcome(val value: String) {
    WIN("Z"), DRAW("Y"), LOSE("X")
}