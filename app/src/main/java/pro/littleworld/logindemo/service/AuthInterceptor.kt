package pro.littleworld.logindemo.service

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.Base64


import java.util.concurrent.atomic.AtomicReference



class AuthInterceptor(user: String, password: String) : Interceptor {

    private val credentials: AtomicReference<String> = AtomicReference()

    init {
        setCredentials(user, password)
    }
    fun setCredentials(user: String, password: String) {
        val basicAuthValue = "Basic " + Base64.getEncoder().encodeToString("$user:$password".toByteArray())
        credentials.set(basicAuthValue)
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val credential: String? = credentials.get()
        val authenticatedRequest: Request = if (credential != null) {
            request.newBuilder()
                .header("Authorization", credential)
                .build()
        } else {
            request
        }
        return chain.proceed(authenticatedRequest)
    }
}