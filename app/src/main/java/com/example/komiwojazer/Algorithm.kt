package com.example.komiwojazer

class Algorithm {

    fun findMinRoute(tsp: Array<IntArray>):String {
        var sum = 0
        var counter = 0
        var j = 0
        var i = 0
        var min = Int.MAX_VALUE
        val visitedRouteList: MutableList<Int> = ArrayList()

        visitedRouteList.add(0)
        val route = IntArray(tsp.size)

        while (i < tsp.size
            && j < tsp[i].size
        ) {
            if (counter >= tsp[i].size - 1) {
                break
            }

            if (j != i
                && !visitedRouteList.contains(j)
            ) {
                if (tsp[i][j] < min) {
                    min = tsp[i][j]
                    route[counter] = j + 1
                }
            }
            j++

            if (j == tsp[i].size) {
                sum += min
                min = Int.MAX_VALUE
                visitedRouteList.add(route[counter] - 1)
                j = 0
                i = route[counter] - 1
                counter++
            }
        }

        i = route[counter - 1] - 1
        j = 0
        while (j < tsp.size) {
            if (i != j && tsp[i][j] < min) {
                min = tsp[i][j]
                route[counter] = j + 1
            }
            j++
        }
        sum += min

        return "Minimum cost "+sum

    }
}