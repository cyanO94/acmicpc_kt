package dp.g

fun main() {
    val solution = Solution2293()
    solution.solve()
}

class Solution2293 {
    fun solve() {
        val (n, k) = readln().split(" ").map { it.toInt() }
        //var arr = IntArray(n) { readln().toInt() }
        val dp = MutableList<Int>(k + 1) { 0 }

        dp[0] = 1

        repeat(n) {
            val node = readln().toInt()
            for (i in 1..k) {
                if (i - node < 0) continue
                dp[i] += dp[i - node]
            }
        }
        println(dp[k])
    }
}