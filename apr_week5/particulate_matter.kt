package com.example.algorithm

fun main() {
    val (r, c, t) = readln().split(" ").map{ it.toInt() }
    var map = Array(r) {
        readln().split(" ").map{ it.toInt() }.toTypedArray()
    }
    val airCleaner = mutableListOf<Pair<Int, Int>>()
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] == -1)
                airCleaner.add(i to j)
        }
    }
    val (upR, upC) = airCleaner[0]
    val (downR, downC) = airCleaner[1]

    val drdc = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
    repeat(t) {
        val spreads = Array(r) { Array(c) {0} }
        for (i in 0 until r) {
            for (j in 0 until c) {
                var spreadCount = 0
                val spreadVal = map[i][j] / 5
                drdc.forEach { (dr, dc) ->
                    val nextI = i + dr
                    val nextJ = j + dc
                    if (nextI in 0 until r && nextJ in 0 until c && map[nextI][nextJ] != -1) {
                        spreads[nextI][nextJ] += spreadVal
                        spreadCount++
                    }
                }
                spreads[i][j] -= spreadVal * spreadCount
            }
        }
        for (i in 0 until r) {
            for (j in 0 until c) {
                map[i][j] += spreads[i][j]
            }
        }
        val tmp = map.map { it.clone() }.toTypedArray()
        for (i in upC+1..c-2) {
            tmp[upR][i+1] = map[upR][i]
        }
        tmp[upR][upC + 1] = 0
        for (i in upR downTo 1) {
            tmp[i-1][c-1] = map[i][c-1]
        }
        for (i in c-1 downTo upC+1) {
            tmp[0][i-1] = map[0][i]
        }
        for (i in 0..upR-1) {
            tmp[i+1][upC] = map[i][upC]
        }

        for (i in downC+1..c-2) {
            tmp[downR][i+1] = map[downR][i]
        }
        tmp[downR][downC + 1] = 0
        for (i in downR..r-2) {
            tmp[i+1][c-1] = map[i][c-1]
        }
        for (i in c-1 downTo downC+1) {
            tmp[r-1][i-1] = map[r-1][i]
        }
        for (i in r-1 downTo downR+1) {
            tmp[i-1][downC] = map[i][downC]
        }

        tmp[upR][upC] = -1
        tmp[downR][downC] = -1
        map = tmp
    }
    print(
        map.sumOf {
            it.sumOf {
                it
            }
        } + 2
    )
}