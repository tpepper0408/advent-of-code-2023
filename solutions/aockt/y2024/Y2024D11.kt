package aockt.y2024

import io.github.jadarma.aockt.core.Solution
import java.util.Map
import kotlin.math.log10
import kotlin.math.pow


class Y2024D11 : Solution {

    override fun partOne(input: String): Any {
        var numbers = input.split(" ").map { it.toLong() }.asSequence()
        repeat(25) {
            numbers = numbers.flatMap { number ->
                when {
                    number == 0L -> sequenceOf(1L)
                    numberOfDigits(number) % 2 == 0 -> {
                        val halfLength = numberOfDigits(number) / 2
                        val left = number / 10.0.pow(halfLength).toLong()
                        val right = number % 10.0.pow(halfLength).toLong()
                        sequenceOf(left, right)
                    }
                    else -> sequenceOf(number * 2024)
                }
            }
        }
        var count = 0L
        for (number in numbers) {
            count ++
        }
        return count
    }

//    private fun processNumbers(numbers: MutableList<Long>, repeat: Int = 25) {
//        repeat(repeat) {
//            val newNumbers = mutableListOf<Long>()
//            for (number in numbers) {
//                when {
//                    number < 0L -> throw IllegalArgumentException("Negative number $number")
//                    number == 0L -> newNumbers.add(1)
//                    number.toString().length % 2 == 0 -> {
//                        val str = number.toString()
//                        val left = str.substring(0, str.length / 2).toLong()
//                        val right = str.substring(str.length / 2).toLong()
//                        newNumbers.add(left)
//                        newNumbers.add(right)
//                    }
//
//                    else -> newNumbers.add(number * 2024L)
//                }
//            }
//            numbers.clear()
//            numbers.addAll(newNumbers)
//        }
//    }

    override fun partTwo(input: String): Any {
        val convertedInput = input.splitToSequence(" ").map { it.toLong() }

        var stones: MutableMap<Long, Long> = HashMap()
        val next: MutableMap<Long, Long> = HashMap()
        val cache: MutableMap<Long, Array<Long>> = HashMap()

        for (l in convertedInput) {
            stones[l] = stones.getOrDefault<Any, Long>(stones, 0L) + 1L
        }

        var count: Long = 0
        for (blink in 1..75) {
            next.clear()

            for (i in stones.keys) {
                if (i == 0L) {
                    // First rule
                    next[1L] = next.getOrDefault(1L, 0L) + stones[i]!!
                } else if (cache.containsKey(i)) {
                    // Check if we've already calculated the result of this symbol already
                    for (l in cache[i]!!) {
                        next[l] = next.getOrDefault(l, 0L) + stones[i]!!
                    }
                } else {
                    val digits = (log10(i.toDouble()) + 1).toInt()
                    if (digits % 2 == 0) {
                        // Second rule
                        val front = (i / 10.0.pow((digits / 2).toDouble())).toLong()
                        val back = (i % 10.0.pow((digits / 2).toDouble())).toLong()
                        next[front] = next.getOrDefault(front, 0L) + stones[i]!!
                        next[back] = next.getOrDefault(back, 0L) + stones[i]!!
                        cache[i] = arrayOf(front, back)
                    } else {
                        // Third rule
                        val num = 2024 * i
                        next[num] = next.getOrDefault(num, 0L) + stones[i]!!
                        cache[i] = arrayOf(num)
                    }
                }
            }
            stones = Map.copyOf(next)
        }
        
        for (num in stones.keys) {
            count += stones[num]!!
        }
        return count
    }

    private fun numberOfDigits(number: Long): Int {
        var count = 0
        var n = number
        while (n != 0L) {
            n /= 10
            count++
        }
        return count
    }
}