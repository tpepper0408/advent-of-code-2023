package aockt.y2022

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2022, 4, "Day 4: Camp Cleanup")
class Y2022D04Test : AdventSpec<Y2022D04>({
   partOne {
       "2-4,6-8\n" +
               "2-3,4-5\n" +
               "5-7,7-9\n" +
               "2-8,3-7\n" +
               "6-6,4-6\n" +
               "2-6,4-8" shouldOutput 2
   }

    partTwo {
    "2-4,6-8\n" +
            "2-3,4-5\n" +
            "5-7,7-9\n" +
            "2-8,3-7\n" +
            "6-6,4-6\n" +
            "2-6,4-8" shouldOutput 4
    }
 })