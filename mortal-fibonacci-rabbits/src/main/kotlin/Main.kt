fun main() {
    val N = 85 // months number
    val M = 19 // months to live for rabbits

    val rabbits = ULongArray(M);
    rabbits[0] = 1uL

    for (n in 1..N - 1) {
        var newRabbits = 0uL

        // right shift for array
        for (i in M - 1 downTo 1) {
            newRabbits += rabbits[i]
            rabbits[i] = rabbits[i - 1]
        }

        // set new rabbit babies
        rabbits[0] = newRabbits
    }

    println("Answer: ${rabbits.sum()} rabbit pairs")
}