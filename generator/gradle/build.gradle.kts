plugins {
    `kotlin-configuration`
}

dependencies {
    implementation(project(":core"))
    implementation(kotlin("gradle-plugin", Versions.kotlinVersion))
    implementation(gradleApi())
}
