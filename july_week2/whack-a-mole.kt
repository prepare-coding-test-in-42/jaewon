package com.example.algorithm

import java.util.*
import kotlin.math.*

fun main() {
    val (n, s) = readln().split(" ").map { it.toInt() }
    val moles = PriorityQueue<Triple<Int, Int, Int>> { a, b ->
        a.third.compareTo(b.third)
    }
    repeat(n) {
        readln().split(" ").map { it.toInt() }.let { (x, y, t) ->
            moles.offer(Triple(x, y, t))
        }
    }

    fun calcDist(jx: Int, jy: Int, mx: Int, my: Int): Double {
        val dx = (jx - mx).toDouble()
        val dy = (jy - my).toDouble()
        return sqrt(dx.pow(2) + dy.pow(2))
    }
}