package com.alexsarrell.cor4al.core.model.type

data class Version(val major: Int, val minor: Int, val patch: Int) {
    override fun toString(): String {
        return "$major.$minor.$patch"
    }
}
