package com.example.algorithm

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val map = Array(n) {
        readln().split(" ").map { it.toInt() }.toMutableList()
    }
    val orders = Array(m) {
        readln().split(" ").map { it.toInt() }
    }
    var clouds = mutableListOf(n to 1, n to 2, n-1 to 1, n-1 to 2)
    val dx = listOf(0, -1, -1, 0, 1, 1, 1, 0, -1)
    val dy = listOf(0, 0, -1, -1, -1, 0, 1, 1, 1) // 5분

    fun circularCalc(newCoord: Int): Int {
        var ret = newCoord % n
        if (ret > 0)
            return ret
        return ret + n
    }
    orders.forEach { (d, s) ->
        clouds.replaceAll { (y, x) ->
            circularCalc(y + dy[d] * s) to circularCalc(x + dx[d] * s)
        } // 45분
        clouds.forEach { (i, j) ->
            map[i-1][j-1]++
        }
        clouds.forEach { (ci, cj) ->
            val i = ci - 1
            val j = cj - 1
            if (i-1 in 0 until n && j-1 in 0 until n && map[i-1][j-1] > 0)
                map[i][j]++
            if (i-1 in 0 until n && j+1 in 0 until n && map[i-1][j+1] > 0)
                map[i][j]++
            if (i+1 in 0 until n && j-1 in 0 until n && map[i+1][j-1] > 0)
                map[i][j]++
            if (i+1 in 0 until n && j+1 in 0 until n && map[i+1][j+1] > 0)
                map[i][j]++
        }
        val newClouds = mutableListOf<Pair<Int, Int>>()
        val cloudsSet = clouds.toSet()
        map.forEachIndexed { i, it ->
            it.forEachIndexed { j, jt ->
                if (jt >= 2 && !cloudsSet.contains(i+1 to j+1)) {
                    newClouds.add(i+1 to j+1)
                    map[i][j] -= 2
                }
            }
        }
        clouds = newClouds
    }
    print(map.sumOf { it.sum() })
}