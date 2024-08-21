package implementation.g

import kotlin.math.abs
import kotlin.math.min
import kotlin.math.pow

// 1107
fun main() {
    Solution1107().solve()
}

// 현재 채널 100 (항상 비교)
// 앞뒤로 1개씩해서 3개 숫자 비교
// 9 오른 쪽에 0, 0 왼쪽에 9
class Solution1107 {
    val numbers = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    var minDiff = 0
    var targetList = mutableListOf<Int>()
    var targetChannel = 0
    var clicks = 0

    fun solve() {
        val targetChannel = readln().toInt()
        this.targetChannel = targetChannel
        val n = readln().toInt()
        val brokenButtons = if (n != 0) readln().split(" ").map { it.toInt() }
        else emptyList()

        brokenButtons.forEach {
            numbers[it] = -1
        }

        targetChannel.toString().forEach {
            targetList.add(it.digitToInt())
        }

        // 100 에서
        minDiff = abs(targetChannel - 100)
        clicks = abs(targetChannel - 100)

        dfs(0, 0)

        println(clicks)
    }

    fun dfs(depth: Int, curChannel: Int) {
        if (depth >= targetList.size - 1 && depth != 0) {
            minDiff = min(minDiff, abs(targetChannel - curChannel))
            if (depth + abs(targetChannel - curChannel) < clicks) {
                clicks = depth + minDiff
            }
            if (depth > targetList.size + 1) {
                return
            }
        }

        numbers.forEach {
            if (it == -1) return@forEach
            dfs(depth + 1, curChannel + it * 10.0.pow(depth.toDouble()).toInt())
        }
    }
}