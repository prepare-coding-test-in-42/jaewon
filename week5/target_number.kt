class Solution {
    fun solution(numbers: IntArray, target: Int): Int {
        fun dfs(i: Int, sum: Int): Int {
            if (i == numbers.size)
                return if (sum == target) 1 else 0
            return dfs(i + 1, sum + numbers[i]) + dfs(i + 1, sum - numbers[i])
        }
        return dfs(0, 0)
    }
}