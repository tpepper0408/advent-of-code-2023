package aockt.y2024

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2024, 21, "Day 21: Keypad Conundrum")
class Y2024D21Test : AdventSpec<Y2024D21>({
   partOne {
            "029A\n" +
            "980A\n" +
            "179A\n" +
            "456A\n" +
            "379A" shouldOutput 126384
//       Y2024D21().findButtonDirectionsForKeyPad(
//           listOf("029A"),
//           hashMapOf()
//       ) shouldHaveKey "029A"
//       Y2024D21().findButtonDirectionsForKeyPad(
//           listOf("029A"),
//           hashMapOf()
//       ) shouldContain ("029A" to listOf("<", "A", "^", "A", "^", "^", ">", "A", "v", "v", "v", "A"))
//       Y2024D21().findButtonDirectionsForDirectionsPad(
//           hashMapOf("029A" to mutableListOf("<", "A", "^", "A", ">", "^", "^", "A", "v", "v", "v", "A"))
//       ) shouldContain ("029A" to listOf())
   }
 })