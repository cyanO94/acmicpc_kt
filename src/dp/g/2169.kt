package dp.g

import kotlin.math.max

// 2169
fun main() {
    Solution2169().solve()
}

class Solution2169 {
    lateinit var map: List<MutableList<Char>>
    fun solve() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val map = List<List<Int>>(n) { readln().split(" ").map { it.toInt() } }
        val memo = List(n) {MutableList(m) { Int.MIN_VALUE } }
        val toLeft = MutableList(m) { 0 }
        val toRight = MutableList(m) { 0 }

        memo[0][0] = map[0][0]
        for (i in 1..< m) {
            memo[0][i] = map[0][i] + memo[0][i - 1]
        }

        for (i in 1..<n) {
            toLeft[m - 1] = memo[i - 1][m - 1] + map[i][m - 1]
            for (j in m - 2 downTo 0) {
                toLeft[j] = max(memo[i - 1][j], toLeft[j + 1]) + map[i][j]
            }

            toRight[0] = memo[i - 1][0] + map[i][0]
            for (j in 1 ..< m) {
                toRight[j] = max(memo[i - 1][j], toRight[j - 1]) + map[i][j]
            }

            for (j in 0..<m) {
                memo[i][j] = max(toLeft[j], toRight[j])
            }
        }

        println(memo[n - 1][m - 1])
    }
}