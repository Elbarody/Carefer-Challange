package com.carefer.basemodule.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carefer.basemodule.data.models.base.MessageResponse
import com.google.gson.Gson
import com.carefer.basemodule.utility.SingleLiveEvent
import retrofit2.HttpException

abstract class BaseViewModel : ViewModel() {
    val message = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val error = SingleLiveEvent<Any>()

    fun getErrorMsg(it: Any?): String {
        if (it is HttpException) {
            return try {
                val messageResponse =
                    getErrorObject(it)
                messageResponse.message
            } catch (e: Exception) {
                return ""
            }
        }
        return ""
    }
    private fun getErrorObject(it: HttpException): MessageResponse {
        return Gson().fromJson<MessageResponse>(
            it.response()?.errorBody()?.charStream(),
            MessageResponse::class.java
        )
    }
}