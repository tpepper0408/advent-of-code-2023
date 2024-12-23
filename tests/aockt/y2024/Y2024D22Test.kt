package aockt.y2024

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2024, 22, "Day 22: Monkey Market")
class Y2024D22Test : AdventSpec<Y2024D22>({
    partOne {
                "1\n" +
                "10\n" +
                "100\n" +
                "2024" shouldOutput 37327623
    }
    partTwo {
        "1\n" +
                "10\n" +
                "100\n" +
                "2024" shouldOutput "-2,1,-1,3"
    }
})