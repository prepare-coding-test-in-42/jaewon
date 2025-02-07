class Solution {
    fun solution(n: Int, computers: Array<IntArray>): Int {
        var answer = 0
        val visited = BooleanArray(n)
        fun dfs(i: Int) {
            visited[i] = true
            computers[i].forEachIndexed { idx, it ->
                if (idx != i && it == 1 && !visited[idx])
                    dfs(idx)
            }
        }
        (0 until n).forEach {
            if (!visited[it]) {
                answer++
                dfs(it)
            }
        }
        return answer
    }
}