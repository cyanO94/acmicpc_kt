package implementation.g

import Solution
import java.util.*

class Solution16236 : Solution {

    data class Fish(val x: Int, val y: Int, val size: Int, var distance : Int)
    data class PosWithDis(val x: Int, val y: Int, val distance: Int)

    lateinit var arr : Array<MutableList<Int>>
    lateinit var sharkPos : Pair<Int, Int>
    var sharkSize = 2
    lateinit var fishList : MutableList<Fish>
    var n : Int = 0

    override fun solve() {
        n = readln().toInt()
        arr = Array(n) { readln().split(" ").map { it.toInt() }.toMutableList() }
        sharkPos = Pair<Int, Int>(0, 0)
        fishList = mutableListOf<Fish>()
        var timeCnt = 0

        arr.forEachIndexed { idx, item ->
            item.forEachIndexed { idx2, chr ->
                if (chr == 9) { // shark
                    sharkPos = Pair(idx, idx2)
                    arr[idx][idx2] = 0
                }
                else if (chr != 0) {
                    fishList.add(Fish(idx, idx2, arr[idx][idx2], 0))
                }
            }
        }

        var sharkExp = 0

        while (fishList.isNotEmpty()) {
            var canEatList = fishList.filter { it.size < sharkSize }.toMutableList()
            canEatList.map { it.distance = moveToFish(it) }
            canEatList = canEatList.sortedBy { it.distance }.filter { it.distance != 0 }.toMutableList()
            if (canEatList.isEmpty()) break

            val targetFish = canEatList.first()

            timeCnt += targetFish.distance
            fishList.remove(targetFish)
            arr[targetFish.x][targetFish.y] = 0
            sharkExp += 1
            sharkPos = Pair(targetFish.x, targetFish.y)
            if (sharkExp == sharkSize) {
                sharkSize += 1
                sharkExp = 0
            }
        }

        println(timeCnt)
    }

    val dx = listOf(0, 0, 1, -1)
    val dy = listOf(1, -1, 0, 0)

    fun moveToFish(fish: Fish) : Int {
        // find path bfs
        val visited = mutableSetOf<Pair<Int, Int>>()
        var distance = 0
        val q : Queue<PosWithDis> = LinkedList()

        q.add(PosWithDis(sharkPos.first, sharkPos.second, 0))
        visited.add(sharkPos)

        while (q.isNotEmpty()) {
            val node = q.poll()

            if (fish.x == node.x && fish.y == node.y) {
                distance = node.distance
                break
            }
            for (i in 0..3) {
                val nx = node.x + dx[i]
                val ny = node.y + dy[i]
                val pnPos = Pair(nx, ny)

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue
                if (visited.contains(pnPos)) continue
                if (arr[nx][ny] > sharkSize) continue

                visited.add(pnPos)
                q.add(PosWithDis(nx, ny, node.distance + 1))
            }
        }

        fish.distance = distance

        return distance
    }
}