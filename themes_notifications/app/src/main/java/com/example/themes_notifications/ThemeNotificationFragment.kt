package com.example.themes_notifications

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.themes_notifications.databinding.FragmentThemeNotificationBinding
import com.example.themes_notifications.model.NotificationData
import com.example.themes_notifications.model.Theme
import com.example.themes_notifications.themeRecycler.ThemeAdapter
import com.example.themes_notifications.utils.NotificationHandler
import com.example.themes_notifications.utils.PermissionsHandler

class ThemeNotificationFragment : Fragment(R.layout.fragment_theme_notification) {

    private val binding : FragmentThemeNotificationBinding by viewBinding()
    private var getImageLauncher: ActivityResultLauncher<Intent>? = null
    private var notificationHandler: NotificationHandler? = null
    private var permissionsHandler: PermissionsHandler? = null
    private var isImageUpload = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                val uri: Uri? = data?.data

                uri?.let {
                    Glide.with(this)
                        .load(it)
                        .circleCrop()
                        .into(binding.profileImage)
                }
            }
        }

        notificationHandler = NotificationHandler(requireContext())

        permissionsHandler = PermissionsHandler(::openGallery, ::showNotification)
        permissionsHandler!!.initContracts(activity as AppCompatActivity)
    }

    override fun onDestroy() {
        super.onDestroy()
        getImageLauncher = null
        notificationHandler = null
        permissionsHandler = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews(){
        binding.apply {

            profileImageFrame.setOnClickListener{
                if(isImageUpload){
                    Toast.makeText(
                        requireContext(),
                        Constants.IMAGE_UPLOADED,
                        Toast.LENGTH_LONG
                    ).show()

                    return@setOnClickListener
                }

                val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    Manifest.permission.READ_MEDIA_IMAGES
                } else {
                    Manifest.permission.READ_EXTERNAL_STORAGE
                }

                if(ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED ){
                    openGallery()
                    isImageUpload = true
                    closeIcon.visibility = View.VISIBLE
                }
                else{
                    Log.d("TEST", permission)
                    permissionsHandler?.requestStoragePermission(permission)
                }
            }

            closeIcon.setOnClickListener {
                Glide.with(this@ThemeNotificationFragment)
                    .load(R.color.white)
                    .circleCrop()
                    .into(profileImage)
                it.visibility = View.GONE
                isImageUpload = false
            }

            openRecycler.setOnClickListener{
                themesRv.visibility = View.VISIBLE
                it.visibility = View.GONE
                closeRecycler.visibility = View.VISIBLE
            }

            closeRecycler.setOnClickListener {
                themesRv.visibility = View.GONE
                it.visibility = View.GONE
                openRecycler.visibility = View.VISIBLE
            }

            resetColorButton.setOnClickListener{
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            }

            showNotificationButton.setOnClickListener{
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
                    permissionsHandler?.requestNotificationsPermission()
                } else {
                    showNotification()
                }
            }
        }

        initRecycler()
        initSpinner()
    }

    private fun initRecycler(){
        val themes = Constants.themes.map {
            it.copy(color = resources.getColor(it.color))
        }

        binding.apply {
            themesRv.adapter = ThemeAdapter(
                themes
            ) { id ->
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.putExtra(Constants.EXTRA_THEME, id)

                startActivity(intent)
                requireActivity().finish()
            }

            themesRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun initSpinner(){
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            Constants.notificationsChannels.map { channel -> channel.name }
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.importanceSpinner.adapter = adapter
    }

    private fun openGallery(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        getImageLauncher?.launch(intent)
    }

    private fun showNotification(){
        binding.apply {
            val title = inputTitle.editText?.text.toString()
            val message = inputMessage.editText?.text.toString()
            if (title.isEmpty()
                || message.isEmpty()
            ) {
                Toast.makeText(
                    requireContext(),
                    Constants.EMPTY_FIELDS,
                    Toast.LENGTH_LONG
                ).show()
            } else {
                notificationHandler?.showNotification(
                    NotificationData(
                        0,
                        title,
                        message,
                        Constants.notificationsChannels.find { it.name == importanceSpinner.selectedItem.toString()}?.channelId!!
                    )
                )
            }
        }
    }
}