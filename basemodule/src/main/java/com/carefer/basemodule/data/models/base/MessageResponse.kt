package com.carefer.basemodule.data.models.base

import com.google.gson.annotations.SerializedName


open class MessageResponse(
    @SerializedName(value = "msg", alternate = ["message", "error_description"])
    val message: String = ""
)