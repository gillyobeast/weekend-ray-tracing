data class Ray(val origin: Vector, val direction: Vector) {
    fun at(t: Double) = origin + t * direction
}