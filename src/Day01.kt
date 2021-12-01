fun main() {
    fun part1(input: List<Int>): Int {
        return input.mapIndexed { index, i -> if (index > 0 && i > input[index - 1]) 1 else 0 }.sum()
    }

    fun part2(input: List<Int>): Int {
        return part1(input.windowed(3, 1, false).map { it.sum() })
    }

    val input = readInput("Day01").map { it.toInt() }
    println(part2(input))
}
