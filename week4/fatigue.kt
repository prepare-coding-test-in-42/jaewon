class Solution {
    fun solution(k: Int, dungeons: Array<IntArray>): Int {
        var answer: Int = 0
        dungeons.forEachIndexed { i, dungeon ->
            if (k >= dungeon[0]) {
                val explore = 1 + solution(
                    k - dungeon[1],
                    dungeons.sliceArray(0 until i) + dungeons.sliceArray(i + 1 until dungeons.size)
                )
                answer = maxOf(answer, explore)
                if (answer == dungeons.size)
                    return answer
            }
        }
        return answer
    }
}