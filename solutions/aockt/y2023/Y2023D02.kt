package aockt.y2023

import io.github.jadarma.aockt.core.Solution

private const val MAX_RED = 12

private const val MAX_BLUE = 14

private const val MAX_GREEN = 13

class Y2023D02 : Solution {
    override fun partOne(input: String) = findGames(input)
        .filter { it.isPossible() }
        .sumOf { it.number }

    private fun findGames(input: String): List<Game> {
        val games = input.lines()
            .map { line ->
            val number = "Game ([0-9]+):".toRegex().find(line)!!.groupValues[1].toInt()
            var blueCount = 0
            var redCount = 0
            var greenCount = 0
            line.substring(line.indexOf(':') + 1)
                .split(';')
                .forEach{ round ->
                    round.split(',')
                        .forEach { draw ->
                            "([0-9]+) blue".toRegex().find(draw)
                                ?.let { m ->
                                    val newBlue = m.groupValues[1].toInt()
                                    if (newBlue > blueCount) blueCount = newBlue
                                }
                            "([0-9]+) red".toRegex().find(draw)
                                ?.let { m ->
                                    val newRed = m.groupValues[1].toInt()
                                    if (newRed > redCount) redCount = newRed
                                }
                            "([0-9]+) green".toRegex().find(draw)
                                ?.let { m ->
                                    val newGreen = m.groupValues[1].toInt()
                                    if (newGreen > greenCount) greenCount = newGreen
                                }
                        }
                }
            Game(number, blueCount, redCount, greenCount)
        }
        return games.toList()
    }

    override fun partTwo(input: String) = findGames(input)
        .sumOf { it.power() }

    class Game(val number: Int, private val blue: Int, private val red: Int, private val green: Int) {
        fun isPossible(): Boolean {
            return red <= MAX_RED
                    && blue <= MAX_BLUE
                    && green <= MAX_GREEN
                    && red + blue + green < MAX_RED + MAX_GREEN + MAX_BLUE
        }
        fun power(): Int {
            return red * blue * green
        }
        fun log() {
            println("Game $number: $blue blue, $red red, $green green, possible: ${isPossible()}")
        }
    }
}
