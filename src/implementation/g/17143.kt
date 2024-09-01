package implementation.g

// 17143
fun main() {
    Solution17143().solve()
}

class Solution17143 {
    data class Shark(val idx: Int, var x: Int, var y: Int, val speed: Int, var d: Int, var size: Int)

    val UP = 0
    val DOWN = 1
    val RIGHT = 2
    val LEFT = 3

    lateinit var map: List<MutableList<Int>>
    val dx = listOf(-1, 1, 0, 0)
    val dy = listOf(0, 0, 1, -1)

    var sharkList = mutableListOf<Shark>()
    val removeList = mutableListOf<Shark>()

    fun solve() {
        val (R, C, S) = readln().split(" ").map { it.toInt() }
        map = List<MutableList<Int>>(R) { MutableList(C) { 0 } }
        repeat(S) {
            val (r, c, s, d, z) = readln().split(" ").map { it.toInt() }
            val ss = when (d - 1) {
                UP, DOWN -> {
                    s % ((R -1) * 2)
                }
                RIGHT, LEFT -> {
                    s % ((C -1) * 2)
                }
                else -> 0
            }


            sharkList.add(Shark(it + 1, r - 1, c - 1, ss, d - 1, z))
            map[r - 1][c - 1] = it + 1
        }

        var totalSize = 0

        var fisherLoc = -1

        while (fisherLoc < C - 1) {
            // 1.
            fisherLoc += 1

            // 2.
            val fishingShark = sharkList.filter { it.y == fisherLoc }.minByOrNull { it.x }
            fishingShark?.let {
                totalSize += fishingShark.size
                sharkList.remove(fishingShark)
                map[fishingShark.x][fishingShark.y] = 0
            }

            // 3.
            var iter = sharkList.iterator()
            while (iter.hasNext()) {
                val shark = iter.next()
                map[shark.x][shark.y] = 0

                var moveCnt = 0
                while (moveCnt < shark.speed) {
                    var nx = shark.x + dx[shark.d]
                    var ny = shark.y + dy[shark.d]

                    if (nx < 0) {
                        nx = 1
                        shark.d = DOWN
                    }
                    else if (nx >= R) {
                        nx = R - 2
                        shark.d = UP
                    }
                    else if (ny < 0)  {
                        ny = 1
                        shark.d = RIGHT
                    }
                    else if (ny >= C) {
                        ny = C - 2
                        shark.d = LEFT
                    }

                    shark.x = nx
                    shark.y = ny
                    moveCnt += 1
                }
            }

            iter = sharkList.iterator()
            while (iter.hasNext()) {
                val s = iter.next()

                if(map[s.x][s.y] == 0) map[s.x][s.y] = s.idx
                else {
                    val overlapShark = sharkList.firstOrNull { it.idx == map[s.x][s.y]}
                    overlapShark?.let {
                        if (it.size > s.size) {
                            iter.remove()
                        } else {
                            map[s.x][s.y] = s.idx
                            removeList.add(it)
                        }
                    }
                }
            }

            sharkList.removeAll(removeList.toSet())
            removeList.clear()
        }
        println(totalSize)
    }
}

