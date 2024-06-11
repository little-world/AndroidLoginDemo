package pro.littleworld.logindemo.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pro.littleworld.logindemo.model.User
import pro.littleworld.logindemo.service.HttpClientService
import pro.littleworld.logindemo.service.Registration

class UserViewModel : ViewModel() {
    var user by  mutableStateOf( User("", ""))

    var loggedInUser = User("", "")

    fun updateUsername(username: String) {
        user = user.copy(name = username)
    }

    fun updatePassword(password: String) {
        user  = user.copy(password = password)
    }

    fun register() {

        viewModelScope.launch {
            Registration.register(user).collect { result ->
                result.onSuccess {
                    // Handle success
                    Log.d("Register", "Success: $it")
                    loggedInUser = user

                }.onFailure {
                    // Handle failure
                    Log.d("Register", "Failure", it)
                }
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            Registration.login(user).collect { result ->
                result.onSuccess {
                    // Handle success
                    Log.d("Login", "Success: $it")

                    loggedInUser = user
                }.onFailure {
                    // Handle failure
                    Log.d("Login", "Failure", it)
                }
            }
        }
    }

    fun greet() {
        viewModelScope.launch {
            Registration.greet().collect { result ->
                result.onSuccess {
                    // Handle success
                    Log.d("Greet", "Success: $it")
                }.onFailure {
                    // Handle failure
                    Log.d("Greet", "Failure", it)
                }
            }
        }
    }
}