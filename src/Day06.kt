fun main() {

    fun String.findMarker(markerSize: Int): String {
        return windowed(
                size = markerSize,
                step = 1
        )
                .first {
                    it.toList().distinct().size == it.length
                }
    }

    fun part1(input: List<String>): Int {
        val inputString = input.joinToString("")

        val marker = inputString.findMarker(markerSize = 4)

        return inputString.indexOf(marker) + marker.length
    }

    fun part2(input: List<String>): Int {
        val inputString = input.joinToString("")

        val marker = inputString.findMarker(markerSize = 14)

        return inputString.indexOf(marker) + marker.length
    }

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
