package com.carefer.carefer_challange

import android.os.Bundle
import com.carefer.basemodule.view.AbstractBaseActivity
import com.carefer.carefer_challange.databinding.ActivityMainBinding

class MainActivity : AbstractBaseActivity<ActivityMainBinding>() {

    override fun initBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}