package aockt.y2024

import io.github.jadarma.aockt.core.Solution

class Y2024D09 : Solution {
    override fun partOne(input: String): Any {
        val explodedList = processInput(input)
        val defraggedList = defragment(explodedList)
        return checkSum(defraggedList)
    }

    override fun partTwo(input: String): Any {
        val defraggedList = defragmentRetainBlocks(processInput(input))
        return checkSum(defraggedList)
    }

    /**
     * Similar to the defragment function expect it only moves the blocks of fileIDs if there is space for them
     * somewhere in the list, otherwise it leaves them where they are. It iterates downwards through the fileIDs
     * to try and find a space for them.
     * Example: [0, 1, -1, -1, 2, 3, 3, 4, 4, -1, 5] -> [0, 1, 5, 2, -1, 3, 3, 4, 4, -1, -1]
     */
    private fun defragmentRetainBlocks(explodedList: List<Int>): List<Int> {
        val defraggedList = explodedList.toMutableList()
        val fileSizes = mutableMapOf<Int, Int>()

        // Calculate the size of each file
        defraggedList.filter { it >= 0 }.forEach { fileSizes[it] = fileSizes.getOrDefault(it, 0) + 1 }

        // Iterate from the highest file ID to the smallest
        for (fileId in fileSizes.keys.sortedDescending()) {
            val fileSize = fileSizes[fileId] ?: continue
            var freeSpaceStart = -1
            var freeSpaceCount = 0

            // Find the left-most block of free space that can contain the file
            for (i in defraggedList.indices) {
                if (defraggedList[i] == -1) {
                    if (freeSpaceStart == -1) freeSpaceStart = i
                    freeSpaceCount++
                    if (freeSpaceCount == fileSize) break
                } else {
                    freeSpaceStart = -1
                    freeSpaceCount = 0
                }
            }

            // If a suitable free space block is found, move the file
            if (freeSpaceCount == fileSize && freeSpaceStart != -1 && freeSpaceStart < defraggedList.indexOf(fileId)) {
                var fileIndex = 0
                for (i in defraggedList.indices) {
                    if (defraggedList[i] == fileId) {
                        defraggedList[i] = -1
                    }
                }
                for (i in freeSpaceStart until freeSpaceStart + fileSize) {
                    defraggedList[i] = fileId
                }
            }
        }

        return defraggedList
    }

    /**
     * Calculates a checksum value for the data in the list
     * This is by summing the product of the position and the value.
     *  E.g. for the list [1, 2, 3, 4, 5] the checksum would be
     *      1 * 0 + 2 * 1 + 3 * 2 + 4 * 3 + 5 * 4 = 40
     */
    private fun checkSum(defraggedList: List<Int>): Long {
        return defraggedList
            .mapIndexed { index, value -> if (value != -1) index.toLong() * value.toLong() else 0 }.sum()
    }

    /**
     * I want to move through the list backwards and move any numbers that are greater than or equal to 0
     * to the first available free space at the start of the list. This will be done in a single pass.
     * I will then move the free space to the end of the list.
     *
     * Example: [0, 1, -1, -1, 2, 3, 3, 4, 4, -1, 5] -> [0, 1, 5, 4, 2, 3, 3, 4]
     */
    private fun defragment(explodedList: List<Int>): List<Int> {
        val defraggedList = explodedList.toMutableList()
        var freeSpaceIndex = defraggedList.indexOf(-1)
        for (i in defraggedList.size - 1 downTo 0) {
            if (defraggedList[i] >= 0 && freeSpaceIndex < i) {
                defraggedList[freeSpaceIndex] = defraggedList[i]
                defraggedList[i] = -1
                freeSpaceIndex = defraggedList.indexOf(-1)
                if (freeSpaceIndex >= i) break
            }
        }
        return defraggedList.subList(0, freeSpaceIndex)
    }

    private fun processInput(input: String): List<Int> {
        val numbers = input.map { it.toString().toInt() }
        val explodedList = mutableListOf<Int>()

        var fileId = 0

        for (i in numbers.indices) {
            if (i % 2 == 0) {
                val fileSize = numbers[i]
                repeat(fileSize) {
                    explodedList.add(fileId)
                }

                fileId++
            } else {
                val freeSpace = numbers[i]
                repeat(freeSpace) {
                    explodedList.add(-1) // Using -1 to represent free space
                }
            }
        }

        return explodedList
    }
}