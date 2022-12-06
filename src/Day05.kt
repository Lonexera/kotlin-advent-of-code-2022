fun main() {

    fun String.getAllNumbers(): List<Int> {
        val decimals = "1234567890"

        return map {
            if (it in decimals) it else " "
        }
                .joinToString("")
                .split(" ")
                .filter { it.isNotBlank() }
                .map { it.toInt() }
    }

    fun part1(instructions: List<String>): String {
        val scheme = cratesScheme.toMutableList()

        instructions.map { instruction ->
            instruction.getAllNumbers()
        }
                .forEach {
                    val numberOfCrates = it[0]
                    val from = it[1] - 1
                    val to = it[2] - 1

                    val cratesToMove = scheme[from].take(numberOfCrates)
                    // remove crates from stack
                    scheme[from] = scheme[from].removePrefix(cratesToMove)
                    // add crates to new stack
                    scheme[to] = cratesToMove.reversed() + scheme[to]
                }

        return scheme
                .mapNotNull { it.firstOrNull() }
                .joinToString(separator = "")
    }

    fun part2(instructions: List<String>): String {
        val scheme = cratesScheme.toMutableList()

        instructions.map { instruction ->
            instruction.getAllNumbers()
        }
                .forEach {
                    val numberOfCrates = it[0]
                    val from = it[1] - 1
                    val to = it[2] - 1

                    val cratesToMove = scheme[from].take(numberOfCrates)
                    // remove crates from stack
                    scheme[from] = scheme[from].removePrefix(cratesToMove)
                    // add crates to new stack
                    scheme[to] = cratesToMove + scheme[to]
                }

        return scheme
                .mapNotNull { it.firstOrNull() }
                .joinToString(separator = "")
    }

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}

private val cratesScheme = listOf(
        "JZGVTDBN",
        "FPWDMRS",
        "ZSRCV",
        "GHPZJTR",
        "FQZDNJCT",
        "MFSGWPVN",
        "QPBVCG",
        "NPBZ",
        "JPW"
)

private val exampleScheme = listOf(
        "NZ",
        "DCM",
        "P"
)