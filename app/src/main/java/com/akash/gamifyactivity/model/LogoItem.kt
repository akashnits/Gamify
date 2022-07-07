package com.akash.gamifyactivity.model

import com.google.gson.annotations.SerializedName


data class LogoItem(
    @field:SerializedName("imageUrl")
    val imageUrl: String? = null,
    @field:SerializedName("name")
    val name: String? = null
)
