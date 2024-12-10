package aockt.y2024

import io.github.jadarma.aockt.core.Solution

class Y2024D08 : Solution {
    override fun partOne(input: String): Any {
        val grid = input.lines()
        return findEquidistantPoints(grid).size
    }

    override fun partTwo(input: String): Any {
        return findResonantPoints(input.lines()).size
    }

    private fun findResonantPoints(grid: List<String>): Set<Point> {
        val pairs = mutableMapOf<Char, MutableList<Point>>()
        val resonantPoints = mutableSetOf<Point>()

        for (y in grid.indices) {
            for (x in grid[y].indices) {
                val char = grid[y][x]
                if (char.isLetterOrDigit()) {
                    val point = Point(x, y)
                    pairs.computeIfAbsent(char) { mutableListOf() }.add(point)
                }
            }
        }

        for ((_, points) in pairs) {
            resonantPoints.addAll(points)
            for (i in points.indices) {
                for (j in i + 1 until points.size) {
                    val p1 = points[i]
                    val p2 = points[j]
                    val distanceX = p2.x - p1.x
                    val distanceY = p2.y - p1.y

                    var newPoint1 = Point(p1.x + distanceX, p1.y + distanceY)
                    while (newPoint1.x in grid[0].indices && newPoint1.y in grid.indices) {
                        resonantPoints.add(newPoint1)
                        newPoint1 = Point(newPoint1.x + distanceX, newPoint1.y + distanceY)
                    }

                    var newPoint2 = Point(p1.x - distanceX, p1.y - distanceY)
                    while (newPoint2.x in grid[0].indices && newPoint2.y in grid.indices) {
                        resonantPoints.add(newPoint2)
                        newPoint2 = Point(newPoint2.x - distanceX, newPoint2.y - distanceY)
                    }
                }
            }
        }
//        printGrid(grid, resonantPoints)
        return resonantPoints
    }

//    private fun printGrid(grid: List<String>, resonantPoints: MutableSet<Point>) {
//        for (y in grid.indices) {
//            for (x in grid[y].indices) {
//                val point = Point(x, y)
//                if (point in resonantPoints) {
//                    print('#')
//                } else {
//                    print(grid[y][x])
//                }
//            }
//            println()
//        }
//    }

    private data class Point(val x: Int, val y: Int)

    private fun findEquidistantPoints(grid: List<String>): Set<Point> {
        val pairs = mutableMapOf<Char, MutableList<Point>>()
        val equidistantPoints = mutableListOf<Point>()

        for (y in grid.indices) {
            for (x in grid[y].indices) {
                val char = grid[y][x]
                if (char.isLetterOrDigit()) {
                    val point = Point(x, y)
                    pairs.computeIfAbsent(char) { mutableListOf() }.add(point)
                }
            }
        }

        for ((_, points) in pairs) {
            for (i in points.indices) {
                for (j in i + 1 until points.size) {
                    val p1 = points[i]
                    val p2 = points[j]
                    val distanceX = p2.x - p1.x
                    val distanceY = p2.y - p1.y

                    val newPoints = listOf(
                        Point(p1.x + distanceX, p1.y + distanceY),
                        Point(p1.x - distanceX, p1.y - distanceY),
                        Point(p2.x + distanceX, p2.y + distanceY),
                        Point(p2.x - distanceX, p2.y - distanceY)
                    )

                    for (newPoint in newPoints) {
                        if (newPoint.x in grid[0].indices && newPoint.y in grid.indices
                            && newPoint != p1
                            && newPoint != p2) {
                            equidistantPoints.add(newPoint)
                        }
                    }
                }
            }
        }
        return equidistantPoints.toSet()
    }
}