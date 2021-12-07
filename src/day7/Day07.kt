package day7

import readInput
import kotlin.math.abs

fun main() {

    fun part1(input: List<Int>): Int {
        return ((input.minOf { it })..(input.maxOf { it }))
            .map { p -> input.sumOf { abs(p - it) } }
            .minOf { it }
    }

    fun part2(input: List<Int>): Int {
        return ((input.minOf { it })..(input.maxOf { it }))
            .map { p -> input.sumOf { (1..abs(p - it)).sum() } }
            .minOf { it }
    }

    val input = readInput("day7/test").map { it.split(",") }.flatten().map { it.toInt() }
    println(part2(input))
}
