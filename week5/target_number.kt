class Solution {
    fun solution(numbers: IntArray, target: Int): Int {
        var answer = 0
        val graph = Array(numbers.size * 2 + 1){ mutableListOf<Int>() }
        graph[0].add(1)
        graph[0].add(2)
        (1 until numbers.size).forEach {
            graph[it*2].add(it*2 + 1)
            graph[it*2].add(it*2 + 2)
            graph[it*2 - 1].add(it*2 + 1)
            graph[it*2 - 1].add(it*2 + 2)
        }
        fun dfs(i: Int, depth: Int, toTarget: Int) {
            if (depth == numbers.size && toTarget == target)
                answer++
            graph[i].forEach {
                dfs(it, depth + 1, toTarget + numbers[(it - 1) / 2] * if (it % 2 == 0) -1 else 1)
            }
        }
        dfs(0, 0, 0)
        return answer
    }
}