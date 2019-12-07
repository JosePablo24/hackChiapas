package com.luisenricke.hackchiapas.ui.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import com.luisenricke.hackchiapas.Constants.USER_ID
import com.luisenricke.hackchiapas.Constants.MAIN_ACTIVITY
import com.luisenricke.hackchiapas.Constants.SIGNUP_FRAGMENT
import com.luisenricke.hackchiapas.R
import com.luisenricke.hackchiapas.common.BaseFragment
import com.luisenricke.hackchiapas.data.dao.UsuarioDAO
import com.luisenricke.hackchiapas.data.entity.Usuario
import com.luisenricke.hackchiapas.network.usuario.UsuarioImp
import com.luisenricke.hackchiapas.ui.view.FormView
import com.luisenricke.hackchiapas.utils.PreferenceHelper
import timber.log.Timber

@SuppressLint("ClickableViewAccessibility")
class LogInFragment : BaseFragment() {

    //Views in fragment
    private lateinit var btnDisparador: MaterialButton
    private lateinit var inputCorreo: TextInputLayout
    private lateinit var txtCorreo: TextInputEditText
    private lateinit var inputContrasenia: TextInputLayout
    private lateinit var txtContrasenia: TextInputEditText
    private lateinit var lblSingUp: MaterialTextView

    private lateinit var usuarioDAO: UsuarioDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout = R.layout.fragment_log_in
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layout!!, container, false)
        usuarioDAO = database.usuario()

        btnDisparador = view.findViewById(R.id.btn_disparador)
        inputCorreo = view.findViewById(R.id.input_correo)
        txtCorreo = view.findViewById(R.id.txt_correo)
        inputContrasenia = view.findViewById(R.id.input_contrasenia)
        txtContrasenia = view.findViewById(R.id.txt_contrasenia)
        lblSingUp = view.findViewById(R.id.lbl_signup)

        lblSingUp.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> listener?.changeFragment(SIGNUP_FRAGMENT)
            }
            return@setOnTouchListener true
        }

        btnDisparador.setOnClickListener {
            if (!txtCorreo.text.isNullOrEmpty() && !txtContrasenia.text.isNullOrEmpty()) {
                //callEndPointUserLogin(txtCorreo.text.toString(), txtContrasenia.text.toString())
                //FIXME: Call ENDPOINT
                val respuesta = UsuarioImp.login(txtCorreo.text.toString(), txtContrasenia.text.toString())
                Timber.i("usuario: ${respuesta.id} :: ${respuesta.correo}")
                //val checkUser = usuarioDAO.get(txtCorreo.text.toString())
                //val usuario: Usuario? = null
                if (respuesta.id == -1) {
                    Toast.makeText(
                        activity.baseContext,
                        "No encuentra logeado",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                Toast.makeText(activity.baseContext, "Ha iniciado sesión", Toast.LENGTH_SHORT)
                    .show()
                //PreferenceHelper.set(activity.baseContext, USER_ID, true)
                //listener?.changeActivity(MAIN_ACTIVITY)
                return@setOnClickListener
            } else {
                Toast.makeText(
                    activity.baseContext,
                    "Es necesario rellenar el formulario",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = LogInFragment()
    }

}