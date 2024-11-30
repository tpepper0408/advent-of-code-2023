package aockt.y2022

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec
import org.junit.jupiter.api.Assertions.*

@AdventDay(2022, 1, "Calorie Counting")
class Y2022D01Test : AdventSpec<Y2022D01>({
    partOne {
        "1000\n" +
                "2000\n" +
                "3000\n" +
                "\n" +
                "4000\n" +
                "\n" +
                "5000\n" +
                "6000\n" +
                "\n" +
                "7000\n" +
                "8000\n" +
                "9000\n" +
                "\n" +
                "10000" shouldOutput 24000
        "1000" shouldOutput 1000
        "1\n\n1\n2" shouldOutput 3
        "1000\n1000" shouldOutput 2000
    }
    partTwo {
        "1000\n" +
                "2000\n" +
                "3000\n" +
                "\n" +
                "4000\n" +
                "\n" +
                "5000\n" +
                "6000\n" +
                "\n" +
                "7000\n" +
                "8000\n" +
                "9000\n" +
                "\n" +
                "10000" shouldOutput 45000
        "1000\n\n" +
                "1000\n\n" +
                "1000\n1000" shouldOutput 4000
    }
})