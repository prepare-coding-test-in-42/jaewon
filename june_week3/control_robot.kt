package com.example.algorithm

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val map = Array(n) {
        readln().split(" ").map { it.toInt() }.toTypedArray()
    }

    val dp = Array(n) {
        Array(m) {
            Array(3) { -Int.MAX_VALUE }
        }
    }
    dp[0][0][0] = map[0][0]
    dp[0][0][1] = map[0][0]
    dp[0][0][2] = map[0][0]

    for (j in 1 until m) {
        dp[0][j][0] = dp[0][j-1][0] + map[0][j]
    }
    for (i in 1 until n) {
        for (j in 0 until m) {
            if (j == 0)
                dp[i][j][0] = maxOf(dp[i-1][j][0], dp[i-1][j][1], dp[i-1][j][2]) + map[i][j]
            else
                dp[i][j][0] = maxOf(dp[i-1][j][0], dp[i-1][j][1], dp[i-1][j][2], dp[i][j-1][0]) + map[i][j]
        }
        for (j in m-1 downTo 0) {
            if (j == m-1)
                dp[i][j][2] = maxOf(dp[i-1][j][0], dp[i-1][j][1], dp[i-1][j][2]) + map[i][j]
            else
                dp[i][j][2] = maxOf(dp[i-1][j][0], dp[i-1][j][1], dp[i-1][j][2], dp[i][j+1][2]) + map[i][j]
        }
        for (j in 0 until m) {
            dp[i][j][1] = maxOf(dp[i-1][j][0], dp[i-1][j][1], dp[i-1][j][2]) + map[i][j]
        }
    }

    println(maxOf(dp[n-1][m-1][0], dp[n-1][m-1][1], dp[n-1][m-1][2]))
}