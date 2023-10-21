class Ray(val origin: Point, val direction: Point) {
    operator fun get(t: Double): Point =
        origin + t * direction
}