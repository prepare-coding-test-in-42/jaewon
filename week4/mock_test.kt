class Solution {
    fun solution(answers: IntArray): IntArray {
        val num1 = listOf(1, 2, 3, 4, 5)
        val num2 = listOf(2, 1, 2, 3, 2, 4, 2, 5)
        val num3 = listOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5)
        val scores = IntArray(3)
        answers.forEachIndexed { i, it ->
            if (it == num1[i % num1.size]) scores[0]++
            if (it == num2[i % num2.size]) scores[1]++
            if (it == num3[i % num3.size]) scores[2]++
        }
        val maxScore = scores.maxOrNull()!!
        return scores.mapIndexed { i, it ->
            if (it == maxScore) i + 1 else null
        }.filterNotNull().toIntArray()
    }
}