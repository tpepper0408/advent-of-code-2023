package aockt.y2022

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec
import org.junit.jupiter.api.Assertions.*

@AdventDay(2022, 2, "Rock, Paper, Scissors")
class Y2022D02Test : AdventSpec<Y2022D02>({
    partOne {
        "A Y\n" +
                "B X\n" +
                "C Z" shouldOutput 15
        "A X" shouldOutput 4
        "A Y" shouldOutput 8
        "A Z" shouldOutput 3
        "B X" shouldOutput 1
        "B Y" shouldOutput 5
        "B Z" shouldOutput 9
        "C X" shouldOutput 7
        "C Y" shouldOutput 2
        "C Z" shouldOutput 6
    }
    partTwo {
        "A Y\n" +
                "B X\n" +
                "C Z" shouldOutput 12
     "A X" shouldOutput 3
     "A Y" shouldOutput 4
     "A Z" shouldOutput 8
     "B X" shouldOutput 1
     "B Y" shouldOutput 5
     "B Z" shouldOutput 9
     "C X" shouldOutput 2
     "C Y" shouldOutput 6
     "C Z" shouldOutput 7
    }
})