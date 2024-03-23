package dp.s

import kotlin.math.min

fun main() {
    val solution = Solution2839()
    solution.solve()
}

// (f - 3) + 1 // (f - 5) + 1
class Solution2839 {
    val dp = MutableList<Int>(5001) {-1}

    fun solve() {
        val weight = readln().toInt()
        dp[3] = 1
        dp[5] = 1

        getMin(weight)

        // println(dp.take(50))

        println(dp[weight])

    }

    fun getMin(weight: Int) : Int {
        if (weight < 3 || weight == 4) return -1
        if (dp[weight] != -1) return dp[weight]

        val v1 = getMin(weight - 3 ) + 1
        val v2 = getMin(weight - 5 ) + 1

        var value = 0
        if (v1 == 0 && v2 == 0) value = -1
        else if (v2 == 0) value = v1
        else if (v1 == 0) value = v2
        else value = min(v1, v2)
        dp[weight] = value
        return value
    }
}