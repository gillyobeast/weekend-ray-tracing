data class Ray(val origin: Vector, val direction: Vector) {
    operator fun get(t: Double) = origin + t * direction

}
