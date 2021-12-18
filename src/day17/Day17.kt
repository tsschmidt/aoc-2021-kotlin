package day17

//val targetX = 20..30; val targetY = -10..-5
val targetX =185..221; val targetY = -122..-74
fun main() {

    fun hit(x: Int, y: Int): Int {
        return when {
            targetX.contains(x) && targetY.contains(y) -> 0
            x > targetX.last || y < targetY.first -> 1
            else -> -1
        }
    }

    fun fire(vX: Int, vY: Int): Pair<Int, Int>? {
        var step = 0
        var x = 0
        var y = 0
        var svX = vX
        var svY = vY
        var hit: Int
        do {
            step++
            x += svX
            y += svY
            svX = if(svX > 0) svX - 1 else if (svX < 0) svX + 1 else 0
            svY -= 1
            hit = hit(x, y)
        }while(hit < 0)
        return if (hit == 0) Pair(vX, vY) else null
    }

    fun part1(): Int {
        val hits = mutableListOf<Pair<Int,Int>>()
        for (i in -2..300) {
            for (j in 1..400) {
                val f = fire(i, j)
                if (f != null) {
                    hits.add(f)
                }
            }
        }
        return hits.size
    }

    fun part2(): Int {
        val hits = mutableListOf<Pair<Int,Int>>()
        for (i in 0..500) {
            for (j in -500..500) {
                val f = fire(i, j)
                if (f != null) {
                    hits.add(f)
                }
            }
        }
        return hits.size
    }

    println(part2())
}
