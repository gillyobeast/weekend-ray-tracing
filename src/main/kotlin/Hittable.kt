interface Hittable {
    /** Returns null if no hit, otherwise returns details of hit.*/
    fun hit(ray: Ray, tMin: Double, tMax: Double): HitRecord?
}

data class HitRecord(val time: Double, val hitPoint: Point, val normalAtPoint: Vec3)