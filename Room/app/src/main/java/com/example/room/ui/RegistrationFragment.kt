package com.example.room.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.room.App
import com.example.room.R
import com.example.room.data.entities.User
import com.example.room.data.repository.UserRepository
import com.example.room.databinding.FragmentRegistrationBinding
import com.example.room.utils.SessionManager
import kotlinx.coroutines.launch

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val binding: FragmentRegistrationBinding by viewBinding()
    private var userRepository: UserRepository? = null
    private var sessionManager: SessionManager? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as? App)?.let {
            userRepository = UserRepository(userDao = it.getDatabase().userDao)
            sessionManager = it.getSessionManager()
        }

        initViews()
    }

    private fun initViews(){
        binding.apply {
            btnSignUp.setOnClickListener{
                val username = etNewUsername.text
                val password = etNewPassword.text
                username?.let { name ->
                    password?.let {pass ->
                        if(name.isNotEmpty() && pass.isNotEmpty()){
                            lifecycleScope.launch {
                                userRepository?.let {
                                    if(!it.checkUserExists(name.toString())){
                                        val user = User(
                                            username = name.toString(),
                                            password = pass.toString()
                                        )

                                        it.insertUser(user)

                                        val id = it.login(name.toString(), password.toString())
                                        sessionManager?.createLoginSession(id)

                                        findNavController().navigate(R.id.action_registrationFragment_to_mainFragment)

                                    } else {
                                        Toast.makeText(requireContext(),
                                            getString(R.string.user_is_exist), Toast.LENGTH_LONG).show()
                                    }
                                }
                            }
                        } else {
                            Toast.makeText(requireContext(),
                                getString(R.string.fill_all_fields), Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        userRepository = null
        sessionManager = null
    }

}