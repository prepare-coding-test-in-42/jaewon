package com.example.algorithm

fun main() {
    var (r, c, k) = readln().split(" ").map { it.toInt() }
    val map = Array(100) { Array(100) { 0 } }
    
    for (i in 0..2) {
        val row = readln().split(" ").map { it.toInt() }
        for (j in 0..2) {
            map[i][j] = row[j]
        }
    }
    
    var rowSize = 3
    var colSize = 3
    var time = 0
    
    while (time <= 100) {
        if (map[r-1][c-1] == k) {
            println(time)
            return
        }
        
        if (rowSize >= colSize) {
            colSize = rOperation(map, rowSize, colSize)
        } else {
            rowSize = cOperation(map, rowSize, colSize)
        }
        
        time++
    }
    
    println(-1)
}

fun rOperation(map: Array<Array<Int>>, rowSize: Int, colSize: Int): Int {
    var maxCol = colSize
    
    for (i in 0 until rowSize) {
        val count = mutableMapOf<Int, Int>()
        for (j in 0 until colSize) {
            if (map[i][j] != 0) {
                count[map[i][j]] = count.getOrDefault(map[i][j], 0) + 1
            }
        }
        
        val sortedList = count.entries
            .sortedWith(
                compareBy({ it.value }, { it.key })
            )
            .flatMap { (key, value) ->
                listOf(key, value)
            }
        
        maxCol = maxOf(maxCol, sortedList.size)
        for (j in 0 until 100) {
            map[i][j] = if (j < sortedList.size) sortedList[j] else 0
        }
    }
    
    return if (maxCol > 100) 100 else maxCol
}

fun cOperation(map: Array<Array<Int>>, rowSize: Int, colSize: Int): Int {
    var maxRow = rowSize
    
    for (j in 0 until colSize) {
        val count = mutableMapOf<Int, Int>()
        for (i in 0 until rowSize) {
            if (map[i][j] != 0) {
                count[map[i][j]] = count.getOrDefault(map[i][j], 0) + 1
            }
        }
        
        val sortedList = count.entries
            .sortedWith(
                compareBy({ it.value }, { it.key })
            )
            .flatMap { (key, value) ->
                listOf(key, value)
            }

        maxRow = maxOf(maxRow, sortedList.size)
        for (i in 0 until 100) {
            map[i][j] = if (i < sortedList.size) sortedList[i] else 0
        }
    }
    
    return if (maxRow > 100) 100 else maxRow
}