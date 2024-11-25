package aockt.y2023

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec
import org.junit.jupiter.api.Assertions.*

@AdventDay(2023, 1, "Trubuchet?!")
class Y2023D01Test : AdventSpec<Y2023D01>({

    partOne {
        "1abc2" shouldOutput 12
        "pqr3stu8vwx" shouldOutput 38
        "a1b2c3d4e5f" shouldOutput 15
        "treb7uchet" shouldOutput 77
        "1abc2\n" +
                "pqr3stu8vwx\n" +
                "a1b2c3d4e5f\n" +
                "treb7uchet" shouldOutput 142
    }

    partTwo {
        "two1nine" shouldOutput 29
        "eightwothree" shouldOutput 83
        "abcone2threexyz" shouldOutput 13
        "xtwone3four" shouldOutput 24
        "4nineeightseven2" shouldOutput 42
        "zoneight234" shouldOutput 14
        "7pqrstsixteen" shouldOutput 76
        "two1nine\n" +
                "eightwothree\n" +
                "abcone2threexyz\n" +
                "xtwone3four\n" +
                "4nineeightseven2\n" +
                "zoneight234\n" +
                "7pqrstsixteen" shouldOutput 281
        "45" shouldOutput 45
        "1fourfivezthdcxfr" shouldOutput 15
        "p8" shouldOutput 88
        "sevensnmhgdxpbksngnflnthreemlqgdvphzk5tvmzjvdzbcseven" shouldOutput 77
        "6hvzkkr4nine2seventwo4" shouldOutput 64
    }

})