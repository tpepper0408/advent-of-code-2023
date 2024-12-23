package aockt.y2024

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2024, 23, "Day 23: LAN Party")
class Y2024D23Test : AdventSpec<Y2024D23>({
    partOne {
        "kh-tc\n" +
                "qp-kh\n" +
                "de-cg\n" +
                "ka-co\n" +
                "yn-aq\n" +
                "qp-ub\n" +
                "cg-tb\n" +
                "vc-aq\n" +
                "tb-ka\n" +
                "wh-tc\n" +
                "yn-cg\n" +
                "kh-ub\n" +
                "ta-co\n" +
                "de-co\n" +
                "tc-td\n" +
                "tb-wq\n" +
                "wh-td\n" +
                "ta-ka\n" +
                "td-qp\n" +
                "aq-cg\n" +
                "wq-ub\n" +
                "ub-vc\n" +
                "de-ta\n" +
                "wq-aq\n" +
                "wq-vc\n" +
                "wh-yn\n" +
                "ka-de\n" +
                "kh-ta\n" +
                "co-tc\n" +
                "wh-qp\n" +
                "tb-vc\n" +
                "td-yn" shouldOutput 7
    }
    partTwo {
        "kh-tc\n" +
                "qp-kh\n" +
                "de-cg\n" +
                "ka-co\n" +
                "yn-aq\n" +
                "qp-ub\n" +
                "cg-tb\n" +
                "vc-aq\n" +
                "tb-ka\n" +
                "wh-tc\n" +
                "yn-cg\n" +
                "kh-ub\n" +
                "ta-co\n" +
                "de-co\n" +
                "tc-td\n" +
                "tb-wq\n" +
                "wh-td\n" +
                "ta-ka\n" +
                "td-qp\n" +
                "aq-cg\n" +
                "wq-ub\n" +
                "ub-vc\n" +
                "de-ta\n" +
                "wq-aq\n" +
                "wq-vc\n" +
                "wh-yn\n" +
                "ka-de\n" +
                "kh-ta\n" +
                "co-tc\n" +
                "wh-qp\n" +
                "tb-vc\n" +
                "td-yn" shouldOutput "co,de,ka,ta"
    }
})