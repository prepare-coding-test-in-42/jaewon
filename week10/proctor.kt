fun main() {
    readln()
    val students = readln().split(" ").map { it.toLong() }
    val (b, c) = readln().split(" ").map { it.toLong() }
    print(
        students.map {
            if (it - b > 0)
                1 + (it - b) / c + if ((it - b) % c != 0.toLong()) 1 else 0
            else
                1
        }.sum()
    )
}