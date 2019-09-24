package com.architectcoders.animalcoders.data.remote.animal

import com.architectcoders.animalcoders.data.BuildConfig
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Service {

    private const val baseUrl = "https://test1-api.rescuegroups.org/v5/public/"
    private const val API_KEY = "QVSiWHOa"

    val animal: AnimalService = getRetrofit().create(AnimalService::class.java)

    private val headers: Headers
        get() {
            val builder = Headers.Builder()
            builder.add("Accept", "application/json")
            builder.add("Authorization", API_KEY)

            return builder.build()
        }

    private fun getRetrofit(): Retrofit {

        val builder = OkHttpClient().newBuilder()

        val client = builder
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }).addInterceptor {chain ->
                val request = chain.request().newBuilder().headers(headers).build()

                chain.proceed(request)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}

