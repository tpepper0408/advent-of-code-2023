package aockt.y2022

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2022, 5, "Day 5: Supply Stacks")
class Y2022D05Test : AdventSpec<Y2022D05>({
    partOne {
                "    [D]    \n" +
                "[N] [C]    \n" +
                "[Z] [M] [P]\n" +
                " 1   2   3 \n" +
                "\n" +
                "move 1 from 2 to 1\n" +
                "move 3 from 1 to 3\n" +
                "move 2 from 2 to 1\n" +
                "move 1 from 1 to 2" shouldOutput "CMZ"
    }

     partTwo {
         "    [D]    \n" +
                 "[N] [C]    \n" +
                 "[Z] [M] [P]\n" +
                 " 1   2   3 \n" +
                 "\n" +
                 "move 1 from 2 to 1\n" +
                 "move 3 from 1 to 3\n" +
                 "move 2 from 2 to 1\n" +
                 "move 1 from 1 to 2" shouldOutput "MCD"
    }
 })