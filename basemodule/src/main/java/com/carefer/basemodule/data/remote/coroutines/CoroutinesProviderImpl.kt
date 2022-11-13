package com.carefer.basemodule.data.remote.coroutines

import com.carefer.basemodule.data.remote.coroutines.dispatchers.BaseCoroutineDispatcher
import com.carefer.basemodule.data.remote.coroutines.dispatchers.RuntimeDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoroutinesProviderImpl @Inject constructor(private val dispatcher: BaseCoroutineDispatcher) :
    CoroutinesProvider {
    override fun <T : Any> request(
        work: suspend (() -> T?),
        callback: ((Result) -> Unit)
    ) =
        CoroutineScope(dispatcher.main()).launch {
            try {
                val data = CoroutineScope(dispatcher.io()).async rt@{ return@rt work() }.await()
                callback(Success(data))
            } catch (e: Exception) {
                callback(Failure(e))
            }
        }
}