class Solution {
    fun solution(clothes: Array<Array<String>>): Int {
        var answer = 1
        // 종류별 의상 개수만으로 이루어진 map
        val map = mutableMapOf<String, Int>()
        clothes.forEach {
            map[it[1]] = map.getOrDefault(it[1], 0) + 1 // 키가 있으면 가져오고, 없으면 2번째 인자
            // 결국 키값(종류)이 동일하면 개수를 1 증가시켜 개수를 카운트
        }

        // 조합론 곱의 법칙
        map.forEach { answer *= (it.value + 1) }
        return answer - 1
    }
}
