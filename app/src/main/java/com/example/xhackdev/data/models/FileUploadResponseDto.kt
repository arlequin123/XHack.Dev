package com.example.xhackdev.data.models

import com.google.gson.annotations.SerializedName

data class FileUploadResponseDto(
    @SerializedName("image_url")
    val imageUrl: String
    )
