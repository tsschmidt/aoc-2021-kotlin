package day15

import readInput
import java.util.PriorityQueue

var dx = intArrayOf(-1, 0, 1, 0)
var dy = intArrayOf(0, 1, 0, -1)
var ROW = 500
var COL = 500

fun main() {
    fun minRisk(grid: Graph, row: Int, col: Int): Int {
        val dist = Array(row) { IntArray(col) { Int.MAX_VALUE } }

        dist[0][0] = grid.get(0,0)

        val pq = PriorityQueue<Cell>(row * col) { a, b -> a.distance.compareTo(b.distance) }

        pq.add(Cell(0, 0, dist[0][0]))
        while (!pq.isEmpty()) {
            val curr = pq.poll()
            for(i in 0 until 4) {
                val rows = curr.x + dx[i]
                val cols = curr.y + dy[i]
                if (rows in 0 until ROW && cols in 0 until COL) {
                    if (dist[rows][cols] > dist[curr.x][curr.y] + grid.get(rows, cols)) {
                        if (dist[rows][cols] != Integer.MAX_VALUE) {
                            val adj = Cell(rows, cols, dist[rows][cols])
                            pq.remove(adj)
                        }
                        dist[rows][cols] = dist[curr.x][curr.y] + grid.get(rows,cols)
                        pq.add(Cell(rows, cols, dist[rows][cols]))
                    }
                }
            }
        }
        return dist[row - 1][col - 1]
    }

    val input = readInput("day15/data").map { it.toList() }.map { it.map { it.toString().toInt() }.toIntArray() }.toTypedArray()
    val graph = Graph(input)
    println(minRisk(graph, ROW, COL) - input[0][0])

}

class Graph(val input: Array<IntArray>) {

    private val rows = input.size
    private val cols = input[0].size

    fun get(r: Int, c: Int): Int {
        val baseRow = r % rows
        val baseCol = c % cols
        val rowAdj = r / rows
        val colAdj = c / cols
        var base = input[baseRow][baseCol]
        repeat(rowAdj + colAdj) {
           base++
           if (base > 9) {
               base = 1
           }
        }
        return base
    }
}

internal class Cell(var x: Int, var y: Int, var distance: Int)
