package com.luisenricke.hackchiapas.common.extension

@Suppress("unused")
fun ArrayList<String>.toStringMultiLine(): String {
    var aux = ""
    for (element in this) {
        aux += element + "\n"
    }
    return aux
}