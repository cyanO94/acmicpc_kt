package implementation.g


// 2447
fun main() {
    Solution2447().solve()
}

class Solution2447 {
    lateinit var map: List<MutableList<Char>>
    fun solve() {
        val n = readln().toInt()

        map = List(n) { MutableList(n) {'*'} }
        recursion(n, 0, 0)

        map.forEach {
            println(it.joinToString(""))
        }

    }

    fun recursion(curN: Int, sX: Int, sY: Int) {
        if (curN == 1) return

        recursion(curN/3, sX, sY)
        recursion(curN/3, sX + curN/3, sY)
        recursion(curN/3, sX + (curN/3) * 2 , sY)
        recursion(curN/3, sX, sY + curN/3)
        //recursion(curN/3, sX + curN/3, sY + curN/3)
        recursion(curN/3, sX + (curN/3) * 2 , sY + curN/3)
        recursion(curN/3, sX, sY + (curN/3) * 2)
        recursion(curN/3, sX + curN/3, sY + (curN/3) * 2)
        recursion(curN/3, sX + (curN/3) * 2 , sY + (curN/3) * 2)

        for (i in 0..<curN/3) {
            for (j in 0..<curN/3) {
                map[sX + curN/3 + i][sY + curN/3 + j] = ' '
            }
        }

    }
}