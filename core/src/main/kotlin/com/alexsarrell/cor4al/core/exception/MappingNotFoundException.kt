package com.alexsarrell.cor4al.core.exception

class MappingNotFoundException(type: String) : RuntimeException("Mapping not found for type: $type")