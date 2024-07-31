package implementation.g

fun main() {
    Solution1806().solve()
}

class Solution1806 {
    fun solve() {
        val (n, s) = readln().split(" ").map { it.toInt() }
        val list = readln().split(" ").map { it.toInt() }
        var r = 0
        var partSum = 0
        var minLen = 100_001

        repeat(n) {
            while (partSum < s && r < list.size) {
                partSum += list[r]
                r += 1
            }
            if (partSum >= s && minLen > r - it) {
                minLen = r - it
            }

            partSum -= list[it]
        }

        if (minLen == 100_001) {
            println(0)
        } else {
            println(minLen)
        }
    }
}