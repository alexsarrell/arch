package com.alexsarrell.cor4al.generator.kotlin

import com.alexsarrell.cor4al.core.Cor4alGenerator
import com.alexsarrell.cor4al.core.pipeline.Cor4alContext
import com.alexsarrell.cor4al.core.pipeline.pipe.context.ClassSchema
import com.alexsarrell.cor4al.core.pipeline.pipe.context.ParsePipeContext
import com.alexsarrell.cor4al.generator.kotlin.model.ModelAccessor
import com.alexsarrell.cor4al.gradle.api.pipeline.pipe.context.GeneratePipeContext
import com.alexsarrell.cor4al.gradle.api.pipeline.pipe.context.LoadPipeContext
import com.github.jknack.handlebars.Handlebars

// TODO Здесь нет ни намёка на то, что это котлин генератор. Подумай получше, какой фрагмент либы гвоздями прибит к котлину, а остальное всё выноси в кор модули.
class Cor4alKotlinGenerator : Cor4alGenerator {
    private val handlebars = Handlebars()

    override fun generate(context: Cor4alContext) {
        val parseContext = context.getContext(ParsePipeContext::class.java)
        val loadContext = context.getContext(LoadPipeContext::class.java)
        val generateContext = context.getContext(GeneratePipeContext::class.java)

        parseContext.specs.forEach { spec ->
            spec.schemas.forEach {
                generateSchema(it.key to it.value, loadContext, generateContext)
            }
        }
    }

    private fun generateSchema(
        schema: ClassSchema,
        loadContext: LoadPipeContext,
        generateContext: GeneratePipeContext,
    ) {
        val template =
            loadContext.templates[
                schema.second.type.name
                    .lowercase(),
            ]
        val compiled = handlebars.compileInline(template)
        println(compiled.apply(ModelAccessor(schema, generateContext.packageName)))
    }
}
