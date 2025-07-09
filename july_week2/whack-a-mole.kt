package com.example.algorithm

import java.util.*
import kotlin.math.*

fun main() {
    val (n, s) = readln().split(" ").map { it.toInt() }
    val moles = Array(n) {
        readln().split(" ").map { it.toInt() }
    }

    fun calcDist(jx: Int, jy: Int, mx: Int, my: Int): Double {
        val dx = (jx - mx).toDouble()
        val dy = (jy - my).toDouble()
        return sqrt(dx.pow(2) + dy.pow(2))
    }

    val dp = Array(n) {
        BooleanArray(n)
    }
    for (i in 0 until n) {
        val (x, y, t) = moles[i]
        val time = calcDist(0, 0, x, y) / s
        if (t >= time) {
            dp[0][i] = true
        }
    }
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (i != j) {
                val (ix, iy, it) = moles[i]
                val (jx, jy, jt) = moles[j]
                val time = calcDist(ix, iy, jx, jy) / s
                if (jt - it >= time) {
                    dp[i][j] = true
                }
            }
        }
    }

    var maxCount = 0
    val visited = BooleanArray(n)
    fun dfs(current: Int, count: Int) {
        maxCount = maxOf(maxCount, count)
        
        for (next in 0 until n) {
            if (!visited[next] && dp[current][next]) {
                visited[next] = true
                dfs(next, count + 1)
                visited[next] = false
            }
        }
    }
    
    for (start in 0 until n) {
        if (dp[0][start]) {
            visited[start] = true
            dfs(start, 1)
            visited[start] = false
        }
    }
    println(maxCount)
}