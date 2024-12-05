package aockt.y2024

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2024, 4, "Day 4: Ceres Search")
class Y2024D04Test : AdventSpec<Y2024D04>({
    partOne { "MMMSXXMASM\n" +
            "MSAMXMSMSA\n" +
            "AMXSXMAAMM\n" +
            "MSAMASMSMX\n" +
            "XMASAMXAMM\n" +
            "XXAMMXXAMA\n" +
            "SMSMSASXSS\n" +
            "SAXAMASAAA\n" +
            "MAMMMXMMMM\n" +
            "MXMXAXMASX" shouldOutput 18 }
    partTwo {
        "MMMSXXMASM\n" +
            "MSAMXMSMSA\n" +
            "AMXSXMAAMM\n" +
            "MSAMASMSMX\n" +
            "XMASAMXAMM\n" +
            "XXAMMXXAMA\n" +
            "SMSMSASXSS\n" +
            "SAXAMASAAA\n" +
            "MAMMMXMMMM\n" +
            "MXMXAXMASX" shouldOutput 9
    ".M.S......\n" +
            "..A..MSMS.\n" +
            ".M.S.MAA..\n" +
            "..A.ASMSM.\n" +
            ".M.S.M....\n" +
            "..........\n" +
            "S.S.S.S.S.\n" +
            ".A.A.A.A..\n" +
            "M.M.M.M.M.\n" +
            ".........." shouldOutput 9
            "XMAS\n" +
            "XMAS\n" +
            "XMAS" shouldOutput 1
        "M.S\n" +
                ".A.\n" +
                "M.S" shouldOutput 1
    }
})