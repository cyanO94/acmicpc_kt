package greedy.s.g

import java.util.PriorityQueue

fun main() {
    Solution1202().solve()
}

class Solution1202 {
    fun solve() {
        val (n, k) = readln().split(" ").map { it.toInt() }
        val pq = PriorityQueue<Pair<Int, Int>>(Comparator { a, b -> if (a.first != b.first) a.first - b.first else b.second - a.second } )
        val pSumQ = PriorityQueue<Int>(Comparator { a , b -> b - a } )

        val bagList = PriorityQueue<Int>()

        repeat(n) {
            val (w, v) = readln().split(" ").map{ it.toInt() }
            pq.add(Pair(w, v))
        }
        repeat(k) {
            val bag = readln().toInt()
            bagList.add(bag)
        }

        var res: Long = 0
        for (bag in bagList) {
            while (pq.isNotEmpty() && pq.peek().first <= bag) {
                pSumQ.add(pq.poll().second)
            }
            if (pSumQ.isNotEmpty()) res += pSumQ.poll()
        }
        println(res)
    }
}