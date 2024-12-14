package aockt.y2024

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2024, 13, "Day 13: Claw Contraption")
class Y2024D13Test :AdventSpec<Y2024D13>({
  partOne {
           "Button A: X+94, Y+34\n" +
           "Button B: X+22, Y+67\n" +
           "Prize: X=8400, Y=5400\n" +
           "\n" +
           "Button A: X+26, Y+66\n" +
           "Button B: X+67, Y+21\n" +
           "Prize: X=12748, Y=12176\n" +
           "\n" +
           "Button A: X+17, Y+86\n" +
           "Button B: X+84, Y+37\n" +
           "Prize: X=7870, Y=6450\n" +
           "\n" +
           "Button A: X+69, Y+23\n" +
           "Button B: X+27, Y+71\n" +
           "Prize: X=18641, Y=10279" shouldOutput 480
  }
  partTwo {
           "Button A: X+94, Y+34\n" +
           "Button B: X+22, Y+67\n" +
           "Prize: X=8400, Y=5400\n" +
           "\n" +
           "Button A: X+26, Y+66\n" +
           "Button B: X+67, Y+21\n" +
           "Prize: X=12748, Y=12176\n" +
           "\n" +
           "Button A: X+17, Y+86\n" +
           "Button B: X+84, Y+37\n" +
           "Prize: X=7870, Y=6450\n" +
           "\n" +
           "Button A: X+69, Y+23\n" +
           "Button B: X+27, Y+71\n" +
           "Prize: X=18641, Y=10279" shouldOutput 875318608908
  }
 })