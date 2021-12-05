package day5

import readInput
import kotlin.math.abs

typealias Grid = MutableMap<Pair<Int, Int>, Int>
typealias Coord = Pair<Int, Int>

fun main() {

    fun increment(grid: Grid, key: Coord) {
        grid[key] = grid.getOrDefault(key, 0) + 1
    }

    fun part1(input: List<Line>): Int {
        val grid = mutableMapOf<Coord, Int>()
        input.forEach {
            when {
                it.run == 0 -> {
                    for (i in 0..abs(it.rise)) {
                        increment(grid, it.x1 to it.y1 + (i * it.yDir))
                    }
                }
                it.rise == 0 -> {
                    for (i in 0..abs(it.run)) {
                        increment(grid, it.x1 + (i * it.xDir) to it.y1)
                    }
                }
            }
        }
        return grid.count { it.value > 1 }
    }

    fun part2(input: List<Line>): Int {
        val grid = mutableMapOf<Coord, Int>()
        input.forEach {
            when {
                it.run == 0 -> {
                    for (i in 0..abs(it.rise)) {
                        increment(grid, it.x1 to it.y1 + (i * it.yDir))
                    }
                }
                it.rise == 0 -> {
                    for (i in 0..abs(it.run)) {
                        increment(grid, it.x1 + (i * it.xDir) to it.y1)
                    }
                }
                abs(it.slope) == 1 -> {
                    for(i in 0..abs(it.run)) {
                        increment(grid, it.x1 + (i * it.xDir) to it.y1 + (i * it.yDir))
                    }
                }
            }
        }
        return grid.count { it.value > 1 }
    }

    val input = readInput("day5/data")
        .map { line ->
            val p1 = line.substringBefore("-").split(",").let { it[0].trim().toInt() to it[1].trim().toInt() }
            val p2 = line.substringAfter(">").split(",").let { it[0].trim().toInt() to it[1].trim().toInt()}
            Line(p1, p2)
        }
    println(part2(input))
}

data class Line(val p1: Coord, val p2: Coord) {
    val x1 = p1.first
    val y1 = p1.second
    val x2 = p2.first
    val y2 = p2.second
    val run = x2 - x1
    val rise = y2 - y1
    val xDir = if (run > 0) 1 else -1
    val yDir = if (rise > 0) 1 else -1
    val slope = if (run != 0 && rise != 0) run / rise else 0
}
