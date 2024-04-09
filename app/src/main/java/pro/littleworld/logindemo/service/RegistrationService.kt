package pro.littleworld.logindemo.service

import android.util.Log
import okhttp3.Response
import pro.littleworld.logindemo.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationService {
    @POST("user")
    fun register(@Body user: User): Call<User>
}

object Registration {

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val registrationService: RegistrationService = retrofit.create(RegistrationService::class.java)

    val user = User(name = "John Doe", password = "secret")

    fun register() {
        registrationService.register(user).enqueue(
            object : Callback<User> {
                override fun onResponse(call: Call<User>, response: retrofit2.Response<User>) {
                    if (response.isSuccessful) {
                        // Handle success
                        Log.d("Upload", "Success: ${response.body()}")
                    } else {
                        // Handle request errors depending on status code
                        Log.d("Upload", "Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    // Handle failure, e.g., network error or JSON parsing error
                    Log.d("Upload", "Failure", t)
                }
            })
    }
}