package day10

import readInput

fun main() {

    fun part1(input: List<String>): Int {
        val corrupt = mutableListOf<Char>()
        for (i in input) {
            val curOpen = mutableListOf<Char>()
            loop@ for (j in i.toList()) {
                if ((curOpen.isEmpty() && j !in chunks.keys)) {
                    corrupt.add(j)
                    break@loop
                }
                if (j in chunks.keys) {
                    curOpen.add(j)
                } else {
                    if (j != chunks[curOpen.last()]) {
                        corrupt.add(j)
                        break@loop
                    } else {
                        curOpen.removeLast()
                    }
                }
            }
        }
        return corrupt.sumOf { points[it] ?: 0 }
    }

    fun part2(input: List<String>): Long {
        val ends = mutableListOf<List<Char>>();
        for (i in input) {
            val curOpen = mutableListOf<Char>()
            loop@ for (j in i.toList()) {
                if (curOpen.isEmpty() && j !in chunks.keys) {
                    curOpen.clear()
                    break@loop
                }
                if (j in chunks.keys) {
                    curOpen.add(j)
                } else {
                    if (j != chunks[curOpen.last()]) {
                        curOpen.clear()
                        break@loop
                    } else {
                        curOpen.removeLast()
                    }
                }
            }
            if (curOpen.isNotEmpty()) {
                ends.add(curOpen.map { chunks[it]!! }.reversed())
            }
        }
        val mapped = ends.map { e -> e.map { endPoints[it] } }
        val scores = mapped.map { ep -> ep.fold(0L) {acc, i -> acc * 5L + i!! } }
        return scores.sorted()[scores.size / 2]
    }

    val input = readInput("day10/data")
    println(part2(input))
}

val chunks = mapOf('{' to '}', '[' to ']', '(' to ')', '<' to '>')
val points = mapOf( ')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
val endPoints = mapOf(')' to 1L, ']' to 2L, '}' to 3L, '>' to 4L)
