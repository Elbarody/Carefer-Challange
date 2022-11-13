package com.carefer.basemodule.data.remote.coroutines.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TestDispatcher : BaseCoroutineDispatcher {
    override fun main(): CoroutineDispatcher {
        return Dispatchers.Unconfined
    }

    override fun io(): CoroutineDispatcher {
        return Dispatchers.Unconfined
    }
}