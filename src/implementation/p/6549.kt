package implementation.p

import kotlin.math.max

fun main() {
    val solution = Solution6549()
    solution.solve()
}

// 받으면서 젤 큰거 저장(최소값)
// 이동하면서 더 높은 방향 탐색
class Solution6549 {
    fun solve() {
        while (true) {
            val list = readln().split(" ").map { it.toLong() }.toMutableList()
            list.removeFirst()
            if (list.size == 0) break
            var maxSquare = list.max()

            list.forEachIndexed { idx, height ->
                var i = idx - 1
                var left: Long = 1
                while (i >= 0 && list[i] >= height ) {
                    left += 1
                    i -= 1
                }

                var right: Long = 1
                i = idx + 1

                while (i < list.size && list[i] >= height ) {
                    right += 1
                    i += 1
                }

                val width = right + left - 1

                maxSquare = max(maxSquare, width * height)
            }

            println(maxSquare)
        }
    }
}