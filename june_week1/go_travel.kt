package com.example.algorithm

fun main() {
    val n = readln().toInt()
    val m = readln().toInt()
    val map = Array(n) {
        readln().split(" ").map { it.toInt() }
    }
    val plan = readln().split(" ").map { it.toInt() - 1 }
    
    val parent = IntArray(n) { it }
    fun find(x: Int): Int {
        if (parent[x] != x) {
            parent[x] = find(parent[x])
        }
        return parent[x]
    }
    fun union(x: Int, y: Int) {
        val rootX = find(x)
        val rootY = find(y)
        if (rootX != rootY)
            parent[rootX] = rootY
    }
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (map[i][j] == 1) {
                union(i, j)
            }
        }
    }
    
    val firstCity = find(plan[0])
    if (plan.all { find(it) == firstCity })
        print("YES")
    else
        print("NO")
}