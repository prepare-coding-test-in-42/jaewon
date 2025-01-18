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
        val sortedJobs = jobs.mapIndexed { i, j ->
            intArrayOf(i, j[0], j[1])
        }.sortedBy { it[1] }

        // 종료시각 = 시작시간 + 소요시간
        var now = 0
        var isProcessing = false
        val processStartTimeList = mutableListOf<Pair<Int, IntArray>>()
        while (processStartTimeList.size != jobs.size) {
            sortedJobs.forEach {
                if (it[1] == now)
                    waitingQueue.add(it)
            }
            if (processStartTimeList.isNotEmpty() && now - processStartTimeList.last().first >= processStartTimeList.last().second[2])
                isProcessing = false
            if (!isProcessing && waitingQueue.isNotEmpty()) {
                processStartTimeList.add(now to waitingQueue.poll())
                isProcessing = true
            }
            now++
        }
        // 반환시간 = 시작시간 + 소요시간 - 요청시각
        return processStartTimeList.map {
            it.first + it.second[2] - it.second[1]
        }.average().toInt()
    }
}