package aockt.y2023

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec
import org.junit.jupiter.api.Assertions.*

@AdventDay(2023, 2, "Cube Conundrum")
class Y2023D02Test : AdventSpec<Y2023D02>({
    partOne {
        "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green" shouldOutput 1
        "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue" shouldOutput 2
        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red" shouldOutput 0
        "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red" shouldOutput 0
        "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green" shouldOutput 5
        "Game 6: 6 red, 20 red, 1 green; 2 blue, 1 red, 2 green" shouldOutput 0
        "Game 7: 11 red" shouldOutput 7
        "Game 8: 12 red" shouldOutput 8
        "Game 9: 13 red" shouldOutput 0
        "Game 10: 12 green" shouldOutput 10
        "Game 11: 13 green" shouldOutput 11
        "Game 12: 14 green" shouldOutput 0
        "Game 13: 13 blue" shouldOutput 13
        "Game 14: 14 blue" shouldOutput 14
        "Game 15: 15 blue" shouldOutput 0
        "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" +
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" +
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" +
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" +
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green\n" +
                "Game 100: 1 blue, 2 green, 1 red" shouldOutput 108
    }

    partTwo {
        "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green" shouldOutput 48
        "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue" shouldOutput 12
        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red" shouldOutput 1560
        "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red" shouldOutput 630
        "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green" shouldOutput 36
        "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" +
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" +
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" +
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" +
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green" shouldOutput 2286
    }
})