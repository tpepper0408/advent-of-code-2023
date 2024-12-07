package aockt.y2022

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2022, 3, "Day 3: Rucksack Reorganization")
class Y2022D03Test : AdventSpec<Y2022D03>({
   partOne {
    "vJrwpWtwJgWrhcsFMMfFFhFp\n" +
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\n" +
            "PmmdzqPrVvPwwTWBwg\n" +
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\n" +
            "ttgJtRGJQctTZtZT\n" +
            "CrZsJsPPZsGzwwsLwLmpwMDw" shouldOutput 157
       "vJrwpWtwJgWrhcsFMMfFFhFp" shouldOutput 16
       "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL" shouldOutput 38
       "PmmdzqPrVvPwwTWBwg" shouldOutput 42
       "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn" shouldOutput 22
       "ttgJtRGJQctTZtZT" shouldOutput 20
       "CrZsJsPPZsGzwwsLwLmpwMDw" shouldOutput 19
   }

    partTwo {
    "vJrwpWtwJgWrhcsFMMfFFhFp\n" +
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\n" +
            "PmmdzqPrVvPwwTWBwg" shouldOutput 18
        "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\n" +
                "ttgJtRGJQctTZtZT\n" +
                "CrZsJsPPZsGzwwsLwLmpwMDw" shouldOutput 52
        "vJrwpWtwJgWrhcsFMMfFFhFp\n" +
                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\n" +
                "PmmdzqPrVvPwwTWBwg\n" +
                "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\n" +
                "ttgJtRGJQctTZtZT\n" +
                "CrZsJsPPZsGzwwsLwLmpwMDw" shouldOutput 70
    }
 })