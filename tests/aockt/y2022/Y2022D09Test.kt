package aockt.y2022

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2022, 9, "Day 9: Rope Bridge")
class Y2022D09Test : AdventSpec<Y2022D09>({
  partOne {
   "R 4\n" +
           "U 4\n" +
           "L 3\n" +
           "D 1\n" +
           "R 4\n" +
           "D 1\n" +
           "L 5\n" +
           "R 2" shouldOutput 13
  }
  partTwo {
      "R 5\n" +
              "U 8\n" +
              "L 8\n" +
              "D 3\n" +
              "R 17\n" +
              "D 10\n" +
              "L 25\n" +
              "U 20" shouldOutput 36
  }
 })