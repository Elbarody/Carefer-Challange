package com.carefer.basemodule.utility


class RequestHelper private constructor() {

    companion object {
        private var INSTANCE: RequestHelper? = null

        // when user blocked
        val BLOCK = 456

        // data not send correctly
        val BAD_REQUEST = 400


        // data not send correctly
        val UNAUTHORIZE = 401


        // data not send correctly
        val FORBIDDEN = 403

        val NOT_FOUND = 404

        val instance: RequestHelper
            get() {
                if (null == INSTANCE) {
                    INSTANCE = RequestHelper()
                }
                return INSTANCE as RequestHelper
            }
    }
}
