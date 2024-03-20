package implementation.s

fun main() {
    val solution = Solution2941()
    solution.solve()
}

class Solution2941 {
    var msg = ""
    val cAlpha = arrayOf<String>("c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=")
    fun input() {
        val line = readln()
        msg = line
    }

    fun solve() {
        input()
        cAlpha.forEach {
            msg = msg.replace(it, "X")
        }

        println(msg.length)

    }

}