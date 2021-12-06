package day6

import readInput

fun main() {

    fun advance(input: MutableList<Long>, days: Int) {
        repeat(days) {
            input.add(input.removeFirst())
            input[6] += input[8]
        }
    }

    fun part1(input: MutableList<Long>): Long {
        advance(input, 80)
        return input.sum()
    }

    fun part2(input: MutableList<Long>): Long {
        advance(input, 256)
        return input.sum()
    }

    val init = mutableListOf<Long>(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L)
    readInput("day6/data")
        .map { it.split(",") }
        .flatten()
        .map { it.toInt() }
        .forEach { init[it]++ }
    println(part2(init))
}
