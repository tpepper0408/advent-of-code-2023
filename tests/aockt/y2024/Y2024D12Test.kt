package aockt.y2024

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2024, 12, "Day 12: Garden Groups")
class Y2024D12Test : AdventSpec<Y2024D12>({
  partOne {
   "AAAA\n" +
           "BBCD\n" +
           "BBCC\n" +
           "EEEC" shouldOutput 140
   "OOOOO\n" +
           "OXOXO\n" +
           "OOOOO\n" +
           "OXOXO\n" +
           "OOOOO" shouldOutput 772
   "RRRRIICCFF\n" +
           "RRRRIICCCF\n" +
           "VVRRRCCFFF\n" +
           "VVRCCCJFFF\n" +
           "VVVVCJJCFE\n" +
           "VVIVCCJJEE\n" +
           "VVIIICJJEE\n" +
           "MIIIIIJJEE\n" +
           "MIIISIJEEE\n" +
           "MMMISSJEEE" shouldOutput 1930
  }
  partTwo {
//      "AA\nAA" shouldOutput 16
//   "AAAA\n" +
//           "BBCD\n" +
//           "BBCC\n" +
//           "EEEC" shouldOutput 80
//   "EEEEE\n" +
//           "EXXXX\n" +
//           "EEEEE\n" +
//           "EXXXX\n" +
//           "EEEEE" shouldOutput 236
//   "AAAAAA\n" +
//           "AAABBA\n" +
//           "AAABBA\n" +
//           "ABBAAA\n" +
//           "ABBAAA\n" +
//           "AAAAAA" shouldOutput 368
  }
 })