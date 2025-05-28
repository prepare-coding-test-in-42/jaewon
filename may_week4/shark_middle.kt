package com.example.algorithm
fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    var map = Array(n) {
        readln().split(" ").map { it.toInt() }.toTypedArray()
    }
    // 계속 반복. 단 1의 결과로 그룹 크기가 2미만이 나오면 정지
    //     1. 가장 큰 그룹 찾기
    //         - 모든 맵을 순회하며, bfs 실행
    //             - 인접한 칸이 본인과 같은 수이거나 0이면 방문
    //             - 그 과정에서 기준 블록을 찾기 위해 행번호가 가장 작은 것, 그 중에서도 열번호가 가장 작은 것을 기준블록으로 갱신하며 기록해둔다
    //         - bfs를 실행 결과로 기준 블록과 블록그룹의 크기를 받는다
    //         - 최대 블록그룹의 크기를 bfs실행마다 비교해가며, 가장 큰 그룹의 기준블록을 기록해두며, 갱신한다
    //             - 만약 현재 최대 블록그룹크기와 같은 블록그룹크기의 블록그룹이 나온다면, 무지개 블록의 수가 많은 것을 최대 블로그룹으로 한다
    //             - 그것도 같다면 기준 블록을 비교하여, 행이 가장 큰 것, 그 중에서도 열이 가장 큰 기준블록을 가장 큰 그룹으로 기록해둔다
    //         - 모든 맵을 순회하며 최종적으로 구한 가장 큰 그룹의 기준블록을 반환한다
    //     2. 1에서 구한 가장 큰 그룹의 기준 블록으로 bfs를 실행한다
    //         - 인접한 칸이 본인과 같은 수이거나 0이면 방문
    //         - 그리고 map에서 그 부분을 (빈칸을 의미하는)-2로 바꾼다
    //         - bfs의 결과값인 그룹크기를 제곱하여, 최종답인 answer에 더해넣는다.
    //     3. -1을 제외한 모든 블록을 최대한 아래로 이동시킨다
    //         - -1에 부딪히거나, map을 벗어나게 이동할 순 없으며, 다른 블록이 이미 밑에 있으면 그 위로 이동시킨다
    //         - 단 -2는 빈칸이므로 이동시키는게 아니라, 블록들의 이동에 따라 잘 바꿔놓는다
    //     4. map을 반시계방향으로 90도 회전한다
    //     5. 3과 같은 행동을 수행한다. 3을 함수로 만들어두고 재실행하면 된다

    fun bfs(visited: Array<BooleanArray>, i: Int, j: Int, color: Int): Triple<Int, Int, Pair<Int, Int>> {
        visited[i][j] = true
        var groupSize = 1
        var rainbowCount = 0
        var standardBlock = Pair(i, j)
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.addLast(Pair(i, j))

        while (queue.isNotEmpty()) {
            val (ci, cj) = queue.removeLast()
            val di = arrayOf(-1, 1, 0, 0)
            val dj = arrayOf(0, 0, -1, 1)
            
            (0..3).forEach {
                val ni = ci + di[it]
                val nj = cj + dj[it]
                if (ni in 0 until n && nj in 0 until n && !visited[ni][nj] &&
                    (map[ni][nj] == color || map[ni][nj] == 0)) {
                    visited[ni][nj] = true
                    groupSize++
                    if (map[ni][nj] == 0) {
                        rainbowCount++
                    } else {
                        if (ni < standardBlock.first || 
                            (ni == standardBlock.first && nj < standardBlock.second)) {
                            standardBlock = Pair(ni, nj)
                        }
                    }
                    queue.addLast(Pair(ni, nj))
                }
            }
        }

        return Triple(groupSize, rainbowCount, standardBlock)
    }

    fun findLargestGroup(): Pair<Int, Pair<Int, Int>> {
        var maxGroupSize = 0
        var maxRainbowCount = 0
        var standardBlock = Pair(-1, -1)

        for (i in 0 until n) {
            for (j in 0 until n) {
                if (map[i][j] > 0) {
                    val visited = Array(n) { BooleanArray(n) }
                    val (groupSize, rainbowCount, block) = bfs(visited, i, j, map[i][j])
                    if (groupSize > maxGroupSize ||
                        (groupSize == maxGroupSize && rainbowCount > maxRainbowCount) ||
                        (groupSize == maxGroupSize && rainbowCount == maxRainbowCount &&
                                (block.first > standardBlock.first ||
                                (block.first == standardBlock.first && block.second > standardBlock.second)))) {
                        maxGroupSize = groupSize
                        maxRainbowCount = rainbowCount
                        standardBlock = block
                    }
                }
            }
        }

        return Pair(maxGroupSize, standardBlock)
    }

    fun removeBlocks(standardBlock: Pair<Int, Int>): Int {
        val visited = Array(n) { BooleanArray(n) }
        val groupSize = bfs(visited, standardBlock.first, standardBlock.second, map[standardBlock.first][standardBlock.second]).first

        for (i in 0 until n) {
            for (j in 0 until n) {
                if (visited[i][j]) {
                    map[i][j] = -2
                }
            }
        }

        return groupSize * groupSize
    }

    fun applyGravity() {
        for (j in 0 until n) {
            for (i in n-1 downTo 0) {
                if (map[i][j] >= 0) {
                    var k = i
                    while (k+1 < n && map[k+1][j] == -2) {
                        map[k+1][j] = map[k][j]
                        map[k][j] = -2
                        k++
                    }
                }
            }
        }
    }

    fun rotateMap() {
        val rotated = Array(n) { i ->
            Array(n) { j ->
                map[j][i]
            }
        }
        map = Array(n) { i->
            Array(n) { j ->
                rotated[n-1 - i][j]
            }
        }
    }

    var answer = 0
    while (true) {
        val (maxGroupSize, standardBlock) = findLargestGroup()
        if (maxGroupSize < 2)
            break
        answer += removeBlocks(standardBlock)
        applyGravity()
        rotateMap()
        applyGravity()
    }

    println(answer)
}