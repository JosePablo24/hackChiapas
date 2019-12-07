package com.luisenricke.hackchiapas.common

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.luisenricke.hackchiapas.R
import com.luisenricke.hackchiapas.data.AppDatabase
import kotlinx.android.synthetic.main.activity_auth.*
import timber.log.Timber

@Suppress("unused")
abstract class BaseFragment : Fragment() {

    var listener: FragmentInteractionListener? = null
    var layout: Int? = null
    lateinit var activity: AppCompatActivity
    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = getActivity() as AppCompatActivity
        database = AppDatabase.getInstance(activity.baseContext)
    }

    fun back(tag: String) {
        fragmentManager?.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun delete(tag: String): Boolean {
        val fragment: Fragment = fragmentManager?.findFragmentByTag(tag)
            ?: return false
        val transaction = fragmentManager?.beginTransaction()
        transaction?.remove(fragment)
        transaction?.commit()
        return true
    }

    companion object {
        fun load(fragmentManager: FragmentManager, fragment: Fragment, tag: String) {
            val fragments = fragmentManager.fragments
            val transaction = fragmentManager
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)

            Timber.d("Stack: ${fragmentManager.backStackEntryCount}")
            Timber.d("Fragments:  ${fragments.size}")

            fragments.filter { !it.isHidden }
                .forEach {
                    transaction.hide(it)
                    Timber.d("Hiding: ${it.tag}")
                }

            if (fragmentManager.findFragmentByTag(tag) == null) {
                transaction.add(R.id.frame_container, fragment, tag)
                transaction.addToBackStack(tag)
                Timber.d("Adding: ${fragment.tag}")
            } else {
                val actual = fragmentManager.findFragmentByTag(tag)!!
                if (actual.isHidden) {
                    transaction.show(actual)
                    Timber.d("Showing: ${actual.tag}")
                }
            }
            transaction.commit()
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentInteractionListener)
            listener = context
        else
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}