package com.example.algorithm
import java.util.*

fun main() {
    var num = 1
    while(true) {
        val n = readln().toInt()
        if (n == 0)
            break
        val map = Array(n) {
            readln().split(" ").map { it.toInt() }
        }

        val minCostRoad = Array (n) {
            Array(n) { Int.MAX_VALUE }
        }
        val pq = PriorityQueue<Triple<Int, Int, Int>> { a, b ->
            a.first.compareTo(b.first)
        }
        val visited = Array(n) {
            BooleanArray(n)
        }
        val dx = listOf(0, 0, -1, 1)
        val dy = listOf(-1, 1, 0, 0)
        
        minCostRoad[0][0] = map[0][0]
        pq.offer(Triple(map[0][0], 0, 0))
        while(pq.isNotEmpty()) {
            val (cost, x, y) = pq.poll()
            if (!visited[x][y]) {
                visited[x][y] = true
                (0..3).forEach {
                    val nx = x + dx[it]
                    val ny = y + dy[it]
                    if (nx in map.indices && ny in map.indices) {
                        val newCost = cost + map[nx][ny]
                        if (newCost < minCostRoad[nx][ny]) {
                            minCostRoad[nx][ny] = newCost
                            pq.offer(Triple(newCost, nx, ny))
                        }
                    }
                }
            }
        }
        println("Problem ${num++}: ${minCostRoad[n-1][n-1]}")
    }
}