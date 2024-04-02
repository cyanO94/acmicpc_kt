package implementation.g

fun main() {
    val solution = Solution3190()
    solution.solve()
}

class Solution3190 {
    var savedDirection = 1

    val dx = listOf(-1, 0, 1, 0)
    val dy = listOf(0, 1, 0, -1)
    var n = 0

    val directionList = mutableMapOf<Int, Char>()
    var head = Pair(0, 0)
    val body = mutableListOf<Pair<Int,Int>>()
    val appleList = mutableListOf<Pair<Int,Int>>()

    fun convertDirection(sec: Int) : Int {
        val d = directionList.getOrDefault(sec, 'X')
        when (d) {
            'L' -> {
                if (savedDirection == 0) savedDirection = 3
                else savedDirection -= 1
            }
            'D' -> {
                if (savedDirection == 3) savedDirection = 0
                else savedDirection += 1
            }
        }

        return savedDirection
    }

    fun solve() {
        val n = readln().toInt()
        this.n = n

        val k = readln().toInt()

        repeat(k) {
            val t = readln().split(" ").map{ it.toInt() }
            appleList.add(Pair(t[0] - 1, t[1] - 1))
        }

        val l = readln().toInt()
        repeat(l) {
            val t = readln().split(" ")
            directionList[t[0].toInt()] = t[1][0]
        }

        var sec = 0

        var res = true
        while (res) {
            sec += 1
            res = goHead()
            if (!res) break

            checkApple()
            changeDirection(sec)
        }
        println(sec)
    }

    fun changeDirection(sec: Int) {
        convertDirection(sec)
    }

    fun checkApple() {
        if (!appleList.contains(head)) {
            body.removeFirst()
        }
        else {
            appleList.remove(head)
        }
    }

    fun goHead(): Boolean {
        body.add(head)
        val nx = head.first + dx[savedDirection]
        val ny = head.second + dy[savedDirection]

        if (nx < 0 || nx >= n || ny < 0 || ny >= n) return false

        head = Pair(nx, ny)
        return !body.contains(head)
    }
}