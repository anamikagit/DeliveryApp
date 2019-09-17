package co.anamika.deliveryapp.api

import co.anamika.deliveryapp.model.Delivery
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

fun searchDeliveries(
    service: DeliveryService,
    offset: Int,
    limit: Int,
    onSuccess: (deliveries: List<Delivery>) -> Unit,
    onError: (error: String) -> Unit
) {

    service.searchDeliveries(offset, limit).enqueue(
        object : Callback<List<DeliverySearchResponse>> {
            override fun onFailure(call: Call<List<DeliverySearchResponse>>?, t: Throwable) {
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(
                call: Call<List<DeliverySearchResponse>>?,
                response: Response<List<DeliverySearchResponse>>
            ) {
                if (response.isSuccessful) {
                    val repos = response.body() ?: emptyList()
                    val delivery: MutableList<Delivery> = repos.map {
                        Delivery(
                            it.id, it.description,
                            it.imageUrl, it.location.lat, it.location.lng, it.location.address
                        )
                    }.toMutableList()
                    onSuccess(delivery)
                } else {
                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
        }
    )
}

interface DeliveryService {
    @GET("deliveries")
    fun searchDeliveries(
        @Query("offset") page: Int,
        @Query("limit") itemsPerPage: Int
    ): Call<List<DeliverySearchResponse>>

    companion object {
        private const val BASE_URL = "https://mock-api-mobile.dev.lalamove.com/"

        fun create(): DeliveryService {
            val logger = HttpLoggingInterceptor()
            logger.level = Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DeliveryService::class.java)
        }
    }
}