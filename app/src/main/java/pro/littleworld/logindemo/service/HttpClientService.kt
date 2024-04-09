package pro.littleworld.logindemo.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpClientService {


    val authInterceptor = Interceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer YOUR_ACCESS_TOKEN")
            .build()
        chain.proceed(newRequest)
    }

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        // You can add other interceptors or configurations here
        .build()


    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()



}