package pro.littleworld.logindemo.service;

import android.util.Log
import pro.littleworld.logindemo.model.Data
import pro.littleworld.logindemo.model.User;
import retrofit2.Call;
import retrofit2.Callback
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


interface DataServiceInterface {
    @GET("data")
    fun send(): Call<Data>

}

public object DataService {


    val service: DataServiceInterface =
        HttpClientService.retrofit.create(DataServiceInterface::class.java)

    fun send() {
        service.send().enqueue(
            object : Callback<Data> {
                override fun onResponse(call: Call<Data>, response: retrofit2.Response<Data>) {
                    if (response.isSuccessful) {
                        // Handle success
                        Log.d("Data", "Success: ${response.body()}")
                    } else {
                        // Handle request errors depending on status code
                        Log.d("Data", "Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Data>, t: Throwable) {
                    // Handle failure, e.g., network error or JSON parsing error
                    Log.d("Data", "Failure", t)
                }
            })
    }
}
