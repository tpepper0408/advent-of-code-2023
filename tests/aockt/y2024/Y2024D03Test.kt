package aockt.y2024

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2024, 3, "Day 3: Mull It Over")
 class Y2024D03Test :AdventSpec<Y2024D03>({
    partOne {
        "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))" shouldOutput  161
        "mul(4*" shouldOutput 0
        "mul ( 2 , 4 )" shouldOutput 0
        "mul(2,4)" shouldOutput 8
        "mul(2,4)mul(3,7)" shouldOutput 29
        "do_not_mul(5,5)" shouldOutput 25
    }
    partTwo {
        "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))" shouldOutput  48
        "mul(4*" shouldOutput 0
        "mul ( 2 , 4 )" shouldOutput 0
        "mul(2,4)" shouldOutput 8
        "mul(2,4)don't()mul(3,7)" shouldOutput 8
        "don't()mul(5,5)do()mul(3,2)don't()mul(9,1)" shouldOutput 6
    }
 })