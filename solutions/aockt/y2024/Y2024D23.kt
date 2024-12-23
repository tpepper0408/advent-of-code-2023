package aockt.y2024

import io.github.jadarma.aockt.core.Solution

class Y2024D23 : Solution {

    /**
     * Find all triangles in the graph and return the count of those that contain a node starting with "t"
     */
    override fun partOne(input: String): Any {
        val connections = input.lines().map { it.split("-") }
        val graph = mutableMapOf<String, MutableSet<String>>()

        for ((a, b) in connections) {
            graph.computeIfAbsent(a) { mutableSetOf() }.add(b)
            graph.computeIfAbsent(b) { mutableSetOf() }.add(a)
        }

        val trios = mutableSetOf<Set<String>>()
        for ((a, neighbors) in graph) {
            for (b in neighbors) {
                for (c in neighbors) {
                    if (b != c && graph[b]?.contains(c) == true) {
                        trios.add(setOf(a, b, c))
                    }
                }
            }
        }
        return trios.count { trio -> trio.any { it.startsWith("t") } }
    }

    /**
     * Find the largest clique in the graph and return it as a sorted list of node names
     */
    override fun partTwo(input: String): Any {
        val connections = input.lines().map { it.split("-") }
        val graph = mutableMapOf<String, MutableSet<String>>()

        for ((a, b) in connections) {
            graph.computeIfAbsent(a) { mutableSetOf() }.add(b)
            graph.computeIfAbsent(b) { mutableSetOf() }.add(a)
        }
        return findLargestClique(graph)
            .sorted()
            .joinToString(",")
    }

    /**
     * Bron-Kerbosch algorithm to find the largest clique in a graph
     */
    private fun findLargestClique(graph: Map<String, MutableSet<String>>): Set<String> {
        var maxClique = mutableSetOf<String>()

        fun bronKerbosch(r: MutableSet<String>, p: MutableSet<String>, x: MutableSet<String>) {
            if (p.isEmpty() && x.isEmpty()) {
                if (r.size > maxClique.size) {
                    maxClique = r
                }
                return
            }

            val u = (p + x).maxByOrNull { graph[it]?.size ?: 0 } ?: return
            for (v in p - (graph[u] ?: emptySet())) {
                bronKerbosch(
                    (r + v).toMutableSet(),
                    p.intersect(graph[v] ?: emptySet()).toMutableSet(),
                    x.intersect(graph[v] ?: emptySet()).toMutableSet()
                )
                p -= v
                x += v
            }
        }

        bronKerbosch(mutableSetOf(), graph.keys.toMutableSet(), mutableSetOf())
        return maxClique
    }
}