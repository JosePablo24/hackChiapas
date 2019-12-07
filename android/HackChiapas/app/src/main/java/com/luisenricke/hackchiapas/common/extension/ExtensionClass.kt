package com.luisenricke.hackchiapas.common.extension

import kotlin.reflect.KClass

fun <T : Any> T.getClass(): KClass<T> = javaClass.kotlin