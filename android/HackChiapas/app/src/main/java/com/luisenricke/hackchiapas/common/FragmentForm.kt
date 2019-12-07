package com.luisenricke.hackchiapas.common

@Suppress("unused")
interface FragmentForm {
    fun check(): Boolean
    fun print()
    fun load(id: Int)
}