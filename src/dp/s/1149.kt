package dp.s

import kotlin.math.min

fun main() {
    val solution = Solution1149()
    solution.solve()
}
class Solution1149 {
    fun solve() {
        val lineCnt = readln().toInt()
        val memo = Array(lineCnt + 1) { IntArray(3) }

        val firstLine = readln().split(" ").map { it.toInt() }.toIntArray()
        memo[0] = firstLine
        var curLine = 1

        repeat(lineCnt - 1) {
            val line = readln().split(" ").map { it.toInt() }

            line.forEachIndexed { idx, item ->
                var minVal = 1000000
                for (i in 0..2) {
                    if (idx == i) continue
                    minVal = min(memo[curLine - 1][i] + item, minVal)
                }

                memo[curLine][idx] = minVal
            }
            curLine += 1
        }

        println(memo[curLine - 1].min())
    }
}