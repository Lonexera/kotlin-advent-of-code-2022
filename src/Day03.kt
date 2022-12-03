fun main() {

    fun String.splitInHalf(): Pair<String, String> {
        return windowed(
                size = length / 2,
                step = length / 2
        )
                .zipWithNext()
                .first()
    }

    fun Char.toElfErrorCode(): Int {
        val alphabetString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

        return alphabetString.indexOf(this) + 1
    }

    fun part1(input: List<String>): Int {
        return input
                .sumOf { ruckpack ->
                    val (first, second) = ruckpack.splitInHalf()

                    val errorChar = first.find { second.contains(it) }

                    errorChar?.toElfErrorCode() ?: 0
                }
    }

    fun part2(input: List<String>): Int {
        return input
                .chunked(3)
                .sumOf { group ->

                    val badgeType = group
                            .first()
                            .find { item ->
                                group.all { it.contains(item) }
                            }

                    badgeType?.toElfErrorCode() ?: 0
                }
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
