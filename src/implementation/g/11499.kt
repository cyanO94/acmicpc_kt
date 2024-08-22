package implementation.g

// 14499
fun main() {
    Solution14499().solve()
}

private class Dice {
    var x = 0
    var y = 0
    var bottom = 0
    var top = 0
    var front = 0
    var left = 0
    var right = 0
    var rear = 0

    fun rollNam() {
        val tmp = bottom

        bottom = front
        front = top
        top = rear
        rear = tmp
    }

    fun rollBook() {
        val tmp = bottom

        bottom = rear
        rear = top
        top = front
        front = tmp
    }

    fun rollDong() {
        val tmp = right

        right = top
        top = left
        left = bottom
        bottom = tmp
    }

    fun rollSeo() {
        val tmp = right

        right = bottom
        bottom = left
        left = top
        top = tmp
    }
}

class Solution14499 {
    val dx = listOf(0, 0, -1, 1)
    val dy = listOf(1, -1, 0, 0)

    fun solve() {
        val (n, m, x, y, k) = readln().split(" ").map { it.toInt() }
        val dice = Dice()
        dice.x = x
        dice.y = y
        val map = List<IntArray>(n) { readln().split(" ").map { it.toInt() }.toIntArray() }
        val orders = readln().split(" ").map { it.toInt() }

        orders.forEach {
            val nx = dice.x + dx[it - 1]
            val ny = dice.y + dy[it - 1]

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) return@forEach

            when (it) {
                1 -> dice.rollDong()
                2 -> dice.rollSeo()
                3 -> dice.rollBook()
                4 -> dice.rollNam()
            }

            if (map[nx][ny] == 0) {
                map[nx][ny] = dice.bottom
            } else {
                dice.bottom = map[nx][ny]
                map[nx][ny] = 0
            }

            dice.x = nx
            dice.y = ny
            println(dice.top)
        }

    }

}