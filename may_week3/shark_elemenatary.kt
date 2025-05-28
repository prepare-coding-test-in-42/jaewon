package com.example.algorithm
import java.util.PriorityQueue

fun main() {
    val n = readln().toInt()
    val favs = Array(n*n) {
        readln().split(" ").map { it.toInt() }
    }
    val seats = Array(n) {
        IntArray(n)
    }

    fun countAdj(row: Int, col: Int, favorites: List<Int>): Pair<Int, Int> {
        val dx = intArrayOf(-1, 1, 0, 0)
        val dy = intArrayOf(0, 0, -1, 1)
        var favCount = 0
        var emptyCount = 0

        (0..3).forEach {
            val newRow = row + dx[it]
            val newCol = col + dy[it]

            if (newRow in 0 until n && newCol in 0 until n) {
                if (seats[newRow][newCol] == 0) {
                    emptyCount++
                } else if (favorites.contains(seats[newRow][newCol])) {
                    favCount++
                }
            }
        }

        return Pair(favCount, emptyCount)
    }

    favs.forEach {
        val pq = PriorityQueue<Triple<Pair<Int, Int>, Int, Int>> { a, b ->
            when {
                a.first.first != b.first.first -> b.first.first - a.first.first
                a.first.second != b.first.second -> b.first.second - a.first.second
                a.second != b.second -> a.second - b.second
                else -> a.third - b.third
            }
        }

        for (i in 0 until n) {
            for (j in 0 until n) {
                if (seats[i][j] != 0) continue
                pq.offer(Triple(countAdj(i, j, it.drop(1)), i, j))
            }
        }

        val (_, row, col) = pq.poll()
        seats[row][col] = it[0]
    }

    var answer = 0
    favs.forEach {
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (it[0] == seats[i][j]) {
                    val (fav, _) = countAdj(i, j, it.drop(1))
                    answer += when (fav) {
                        0 -> 0
                        1 -> 1
                        2 -> 10
                        3 -> 100
                        else -> 1000
                    }
                }
            }
        }
    }
    print(answer)
}