data class HitRecord(
    val hitPoint: Point,
    private val normal: Vector,
    val normalFacesOut: Boolean,
    val material: Material,
    val t: Double
) {
    val outwardNormal
        get() = if (normalFacesOut) normal else -normal
}
