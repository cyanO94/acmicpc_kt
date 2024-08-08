package greedy.s.g

import kotlin.math.max

fun main() {
    Solution24337().solve()
}

class Solution24337 {
    fun solve() {
        val (n, a, b) = readln().split(" ").map { it.toInt() }
        val list = MutableList<Int>(n) { 0 }
        val highHeight = max(a, b)
        var highIdx = n - b
        if (a == 1) highIdx = 0

        if (highHeight > n || a + b > n + 1) {
            println("-1")
            return
        }

        list[highIdx] = highHeight

        var curHeight = 1
        for (i in 0..<highIdx) {
            if (list[i] != 0) continue

            if (highIdx - a + 1 > i) {
                list[i] = 1
            } else {
                list[i] = curHeight
                curHeight += 1
            }
        }

        if (curHeight != a) {
            println("-1")
            return
        }

        curHeight = 1
        for (i in n - 1 downTo highIdx + 1) {
            if (list[i] != 0) continue

            if (curHeight < b) {
                list[i] = curHeight
                curHeight += 1
            } else {
                list[i] = 1
            }
        }

        if (curHeight != b) {
            println("-1")
            return
        }

        println(list.joinToString(" "))
    }
}