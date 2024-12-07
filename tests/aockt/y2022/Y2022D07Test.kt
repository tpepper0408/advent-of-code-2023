package aockt.y2022

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2022, 7, "Day 7: No Space Left On Device")
class Y2022D07Test : AdventSpec<Y2022D07>({
  partOne {
           "\$ cd /\n" +
           "\$ ls\n" +
           "dir a\n" +
           "14848514 b.txt\n" +
           "8504156 c.dat\n" +
           "dir d\n" +
           "\$ cd a\n" +
           "\$ ls\n" +
           "dir e\n" +
           "29116 f\n" +
           "2557 g\n" +
           "62596 h.lst\n" +
           "\$ cd e\n" +
           "\$ ls\n" +
           "584 i\n" +
           "\$ cd ..\n" +
           "\$ cd ..\n" +
           "\$ cd d\n" +
           "\$ ls\n" +
           "4060174 j\n" +
           "8033020 d.log\n" +
           "5626152 d.ext\n" +
           "7214296 k" shouldOutput 95437
  }

  partTwo {
      "\$ cd /\n" +
              "\$ ls\n" +
              "dir a\n" +
              "14848514 b.txt\n" +
              "8504156 c.dat\n" +
              "dir d\n" +
              "\$ cd a\n" +
              "\$ ls\n" +
              "dir e\n" +
              "29116 f\n" +
              "2557 g\n" +
              "62596 h.lst\n" +
              "\$ cd e\n" +
              "\$ ls\n" +
              "584 i\n" +
              "\$ cd ..\n" +
              "\$ cd ..\n" +
              "\$ cd d\n" +
              "\$ ls\n" +
              "4060174 j\n" +
              "8033020 d.log\n" +
              "5626152 d.ext\n" +
              "7214296 k" shouldOutput 24933642
  }
 })