package graphs.g

import java.util.LinkedList
import java.util.Queue


fun main() {
    val solution = Solution13539()
    solution.solve()

}
class Solution13539 {
    val MAX_LOCATION = 100_000
    fun solve() {
        val(n, k) = readln().split(" ").map { it.toInt() }
        val q = LinkedList<Pair<Int, Int>>() as Queue<Pair<Int, Int>>
        val visited = MutableList<Boolean>(MAX_LOCATION + 1) { false }
        // 반복문 내에서 초를 기준으로 이동할 수 있는 곳 확인...
        // 현재 위치를 방문했는지 알아야하니 BFS
        q.add(Pair(n, 0))
        var res = 0

        while (q.isNotEmpty()) {
            val (curN, time) = q.poll()

            if (curN > MAX_LOCATION) continue
            if (curN == k) {
                res = time
                break
            }
            if (visited[curN]) continue
            visited[curN] = true
            var mN = curN * 2
            while (mN != 0 && mN <= MAX_LOCATION) {
                if (!visited[mN]) q.add(Pair(mN, time))
                mN *= 2
            }

            if (curN - 1 >=0 && !visited[curN - 1]) q.add(Pair(curN - 1, time + 1))
            if (curN + 1 <= MAX_LOCATION && !visited[curN + 1]) q.add(Pair(curN + 1, time + 1))
        }

        println(res)

    }
}