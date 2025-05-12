package com.example.algorithm
import java.util.*

fun main() {
    val n = readln().toInt()
    var shark = Triple(0, 0, 0 to 0)
    val map = Array(n) { r ->
        readln().split(" ").mapIndexed{ c, s ->
            val size = s.toInt()
            if (size == 9)
                shark = Triple(2, 0, r to c)
            size
        }.toTypedArray()
    }

    val drdc = listOf(-1 to 0, 0 to -1, 0 to 1, 1 to 0) // 상 좌 우 하
    var time = 0

    var (size, eat, rc) = shark
    map[rc.first][rc.second] = 0
    var q = PriorityQueue<Triple<Int, Int, Int>> { (r1, c1, d1), (r2, c2, d2) ->
        when {
            d1 != d2 -> d1.compareTo(d2)
            r1 != r2 -> r1.compareTo(r2)
            else -> c1.compareTo(c2)
        }
    }
    q.offer(Triple(rc.first, rc.second, 0))
    var visited = Array(n) { BooleanArray(n) }
    while(q.isNotEmpty()) {
        val (fr, fc, depth) = q.poll()
        visited[fr][fc] = true
        if (map[fr][fc] in 1 until size) {
            map[fr][fc] = 0
            shark = Triple(
                if (size == eat + 1) size + 1 else size,
                if (size == eat + 1) 0 else eat + 1,
                fr to fc
            )
            time += depth
            size = shark.first
            eat = shark.second
            rc = shark.third
            q.clear()
            q.offer(Triple(rc.first, rc.second, 0))
            visited = Array(n) { BooleanArray(n) }
            continue
        }
        drdc.forEach { (dr, dc) ->
            val nextR = fr + dr
            val nextC = fc + dc
            if (nextR in map.indices && nextC in map.indices &&
                !visited[nextR][nextC] &&
                map[nextR][nextC] <= size) {
                q.offer(Triple(nextR, nextC, depth + 1))
            }
        }
    }
    print(time)
}