package com.example.algorithm

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    var (r, c, d) = readln().split(" ").map { it.toInt() }
    val map = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }
    var count = 0
    
    val dx = arrayOf(-1, 0, 1, 0)
    val dy = arrayOf(0, 1, 0, -1)
    
    while (true) {
        if (map[r][c] == 0) {
            map[r][c] = 2  // 청소 완료 표시
            count++
        }
        var turn = 0
        var cleaned = false
        
        while (turn < 4) {
            d = if (d == 0) 3 else d - 1
            val nextX = r + dx[d]
            val nextY = c + dy[d]
            
            if (nextX in 0 until n && nextY in 0 until m && map[nextX][nextY] == 0) {
                r = nextX
                c = nextY
                cleaned = true
                break
            }
            turn++
        }
        
        if (!cleaned) {
            val backX = r - dx[d]
            val backY = c - dy[d]
            
            if (backX in map.indices && backY in map[backX].indices && map[backX][backY] != 1) {
                r = backX
                c = backY
            } else {
                break
            }
        }
    }
    
    println(count)
}