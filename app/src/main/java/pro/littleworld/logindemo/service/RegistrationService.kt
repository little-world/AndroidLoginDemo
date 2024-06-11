package pro.littleworld.logindemo.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import pro.littleworld.logindemo.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RegistrationService {
    @POST("register")
    suspend fun register(@Body user: User): User

    @POST("login")
    suspend fun login(@Body user: User): User

    @GET("hi")
    suspend fun greet(): User

}

object Registration {
    val registrationService: RegistrationService =
        HttpClientService.retrofit.create(RegistrationService::class.java)

    fun register(user: User): Flow<Result<User>> = flow {
        emit(Result.success(registrationService.register(user)))
    }.catch { e ->
        emit(Result.failure(e))
    }

    fun login(user: User): Flow<Result<User>> = flow {
        HttpClientService.updateCredentials(user.name, user.password)
        emit(Result.success(registrationService.login(user)))
    }.catch { e ->
        emit(Result.failure(e))
    }

    fun greet(): Flow<Result<User>> = flow {
        emit(Result.success(registrationService.greet()))
    }.catch { e ->
        emit(Result.failure(e))
    }
}
