package dp.s

import java.util.*
import kotlin.math.min

fun main() {
    val solution = Solution1463()
    solution.solve()
}

class Solution1463 {
    val dp = MutableList<Int>(1000001) { 1000000 }
    fun solve() {
        val x = readln().toInt()

        dp[x] = 0
        doFunc(x)

        println(dp[1])
    }

    fun doFunc(x: Int) {
        if (x < 1) return

        val q : Queue<Pair<Int,Int>> = LinkedList<Pair<Int,Int>>()

        q.add(Pair(x, 0))

        while (!q.isEmpty()) {
            val node = q.poll()

            val idx = node.first
            val savedDp = node.second

            if (idx < 1) continue

            //println(dp[idx])
            if (idx % 3 == 0 && savedDp + 1 < dp[idx/3]) {
                dp[idx/3] = savedDp + 1
                q.add(Pair(idx/3, dp[idx/3]))
                //println("3, $idx")
            }
            if (idx % 2 == 0 && savedDp + 1 < dp[idx/2]) {
                dp[idx/2] = savedDp + 1
                q.add(Pair(idx/2, dp[idx/2]))
                //println("2, $idx")
            }
            if (savedDp + 1 < dp[idx - 1]) {
                dp[idx - 1] = savedDp + 1
                q.add(Pair(idx - 1, dp[idx - 1]))
                //println("1, $idx")
            }
        }
    }
}