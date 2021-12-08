package day7

import readInts
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

    val input = readInts("day7/test")
    println(part2(input))
}
