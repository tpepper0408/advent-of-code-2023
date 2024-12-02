package aockt.y2024

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2024, 2, "Day 2: Red-Nosed Reports")
 class Y2024D02Test : AdventSpec<Y2024D02>({
    partOne {
        "7 6 4 2 1\n" +
                "1 2 7 8 9\n" +
                "9 7 6 2 1\n" +
                "1 3 2 4 5\n" +
                "8 6 4 4 1\n" +
                "1 3 6 7 9" shouldOutput 2
        "7 6 4 2 1" shouldOutput 1
        "1 2 7 8 9" shouldOutput 0
        "9 7 6 2 1" shouldOutput 0
        "1 3 2 4 5" shouldOutput 0
        "8 6 4 4 1" shouldOutput 0
        "1 3 6 7 9" shouldOutput 1
    }

    partTwo {
        "7 6 4 2 1\n" +
                "1 2 7 8 9\n" +
                "9 7 6 2 1\n" +
                "1 3 2 4 5\n" +
                "8 6 4 4 1\n" +
                "1 3 6 7 9" shouldOutput 4
        "7 6 4 2 1" shouldOutput 1
        "1 2 7 8 9" shouldOutput 0
        "9 7 6 2 1" shouldOutput 0
        "1 3 2 4 5" shouldOutput 1
        "8 6 4 4 1" shouldOutput 1
        "1 3 6 7 9" shouldOutput 1
    }
 })