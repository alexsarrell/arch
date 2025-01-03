package com.alexsarrell.cor4al.core

class DependencyTree<K, V> {
    private val tree: MutableMap<K, MutableList<V>> = mutableMapOf()

    fun register(dependencyName: K, dependency: V) {
        set(dependencyName, dependency)
    }

    operator fun set(key: K, value: V) {
        tree[key]?.add(value) ?: (tree.set(key, mutableListOf(value)))
    }

    operator fun get(key: K) = tree[key]
}
