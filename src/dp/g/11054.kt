package dp.g

import kotlin.math.max


fun main() {
    val solution = Solution11054()
    solution.solve()
}

class Solution11054 {
    lateinit var dpAsc: IntArray
    lateinit var dpDesc: IntArray

    fun solve() {
        val cnt = readln().toInt()
        val arr = readln().split(" ").map { it.toInt() }

        dpAsc = IntArray(cnt) { 1 }
        dpDesc = IntArray(cnt) { 1 }

        arr.forEachIndexed { idx, item ->
            for (i in 0..<idx) {
                if (arr[i] < item) {
                    dpAsc[idx] = max(dpAsc[i] + 1, dpAsc[idx])
                }
            }
        }

        val reversedArr = arr.reversed()

        reversedArr.forEachIndexed { idx, item ->
            for (i in 0..<idx) {
                if (reversedArr[i] < item) {
                    dpDesc[idx] = max(dpDesc[i] + 1, dpDesc[idx])
                }
            }
        }

        val maxValue = dpAsc.mapIndexed { index, i -> dpDesc[dpAsc.size - 1 - index] + i }.max()

        println(maxValue - 1)

    }
}