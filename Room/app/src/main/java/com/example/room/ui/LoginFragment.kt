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
import com.example.room.data.repository.UserRepository
import com.example.room.databinding.FragmentLoginBinding
import com.example.room.utils.SessionManager
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding()
    private var userRepository: UserRepository? = null
    private var sessionManager: SessionManager? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as? App)?.let {
            userRepository = UserRepository(userDao = it.getDatabase().userDao)
            sessionManager = it.getSessionManager()
        }

        if(sessionManager?.isLoggedIn() == true){
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }

        initViews()
    }

    private fun initViews(){
        binding.apply { 
            btnRegister.setOnClickListener{
                findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
            }
            
            btnLogin.setOnClickListener{
                val username = etUsername.text
                val password = etPassword.text

                username?.let {name ->
                    password?.let { pass ->
                        if(name.isNotEmpty() && pass.isNotEmpty()){
                            lifecycleScope.launch {
                                userRepository?.let {
                                    val id = it.login(name.toString(), pass.toString())
                                    if(id != -1){
                                        sessionManager?.createLoginSession(id)
                                        findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                                    } else {
                                        Toast.makeText(requireContext(),
                                           getString(R.string.user_is_not_exist), Toast.LENGTH_LONG).show()
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