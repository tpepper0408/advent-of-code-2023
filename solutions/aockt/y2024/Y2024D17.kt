package aockt.y2024

import io.github.jadarma.aockt.core.Solution
import kotlin.math.pow

class Y2024D17 : Solution {
    private val debug = false

    override fun partOne(input: String): Any {
        val (registers, program) = parseInput(input)
        return executeProgram(registers, program, false).joinToString(",")
    }

    override fun partTwo(input: String): Any {
        val (registers, program) = parseInput(input)
        var low =   580000000
        val high =  Int.MAX_VALUE -1
        var result = -1

        while (low <= high) {
            if (low % 1000000 == 0) {
                println("Checking $low")
            }
            registers['A'] = low
            val output = executeProgram(registers, program, true)
            if (output.joinToString(",") == program.joinToString(",")) {
                result = low
                break
            } else {
                low++
            }
        }
        return result
    }

    private fun parseInput(input: String): Pair<MutableMap<Char, Int>, List<Int>> {
        val split = input.split("\n\n")
        val lines = split[0].lines()
        val registers = mutableMapOf<Char, Int>()
        lines.take(3).forEach { line ->
            val (register, value) = line.split(": ")
            registers[register.last()] = value.toInt()
        }
        val program = "Program: (.+)".toRegex().find(split[1])!!.groupValues[1].split(",").map { it.toInt() }
        return Pair(registers, program)
    }

    private fun executeProgram(registers: MutableMap<Char, Int>, program: List<Int>, partTwo: Boolean): List<String> {
        var pointer = 0
        val output = mutableListOf<String>()

        if (debug) {
            println("Initial registers: $registers")
            println("Program: $program")
        }

        while (pointer < program.size) {
            val operation = program[pointer]
            val operand = program[pointer + 1]
            when (operation) {
                0 -> adv(operand, registers)
                1 -> bxl(operand, registers)
                2 -> bst(operand, registers)
                3 -> {
                    if (registers['A'] != 0) {
                        if (debug) {
                            println("Jumping to $operand")
                        }
                        pointer = operand
                        continue
                    }
                    if (debug) {
                        println("Not jumping to $operand")
                    }
                }
                4 -> bxc(registers)
                5 -> {
                    calculateOutput(output, operand, registers)
                    //check if it's going off track
                    if (partTwo && (!program.joinToString("," ).startsWith(output.joinToString(","))))
                        return output
                }
                6 -> bdv(operand, registers)
                7 -> cdv(operand, registers)
                else -> throw IllegalArgumentException("Unknown operation: $operation")
            }
            pointer += 2
        }

        return output
    }

    private fun bxc(registers: MutableMap<Char, Int>) {
        if (debug) {
            println("BXC: ${registers['B']} xor ${registers['C']}")
        }
        registers['B'] = registers['B']!! xor registers['C']!!
        if (debug) {
            println("BXC result - Registers: $registers")
        }
    }

    private fun bst(operand: Int, registers: MutableMap<Char, Int>) {
        if (debug) {
            println("BST: ${comboOperand(operand, registers)}")
        }
        registers['B'] = comboOperand(operand, registers) % 8
        if (debug) {
            println("BST result - Registers: $registers")
        }
    }

    private fun bxl(operand: Int, registers: MutableMap<Char, Int>) {
        if (debug) {
            println("BXL: operand: $operand, B: ${registers['B']}")
        }
        registers['B'] = registers['B']!! xor operand
        if (debug) {
            println("BXL result - Registers: $registers")
        }
    }

    private fun adv(operand: Int, registers: MutableMap<Char, Int>) {
        if (debug) {
            println("ADV: operand: $operand, A: ${registers['A']}")
        }
        registers['A'] = (registers['A']!!.toDouble() / (2.0.pow(comboOperand(operand, registers)))).toInt()
        if (debug) {
            println("ADV result - Registers: $registers")
        }
    }

    private fun bdv(operand: Int, registers: MutableMap<Char, Int>) {
        if (debug) {
            println("BDV: operand: $operand, B: ${registers['B']}")
        }
        registers['B'] = (registers['A']!!.toDouble() / (2.0.pow(comboOperand(operand, registers)))).toInt()
        if (debug) {
            println("BDV result - Registers: $registers")
        }
    }

    private fun cdv(operand: Int, registers: MutableMap<Char, Int>) {
        if (debug) {
            println("CDV: operand: $operand, C: ${registers['C']}")
        }
        registers['C'] = (registers['A']!!.toDouble() / (2.0.pow(comboOperand(operand, registers)))).toInt()
        if (debug) {
            println("CDV result - Registers: $registers")
        }
    }

    private fun calculateOutput(output: MutableList<String>, operand: Int, registers: MutableMap<Char, Int>): Int {
        val result = comboOperand(operand, registers) % 8
        output.add(result.toString())
        if (debug) {
            println("Output: $result")
        }
        return result
    }

    private fun comboOperand(operand: Int, registers: MutableMap<Char, Int>): Int {
        return when (operand) {
            in 0 .. 3 -> operand
            4 -> registers['A']!!
            5 -> registers['B']!!
            6 -> registers['C']!!
            7 -> throw IllegalArgumentException("Reserved operand: $operand")
            else -> throw IllegalArgumentException("Unknown operand: $operand")
        }
    }
}