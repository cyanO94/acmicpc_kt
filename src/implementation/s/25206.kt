package implementation.s

fun main() {
    val solution = Solution25206()
    solution.solve()
}


class Solution25206 {

    val scoreMap = mapOf(
        "A+" to 4.5,
        "A0" to 4.0,
        "B+" to 3.5,
        "B0" to 3.0,
        "C+" to 2.5,
        "C0" to 2.0,
        "D+" to 1.5,
        "D0" to 1.0,
        "F" to 0.0
    )
    fun solve() {
        var cnt = 0
        var total = 0.0
        var totalScore = 0.0

        repeat(20) {
            val (_, score, grade) =  readln().split(" ")
            val g = score.toDouble() * scoreMap.getOrDefault(grade, 0.0)

            if (grade != "P") totalScore += score.toDouble()
            total += g
            cnt += 1
        }

        println(total/totalScore)
    }

}
