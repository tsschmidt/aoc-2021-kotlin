package day11

import readInput

fun main() {

    fun increase(input: MutableList<MutableList<Int>>) {
        for (y in input.indices) {
            for (x in input[y].indices) {
                input[y][x]++
            }
        }
    }

    fun flash(input: MutableList<MutableList<Int>>, y: Int, x: Int, flashes: MutableList<Pair<Int,Int>>): MutableList<Pair<Int, Int>>  {
       if (input[y][x] > 9) {
           flashes.add(y to x)
           input[y][x] = 0
           if (y - 1 > -1 && x - 1 > -1 && y - 1 to x -1 !in flashes ) {
               input[y - 1][x - 1]++
           }
           if (y - 1 > -1 && y - 1 to x !in flashes) {
               input[y - 1][x]++
           }
           if (y - 1 > -1 && x + 1 < input[y].size && y - 1 to x +1 !in flashes) {
               input[y - 1][x + 1]++
           }
           if (x - 1 > -1 && y to x - 1 !in flashes) {
               input[y][x -1]++
           }
           if (x + 1 < input[y].size && y to x + 1 !in flashes) {
               input[y][x + 1]++
           }
           if (y + 1 < input.size && x - 1 > -1 && y + 1 to x - 1 !in flashes) {
               input[y + 1][x - 1]++
           }
           if (y + 1 < input.size && y + 1 to x !in flashes) {
               input[y + 1][x]++
           }
           if (y + 1 < input.size && x + 1 < input[y].size && y + 1 to x + 1 !in flashes) {
               input[y + 1][x + 1]++
           }
           return flash(input, if(y - 1 > -1) y - 1 else 0, if (x - 1 > -1) x - 1 else 0, flashes)
       }
       if (x + 1 < input[y].size) {
           return flash(input, y, x + 1, flashes)
       } else if (x + 1 == input[y].size && y + 1 < input.size) {
           return flash(input, y + 1, 0, flashes)
       }
       return flashes
    }

    fun part1(input: MutableList<MutableList<Int>>): Int {
        var flashes = 0
        input.forEach(::println)
        repeat(100) {
            increase(input)
            flashes += flash(input, 0, 0, mutableListOf()).size
        }
        return flashes
    }

    fun part2(input: MutableList<MutableList<Int>>): Int {
        var i = 0
        do {
            i++
            increase(input)
        } while (flash(input, 0, 0, mutableListOf()).size < 100)
        return i
    }

    val input = readInput("day11/data").map { it.toList() }.map { it.map { it.toString().toInt() }.toMutableList() }.toMutableList()
    println(part2(input))
}
