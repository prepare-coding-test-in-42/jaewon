package com.example.algorithm

fun main() {
    val n = readln().toInt()
    var (sx, sy) = readln().split(" ").map { it.toLong() }
    val customers = Array(n) {
        readln().split(" ").map { it.toLong() }
    }
    
    val dx = intArrayOf(0, -1, 1, 0, 0)
    val dy = intArrayOf(0, 0, 0, -1, 1)
    val dp = Array(n) { Array(5) { Long.MAX_VALUE } }

    for (i in dx.indices) {
        val (fx, fy) = customers[0].let { (x, y) ->
            x + dx[i] to y + dy[i]
        }
        dp[0][i] = Math.abs(sx - fx) + Math.abs(sy - fy)
    }

    for (i in 1 until n) {
        val (x, y) = customers[i]
        
        for (pi in 0 until 5) {
            val (px, py) = customers[i - 1].let { (x, y) ->
                x + dx[pi] to y + dy[pi]
            }
            
            for (ci in 0 until 5) {
                val cx = x + dx[ci]
                val cy = y + dy[ci]
                val dist = Math.abs(px - cx) + Math.abs(py - cy)
                dp[i][ci] = minOf(dp[i][ci], dp[i - 1][pi] + dist)
            }
        }
    }

    println(dp[n - 1].min())
}