package com.example.mobilehealthapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mobilehealthapp.databinding.ActivityPatientProfileBinding

class PatientProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPatientProfileBinding
    private val viewModel: PatientProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        viewModel.patientName.observe(this, Observer { name ->
            binding.editTextPatientName.setText(name)
        })

        viewModel.patientAge.observe(this, Observer { age ->
            binding.editTextAge.setText(age)
        })

        viewModel.saveSuccess.observe(this, Observer { success ->
            if (success) {
                finish() // Return to previous screen after successful save
            }
        })
    }

    private fun setupListeners() {
        binding.buttonSaveProfile.setOnClickListener {
            viewModel.updatePatientName(binding.editTextPatientName.text.toString())
            viewModel.updatePatientAge(binding.editTextAge.text.toString())
            viewModel.savePatientProfile()
        }
    }
}