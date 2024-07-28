package graphs.g

import java.util.LinkedList
import java.util.Queue

fun main() {
    val solution = Solution13913()
    solution.solve()

}

class Solution13913 {
    val MAX_LOCATION = 100_000
    fun solve() {
        val(n, k) = readln().split(" ").map { it.toInt() }
        val q = LinkedList<Int>() as Queue<Int>
        val visited = MutableList<Boolean>(MAX_LOCATION + 1) { false }
        val parentList = MutableList<Int>(MAX_LOCATION + 1) { -1 }
        val times = MutableList<Int>(MAX_LOCATION + 1) { 0 }
        q.add(n)
        visited[n] = true

        while (q.isNotEmpty()) {
            val curN = q.poll()

            if (curN == k) {
                val path = mutableListOf<Int>()
                var curPath = curN
                while (curPath != -1) {
                    path.add(0, curPath)
                    curPath = parentList[curPath]
                }

                println(times[curN])
                println(path.joinToString(" "))

                return
            }

            val nextNodes = listOf(curN * 2, curN - 1, curN + 1)
            for (next in nextNodes) {
               if (next in 0..MAX_LOCATION &&!visited[next]) {
                   visited[next] = true
                   q.add(next)
                   parentList[next] = curN
                   times[next] = times[curN] + 1
               }
            }
        }
    }
}