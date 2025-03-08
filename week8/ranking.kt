class Solution {
    fun solution(n: Int, results: Array<IntArray>): Int {
        var answer = 0
        val graph = Array(n + 1) { Array(n + 1) {0} }
        results.forEach { (a, b) ->
            graph[a][b] = 1
        }
        for (i in 1..n) {
            for (j in 1..n) {
                for (k in 1..n) {
                    if (graph[j][i] == 1 && graph[i][k] == 1)
                        graph[j][k] = 1
                }
            }
        }
        for (i in 1..n) {
            var count = 0
            for (j in 1..n) {
                if (graph[i][j] == 1 || graph[j][i] == 1)
                    count++
            }
            if (count == n - 1)
                answer++
        }
        return answer
    }
}