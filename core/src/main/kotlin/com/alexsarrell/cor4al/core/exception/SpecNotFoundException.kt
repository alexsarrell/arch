package com.alexsarrell.cor4al.core.exception

class SpecNotFoundException(
    parentSpec: String,
) : RuntimeException("Parent spec '$parentSpec' not found in serialization context. Check your specification settings")
