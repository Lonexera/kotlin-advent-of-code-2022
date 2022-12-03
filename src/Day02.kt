fun main() {

    fun String.mapYourChoiceToRockPaperScissors(): RockPaperScissors {
        return when (this) {
            "X" -> RockPaperScissors.ROCK
            "Y" -> RockPaperScissors.PAPER
            "Z" -> RockPaperScissors.SCISSORS
            else -> error("This input is not allowed - $this!")
        }
    }

    fun String.mapOpponentsChoiceToRockPaperScissors(): RockPaperScissors {
        return when (this) {
            "A" -> RockPaperScissors.ROCK
            "B" -> RockPaperScissors.PAPER
            "C" -> RockPaperScissors.SCISSORS
            else -> error("This input is not allowed - $this!")
        }
    }

    fun getYourResultPoints(
            you: RockPaperScissors,
            opponent: RockPaperScissors
    ): Int {
        val choicePoints = when (you) {
            RockPaperScissors.ROCK -> 1
            RockPaperScissors.PAPER -> 2
            RockPaperScissors.SCISSORS -> 3
        }

        val matchResultPoints = when {
            you == opponent -> 3
            you == RockPaperScissors.PAPER && opponent == RockPaperScissors.ROCK -> 6
            you == RockPaperScissors.SCISSORS && opponent == RockPaperScissors.PAPER -> 6
            you == RockPaperScissors.ROCK && opponent == RockPaperScissors.SCISSORS -> 6
            opponent == RockPaperScissors.PAPER && you == RockPaperScissors.ROCK -> 0
            opponent == RockPaperScissors.SCISSORS && you == RockPaperScissors.PAPER -> 0
            opponent == RockPaperScissors.ROCK && you == RockPaperScissors.SCISSORS -> 0
            else -> error("Undefined match - $opponent vs $you!")
        }

        return choicePoints + matchResultPoints
    }

    fun part1(input: List<String>): Int {
        return input.sumOf {
            val (opponent, you) = it.split(" ")
                    .zipWithNext()
                    .first()

            getYourResultPoints(
                    you = you.mapYourChoiceToRockPaperScissors(),
                    opponent = opponent.mapOpponentsChoiceToRockPaperScissors()
            )
        }
    }

    fun RockPaperScissors.winChoice(): RockPaperScissors {
        return when (this) {
            RockPaperScissors.ROCK -> RockPaperScissors.PAPER
            RockPaperScissors.PAPER -> RockPaperScissors.SCISSORS
            RockPaperScissors.SCISSORS -> RockPaperScissors.ROCK
        }
    }

    fun RockPaperScissors.loseChoice(): RockPaperScissors {
        return when (this) {
            RockPaperScissors.ROCK -> RockPaperScissors.SCISSORS
            RockPaperScissors.PAPER -> RockPaperScissors.ROCK
            RockPaperScissors.SCISSORS -> RockPaperScissors.PAPER
        }
    }

    fun String.getYourChoice(
            opponent: RockPaperScissors
    ): RockPaperScissors {
        return when (this) {
            "X" -> opponent.loseChoice()
            "Y" -> opponent
            "Z" -> opponent.winChoice()
            else -> error("Illegal input - $this")
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf {
            val (opponent, you) = it.split(" ")
                    .zipWithNext()
                    .first()

            val opponentChoice = opponent.mapOpponentsChoiceToRockPaperScissors()

            getYourResultPoints(
                    you = you.getYourChoice(opponentChoice),
                    opponent = opponentChoice
            )
        }
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

enum class RockPaperScissors {
    ROCK,
    PAPER,
    SCISSORS
}
