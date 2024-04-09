package pro.littleworld.logindemo.service

import android.util.Log
import pro.littleworld.logindemo.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationService {
    @POST("register")
    fun register(@Body user: User): Call<User>
    @POST("login")
    fun login(@Body user: User): Call<User>

}

object Registration {
    val registrationService: RegistrationService = HttpClientService.retrofit.create(RegistrationService::class.java)

    fun register(user: User) {
        registrationService.register(user).enqueue(
            object : Callback<User> {
                override fun onResponse(call: Call<User>, response: retrofit2.Response<User>) {
                    if (response.isSuccessful) {
                        // Handle success
                        Log.d("Register", "Success: ${response.body()}")
                    } else {
                        // Handle request errors depending on status code
                        Log.d("Register", "Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    // Handle failure, e.g., network error or JSON parsing error
                    Log.d("Register", "Failure", t)
                }
            })
    }

    fun login(user: User) {
        registrationService.login(user).enqueue(
            object : Callback<User> {
                override fun onResponse(call: Call<User>, response: retrofit2.Response<User>) {
                    if (response.isSuccessful) {
                        // Handle success
                        Log.d("Login", "Success: ${response.body()}")
                    } else {
                        // Handle request errors depending on status code
                        Log.d("Login", "Error: ${response.code()},  ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    // Handle failure, e.g., network error or JSON parsing error
                    Log.d("Upload", "Failure", t)
                }
            })
    }
}