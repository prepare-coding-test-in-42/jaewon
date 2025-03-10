fun main() {
    var (n, m, x, y, k) = readln().split(" ").map { it.toInt() }
    val map = Array(n) { mutableListOf<Int>() }
    map.forEach {
        it.addAll(readln().split(" ").map { it.toInt() })
    }
    val orders = readln().split(" ").map { it.toInt() }

    val dice = IntArray(7)
    orders.forEach { order ->
        var (nextX, nextY) = listOf(x, y)
        var tmp = dice[1]
        when {
            order == 1 && ++nextY < m -> {
                y = nextY
                dice[1] = dice[4]
                dice[4] = dice[6]
                dice[6] = dice[3]
                dice[3] = tmp
            }
            order == 2 && --nextY >= 0 -> {
                y = nextY
                dice[1] = dice[3]
                dice[3] = dice[6]
                dice[6] = dice[4]
                dice[4] = tmp
            }
            order == 3 && --nextX >= 0 -> {
                x = nextX
                dice[1] = dice[5]
                dice[5] = dice[6]
                dice[6] = dice[2]
                dice[2] = tmp
            }
            order == 3 && --nextX >= 0 -> {
                x = nextX
                dice[1] = dice[5]
                dice[5] = dice[6]
                dice[6] = dice[2]
                dice[2] = tmp
            }
            order == 4 && ++nextX < n -> {
                x = nextX
                dice[1] = dice[2]
                dice[2] = dice[6]
                dice[6] = dice[5]
                dice[5] = tmp
            }
            else -> return@forEach
        }
        if (map[x][y] == 0)
            map[x][y] = dice[6]
        else {
            dice[6] =  map[x][y]
            map[x][y] = 0
        }
        println(dice[1])
    }
}