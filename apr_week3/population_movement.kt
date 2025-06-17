fun main() {
    val (n, l, r) = readln().split(" ").map { it.toInt() }
    val map = Array(n) {
        readln().split(" ").map { it.toInt() }.toMutableList()
    }

    val dx = listOf(0, 0, -1, 1)
    val dy = listOf(-1, 1, 0, 0)
    var visited = arrayOf<BooleanArray>()

    fun dfs(i: Int, j: Int): Pair<List<Pair<Int, Int>>, Boolean> {
        var route = listOf(i to j)
        var adj = false
        visited[i][j] = true
        (0..3).forEach {
            val mi = i + dy[it]
            val mj = j + dx[it]
            if (mi in map.indices && mj in map.indices
                && Math.abs(map[i][j] - map[mi][mj]) in l..r
                && !visited[mi][mj]) {
                adj = true
                route += dfs(mi, mj).first
            }
        }
        return route to adj
    }
    fun move_pop(route: List<Pair<Int, Int>>){
        val avg = route.sumOf { (i, j) ->
            map[i][j]
        } / route.size
        route.forEach { (i, j) ->
            map[i][j] = avg
        }
    }

    var answer = 0
    var move = true
    while (move) {
        move = false
        visited = map.map {
            BooleanArray(it.size)
        }.toTypedArray()
        map.indices.forEach { i ->
            map[i].indices.forEach { j ->
                if (!visited[i][j]) {
                    val (route, adj) = dfs(i, j)
                    if (adj) {
                        move = true
                        move_pop(route)
                    }
                }
            }
        }
        if (move)
            answer++
    }
    print(answer)
}