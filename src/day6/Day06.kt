package day6

import readInput

fun main() {
    fun part1(input: MutableMap<Int, Long>): Long {
        var start = input
        repeat(80) {
            val next = mutableMapOf<Int, Long>()
            next[8] = start[0]!!
            next[7] = start[8]!!
            next[6] = start[7]!! + start[0]!!
            next[5] = start[6]!!
            next[4] = start[5]!!
            next[3] = start[4]!!
            next[2] = start[3]!!
            next[1] = start[2]!!
            next[0] = start[1]!!
            start = next
        }
        return start.values.sum()
    }

    fun part2(input: MutableMap<Int, Long>): Long {
        var start = input
        repeat(256) {
            val next = mutableMapOf<Int, Long>()
            next[8] = start[0]!!
            next[7] = start[8]!!
            next[6] = start[7]!! + start[0]!!
            next[5] = start[6]!!
            next[4] = start[5]!!
            next[3] = start[4]!!
            next[2] = start[3]!!
            next[1] = start[2]!!
            next[0] = start[1]!!
            start = next
        }
        return start.values.sum()
    }

    val map = mutableMapOf(0 to 0L, 1 to 0L, 2 to 0L, 3 to 0L, 4 to 0L, 5 to 0L, 6 to 0L ,7 to 0L, 8 to 0L)
    val input = readInput("day6/data")
        .map { it.split(",") }
        .flatten()
        .map { it.toInt() }
        .forEach { map[it] = map[it]!!  + 1 }
    println(part1(map))
}
