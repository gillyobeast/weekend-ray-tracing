fun interface Material {
    /** Returns a scattered ray and how much attenuation happens, or null if the ray is absorbed*/
    fun scatter(ray: Ray, hitRecord: HitRecord): Pair<Ray, Colour>?
}
