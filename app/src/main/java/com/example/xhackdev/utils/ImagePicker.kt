package com.example.xhackdev.utils

import android.net.Uri
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.LifecycleOwner

class ImagePicker(
    private val activityResultRegistry: ActivityResultRegistry,
    private val lifecycleOwner: LifecycleOwner,
    private val callback: (imageUri: Uri?) -> Unit
) {

    private val getContent = activityResultRegistry.register(REGISTRY_KEY, lifecycleOwner, ActivityResultContracts.GetContent(), callback)

    fun pickImage(){
        getContent.launch("image/*")
    }

    companion object{
        private const val REGISTRY_KEY = "ImagePicker"
    }
}