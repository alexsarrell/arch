package com.alexsarrell.cor4al.core

import com.alexsarrell.cor4al.core.model.SchemaProperty

interface SchemaPropertyValidator {

    fun validate(schemaProperty: SchemaProperty)
}
