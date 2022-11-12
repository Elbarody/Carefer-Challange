package com.carefer.basemodule.data.remote.coroutines

import kotlinx.coroutines.Job

interface CoroutinesProvider {
    fun <T : Any> request(
        work: suspend (() -> T?),
        callback: ((Result) -> Unit)
    ): Job
}