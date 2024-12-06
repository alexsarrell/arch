rootProject.name = "cor4al"

include(":core")
include(":generator:gradle")
findProject("generator:gradle")?.name = "generator-gradle"
include(":generator:kotlin")
findProject("generator:kotlin")?.name = "generator-kotlin"