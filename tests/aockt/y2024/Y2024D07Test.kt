package aockt.y2024

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2024, 7, "Day 7: Bridge Repair")
class Y2024D07Test : AdventSpec<Y2024D07>({
    partOne {
     "190: 10 19\n" +
             "3267: 81 40 27\n" +
             "83: 17 5\n" +
             "156: 15 6\n" +
             "7290: 6 8 6 15\n" +
             "161011: 16 10 13\n" +
             "192: 17 8 14\n" +
             "21037: 9 7 18 13\n" +
             "292: 11 6 16 20" shouldOutput 3749
     "190: 10 19" shouldOutput 190
     "3267: 81 40 27" shouldOutput 3267
     "292: 11 6 16 20" shouldOutput 292
     "83: 17 5" shouldOutput 0
     "156: 15 6" shouldOutput 0
     "161011: 16 10 13" shouldOutput 0
    }

    partTwo {
        "190: 10 19\n" +
                "3267: 81 40 27\n" +
                "83: 17 5\n" +
                "156: 15 6\n" +
                "7290: 6 8 6 15\n" +
                "161011: 16 10 13\n" +
                "192: 17 8 14\n" +
                "21037: 9 7 18 13\n" +
                "292: 11 6 16 20" shouldOutput 11387
        "190: 10 19" shouldOutput 190
        "3267: 81 40 27" shouldOutput 3267
        "292: 11 6 16 20" shouldOutput 292
        "83: 17 5" shouldOutput 0
        "156: 15 6" shouldOutput 156
        "161011: 16 10 13" shouldOutput 0
        "7290: 6 8 6 15" shouldOutput 7290
        "192: 17 8 14" shouldOutput 192
    }
 })