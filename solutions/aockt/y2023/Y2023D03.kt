package aockt.y2023

import io.github.jadarma.aockt.core.Solution

class Y2023D03 :Solution {
    override fun partOne(input: String) = findSymbolValues(input).sum()

    private fun findSymbolValues(input: String): List<Int> {
        val retval = mutableListOf<Int>()
        val lines = input.lines()
        lines
            .forEachIndexed{ lineIndex, line ->
                "[0-9]+".toRegex().findAll(line)
                    .forEach { m ->
                        var symbology = false

                        //where are you in the line
                        val numberLocation = line.indexOf(m.value)

                        //search before and after the number for symbols
                        if (numberLocation > 0) {
                            symbology = symbology || isASymbol(line[numberLocation - 1])
                        }
                        if (numberLocation + m.value.length < line.length) {
                            symbology = symbology || isASymbol(line[numberLocation + m.value.length])
                        }

                        //now look above for symbols
                        if (lineIndex > 0) {
                            val previousLine = lines[lineIndex - 1]
                            if (numberLocation > 0) {
                                symbology = symbology || isASymbol(previousLine[numberLocation - 1])
                            }
                            for (i in numberLocation until numberLocation + m.value.length) {
                                symbology = symbology || isASymbol(previousLine[i])
                            }
                            if (numberLocation + m.value.length < previousLine.length) {
                                symbology = symbology || isASymbol(previousLine[numberLocation + m.value.length])
                            }
                        }
                        //now look below for symbols
                        if (lineIndex + 1 < lines.size) {
                            val nextLine = lines[lineIndex + 1]
                            if (numberLocation > 0) {
                                symbology = symbology || isASymbol(nextLine[numberLocation - 1])
                            }
                            for (i in numberLocation until numberLocation + m.value.length) {
                                symbology = symbology || isASymbol(nextLine[i])
                            }
                            if (numberLocation + m.value.length < nextLine.length) {
                                symbology = symbology || isASymbol(nextLine[numberLocation + m.value.length])
                            }
                        }

                        if (symbology) {
                            retval.add(m.value.toInt())
                        }
                    }

            }
        return retval
    }

    private fun isASymbol(c: Char): Boolean {
        return !"[0-9]|\\.".toRegex().matches(c.toString())
    }

    override fun partTwo(input: String) = 0
}