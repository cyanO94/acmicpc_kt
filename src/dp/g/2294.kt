package dp.g

import kotlin.math.*

fun main() {
    val solution = Solution2294()
    solution.solve()
}

class Solution2294 {
    fun solve() {
        val (n, k) = readln().split(" ").map { it.toInt() }
        val arr = IntArray(n) { readln().toInt() }
        val dp = MutableList<Int>(k + 1) {  k + 1 }

        dp[0] = 0

        for (i in 1..k) {
            repeat(n) {
                val coin = arr[it]
                if (i - coin < 0) return@repeat

                dp[i] = min(dp[i - coin] + 1, dp[i])
            }
        }

        if (dp[k] == k + 1) println("-1")
        else println(dp[k])
    }
}