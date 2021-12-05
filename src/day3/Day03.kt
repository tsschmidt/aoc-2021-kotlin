package day3

import readInput

fun main() {

    fun part1(input: List<String>): Int {
        val split = input.map { it.toList() }
        val pred: (Int) -> String = { i -> if (split.map { it[i] }.count { a -> a == '1' } > input.size / 2) "1" else "0" }
        val gamma = (0 until split[0].size).joinToString("", transform = pred)
        val epsilon = gamma.map { if (it == '1') "0" else "1" }.joinToString("")
        return gamma.toInt(2) * epsilon.toInt(2)
    }

    fun oxygen(ratings : List<List<Char>>, index: Int): List<Char> {
        val cnt = ratings.partition { it[index] == '1' }
            .let { (ones, zeros) -> if (ones.size >= zeros.size) ones else zeros }
        return if (cnt.size == 1) cnt[0] else oxygen(cnt, index + 1)
    }

    fun co2(ratings : List<List<Char>>, index: Int): List<Char> {
        val cnt = ratings.partition { it[index] == '0' }
            .let { (zeros, ones) -> if (zeros.size <= ones.size) zeros else ones }
        return if (cnt.size == 1) cnt[0] else co2(cnt, index + 1)
    }

    fun part2(input: List<String>): Int {
        val split = input.map { it.toList() }
        val o2 = oxygen(split, 0).joinToString("").toInt(2)
        val c2 = co2(split, 0).joinToString("").toInt(2)
        return o2 * c2
    }

    val input = readInput("day3/data")
    println(part2(input))
}
