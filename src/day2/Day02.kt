package day2

import readInput

fun main() {
    fun part1(input: List<Pair<String, Int>>): Int {
        var hpos = 0
        var dpos = 0
        input.forEach { (command, amount) ->
            when(command) {
                "forward" -> hpos += amount
                "down" -> dpos += amount
                "up" -> dpos -= amount
            }
        }
        return hpos * dpos
    }

    fun part2(input: List<Pair<String, Int>>): Int {
        var hpos = 0
        var dpos = 0
        var aim = 0
        input.forEach { (command, amount) ->
            when(command) {
                "forward" -> {
                    hpos += amount
                    dpos += aim * amount
                }
                "down" -> aim += amount
                "up" -> aim -= amount
            }
        }
        return hpos * dpos
    }

    val input = readInput("day2/data")
        .map { it.split(" ") }
        .map { (a, b) -> Pair(a, b.toInt()) }
    println(input)
    println(part2(input))
}
