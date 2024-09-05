package implementation.g

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    Solution17144().solve()
}

class Solution17144 {
    data class Node(var cur: Int, var next: Int)
    lateinit var map: List<List<Node>>
    lateinit var airCon: MutableList<Pair<Int, Int>>
    var R = 0
    var C = 0
    var T = 0

    val dx = listOf(0, 0, 1, -1)
    val dy = listOf(1, -1, 0, 0)
    fun solve() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val (R, C, T) = br.readLine().split(" ").map { it.toInt() }
        this.R = R
        this.C = C
        this.T = T
        airCon = mutableListOf<Pair<Int, Int>>()
        map = List(R) { List<Node>(C) { Node(0, 0)} }
        repeat(R) { r ->
            val line = br.readLine().split(" ")
            repeat(C) { c ->
                if (line[c].toInt() == -1) {
                    airCon += Pair(r, c)
                }
                map[r][c].cur = line[c].toInt()
            }
        }
        //debugPrint()

        repeat(T) {
            expand()
            //debugPrint()

            doAirCon()
            //debugPrint()
        }

        println(map.flatten().sumOf { it.cur } + 2)
    }

    fun expand() {
        for (i in map.indices) {
            for (j in map[i].indices) {
                val node = map[i][j]
                if (node.cur <= 0) continue

                val expandedQuantity = node.cur / 5
                if (expandedQuantity == 0) {
                    continue
                }
                var expandCnt = 0
                repeat(4) {
                    val nx = i + dx[it]
                    val ny = j + dy[it]

                    if (nx < 0 || ny < 0 || nx >= R || ny >= C || map[nx][ny].cur == -1) return@repeat
                    map[nx][ny].next += expandedQuantity
                    expandCnt += 1
                }

                node.next -= expandedQuantity * expandCnt
            }
        }

        for (i in map.indices) {
            for (j in map[i].indices) {
                if (map[i][j].cur == -1) continue
                map[i][j].cur += map[i][j].next
                map[i][j].next = 0
                if (map[i][j].cur < 0) map[i][j].cur = 0
            }
        }
    }

    fun doAirCon() {
        var height = airCon[0].first
        var width = C

        map[height - 1][0].cur = 0
        for (i in height - 1 downTo 1) {
            map[i][0].cur = map[i - 1][0].cur
            map[i - 1][0].cur = 0
        }

        for (i in 0 ..< width - 1) {
            map[0][i].cur = map[0][i + 1].cur
            map[0][i + 1].cur = 0
        }

        for (i in 0 ..< height) {
            map[i][C - 1].cur = map[i + 1][C - 1].cur
            map[i + 1][C - 1].cur = 0
        }

        for (i in width - 1 downTo 2) {
            map[height][i].cur = map[height][i - 1].cur
            map[height][i - 1].cur = 0
        }

        height = airCon[1].first
        map[height + 1][0].cur = 0
        for (i in height + 1 ..< R - 1) {
            map[i][0].cur = map[i + 1][0].cur
            map[i + 1][0].cur = 0
        }

        for (i in 0 ..< width - 1) {
            map[R - 1][i].cur = map[R - 1][i + 1].cur
            map[R - 1][i + 1].cur = 0
        }

        for (i in R - 1 downTo height + 1) {
            map[i][C - 1].cur = map[i -1][C - 1].cur
            map[i - 1][C - 1].cur = 0
        }

        for (i in width - 1 downTo 2) {
            map[height][i].cur = map[height][i - 1].cur
            map[height][i - 1].cur = 0
        }
    }

    fun debugPrint() {
        for (line in map) {
            println(line.joinToString(" "){ "${it.cur}"} )
        }
        println()
    }
}