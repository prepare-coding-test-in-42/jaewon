fun main() {
    val n = readln().toInt()
    val infos = Array (n) { readln().split(" ").map { it.toInt() } }
    val dx = listOf(1, 0, -1, 0)
    val dy = listOf(0, -1, 0, 1)
    val board = Array(101) { IntArray(101) }

    infos.forEach { info ->
        val (x, y, d, g) = info
        board[y][x] = 1

        val dirs = mutableListOf(d)
        repeat(g) {
            dirs.addAll(dirs.reversed().map{ (it + 1) % 4 })
        }
        var nextX = x
        var nextY = y
        dirs.forEach {
            nextX += dx[it]
            nextY += dy[it]
            board[nextY][nextX] = 1
        }
    }

    print(
        board.mapIndexed { i, it ->
            it.indices.count { j ->
                board[i][j] == 1 &&
                i + 1 in board.indices && board[i + 1][j] == 1 && 
                j + 1 in board[i].indices && board[i][j + 1] == 1 && 
                i + 1 in board.indices && j + 1 in board[i + 1].indices && board[i + 1][j + 1] == 1
            }
        }.sum()
    )
}