package day4

import readInput

fun main() {

    fun getCards(input: List<String>): MutableList<Card> {
        var cells = mutableListOf<List<Cell>>()
        val cards = mutableListOf<Card>()
        for (i in 2 until input.size) {
            if (input[i].isNotEmpty()) {
                cells.add(input[i].windowed(3, 3, true).map { Cell(it.trim().toInt()) } )
            } else {
                cards.add(Card(cards.size, cells.toList()))
                cells = mutableListOf()
            }
        }
        cards.add(Card(cards.size, cells.toList()))
        return cards
    }

    fun part1(draws: MutableList<Int>, cards: MutableList<Card>): Int {
        var winner: Card? = null
        var d: Int? = null
        while (winner == null) {
            d = draws.removeFirst()
            cards.forEach { it.mark(d) }
            winner = cards.firstOrNull { it.checkForBingo() }
        }
        return winner.sumCard() * d!!
    }

    fun part2(draws: MutableList<Int>, cards: MutableList<Card>): Int {
        var winners: List<Card>? = null
        var dWinner: Int? = null
        while (cards.size > 0 && draws.size > 0) {
            val d = draws.removeFirst()
            cards.forEach { it.mark(d) }
            val curWinners = cards.filter { it.checkForBingo() }
            if (curWinners.isNotEmpty()) {
                winners = curWinners
                cards.removeAll(curWinners)
                dWinner = d
            }
        }
        return winners!![0].sumCard() * dWinner!!
    }

    val input = readInput("day4/data")
    println(part2(input[0].split(",").map { it.toInt() }.toMutableList(), getCards(input)))
}

data class Cell(val id: Int, var marked: Boolean = false)

data class Card(val id: Int, val cells: List<List<Cell>>) {

    private val columns = (0 until cells[0].size).map { i -> cells.map { it[i] } }

    fun mark (drawn: Int) {
        cells.flatten().firstOrNull { c -> c.id == drawn }?.let { it.marked = true }
    }

    fun checkForBingo(): Boolean {
        return cells.any { it.all { c -> c.marked } } ||
            columns.any { it.all { c -> c.marked } }
    }

    fun sumCard(): Int {
        return cells.flatten().filter { c -> !c.marked }.sumOf { c -> c.id }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Card

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}

