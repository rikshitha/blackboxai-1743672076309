package com.example.mobilehealthapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PatientProfileViewModel : ViewModel() {
    private val _patientName = MutableLiveData<String>()
    val patientName: LiveData<String> = _patientName

    private val _patientAge = MutableLiveData<String>()
    val patientAge: LiveData<String> = _patientAge

    private val _saveSuccess = MutableLiveData<Boolean>()
    val saveSuccess: LiveData<Boolean> = _saveSuccess

    fun updatePatientName(name: String) {
        _patientName.value = name
    }

    fun updatePatientAge(age: String) {
        _patientAge.value = age
    }

    fun savePatientProfile() {
        // TODO: Implement actual save logic to database
        _saveSuccess.value = true
    }
}