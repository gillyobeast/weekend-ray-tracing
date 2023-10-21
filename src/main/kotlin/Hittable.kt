interface Hittable {
    /** Returns null if no hit, otherwise returns details of hit.*/
    fun hit(ray: Ray, tMin: Double, tMax: Double): HitRecord?
}

data class HitRecord(val t: Double, val P: Point, val normal: Vec3)