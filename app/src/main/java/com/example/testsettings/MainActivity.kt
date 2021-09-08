package com.example.testsettings

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.example.testsettings.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binder: ActivityMainBinding
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                binder.txtResult.text = "After turned back activity"
                this.onResume()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binder.txtActivityState.text =
            "current lifecycle = ${lifecycle.currentState.name}, activity id = ${this.javaClass.simpleName}(${this.hashCode()})"
        binder.btnNavigateToSystemSettings.setOnClickListener { navigateToSystemSettingsForStartActivityGoogleAds() }
        binder.btnNavigateToSystemSettingsWithReturnValue.setOnClickListener { navigateToSystemSettingsForGoogleAdsForStartActivityWithResultGoogleAds() }
    }

    private fun navigateToSystemSettingsForStartActivityGoogleAds() {
        startActivity(Intent("com.google.android.gms.settings.ADS_PRIVACY"))
    }

    private fun navigateToSystemSettingsForGoogleAdsForStartActivityWithResultGoogleAds() {
        resultLauncher.launch(Intent("com.google.android.gms.settings.ADS_PRIVACY"))
    }
}