package com.example.algorithm

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val office = Array(n) { readln().split(" ").map { it.toInt() }.toTypedArray() }

    val dx = listOf(0, 0, -1, 1)
    val dy = listOf(-1, 1, 0, 0)
    val cctvs = mutableListOf<Triple<Int, Int, List<List<Int>>>>()
    office.forEachIndexed { i, row ->
        row.forEachIndexed { j, it ->
            if (it != 0 && it != 6)
                cctvs.add(Triple(i, j, when(it) {
                    1 -> listOf(listOf(0), listOf(1), listOf(2), listOf(3))
                    2 -> listOf(listOf(0, 1), listOf(2, 3))
                    3 -> listOf(listOf(0, 3), listOf(1, 3), listOf(1, 2), listOf(0, 2))
                    4 -> listOf(listOf(0, 1, 2), listOf(0, 1, 3), listOf(0, 2, 3), listOf(1, 2, 3))
                    else -> listOf(listOf(0, 1, 2, 3))
                }))
        }
    }

    var answer = n*m
    fun dfs(idx: Int, currentOffice: Array<Array<Int>>) {
        if (idx == cctvs.size) {
            answer = minOf(answer, currentOffice.sumOf { it.count { it == 0 } })
            return
        }
        val (i, j, monitorRange) = cctvs[idx]
        monitorRange.forEach { dirs ->
            val newOffice = currentOffice.map { it.clone() }.toTypedArray()
            dirs.forEach {
                var x = j + dx[it]
                var y = i + dy[it]
                while(y in newOffice.indices && x in newOffice[y].indices && newOffice[y][x] != 6) {
                    if (newOffice[y][x] == 0)
                        newOffice[y][x] = -1
                    x += dx[it]
                    y += dy[it]
                }
            }
            dfs(idx + 1, newOffice)
        }
    }
    dfs(0, office)
    print(answer)
}