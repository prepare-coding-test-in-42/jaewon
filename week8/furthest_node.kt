class Solution {
    fun solution(n: Int, edge: Array<IntArray>): Int {
        val visited = Array(n + 1) { false to 0}
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.addLast(1 to 0)
        visited[1] = true to 0
        while (queue.isNotEmpty()) {
            val (front, depth) = queue.removeFirst()
            edge.forEach {
                if (it[0] == front && !visited[it[1]].first) {
                    queue.addLast(it[1] to depth + 1)
                    visited[it[1]] = true to depth + 1
                }
                else if (it[1] == front && !visited[it[0]].first) {
                    queue.addLast(it[0] to depth + 1)
                    visited[it[0]] = true to depth + 1
                }
            }
        }
        return visited.filter { node -> node.second == visited.maxOf { it.second } }.size
    }
}