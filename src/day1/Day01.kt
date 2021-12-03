package day1

import readInput

fun main() {
    fun part1(input: List<Int>): Int {
        return input.windowed(2).count { (a, b)  -> b > a }
    }

    fun part2(input: List<Int>): Int {
        return part1(input.windowed(3, 1, false).map { it.sum() })
    }

    val input = readInput("day1/test").map { it.toInt() }
    println(part2(input))
}
