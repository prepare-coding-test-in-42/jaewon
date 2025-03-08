class Solution {
    fun solution(arrows: IntArray): Int {
        var answer = -1
        val dir = listOf (
            listOf(0, -1),
            listOf(1, -1),
            listOf(1, 0),
            listOf(1, 1),
            listOf(0, 1),
            listOf(-1, 1),
            listOf(-1, 0),
            listOf(-1, -1),
        )

        var minX = 0
        var maxX = 0
        var minY = 0
        var maxY = 0
        var curX = 0
        var curY = 0
        arrows.forEach { arrow ->
            repeat(4) {
                curX += dir[arrow][0]
                curY += dir[arrow][1]
                minX = minOf(minX, curX)
                maxX = maxOf(maxX, curX)
                minY = minOf(minY, curY)
                maxY = maxOf(maxY, curY)
            }
        }
        val width = maxX - minX + 3
        val height = maxY - minY + 3
        val map = Array(height) { IntArray(width) }
        val visited = Array(height) { BooleanArray(width) }

        var x = -minX + 1
        var y = -minY + 1
        map[y][x] = 1
        arrows.forEach { arrow ->
            repeat(4) {
                x += dir[arrow][0]
                y += dir[arrow][1]
                map[y][x] = 1
            }
        }

        fun dfs(i: Int, j: Int) {
            visited[i][j] = true
            dir.filterIndexed { idx, it ->
                idx % 2 != 1
            }.forEach {
                val x = j + it[0]
                val y = i + it[1]
                if (y in map.indices && x in map[y].indices &&
                    !visited[y][x] && map[y][x] == 0)
                    dfs(y, x)
            }
        }
        map.forEachIndexed { i, it ->
            it.indices.forEach { j ->
                if (!visited[i][j] && map[i][j] == 0) {
                    answer++
                    dfs(i, j)
                }
            }
        }

        return answer
    }
}