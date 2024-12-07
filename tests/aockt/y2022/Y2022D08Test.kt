package aockt.y2022

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2022, 8, "Day 8: Treetop Tree House")
class Y2022D08Test : AdventSpec<Y2022D08>({
    partOne {
                "30373\n" +
                "25512\n" +
                "65332\n" +
                "33549\n" +
                "35390" shouldOutput 21
    }
    partTwo {
                "30373\n" +
                "25512\n" +
                "65332\n" +
                "33549\n" +
                "35390" shouldOutput 8
    }
 })