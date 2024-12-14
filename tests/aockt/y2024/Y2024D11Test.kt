package aockt.y2024

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2024, 11, "Day 11: Plutonian Pebbles")
class Y2024D11Test : AdventSpec<Y2024D11>({
  partOne {
   "125 17" shouldOutput 55312
  }
  partTwo {
      "125 17" shouldOutput 65601038650482
  }
 })