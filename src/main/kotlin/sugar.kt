operator fun StringBuilder.plus(s: Any): StringBuilder {
    this.append(s)
    return this
}

operator fun StringBuilder.invoke(): String {
    return toString()
}


val Number.d get() = this.toDouble()

operator fun Double.times(vector: Vector) = vector * this

fun Double.clamp(minimumValue: Double, maximumValue: Double) =
    coerceAtMost(maximumValue).coerceAtLeast(minimumValue)



typealias Point = Vector
