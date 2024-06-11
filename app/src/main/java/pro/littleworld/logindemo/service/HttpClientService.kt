package pro.littleworld.logindemo.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpClientService {

    private var client: OkHttpClient? = null
    private var authInterceptor: AuthInterceptor? = null

    @Synchronized
    fun getClient(username: String, password: String): OkHttpClient {
        if (authInterceptor == null) {
            authInterceptor = AuthInterceptor(username, password)
        } else {
            authInterceptor?.setCredentials(username, password)
        }

        if (client == null) {
            client = OkHttpClient.Builder()
                .addInterceptor(authInterceptor!!)
                .build()
        }
        return client!!
    }

    @Synchronized
    fun updateCredentials(username: String, password: String) {
        authInterceptor?.setCredentials(username, password)
    }


//    val authInterceptor = Interceptor { chain ->
//        val newRequest = chain.request().newBuilder()
//            .addHeader("Authorization", "Bearer YOUR_ACCESS_TOKEN")
//            .build()
//        chain.proceed(newRequest)
//    }


    val okHttpClient = HttpClientService.getClient("", "")


    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080")

        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


}