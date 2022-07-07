package com.akash.gamifyactivity.model

import com.google.gson.annotations.SerializedName


data class LogoItem(
    @field:SerializedName("imgUrl")
    val imgUrl: String? = null,
    @field:SerializedName("name")
    val name: String? = null
)
