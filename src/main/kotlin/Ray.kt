class Ray(val origin: Point, val direction: Point) {
    fun pointAtParameter(t: Double): Point =
        origin + t * direction
}