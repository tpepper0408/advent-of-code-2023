package aockt.y2023

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@AdventDay(2023, 4, "Something")
class Y2023D04Test: AdventSpec<Y2023D04>({
    partOne { "Hello" shouldOutput  1 }
    partTwo { "Hello" shouldOutput 2 }
})