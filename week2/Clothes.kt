class Solution {
    fun solution(clothes: Array<Array<String>>): Int {
        var answer = 0
        // 종류별 의상 개수만으로 이루어진 map
        val map = clothes.groupBy { it[1] }.mapValues { it.value.size }

        // 조합론 곱의 법칙
        answer = map.values.fold(1) { acc, i -> acc * (i + 1) } - 1
        return answer
    }
}
