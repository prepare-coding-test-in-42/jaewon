class Solution {
    fun solution(N: Int, number: Int): Int {
        val dp = mutableListOf(mutableListOf<Int>())
        dp.add(mutableListOf(N))
        dp.add(mutableListOf(2*N, N*N, 1, 0, "$N$N".toInt()))

        dp.forEachIndexed { i, comb ->
            comb.forEach {
                if (it == number)
                    return i
            }
        }
        var answer = 3
        while (answer < 8) {
            dp.add(mutableListOf())

            (1 until answer).forEach {
                dp[answer].addAll(
                    dp[answer-it].flatMap { i ->
                        dp[it].map { j -> i + j }
                    }
                )
                dp[answer].addAll(
                    dp[answer-it].flatMap { i ->
                        dp[it].map { j -> i * j }
                    }
                )
                dp[answer].addAll(
                    dp[answer-it].flatMap { i ->
                        dp[it].mapNotNull { j ->
                            if (j != 0 && i % j == 0)
                                i / j
                            else
                                null
                        }
                    }
                )
                dp[answer].addAll(
                    dp[answer-it].flatMap { i ->
                        dp[it].map { j -> i - j }
                    }
                )
            }
            dp[answer].add("$N".repeat(answer).toInt())

            dp[answer].forEach {
                if (it == number)
                    return answer
            }
            answer++
        }

        return -1
    }
}