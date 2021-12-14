package day13

import readInput

fun main() {

    fun foldUp(map: MutableSet<Pair<Int, Int>>, f: Int) {
        val below = map.filter { it.second > f }
        below.forEach { map.remove(it); map.add(Pair(it.first, f - (it.second - f))) }

    }

    fun foldLeft(map: MutableSet<Pair<Int, Int>>, f: Int) {
        val right = map.filter { it.first > f }
        right.forEach { map.remove(it); map.add(Pair(f - (it.first - f), it.second)) }
    }

    fun part1(map: MutableSet<Pair<Int, Int>>): Int {
        foldLeft(map, 655)
        return map.size
    }

    fun part2(map: MutableSet<Pair<Int, Int>>) {
        foldLeft(map, 655)
        foldUp(map, 447)
        foldLeft(map, 327)
        foldUp(map, 223)
        foldLeft(map, 163)
        foldUp(map, 111)
        foldLeft(map, 81)
        foldUp(map, 55)
        foldLeft(map, 40)
        foldUp(map, 27)
        foldUp(map, 13)
        foldUp(map, 6)

        val maxX = map.map { it.first }.maxOf { it }
        val maxY = map.map { it.second }.maxOf { it }

        for(y in 0..maxY) {
            for (x in 0..maxX) {
                print(if (map.contains(Pair(x, y))) "##" else "  ")
            }
            println()
        }
    }

    val input = readInput("day13/data").filter { it.isNotEmpty() && !it.startsWith("fold")}
        .map { Pair(it.split(",")[0].toInt(), it.split(",")[1].toInt())  }.toMutableSet()
    part2(input)
}
