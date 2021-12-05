package day5

import readInput
import kotlin.math.abs

fun main() {

    fun part1(input: List<Line>): Int {
        val grid = mutableMapOf<Pair<Int,Int>, Int>()
        input.forEach { line ->
            val (x1,y1) = line.p1
            val (x2, y2) = line.p2
            val run = x2 - x1
            val rise = y2 - y1
            val xDir = if (run > 0) 1 else -1
            val yDir = if (rise > 0) 1 else -1
            when {
                run == 0 -> {
                    for (i in 0..abs(rise)) {
                        val key = x1 to y1 + (i * yDir)
                        grid[key] = grid.getOrDefault(key, 0) + 1
                    }
                }
                rise == 0 -> {
                    for (i in 0..abs(run)) {
                        val key = x1 + (i * xDir) to y1
                        grid[key] = grid.getOrDefault(key, 0) + 1
                    }
                }
            }
        }
        return grid.count { it.value > 1 }
    }

    fun part2(input: List<Line>): Int {
        val grid = mutableMapOf<Pair<Int,Int>, Int>()
        input.forEach { line ->
            val (x1,y1) = line.p1
            val (x2, y2) = line.p2
            val run = x2 - x1
            val rise = y2 - y1
            val slope = if (run != 0 && rise != 0) run / rise else 0
            val xDir = if (run > 0) 1 else -1
            val yDir = if (rise > 0) 1 else -1
            when {
                run == 0 -> {
                    for (i in 0..abs(rise)) {
                        val key = x1 to y1 + (i * yDir)
                        grid[key] = grid.getOrDefault(key, 0) + 1
                    }
                }
                rise == 0 -> {
                    for (i in 0..abs(run)) {
                        val key = x1 + (i * xDir) to y1
                        grid[key] = grid.getOrDefault(key, 0) + 1
                    }
                }
                abs(slope) == 1 -> {
                    for(i in 0..abs(run)) {
                        val key = x1 + (i * xDir) to y1 + (i * yDir)
                        grid[key] = grid.getOrDefault(key, 0) + 1
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
    println(part1(input))
}

data class Line(val p1: Pair<Int, Int>, val p2: Pair<Int, Int>)
