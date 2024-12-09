package aockt.y2024

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2024, 9, "Day 9: Disk Fragmenter")
class Y2024D09Test : AdventSpec<Y2024D09>({
  partOne {
   "2333133121414131402" shouldOutput 1928
  }
  partTwo {
   "2333133121414131402" shouldOutput 2858
  }
 })