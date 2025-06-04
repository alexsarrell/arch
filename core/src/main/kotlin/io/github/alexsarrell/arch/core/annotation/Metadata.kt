package io.github.alexsarrell.arch.core.annotation

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class Metadata(val key: String, val value: String)
