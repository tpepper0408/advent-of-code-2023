package aockt.y2024

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2024, 17, "Day 15: Chronospatial Computer")
class Y2024D17Test : AdventSpec<Y2024D17>({
  partOne {
           "Register A: 729\n" +
           "Register B: 0\n" +
           "Register C: 0\n" +
           "\n" +
           "Program: 0,1,5,4,3,0" shouldOutput "4,6,3,5,6,3,5,2,1,0"
  }
    partTwo {
                "Register A: 2024\n" +
                "Register B: 0\n" +
                "Register C: 0\n" +
                "\n" +
                "Program: 0,3,5,4,3,0" shouldOutput 117440
    }
 })