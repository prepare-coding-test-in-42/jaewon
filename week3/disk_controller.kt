import java.util.PriorityQueue

class Solution {
    fun solution(jobs: Array<IntArray>): Int {
        val waitingQueue = PriorityQueue<IntArray> { a, b ->
            when {
                a[2] != b[2] -> a[2].compareTo(b[2])
                a[1] != b[1] -> a[1].compareTo(b[1])
                else -> a[0].compareTo(b[0])
            }
        }
        // 작업번호, 요청시각, 소요시간
        waitingQueue.addAll(
            jobs.mapIndexed { i, j ->
                intArrayOf(i, j[0], j[1])
            }
        )

        // 종료시각 = 시작시간 + 소요시간
        var now = 0
        var ingProcess = intArrayOf()
        val processStartTimeList = mutableListOf<Pair<Int, IntArray>>()
        while (waitingQueue.isNotEmpty()) {
            if (processStartTimeList.isNotEmpty() && now - processStartTimeList.last().first >= processStartTimeList.last().second[2])
                ingProcess = intArrayOf()
            if (ingProcess.isEmpty() && waitingQueue.first()[1] <= now) {
                ingProcess = waitingQueue.poll()
                processStartTimeList.add(now to ingProcess)
            }
            now++
        }
        // 반환시간 = 시작시간 + 소요시간 - 요청시각
        return processStartTimeList.map {
            it.first + it.second[2] - it.second[1]
        }.average().toInt()
    }
}
// 1시간
println(Solution().solution(arrayOf(intArrayOf(0, 3), intArrayOf(1, 9), intArrayOf(3, 5))))