fun main() {
    val N = 85 // months number
    val M = 19 // months to live for rabbits

    val rabbits = ULongArray(M);
    rabbits[0] = 1uL

    for (n in 1..N - 1) {
        val newRabbits = rabbits.sum() - rabbits[0]

        // right shift for array
        for (i in M - 1 downTo 1) {
            rabbits[i] = rabbits[i - 1]
        }

        // set new rabbit babies
        rabbits[0] = newRabbits
    }

    println("Answer: ${rabbits.sum()} rabbit pairs")
}