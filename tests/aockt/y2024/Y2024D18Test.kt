package aockt.y2024

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2024, 18, "Day 18: RAM Run")
class Y2024D18Test : AdventSpec<Y2024D18>({
  partOne {
           "5,4\n" +
           "4,2\n" +
           "4,5\n" +
           "3,0\n" +
           "2,1\n" +
           "6,3\n" +
           "2,4\n" +
           "1,5\n" +
           "0,6\n" +
           "3,3\n" +
           "2,6\n" +
           "5,1\n" +
           "1,2\n" +
           "5,5\n" +
           "2,5\n" +
           "6,5\n" +
           "1,4\n" +
           "0,4\n" +
           "6,4\n" +
           "1,1\n" +
           "6,1\n" +
           "1,0\n" +
           "0,5\n" +
           "1,6\n" +
           "2,0" shouldOutput 22
  }
    partTwo {
        "5,4\n" +
                "4,2\n" +
                "4,5\n" +
                "3,0\n" +
                "2,1\n" +
                "6,3\n" +
                "2,4\n" +
                "1,5\n" +
                "0,6\n" +
                "3,3\n" +
                "2,6\n" +
                "5,1\n" +
                "1,2\n" +
                "5,5\n" +
                "2,5\n" +
                "6,5\n" +
                "1,4\n" +
                "0,4\n" +
                "6,4\n" +
                "1,1\n" +
                "6,1\n" +
                "1,0\n" +
                "0,5\n" +
                "1,6\n" +
                "2,0" shouldOutput "6,1"
    }
 }
 )