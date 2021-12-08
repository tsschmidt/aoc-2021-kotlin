package day8

import readInput

fun main() {

    fun part1(input: List<Pair<List<String>, List<String>>>): Int {
        val t = input.map { it.second }.flatten().filter { it.length in listOf(2,4,3,7) }
        println(t)
        return t.count()
    }

    fun getSigMap(inSignal: List<String>): Map<String, Int> {
        val find1 = inSignal.first { it.length == 2 }
        val find7 = inSignal.first { it.length == 3 }
        val find4 = inSignal.first { it.length == 4 }
        val find8 = inSignal.first { it.length == 7 }
        val find235 = inSignal.filter { it.length == 5 }
        val find3 = find235.first { (it.toList() - find7.toSet()).size == 2 }
        val find5 = (find235 - find3).first { (it.toList() - find4.toSet()).size == 2 }
        val find2 = (find235 - find3 - find5).joinToString("")
        val find069 = inSignal.filter { it.length == 6 }
        val find9 = find069.first { (it.toList() - find3.toSet()).size == 1 }
        val find6 = find069.first { (it.toList() - find7.toSet()).size == 4 }
        val find0 = (find069 - find9 - find6).joinToString("")
        return mapOf(find0 to 0,
            find1 to 1,
            find2 to 2,
            find3 to 3,
            find4 to 4,
            find5 to 5,
            find6 to 6,
            find7 to 7,
            find8 to 8,
            find9 to 9)
    }

    fun part2(input: List<Pair<List<String>, List<String>>>): Int {
        return input.sumOf { (inSignal, outSignal) ->
            val signalMap = getSigMap(inSignal)
            outSignal.map {
                signalMap[signalMap.keys.first { k ->
                    k.length == it.length && (k.toList().containsAll(it.toList()))
                }]
            }.joinToString("").toInt()
        }
    }

    val input = readInput("day8/data")
        .map { Pair(it.substringBefore( " |").split(" "), it.substringAfter("| ").split(" ")) }
    println(part2(input))

}
