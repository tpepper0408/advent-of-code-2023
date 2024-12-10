package aockt.y2024

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2024, 10, "Day 10: Hoof It")
class Y2024D10Test : AdventSpec<Y2024D10>({
    partOne {
        "9990999\n" +
                "9991999\n" +
                "9992999\n" +
                "6543456\n" +
                "7999997\n" +
                "8111118\n" +
                "9111119" shouldOutput 2
        "89010123\n" +
                "78121874\n" +
                "87430965\n" +
                "96549874\n" +
                "45678903\n" +
                "32019012\n" +
                "01329801\n" +
                "10456732" shouldOutput 36

    }
    partTwo {
        "89010123\n" +
                "78121874\n" +
                "87430965\n" +
                "96549874\n" +
                "45678903\n" +
                "32019012\n" +
                "01329801\n" +
                "10456732" shouldOutput 81
    }

})