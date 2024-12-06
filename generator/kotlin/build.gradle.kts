plugins {
    `kotlin-configuration`
}

dependencies {
    implementation(project(":core"))
    implementation(kotlin("gradle-plugin", Versions.kotlinVersion))
    implementation(gradleApi())

    implementation("org.jetbrains.kotlin:kotlin-script-runtime:1.8.0")
    implementation("org.jetbrains.kotlin:kotlin-script-util:1.8.0")
    implementation("org.jetbrains.kotlin:kotlin-compiler:1.8.0")
}
