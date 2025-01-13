class Solution {
    fun solution(clothes: Array<Array<String>>): Int {
        var answer = 0
        val map = HashMap<String, String>()
        clothes.forEach { map[it[1]] = it[0] }
        repeat(map.size) { answer += combInHash(clothes, it + 1) }
        return answer
    }
    val comb = mutableListOf<Array<String>>()
    val combToMaps = mutableListOf(mapOf<String, String>())

    fun combClothes(clothes: Array<Array<String>>, num: Int, curr: Int) {
        if (comb.size == num) {
            // [1]을 key로 map에 넣음
            combToMaps.add(comb.map { it[1] to it[0] }.toMap())
            return
        }
        for (i in curr until clothes.size) {
            comb.add(clothes[i])
            combClothes(clothes, num, i + 1)
            comb.removeLast()
        }
    }

    fun combInHash(clothes: Array<Array<String>>, num: Int): Int {
        var comb = 0
        // clothes 중 중복되지 않게 num개 고름
        combClothes(clothes, num, 0)
        // 길이가 num인지 확인
        combToMaps.forEach { if (it.size == num) comb += 1 }
        combToMaps.clear()
        return comb
    }
}
