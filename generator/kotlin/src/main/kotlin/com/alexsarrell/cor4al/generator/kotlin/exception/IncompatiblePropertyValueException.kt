package com.alexsarrell.cor4al.generator.kotlin.exception

class IncompatiblePropertyValueException(
    propertyClass: Class<out Any>,
    specValue: String,
    valueClass: Class<out Any>,
) : RuntimeException("Incompatible property value: property type is $propertyClass, but value: $specValue with type: $valueClass was provided")
