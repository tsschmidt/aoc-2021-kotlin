package day09

import readInput

fun main() {

    fun findLows(input: List<List<Int>>): List<Pair<Int, Int>> {
        val lows = mutableListOf<Pair<Int, Int>>()
        for(y in input.indices) {
            for (x in 0 until input[y].size) {
                val cur = input[y][x]
                val up = y - 1 < 0 || input[y - 1][x] > cur
                val down = y + 1 == input.size || input[y + 1][x] > cur
                val right = x + 1 == input[y].size || input[y][x + 1] > cur
                val left = x - 1 < 0 || input[y][x - 1] > cur
                if (left && up && down && right) lows.add(y to x)
            }
        }
        return lows
    }

    fun part1(input: List<List<Int>>): Int {
        return findLows(input).map { input[it.first][it.second] + 1}.sum()
    }

    fun findBasin(input: List<List<Int>>, basin: MutableList<Pair<Int, Int>>): List<Pair<Int, Int>> {
        val (y, x) = basin.last()
        if (y - 1 > - 1 && input[y -1][x] != 9 && (y - 1 to x) !in basin) {
            basin.add(y -1 to x)
            findBasin(input, basin)
        }
        if (y + 1 < input.size && input[y + 1][x] != 9 && (y + 1 to x) !in basin)  {
            basin.add(y + 1 to x)
            findBasin(input, basin)
        }
        if (x - 1 > -1 && input[y][x - 1] != 9 && (y to x - 1) !in basin) {
            basin.add(y to x - 1)
            findBasin(input, basin)
        }
        if (x + 1 < input[y].size && input[y][x + 1] != 9 && (y to x + 1) !in basin) {
            basin.add(y to x + 1)
            findBasin(input, basin)
        }
        return basin
    }

    fun part2(input: List<List<Int>>): Int {
        val basins = mutableListOf<Int>()
        for (low in findLows(input)) {
            basins.add(findBasin(input, mutableListOf(low)).count())
        }
        basins.sortDescending()
        return basins.take(3).reduce { acc, i ->  acc * i }
    }

    val input = readInput("day09/data").map { it.toList() }.map { it.map { it.toString().toInt() } }
    println(part2(input))
}
