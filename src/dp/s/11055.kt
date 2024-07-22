package dp.s

import kotlin.math.max

fun main() {
    val solution = Solution11055()
    solution.solve()
}

class Solution11055 {
    fun solve() {
        readln()
        val array = readln().split(" ").map { it.toInt() }

        val dp = MutableList<Int>(array.size) { 0 }

        array.forEachIndexed { idx, item ->
            var max = 0
            repeat(idx) {
                if (item > array[it]) {
                    max = max(max, dp[it])
                }
            }
            dp[idx] = max + array[idx]
        }

        println(dp.max())
    }
}