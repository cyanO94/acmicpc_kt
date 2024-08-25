package implementation.g


import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.StringTokenizer

// 2573
fun main() {
    Solution2573().solve()
}

class Solution2573 {
    val dx = listOf(0, 0, -1, 1)
    val dy = listOf(1, -1, 0, 0)
    val iceList = mutableSetOf<Pair<Int, Int>>()

    lateinit var map :List<MutableList<Int>>
    fun solve() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        map = List(n) { MutableList(m) { 0 } }

        repeat(n) { r ->
            val token = StringTokenizer(br.readLine())
            repeat(m) { c ->
                val input = token.nextToken().toInt()
                map[r][c] = input
                if (input != 0) iceList.add(Pair(r, c))
            }
        }

        var chunkCnt = checkChunk(n, m)
        var loopCnt = 0

        while (!(chunkCnt >= 2 || chunkCnt == 0)) {
            val visited = List(n) { MutableList(m) { false } }


            val iterator = iceList.iterator()
            while (iterator.hasNext()) {
                val water = iterator.next()
                visited[water.first][water.second] = true
                repeat(4) loop4@ {
                    val nx = water.first + dx[it]
                    val ny = water.second + dy[it]

                    if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny]) return@loop4
                    if (map[nx][ny] == 0 && map[water.first][water.second] > 0) map[water.first][water.second] -= 1
                }
                if (map[water.first][water.second] == 0) iterator.remove()
            }
            loopCnt += 1
            chunkCnt = checkChunk(n, m)
        }

        if (chunkCnt == 0) println(0)
        else println(loopCnt)
    }

    fun checkChunk(n: Int, m: Int): Int {
        val visited = List(n) { MutableList(m) { 0 } }
        val q = LinkedList<Pair<Int, Int>>()
        var chunkCnt = 0
        repeat(n) { r ->
            repeat(m) m@ { c->
                if (visited[r][c] != 0 || map[r][c] == 0) return@m
                q.add(Pair(r, c))
                chunkCnt += 1
                visited[r][c] = chunkCnt

                while (q.isNotEmpty()) {
                    val node = q.poll()

                    repeat(4) loop4@ {
                        val nx = node.first + dx[it]
                        val ny = node.second + dy[it]

                        if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny] != 0 || map[nx][ny] == 0) return@loop4
                        q.add(Pair(nx, ny))
                        visited[nx][ny] = chunkCnt
                    }
                }
            }
        }

        return chunkCnt
    }
}