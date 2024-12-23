package aockt.y2024

import io.github.jadarma.aockt.core.Solution

class Y2024D22 : Solution {
    override fun partOne(input: String): Any {
        return input.splitToSequence("\n")
            .map { it.toLong() }
            .map { number ->
                var result = number
                repeat(2000) {
                    result = generateSecretNumber(result)
                }
                result
            }
            .sum()
    }

    private fun generateSecretNumber(result: Long): Long {
        var toProcess = result
        toProcess = ((toProcess * 64) xor toProcess) % 16777216
        toProcess = ((toProcess / 32) xor toProcess) % 16777216
        return ((toProcess * 2048) xor toProcess) % 16777216
    }

    override fun partTwo(input: String): Any {
        val inputs = input.splitToSequence("\n").map { it.toLong() }.toList()
        val allBuyingSequences = generateAllBuyingSequences()
        var maxBananas = Long.MIN_VALUE
        var bestSequence: List<Long> = emptyList()

        for (sequence in allBuyingSequences) {
            val totalBananas = inputs.sumOf { calculateBananasForSequence(it, sequence) }
            if (totalBananas > maxBananas) {
                maxBananas = totalBananas
                bestSequence = sequence
            }
        }

        return bestSequence.joinToString(",") { it.toString() }
    }

    private fun generateAllBuyingSequences(): List<List<Long>> {
        val range = -13L..13L
        val sequences = mutableListOf<List<Long>>()
        for (a in range) {
            for (b in range) {
                for (c in range) {
                    for (d in range) {
                        sequences.add(listOf(a, b, c, d))
                    }
                }
            }
        }
        return sequences
    }

    private fun calculateBananasForSequence(starter: Long, sequence: List<Long>): Long {
        val bananaDifferences = mutableListOf<Long>()
        var result = generateSecretNumber(starter)
        repeat(2000) {
            val initialBananas = calculateBananas(result)
            val nextNumber = generateSecretNumber(result)
            val nextBananas = calculateBananas(nextNumber)
            bananaDifferences.add(nextBananas - initialBananas)
            result = nextNumber
        }

        var totalBananas = 0L
        for (i in 0..bananaDifferences.size - sequence.size) {
            if (bananaDifferences.subList(i, i + sequence.size) == sequence) {
                totalBananas += calculateBananas(generateSecretNumber(result))
            }
        }

        return totalBananas
    }

    private fun calculateBananas(secretNumber: Long): Long {
        var number = secretNumber
        var count = 0L
        while (number > 0) {
            count += number and 1
            number = number shr 1
        }
        return count
    }
}