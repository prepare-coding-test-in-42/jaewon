package com.example.algorithm

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val map = Array(n) {
        readln().split(" ").map { it.toInt() }.toTypedArray()
    }
    
    val dx = arrayOf(-1, 1, 0, 0)
    val dy = arrayOf(0, 0, -1, 1)
    
    var year = 0
    while (true) {
        val tempMap = Array(n) { map[it].clone() }
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (tempMap[i][j] > 0) {
                    var meltCount = 0
                    for (k in 0..3) {
                        val nx = i + dx[k]
                        val ny = j + dy[k]
                        if (nx in 0 until n && ny in 0 until m && tempMap[nx][ny] == 0)
                            meltCount++
                    }
                    map[i][j] = maxOf(0, tempMap[i][j] - meltCount)
                }
            }
        }
        
        if (map.sumOf { it.sum() } == 0) {
            println(0)
            return
        }
        
        year++

        val visited = Array(n) { BooleanArray(m) }
        var icebergCount = 0
        
        fun dfs(i: Int, j: Int) {
            if (i < 0 || i >= n || j < 0 || j >= m || visited[i][j] || map[i][j] == 0) return

            visited[i][j] = true
            for (k in 0..3) {
                val nx = i + dx[k]
                val ny = j + dy[k]
                dfs(nx, ny)
            }
        }
        
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    icebergCount++
                    if (icebergCount > 1) {
                        println(year)
                        return
                    }
                    dfs(i, j)
                }
            }
        }
    }
}