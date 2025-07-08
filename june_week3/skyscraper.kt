package com.example.algorithm

fun main() {
    val n = readln().toInt()
    val buildings = readln().split(" ").map { it.toInt() }
    // 모든 빌딩들에 대해, 현 위치에서 좌우로 보이는 빌딩 수를 계산하고, 빌딩들 중 최대로 빌딩이 보일 때의 수를 answer 변수에 갱신할 것
    // 현 위치에서 좌우로 보이는 빌딩 수 계산은 다음과 같다.
    // 왼오의 최고층의 기울기 재야함
    // 최고층이 나보다 낮을 때
        // 최고층보다 높거나 같으면 무조건 보임
        // 최고층보다 낮은 층인 경우
            // 오른쪽은 기울기가 같거나 작으면 안 보임
            // 왼쪽은 기울기가 같거나 크면 안 보임
    // 최고층이 나보다 높거나 같을 때
        // 최고층보다 낮거나 같으면 무조건 안 보임
        // 최고층보다 높은 층인 경우
            // 오른쪽은 기울기가 같거나작으면 안 보임
            // 왼쪽은 기울기가 같거나 크면 안 보임
    var answer = 0
    
    buildings.indices.forEach { i ->
        var visibleCount = 0

        var leftHeightMax = 0
        var leftSlope = Double.POSITIVE_INFINITY        
        for (j in i - 1 downTo 0) {
            val leftHeight = buildings[j]
            val width = i - j
            val slope = (buildings[i] - leftHeight).toDouble() / width

            if (leftHeightMax < buildings[i]) {
                if (leftHeight >= leftHeightMax) {
                    visibleCount++
                    leftHeightMax = leftHeight
                    leftSlope = slope
                } else if (slope < leftSlope) {
                    visibleCount++
                }
            } else {
                if (leftHeight > leftHeightMax) {
                    if (slope < leftSlope)
                        visibleCount++
                    leftHeightMax = leftHeight
                    leftSlope = slope
                }
            }
        }

        var rightMaxHeight = 0
        var rightMaxSlope = Double.NEGATIVE_INFINITY
        for (j in i + 1 until n) {
            val rightHeight = buildings[j]
            val width = j - i
            val slope = (rightHeight - buildings[i]).toDouble() / width
            
            if (rightMaxHeight < buildings[i]) {
                if (rightHeight >= rightMaxHeight) {
                    visibleCount++
                    rightMaxHeight = rightHeight
                    rightMaxSlope = slope
                } else if (slope > rightMaxSlope) {
                    visibleCount++
                }
            } else {
                if (rightHeight > rightMaxHeight) {
                    if (slope > rightMaxSlope)
                        visibleCount++
                    rightMaxHeight = rightHeight
                    rightMaxSlope = slope
                }
            }
        }

        answer = maxOf(answer, visibleCount)
    }
    
    println(answer)
}