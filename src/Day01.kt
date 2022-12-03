fun main() {

    fun List<String>.splitByEmpty(): List<List<String>> {
        return buildList {
            this@splitByEmpty.mapIndexedNotNull { index, string ->
                if (string.isBlank()) {
                    index
                } else {
                    null
                }
            }
                    .zipWithNext() // 4,5    5,8   8,10  - no 0,4
                    .forEachIndexed { index, (start, end) ->
                        if (index == 0) {
                            this.add(this@splitByEmpty.subList(0, start).toList())
                        }

                        this.add(this@splitByEmpty.subList(start + 1, end).toList())
                    }
        }
    }

    fun part1(input: List<String>): Int {
        return input
                .splitByEmpty()
                .maxOf { calories ->
                    calories.sumOf { it.toInt() }
                }
    }

    fun part2(input: List<String>): Int {
        return input
                .splitByEmpty()
                .map { calories ->
                    calories.sumOf { it.toInt() }
                }
                .sortedDescending()
                .take(3)
                .sum()
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
