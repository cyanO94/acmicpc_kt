package implementation.g

import kotlin.math.min

fun main() {
    val solution = Solution15686()
    solution.solve()
}

class Solution15686 {
    var n = 0
    var pickCnt = 0
    var cityMinDist = 50000

    val houses = mutableListOf<Pair<Int, Int>>()
    val chicks = mutableListOf<Pair<Int, Int>>()
    val chicksPick = mutableListOf<Pair<Int, Int>>()

    fun solve() {
        val (n, pickCnt) = readln().split(" ").map { it.toInt() }
        val board = Array(n) {readln().split(" ").map { it.toInt() }.toIntArray() }

        this.n = n
        this.pickCnt = pickCnt

        for (i in 0 until n) {
            for (j in 0 until n) {
                if (board[i][j] == 1) houses.add(Pair(i, j))
                else if (board[i][j] == 2) chicks.add(Pair(i, j))
            }
        }

        pick(0)

        println(cityMinDist)
    }

    fun pick(idx:Int) {
        if (chicksPick.size == pickCnt) {
            cityMinDist = min(cityMinDist, countDist())
        }
        else {
            for (i in idx until chicks.size) {
                chicksPick.add(chicks.get(i))
                pick(i + 1)
                chicksPick.remove(chicks.get(i))
            }
        }
    }

    fun countDist() : Int {
        var cityDist = 0
        houses.forEach { house ->
            var dist = 5000
            chicksPick.forEach { chick ->
                dist = min(dist, getDist(house, chick))
            }

            cityDist += dist
        }

        return cityDist
    }

    fun getDist(house: Pair<Int, Int>, chick: Pair<Int, Int>) :Int {
        var x = house.first - chick.first
        if (x < 0) x = -x
        var y = house.second - chick.second
        if (y < 0) y = -y

        return x + y
    }
}