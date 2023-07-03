package com.example.dependency_injection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LogInRepository) : ViewModel() {
    private val _loginReApiResponsesult = MutableLiveData<ApiResponse<List<LoginResponse>>>()
    val loginReApiResponsesult: LiveData<ApiResponse<List<LoginResponse>>> = _loginReApiResponsesult

    suspend fun login() {
        viewModelScope.launch {
            _loginReApiResponsesult.value = ApiResponse.Loading
            val result = repository.hitlogInApi()
            _loginReApiResponsesult.value = result
        }
    }
}

