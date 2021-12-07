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
        input.filter { it.run == 0 || it.rise == 0 }
            .forEach { it.points().forEach { pt -> increment(grid, pt) } }
        return grid.count { it.value > 1 }
    }

    fun part2(input: List<Line>): Int {
        val grid = mutableMapOf<Coord, Int>()
        input.forEach { it.points().forEach { pt -> increment(grid, pt) } }
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

    fun points(): List<Coord> {
        return when {
            run == 0 -> (0..abs(rise)).map { x1 to y1 + (it * yDir) }
            rise == 0 -> (0..abs(run)).map { x1 + (it * xDir) to y1 }
            abs(slope) == 1 -> (0..abs(run)).map { x1 + (it * xDir) to y1 + (it * yDir) }
            else -> listOf()
        }
    }
}
