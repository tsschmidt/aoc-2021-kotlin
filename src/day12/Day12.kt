package day12

import readInput

typealias CaveMap = MutableMap<String, MutableList<String>>
typealias Paths = MutableList<MutableList<String>>
typealias SplitOn = (String, MutableList<String>) -> Boolean

fun String.isLower() = this.all { it.isLowerCase() }

fun String.isUpper() = this.all { it.isUpperCase() }

const val START = "start"
const val END = "end"

fun main() {

    val splitOn1: SplitOn = { s, cur -> s.isUpper() || s !in cur }

    val splitOn2: SplitOn = { s, cur ->
           s.isUpper()
               || cur.filter { it != START && it.isLower() }.groupBy { it }.none { it.value.size > 1 }
               || s !in cur
        }

    fun findPaths(map: CaveMap, index: Int, splitOn: SplitOn, paths: Paths): Paths {
        val cave = paths[index].last()
        val cur = paths[index].toMutableList()
        map[cave]?.forEachIndexed { i, s ->
            if (splitOn(s, cur)) {
                if (i > 0) {
                    paths.add(cur.toMutableList().also { it.add(s) })
                    findPaths(map, paths.size - 1, splitOn, paths)
                } else {
                    paths[index].add(s)
                    findPaths(map, index, splitOn, paths)
                }
            }
        }
        return paths
    }

    fun createMap(input: List<Pair<String, String>>): CaveMap {
        val map = mutableMapOf<String, MutableList<String>>()
        input.forEach {
            if (it.first != END && it.second != START) {
                map.putIfAbsent(it.first, mutableListOf())
                map[it.first]!!.add(it.second)
            }
            if (it.second != END && it.first != START) {
                map.putIfAbsent(it.second, mutableListOf())
                map[it.second]!!.add(it.first)
            }
        }
        return map
    }

    fun part1(input: CaveMap) =
        findPaths(input, 0, splitOn1, mutableListOf(mutableListOf(START)))
            .filter { it.last() == END }
            .distinct()
            .count()

    fun part2(input: CaveMap) =
        findPaths(input, 0, splitOn2, mutableListOf(mutableListOf(START)))
            .filter { it.last() == END }
            .distinct()
            .count()

    val input = readInput("day12/data").map { Pair(it.split("-")[0], it.split("-")[1]) }
    println(part2(createMap(input)))
}

