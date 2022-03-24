package com.irfandev.twiliointegration.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.irfandev.twiliointegration.Utils.AppConstants.Companion.CALL_TOKEN
import com.irfandev.twiliointegration.Utils.AppConstants.Companion.CODE
import com.irfandev.twiliointegration.Utils.AppConstants.Companion.COUNTRY_CODE
import com.irfandev.twiliointegration.Utils.AppConstants.Companion.DEVICE_ID
import com.irfandev.twiliointegration.Utils.AppConstants.Companion.EMAIL_VERIFIED
import com.irfandev.twiliointegration.Utils.AppConstants.Companion.GCM_ID
import com.irfandev.twiliointegration.Utils.AppConstants.Companion.MANUFACTURER
import com.irfandev.twiliointegration.Utils.AppConstants.Companion.MSOSDN
import com.irfandev.twiliointegration.Utils.AppConstants.Companion.OPERATING_SYSTEM
import com.irfandev.twiliointegration.Utils.AppConstants.Companion.PHONE_NUMBER
import com.irfandev.twiliointegration.Utils.AppConstants.Companion.USER_EMAIL
import com.irfandev.twiliointegration.Utils.AppConstants.Companion.VERIFICATION_TYPE
import com.irfandev.twiliointegration.Utils.AppConstants.Companion.VERSION
import com.irfandev.twiliointegration.R
import com.irfandev.twiliointegration.VerificationViewModel
import com.irfandev.twiliointegration.databinding.ActivityMainBinding
import kotlin.collections.HashMap

/**
 *Irfan Elahi Dev
 */

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: VerificationViewModel
    private lateinit var mView: View
    private lateinit var codeBtn: Button
    private lateinit var phoneNumber: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
    }

    private fun setupBinding() {
        val activityLoginBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )
        viewModel = ViewModelProviders.of(this).get(VerificationViewModel::class.java)
        activityLoginBinding.varLogin = viewModel
        mView = findViewById(R.id.parent)
        codeBtn = findViewById(R.id.button)
        phoneNumber = findViewById(R.id.phoneNumber)
        viewModel.init()

        viewModel.serverLiveData?.observe(
            this
        ) { serverResponse ->
            if (serverResponse != null) {
                if (serverResponse.errNum.equals("0")) {
                    Toast.makeText(applicationContext, serverResponse.message, Toast.LENGTH_LONG).show()
                    val intent = Intent(this@MainActivity, CodeVerificationActivity::class.java)
                    intent.putExtra(CODE, serverResponse.code)
                    startActivity(intent)
                } else
                    Toast.makeText(applicationContext, serverResponse.message, Toast.LENGTH_LONG).show()

            }
        }
    }


    /**
     *Values required by server for verification
     */

    @SuppressLint("HardwareIds")
    fun fetchParams(): HashMap<String, String> {

        val params = HashMap<String, String>()
        val cCode = "+92"
        val uPhone = phoneNumber.text.toString()
        val android_id = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        params[MSOSDN] = "+$cCode$uPhone"
        params[DEVICE_ID] = android_id
        params[GCM_ID] = "121"
        params[USER_EMAIL] = ""
        params[VERIFICATION_TYPE] = "phone"
        params[EMAIL_VERIFIED] = "1"
        params[MANUFACTURER] = Build.MANUFACTURER
        params[VERSION] = Build.VERSION.RELEASE
        params[OPERATING_SYSTEM] = "android"
        params[PHONE_NUMBER] = uPhone
        params[COUNTRY_CODE] = "+$cCode"
        params[CALL_TOKEN] = "Android"
        return params

    }

    fun sendCode(v: View) {
        viewModel.verifyPhone(fetchParams())
    }
}