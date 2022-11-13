package com.carefer.basemodule.view


import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.carefer.basemodule.R
import com.carefer.basemodule.data.models.base.MessageResponse
import com.carefer.basemodule.utility.RequestHelper
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
abstract class BaseFragment<V : ViewDataBinding> : Fragment() {


    private var mRootView: View? = null
    protected lateinit var mViewDataBinding: V

    protected abstract val mViewModel: BaseViewModel


    abstract fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): V

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = initBinding(inflater, container, false)
        return mViewDataBinding.root
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.message.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                mViewModel.message.value = ""
            }
        })

        mViewModel.error.observe(viewLifecycleOwner, Observer {
            try {
                if (it is HttpException) {
                    onApiError(it.code(), getErrorMsg(it))
                }

                if (it is IOException) {
                    showToast( getString(R.string.internet_connection_error))
                }
            } catch (e: Exception) {

            }
        }
        )

    }

    open fun getErrorMsg(it: Any?): String {
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

    fun getErrorObject(it: HttpException): MessageResponse {
        return Gson().fromJson<MessageResponse>(
            it.response()?.errorBody()?.charStream(),
            MessageResponse::class.java
        )
    }


    open fun onApiError(code: Int, responseMessage: String) {
        showToast(responseMessage)
        when (code) {
            RequestHelper.BAD_REQUEST -> {
                //  handleBadRequest()
            }
            RequestHelper.UNAUTHORIZE -> {

            }

            RequestHelper.NOT_FOUND -> {

            }

            RequestHelper.FORBIDDEN -> try {

            } catch (e: IOException) {
                e.printStackTrace()
            }

            else -> {
                showToast(responseMessage)
            }
        }
    }


    fun showToast(msg: String) {
        if (msg != null)
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

}