package com.example.mobilehealthapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class TelemedicineService : Service() {
    companion object {
        const val ACTION_CALL_STATUS = "CALL_STATUS_UPDATE"
        const val EXTRA_STATUS = "STATUS"
        const val TAG = "TelemedicineService"
    }

    private var isCallActive = false

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            "START_CALL" -> startCall()
            "END_CALL" -> endCall()
        }
        return START_STICKY
    }

    private fun startCall() {
        Log.d(TAG, "Starting telemedicine call")
        isCallActive = true
        sendCallStatusUpdate("Call connected")
        // TODO: Implement actual video call setup with third-party SDK
    }

    private fun endCall() {
        Log.d(TAG, "Ending telemedicine call")
        isCallActive = false
        sendCallStatusUpdate("Call ended")
        // TODO: Implement actual call termination
    }

    private fun sendCallStatusUpdate(status: String) {
        val intent = Intent(ACTION_CALL_STATUS).apply {
            putExtra(EXTRA_STATUS, status)
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }
}