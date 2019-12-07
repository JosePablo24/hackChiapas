package com.luisenricke.hackchiapas.common.extension

fun Double.format(digits: Int) = "%.${digits}f".format(this)