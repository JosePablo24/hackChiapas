package com.luisenricke.hackchiapas.common

@Suppress("unused")
interface FragmentInteractionListener {
    fun changeFragment(fragment: String)
    fun changeActivity(activity: String, params: ArrayList<String> = arrayListOf())
}