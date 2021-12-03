fun main() {

    fun part1(input: List<String>): Int {
        val split = input.map { it.toList() }
        val pred: (Int) -> String = { i -> if (split.map { it[i] }.count { a -> a == '1' } > input.size / 2) "1" else "0" }
        val gamma = (0 until split[0].size).joinToString("", transform = pred)
        val epsilon = gamma.map { if (it == '1') "0" else "1" }.joinToString("")
        return gamma.toInt(2) * epsilon.toInt(2)
    }

    fun oxygen(ratings : List<List<Char>>, index: Int): List<Char> {
        val cnt = ratings.count { it[index] == '1' }
        val pred = if (cnt >= ratings.size - cnt) '1' else '0'
        val f = ratings.filter { it[index] == pred }
        return if (f.size == 1) f[0] else oxygen(f, index + 1)
    }

    fun co2(ratings : List<List<Char>>, index: Int): List<Char> {
        val cnt = ratings.count { it[index] == '0' }
        val pred = if (cnt <= ratings.size - cnt) '0' else '1'
        val f = ratings.filter { it[index] == pred }
        return if (f.size == 1) f[0] else co2(f, index + 1)
    }

    fun part2(input: List<String>): Int {
        val split = input.map { it.toList() }
        val o2 = oxygen(split, 0).joinToString("").toInt(2)
        val c2 = co2(split, 0).joinToString("").toInt(2)
        return o2 * c2
    }

    val input = readInput("Day03_test")
    println(part1(input))
}
