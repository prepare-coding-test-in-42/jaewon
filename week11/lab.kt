package com.example.algorithm

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val map = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }

    var maxSafeArea = 0
    val dx = arrayOf(-1, 1, 0, 0)
    val dy = arrayOf(0, 0, -1, 1)

    fun spreadVirus(tempMap: Array<IntArray>): Int {
        val queue = ArrayDeque<Pair<Int, Int>>()
        val visited = Array(n) { BooleanArray(m) }

        // 바이러스 위치 찾기
        map.indices.forEach { i ->
            map[i].indices.forEach { j ->
                if (tempMap[i][j] == 2) {
                    queue.addLast(i to j)
                    visited[i][j] = true
                }
            }
        }

        // 바이러스 퍼뜨리기
        while(queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()

            for(i in 0..3) {
                val nx = x + dx[i]
                val ny = y + dy[i]

                if(nx in 0 until n && ny in 0 until m && !visited[nx][ny] && tempMap[nx][ny] == 0) {
                    tempMap[nx][ny] = 2
                    queue.add(nx to ny)
                    visited[nx][ny] = true
                }
            }
        }

        // 안전 영역 계산
        return tempMap.sumOf { row -> row.count { it == 0 } }
    }

    fun buildWall(count: Int) {
        if(count == 3) {
            // 맵 복사
            val tempMap = Array(n) { i -> map[i].clone() }
            maxSafeArea = maxOf(maxSafeArea, spreadVirus(tempMap))
            return
        }

        map.indices.forEach { i -> 
            map[i].indices.forEach { j ->
                if (map[i][j] == 0) {
                    map[i][j] = 1
                    buildWall(count + 1)
                    map[i][j] = 0
                }
            }
        }
    }

    buildWall(0)
    println(maxSafeArea)
}