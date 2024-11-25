package aockt.y2023

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec
import org.junit.jupiter.api.Assertions.*

@AdventDay(2023, 3, "Gear Ratios")
class Y2023D03Test : AdventSpec<Y2023D03>({
    partOne {
        "467..114..\n" +
            "...*......\n" +
            "..35..633.\n" +
            "......#...\n" +
            "617*......\n" +
            ".....+.58.\n" +
            "..592.....\n" +
            "......755.\n" +
            "...\$.*....\n" +
            ".664.598.." shouldOutput 4361
        "*.........\n" +
        "..35...33." shouldOutput 0
        ".*........\n" +
        "..35...33." shouldOutput 35
        "..*.......\n" +
        "..35...33." shouldOutput 35
        "...*......\n" +
        "..35...33." shouldOutput 35
        "....*.....\n" +
        "..35...33." shouldOutput 35
        ".....*....\n" +
        "..35...33." shouldOutput 0
        "..........\n" +
        "..35...33." shouldOutput 0
        "..35...33.\n" +
        ".@........" shouldOutput 35
        "..35...33.\n" +
        "..&......." shouldOutput 35
        "..35...33.\n" +
        "...\$......" shouldOutput 35
        "..35...33.\n" +
        "..../....." shouldOutput 35
        "..35...33.\n" +
        ".....*...." shouldOutput 0
        ".35." shouldOutput 0
        "@35." shouldOutput 35
        ".35&" shouldOutput 35
        "..35" shouldOutput 0
        "35.." shouldOutput 0
        "35=." shouldOutput 35
        ".&35" shouldOutput 35
        "35+35" shouldOutput 70
        "...35\n" +
        "..*.." shouldOutput 35
        "..*..\n" +
        "...35" shouldOutput 35
    }

    partTwo {
        "Hello" shouldOutput 0
    }
})