import kotlin.math.sqrt

// note: I switched from the old PDF to the new GitHub page about halfway through chapter 6...
class Sphere(val center: Point, val radius: Double) : Hittable {
    constructor(center: Point, radius: Int) : this(center, radius.toDouble())

    override fun hit(ray: Ray, tMin: Double, tMax: Double): HitRecord? {
        val oc = ray.origin - center
        val a = ray.direction.squaredLength()
        val halfB = oc dot ray.direction
        val c = oc.squaredLength() - (radius * radius)
        val discriminant = (halfB * halfB) - (a * c)

        if (discriminant < 0) return null

        val sqrtDiscriminant = sqrt(discriminant)
        var root = (-halfB - sqrtDiscriminant) / a

        if (root !in tMin..tMax) root = (-halfB + sqrtDiscriminant) / a
        if (root !in tMin..tMax) return null

        return hitRecord(ray, root)
    }

    private fun hitRecord(ray: Ray, t: Double): HitRecord {
        val point = ray[t]
        val hitRecord = HitRecord(t, point, (point - center) / radius)
        hitRecord.setFaceNormal(ray)
        return hitRecord
    }
}