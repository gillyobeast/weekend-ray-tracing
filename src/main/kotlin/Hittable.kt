interface Hittable {
    /** Returns null if no hit, otherwise returns details of hit.*/
    fun hit(ray: Ray, tMin: Double, tMax: Double): HitRecord?
}

data class HitRecord(
    val time: Double, val hitPoint: Point,
    val normalAtPoint: Vec3, val frontFace: Boolean = false,
) {
    // ew
    fun setFaceNormal(ray: Ray): HitRecord {
        val other = copy(frontFace = ray.direction dot normalAtPoint < 0)
        return other
            .copy(normalAtPoint = if (other.frontFace) normalAtPoint else -normalAtPoint)
    }
}