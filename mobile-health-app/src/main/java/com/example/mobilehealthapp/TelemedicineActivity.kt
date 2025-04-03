package com.example.mobilehealthapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.SurfaceHolder
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.mobilehealthapp.databinding.ActivityTelemedicineBinding

class TelemedicineActivity : AppCompatActivity(), SurfaceHolder.Callback {
    private lateinit var binding: ActivityTelemedicineBinding
    private lateinit var telemedicineServiceIntent: Intent
    private val callStatusReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when(intent?.action) {
                TelemedicineService.ACTION_CALL_STATUS -> {
                    val status = intent.getStringExtra(TelemedicineService.EXTRA_STATUS)
                    binding.textCallStatus.text = status
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelemedicineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        telemedicineServiceIntent = Intent(this, TelemedicineService::class.java)
        binding.videoView.holder.addCallback(this)

        setupListeners()
        registerCallStatusReceiver()
    }

    private fun setupListeners() {
        binding.buttonStartCall.setOnClickListener {
            telemedicineServiceIntent.action = "START_CALL"
            startService(telemedicineServiceIntent)
        }

        binding.buttonEndCall.setOnClickListener {
            telemedicineServiceIntent.action = "END_CALL"
            startService(telemedicineServiceIntent)
            finish()
        }
    }

    private fun registerCallStatusReceiver() {
        LocalBroadcastManager.getInstance(this).registerReceiver(
            callStatusReceiver,
            IntentFilter(TelemedicineService.ACTION_CALL_STATUS)
        )
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        // TODO: Initialize video stream with third-party SDK
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        // Handle surface changes
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        // Clean up resources
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(callStatusReceiver)
    }
}