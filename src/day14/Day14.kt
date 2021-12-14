package day14

import readInput

fun main() {

     fun insert(rules: MutableMap<String, String>, template: String, repeat: Int): Long {
          val pairs = template.windowed(2)
          var pairCount = mutableMapOf<String, Long>()
          pairs.forEach { pairCount.putIfAbsent(it, 0L); pairCount[it] = pairCount[it]!! + 1L }
          val count = mutableMapOf<Char, Long>()
          template.forEach { count.putIfAbsent(it, 0L); count[it] = count[it]!! + 1L }
          repeat(repeat) {
               val ne = mutableMapOf<String, Long>()
               pairCount.map {
                    val insert = rules[it.key]!![0]
                    count.putIfAbsent(insert, 0L); count[insert] = count[insert]!! + it.value
                    val p1 = "${it.key[0]}$insert"
                    val p2 = "$insert${it.key[1]}"
                    ne.putIfAbsent(p1, 0L); ne[p1] = ne[p1]!! + it.value
                    ne.putIfAbsent(p2, 0L); ne[p2] = ne[p2]!! + it.value
               }
               pairCount = ne
          }
          return count.values.maxOf { it } - count.values.minOf { it }
     }

     fun part1(rules: MutableMap<String,String>, template: String): Long {
          return insert(rules, template, 10)
     }

     fun part2(rules: MutableMap<String,String>, template: String): Long {
          return insert(rules, template, 40)
     }

     val input = readInput("day14/data")
     val template = input[0]
     val rules = mutableMapOf<String, String>()
     input.drop(2).map { rules[it.substringBefore("->").trim()] = it.substringAfter("->").trim() }
     println(part2(rules, template))
}
