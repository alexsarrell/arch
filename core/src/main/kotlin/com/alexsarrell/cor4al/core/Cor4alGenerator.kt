package com.alexsarrell.cor4al.core

import com.alexsarrell.cor4al.core.pipeline.Cor4alContext

interface Cor4alGenerator {

    fun generate(context: Cor4alContext)
}
