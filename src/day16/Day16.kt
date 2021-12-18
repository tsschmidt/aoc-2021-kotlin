package day16

import readInput

fun main() {

    fun sumVersion(packet: String): Int {
        if (type(packet) != 4) {
            return version(packet) + subPackets(packet).sumOf {
                sumVersion(it)
            }
        }
        return version(packet)
    }

    fun part1(packet: String): Int {
        return sumVersion(packet)
    }

    fun part2(packet: String): Long {
        return eval(pullOperator(packet))
    }

    var input = toBinary(readInput("day16/data")[0])
    println(part2(input))
}


fun eval(packet: String): Long {
    if (type(packet) != 4) {
        val sp = subPackets(packet).map { eval(it) }
        return when(type(packet)) {
            0 -> sp.sumOf { it }
            1 -> sp.fold(1L) { acc, v -> acc * v }
            2 -> sp.minOf { it }
            3 -> sp.maxOf { it }
            5 -> if (sp[0] > sp[1]) 1 else 0
            6 -> if (sp[0] < sp[1]) 1 else 0
            7 -> if (sp[0] == sp[1]) 1 else 0
            else -> throw IllegalArgumentException()
        }
    }
    return literalValue(packet)
}

fun toBinary(hex: String): String {
    val s = StringBuilder()
    hex.forEach {
        s.append(
            when (it) {
                '0' -> "0000"
                '1' -> "0001"
                '2' -> "0010"
                '3' -> "0011"
                '4' -> "0100"
                '5' -> "0101"
                '6' -> "0110"
                '7' -> "0111"
                '8' -> "1000"
                '9' -> "1001"
                'A' -> "1010"
                'B' -> "1011"
                'C' -> "1100"
                'D' -> "1101"
                'E' -> "1110"
                'F' -> "1111"
                else -> throw IllegalArgumentException()
            })
    }
    return s.toString()
}

fun pullHeader(code: String): String {
    return code.take(6)
}

fun version(code: String): Int {
    return code.take(3).toInt(2)
}

fun type(code: String): Int {
    return pullHeader(code).drop(3).toInt(2)
}

fun pullLiteral(code: String): String {
    var s = 6
    while(code[s] != '0') { s += 5 }
    return code.substring(0, s + 5)
}

fun pullOperator(code: String): String {
    var s = 6
    if (code[s] == '0') {
        val l = code.substring(7, 22).toInt(2)
        return code.substring(0, 22 + l)
    } else {
        val np = code.substring(7, 18).toInt(2)
        s = 18
        var fp = 0
        do {
            val t = code.substring(s, s + 6).drop(3)
            if (t.toInt(2) == 4) {
                s += pullLiteral(code.substring(s)).length
            } else {
                s += pullOperator(code.substring(s)).length
            }
            fp++
        }while (fp < np)
        return code.substring(0, s)
    }
}

fun subPackets(code: String): List<String> {
    var s = 6
    if (code[s] == '0') {
        val l = code.substring(7, 22).toInt(2)
        s = 22
        val fp = mutableListOf<String>()
        do {
            val t = code.substring(s, s + 6).drop(3).toInt(2)
            if (t == 4) {
                fp.add(pullLiteral(code.substring(s)))
            } else {
                fp.add(pullOperator(code.substring(s)))
            }
            s += fp.last().length
        }while (fp.sumOf { it.length } < l)
        return fp
    } else {
        val np = code.substring(7, 18).toInt(2)
        s = 18
        val fp = mutableListOf<String>()
        do {
            val t = code.substring(s, s + 6).drop(3).toInt(2)
            if (t == 4) {
                fp.add(pullLiteral(code.substring(s)))
            } else {
                fp.add(pullOperator(code.substring(s)))
            }
            s += fp.last().length
        }while (fp.size < np)
        return fp
    }
}

fun literalValue(code: String): Long {
    var s = 6
    var v = ""
    while(code[s] != '0') {
        v += code.substring(s + 1, s + 5)
        s += 5
    }
    v += code.substring(s + 1, s + 5)
    return v.toLong(2)
}
