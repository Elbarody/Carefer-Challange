package com.carefer.basemodule.view

import android.content.Context
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding


abstract class AbstractBaseActivity<V : ViewDataBinding> : AppCompatActivity() {
    lateinit var activity: AppCompatActivity
    lateinit var mViewDataBinding: V
    lateinit var mViewBinding: V

    abstract fun initBinding(): V

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = this
        mViewDataBinding = initBinding()
        setContentView(mViewDataBinding.root)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        onBackPressed()
        return true
    }


    fun showMessage(message: String) {
        showLongToaster(this, message)
    }



    fun hideKeyboard(editText: EditText) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }


    fun showLongToaster(context: Context?, message: String) {
        if (!TextUtils.isEmpty(message))
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun onFragmentAttached() {

    }

    fun onFragmentDetached(tag: String) {

    }

    fun deviceId() = Settings.Secure.getString(
        contentResolver,
        Settings.Secure.ANDROID_ID
    )

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
    }
}
