fun main() {
    fun part1(input: List<Pair<String, Int>>): Int {
        var hpos = 0;
        var dpos = 0;
        input.forEach {
            when(it.first) {
                "forward" -> hpos += it.second
                "down" -> dpos += it.second
                "up" -> dpos -= it.second
            }
        }
        return hpos * dpos
    }

    fun part2(input: List<Pair<String, Int>>): Int {
        var hpos = 0;
        var dpos = 0;
        var aim = 0;
        input.forEach {
            when(it.first) {
                "forward" -> {
                    hpos += it.second
                    dpos += aim * it.second
                }
                "down" -> aim += it.second
                "up" -> aim -= it.second
            }
        }
        return hpos * dpos
    }

    val input = readInput("Day02_test")
        .map { it.split(" ") }
        .map { Pair(it.get(0), it.get(1).toInt()) }
    println(input)
    println(part2(input))
}
