package implementation.g

fun main() {
    val solution = Solution1253()
    solution.solve()
}

// 같은 수여도 index 가 다르면 다른 수
class Solution1253 {
    fun solve() {
        readln().toInt()
        val list = readln().split(" ").map { it.toInt() }.sorted()

        var res = 0
        list.forEachIndexed { idx, target ->
            var left = 0
            var right = list.size - 1

            while (left < right) {
                if (left == idx) {
                    left += 1
                    continue
                }
                if (right == idx) {
                    right -= 1
                    continue
                }

                val mid = list[left] + list[right]

                if (target > mid) {
                    left += 1
                } else if (target < mid) {
                    right -= 1
                } else {
                    res += 1
                    break
                }
            }
        }

        println(res)
    }
}