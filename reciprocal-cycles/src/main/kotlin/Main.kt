import java.util.HashMap

fun getReciprocalCycleLength(denominator: Int): Int {
    val map = HashMap<Int, Int>()
    var numerator = 1
    var index = 1

    while (numerator > 0) {
        if (map.containsKey(numerator)) {
            return index - map[numerator]!!
        }
        map[numerator] = index
        numerator = numerator * 10 % denominator
        index += 1
    }

    return 0
}

fun getLongestReciprocalCycle(upperBound: Int): Int {
    var maxLength = 0
    var d = 0

    for (denom: Int in 2..upperBound) {
        val length = getReciprocalCycleLength(denom)
        if (length > maxLength) {
            d = denom
            maxLength = length
        }
    }

    return d
}

fun main() {
    val upperBound = 1000
    println("Answer: ${getLongestReciprocalCycle(upperBound)}")
}