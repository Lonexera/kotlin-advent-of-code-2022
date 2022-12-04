fun main() {

    fun String.mapToElvesSections(): Pair<List<Int>, List<Int>> {
        val sections = split(",")
                .map { sections ->
                    val (start, end) = sections
                            .split("-")
                            .map { it.toInt() }
                            .zipWithNext()
                            .first()

                    (start..end).toList()
                }

        return sections[0] to sections[1]
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { elvesSection ->
            val (firstSection, secondSection) = elvesSection.mapToElvesSections()

            when {
                firstSection.containsAll(secondSection) -> 1
                secondSection.containsAll(firstSection) -> 1
                else -> 0
            }
                    .toInt()
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { elvesSection ->
            val (firstSection, secondSection) = elvesSection.mapToElvesSections()

            when {
                firstSection.any { secondSection.contains(it) } -> 1
                else -> 0
            }
                    .toInt()
        }
    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
