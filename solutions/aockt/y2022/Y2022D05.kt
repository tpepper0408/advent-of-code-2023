package aockt.y2022

import io.github.jadarma.aockt.core.Solution
import java.util.Stack

class Y2022D05 : Solution {
    override fun partOne(input: String): Any {
        val (stacksRaw, instructionsRaw) = input.splitToSequence("\n\n").toList()
        val stacks = createInitialStacks(stacksRaw)
        println(stacks)
        val instructions = instructionsRaw.split("\n")
        instructions.forEach { instruction ->
            val (from, to) = instruction.split(" from ")[1].split(" to ")
            val fromStack = stacks[from.toInt() - 1]
            val toStack = stacks[to.toInt() - 1]
            repeat(instruction.split(" ")[1].toInt()) {
                toStack.push(fromStack.pop())
            }
            println(stacks)
        }
        return stacks.joinToString("") { stack -> stack.pop().toString() }
    }

    private fun createInitialStacks(stacksRaw: String): List<Stack<Char>> {
        val lines = stacksRaw.split("\n")
        val stackIds = lines.last().trim().split(Regex("\\s+")).map { it.toInt() }
        val stacks = List(stackIds.size) { Stack<Char>() }

        for (i in lines.size - 2 downTo 0) {
            val line = lines[i]
            for (j in stackIds.indices) {
                val charIndex = j * 4 + 1
                if (charIndex < line.length && line[charIndex] != ' ') {
                    stacks[j].push(line[charIndex])
                }
            }
        }
        return stacks
    }

    override fun partTwo(input: String): Any {
        return -1
    }

}