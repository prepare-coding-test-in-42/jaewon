import kotlin.math.*

class Solution {
    fun solution(name: String): Int {
        var answer = 0
        var greedy = name.length - 1
        name.forEach {
            answer += min(abs(it - 'A'), 26 - abs(it - 'A'))
        }
        name.indices.forEach { 
            var next = it + 1
            while (next in name.indices && name[next] == 'A') {
                next++
            }
            greedy = minOf(greedy, it * 2 + (name.length - next), it + (name.length - next) * 2)
        }
        return answer + greedy
    }
}