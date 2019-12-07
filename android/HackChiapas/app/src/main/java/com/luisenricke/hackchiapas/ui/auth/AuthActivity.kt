package com.luisenricke.hackchiapas.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.luisenricke.hackchiapas.Constants
import com.luisenricke.hackchiapas.Constants.LOGIN_FRAGMENT
import com.luisenricke.hackchiapas.Constants.MAIN_ACTIVITY
import com.luisenricke.hackchiapas.Constants.SIGNUP_FRAGMENT
import com.luisenricke.hackchiapas.Constants.USER_ID
import com.luisenricke.hackchiapas.R
import com.luisenricke.hackchiapas.common.BaseActivity
import com.luisenricke.hackchiapas.common.BaseFragment
import com.luisenricke.hackchiapas.common.FragmentInteractionListener
import com.luisenricke.hackchiapas.ui.main.MainActivity
import com.luisenricke.hackchiapas.utils.PreferenceHelper
import timber.log.Timber

class AuthActivity : BaseActivity(), FragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        if (PreferenceHelper.get(this, USER_ID, Int::class) == null)
            changeFragment(LOGIN_FRAGMENT)
        else
            changeActivity(MAIN_ACTIVITY)
    }

    override fun changeFragment(fragment: String) {
        when (fragment) {
            LOGIN_FRAGMENT -> BaseFragment.load(
                this.supportFragmentManager, LogInFragment.newInstance(), LOGIN_FRAGMENT
            )
            SIGNUP_FRAGMENT -> BaseFragment.load(
                this.supportFragmentManager, SignUpFragment.newInstance(), SIGNUP_FRAGMENT
            )
        }
    }

    override fun changeActivity(activity: String, params: ArrayList<String>) {
        val intent: Intent = when (activity) {
            MAIN_ACTIVITY -> {
                Intent(this, MainActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
            else -> {
                Timber.e("Activity not found")
                return
            }
        }
        startActivity(intent)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1)
            finish()
        super.onBackPressed()
    }
}