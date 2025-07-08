package com.example.algorithm

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val ladders = Array(n) {
        readln().split(" ").map { it.toInt() - 1 }
    }
    val snakes = Array(m) {
        readln().split(" ").map { it.toInt() - 1}
    }

    // map의 요소들을 0부터 99번까지의 숫자를 가지고 있다고 생각
    // 요소 하나하나들을 순회하며, 해당 요소까지 갈 수 있는 최소 주사위 횟수와 가장 최근 주사위 몇이 나와 현재로 이동했는지를 기록
        // 맨 처음인 0은 Pair(0, 6), 즉 주사위를 0번 사용했고, 가장 최근 주사위를 굴려 나온 숫자는 6이라고 기록
        // 내 위치로 오는 사다리가 있는 지 ladders를 순회하며 판단
            // 없다면, 주사위 한 번을 최대 6이라 생각하며 횟수를 기록
                // 내 이전 번호의 가장 최근 주사위를 굴려 나온 숫자가 6이면 주사위 회수를 한 번 추가 하고, 가장 최근 주사위 수를 1이라 기록
                // 6미만 이라면, 그저 가장 최근 주사위를 1 증가
            // 있다면, 그 사다릴 타고 오는게 나은지, 아니면 없다치고 이전 번호에서 한 칸 더 오는게 나은지 비교
                // 사다리 타는 곳의 최소 주사위 횟수를 그대로 가져오고, 가장 최근 주사위를 6으로 기록한 값과 없다쳤을 때의 값을 비교하면 된다
                    // 주사위 횟수가 더 작으면 무조건 더 낫다
        // 99번까지 도달하여 기록하고 나면 그때의 최소 주사위 횟수를 출력
    
    val dp = Array(100) { Pair(0, 0) }
    dp[0] = Pair(0, 6)

    var i = 1
    while (i < 100) {
        if (dp[i-1].second == 6) {
            dp[i] = Pair(dp[i-1].first + 1, 1)
        } else {
            dp[i] = Pair(dp[i-1].first, dp[i-1].second + 1)
        }

        // 사다리 확인
        for (ladder in ladders) {
            if (ladder[1] == i) {
                val from = ladder[0]
                if (dp[from].first < dp[i].first) {
                    dp[i] = Pair(dp[from].first, 6)
                }
            }
        }

        // 뱀 확인
        var shouldRestart = false
        for (snake in snakes) {
            if (snake[0] == i) {
                val to = snake[1]
                if (dp[i].first < dp[to].first) {
                    dp[to] = Pair(dp[i].first, 6)
                    i = to + 1
                    shouldRestart = true
                }
            }
        }
        if (!shouldRestart) i++
    }
    println(dp[99].first)
}