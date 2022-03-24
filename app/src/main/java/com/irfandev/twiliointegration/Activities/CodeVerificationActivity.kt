package com.irfandev.twiliointegration.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.irfandev.twiliointegration.Utils.AppConstants
import com.irfandev.twiliointegration.R
import com.irfandev.twiliointegration.databinding.ActivityCodeVerificationBinding
/**
 *Irfan Elahi Dev
 */
class CodeVerificationActivity : AppCompatActivity() {
    private lateinit var codeInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityCodeVerificationBinding = DataBindingUtil.setContentView<ActivityCodeVerificationBinding>(this,
            R.layout.activity_code_verification
        )
        codeInput = activityCodeVerificationBinding.code
    }

    fun verify(v: View) {
        val code = codeInput.text.toString()
        val serverCode:String = intent.getStringExtra(AppConstants.CODE).toString()
        if(serverCode == code)
            Toast.makeText(applicationContext, getString(R.string.verified), Toast.LENGTH_LONG).show()
        else
            Toast.makeText(applicationContext, getString(R.string.not_verified), Toast.LENGTH_LONG).show()
    }
}