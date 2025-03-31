fun main() {
    val n = readln().toInt()
    val table = Array(n) { readln().split(" ").map { it.toInt() } }
    var max = 0
    fun dfs(i: Int, cost: Int){
        if (i > n)
            return
        if (cost > max)
            max = cost
        if (i == n)
            return
        dfs(i + table[i][0], cost + table[i][1])
        dfs(i + 1, cost)
    }
    dfs(table[0][0], table[0][1])
    dfs(1, 0)
    println(max)
}