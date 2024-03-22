package dp.g

import java.util.*
import kotlin.math.*
fun main() {
    val solution = Solution12865()
    solution.solve()
}

class Solution12865 {
    val dp = MutableList<Int>(100001) { 0 }
    lateinit var arr : Array<List<Int>>
    var n = 0
    var k = 0
    fun solve() {
        val (n, k) = readln().split(" ").map { it.toInt() }
        this.n = n
        this.k = k

        arr = Array(n) { readln().split(" ").map { it.toInt() } }

        for (i in 0 until n) {
            val w = arr[i][0]
            val v = arr[i][1]

            for (j in k downTo w ) {
                dp[j] = max(dp[j], dp[j-w] + v)
            }
        }
        println(dp[k])
    }
}