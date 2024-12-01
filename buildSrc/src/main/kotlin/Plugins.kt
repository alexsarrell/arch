import org.gradle.plugin.use.PluginDependenciesSpec

private fun PluginDependenciesSpec.internal(plugin: String) = id("com.alexsarrell.plugins.$plugin")

val PluginDependenciesSpec.`kotlin-configuration`
    get() = internal("kotlin")
