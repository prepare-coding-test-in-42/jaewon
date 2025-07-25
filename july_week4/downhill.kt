package com.example.algorithm

fun main() {
    val (m, n) = readln().split(" ").map { it.toInt() }
    val map = Array(m) {
        readln().split(" ").map { it.toInt() }
    }

    val dp = Array(m) {
        IntArray(n) { -1 }
    }
    val dx = listOf(0, 0, -1, 1)
    val dy = listOf(-1, 1, 0, 0)

    fun dfs(x: Int, y: Int): Int {
        if (x == n - 1 && y == m - 1)
            return 1
        if (dp[y][x] != -1)
            return dp[y][x]
        dp[y][x] = 0
        for (i in 0..3) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (nx in 0 until n && ny in 0 until m && map[y][x] > map[ny][nx])
                dp[y][x] += dfs(nx, ny)
        }
        return dp[y][x]
    }
    print(dfs(0, 0))
}