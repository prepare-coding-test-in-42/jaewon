val dirs = listOf(
    listOf(0, 1),
    listOf(0, -1),
    listOf(-1, 0),
    listOf(1, 0),
)
fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val board = Array(n) { readln().toCharArray() }
    var (rx, ry) = listOf(0, 0)
    var (bx, by) = listOf(0, 0)
    board.forEachIndexed { i, it ->
        it.forEachIndexed { j, jt ->
            if (jt == 'R') {
                rx = j
                ry = i
            } else if (jt == 'B') {
                bx = j
                by = i
            }
        }
    }
    board[ry][rx] = '.'
    board[by][bx] = '.'

    fun move(dir: List<Int>, rbxy: List<Int>): List<Int> {
        var (rx, ry, bx, by) = rbxy
        var (nextRx, nextRy) = listOf(rx + dir[0], ry + dir[1])
        var (nextBx, nextBy) = listOf(bx + dir[0], by + dir[1])
        while ((board[nextRy][nextRx] == '.' || board[nextRy][nextRx] == 'O') &&
            (((nextRx == bx && nextRy == by) && board[nextRy][nextRx] == 'O') ||
            !(nextRx == bx && nextRy == by))) {
            rx = nextRx
            ry = nextRy
            if (board[ry][rx] == 'O')
                break
            nextRx = rx + dir[0]
            nextRy = ry + dir[1]
        }
        while ((board[nextBy][nextBx] == '.' || board[nextBy][nextBx] == 'O') &&
            (((nextBx == rx && nextBy == ry) && board[nextBy][nextBx] == 'O') ||
            !(nextBx == rx && nextBy == ry))) {
            bx = nextBx
            by = nextBy
            if (board[by][bx] == 'O')
                break
            nextBx = bx + dir[0]
            nextBy = by + dir[1]
        }
        while ((board[nextRy][nextRx] == '.' || board[nextRy][nextRx] == 'O') &&
            (((nextRx == bx && nextRy == by) && board[nextRy][nextRx] == 'O') ||
            !(nextRx == bx && nextRy == by))) {
            rx = nextRx
            ry = nextRy
            if (board[ry][rx] == 'O')
                break
            nextRx = rx + dir[0]
            nextRy = ry + dir[1]
        }
        return listOf(rx, ry, bx, by)
    }

    // 30~45분
    // bfs로 몇 번 조작 뒤에 도착하는 지 검사
    val queue = ArrayDeque <Pair<List<Int>, Int>>()
    queue.add(listOf(rx, ry, bx, by) to 0)
    while (queue.isNotEmpty()) {
        val (front, depth) = queue.removeFirst()
        if (depth + 1 > 10) {
            print(-1)
            break
        }
        dirs.forEach {
            val (rx, ry, bx, by) = move(it, front)
            if (board[ry][rx] == 'O' && board[by][bx] != 'O') {
                print(depth + 1)
                return
            } else if (board[by][bx] == 'O')
                return@forEach
            queue.add(listOf(rx, ry, bx, by) to depth + 1)
        }
    }
}
