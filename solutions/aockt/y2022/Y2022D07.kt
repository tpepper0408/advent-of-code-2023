package aockt.y2022

import io.github.jadarma.aockt.core.Solution

class Y2022D07 : Solution {
    private val requiredSize: Int = 30000000
    private val diskSize: Int = 70000000

    override fun partOne(input: String): Any {
        val root = parseDirectoryStructure(input)
        return findDirectoriesInRange(root, 100000)
            .map { it.calculateTotalSize() }
            .sum()
    }

    override fun partTwo(input: String): Any {
        val root = parseDirectoryStructure(input)
        return findLargeEnoughDirectory(root).calculateTotalSize()
    }

    /**
     * Find the smallest directory that is large enough to hold the required size
     */
    private fun findLargeEnoughDirectory(directory: Directory): Directory {
        return findDirectoriesInRange(
            directory,
            requiredSize,
            requiredSize - (diskSize - directory.calculateTotalSize())
        )
            .minByOrNull { it.calculateTotalSize() }!!
    }

    /**
     * Parse the input string into a directory structure
     */
    private fun parseDirectoryStructure(input: String): Directory {
        val root = Directory("/", mutableListOf(), mutableListOf())
        var currentDir = root
        val dirStack = mutableListOf<Directory>()

        input.lines().forEach { line ->
            when {
                line.startsWith("\$ cd ") -> {
                    val dirName = line.removePrefix("\$ cd ")
                    currentDir = when (dirName) {
                        "/" -> root
                        ".." -> dirStack.removeAt(dirStack.size - 1)
                        else -> {
                            val newDir = currentDir.subDirs.find { it.name == dirName }!!
                            dirStack.add(currentDir)
                            newDir
                        }
                    }
                }

                line.startsWith("\$ ls") -> {
                    // No action needed for ls command itself
                }

                line.startsWith("dir ") -> {
                    val dirName = line.removePrefix("dir ")
                    currentDir.subDirs.add(Directory(dirName, mutableListOf(), mutableListOf()))
                }

                else -> {
                    val (size, name) = line.split(" ", limit = 2)
                    currentDir.files.add(File(name, size.toInt()))
                }
            }
        }
        return root
    }

    private fun findDirectoriesInRange(dir: Directory, maxSize: Int, minSize: Int = 0): List<Directory> {
        val result = mutableListOf<Directory>()
        val totalSize = dir.calculateTotalSize()
        if (totalSize in (minSize + 1)..<maxSize) {
            result.add(dir)
        }
        dir.subDirs.forEach { subDir ->
            result.addAll(findDirectoriesInRange(subDir, maxSize, minSize))
        }
        return result
    }

    private class Directory(val name: String, val files: MutableList<File>, val subDirs: MutableList<Directory>) {
        fun calculateTotalSize(): Int {
            val fileSize = files.sumOf { it.size }
            val subDirSize = subDirs.sumOf { it.calculateTotalSize() }
            return fileSize + subDirSize
        }

        fun print(level: Int = 0) {
            println("Directory $level-$name")
            files.forEach { println("  File ${it.name} ${it.size}") }
            subDirs.forEach { it.print(level + 1) }
        }
    }

    private class File(val name: String, val size: Int)
}