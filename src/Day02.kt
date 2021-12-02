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

    val input = readInput("Day02")
        .map {
            Pair(it.substring(0, it.indexOf(" ")), it.substring(it.indexOf(" ") + 1).toInt())
        }
    println(input)
    println(part2(input))
}
